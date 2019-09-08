package com.seomse.trading.technical.analysis.pattern;

import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.technical.analysis.candle.candles.CandleChangeObserver;
import com.seomse.trading.technical.analysis.candle.candles.TradeCandles;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  파 일 명 : CandlePatternDefault.java
 *  설    명 : 캔들 패턴 기본형 공통 부분 정의
 *  작 성 자 : macle
 *  작 성 일 : 2019.09.08
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public abstract class CandlePatternDefault implements CandlePattern{


    protected TradeCandles tradeCandles;

    protected CandlePatternPoint lastPoint;

    protected TradeCandle lastCheckCandle = null;

    /**
     * 캔들 배열 설정
     * 캔들배열이 설정될
     * @param tradeCandles 캔들 배열
     */
    @Override
    public void setCandles(TradeCandles tradeCandles){
        this.tradeCandles = tradeCandles;
    }

    @Override
    public void initRealTime(){
        TradeCandle [] candles = tradeCandles.getCandles();

        if(candles.length == 0){
            return ;
        }
        lastCheckCandle = candles[candles.length-1];

        for(int i=candles.length-1 ; i > -1 ; i--){
            CandlePatternPoint point = getPoint(candles, i, tradeCandles.getShortGapPercent());
            if(point != null){
                lastPoint = point;
                return ;
            }
        }

        setObserver();
    }


    protected CandleChangeObserver candleChangeObserver = null;
    //옵져버 설정
    public void setObserver(){
        candleChangeObserver = (lastEndCandle, newCandle) -> changeLastCandle(lastEndCandle);
        tradeCandles.addChangeObserver(candleChangeObserver);
    }

    //설정된 옵져버 제거
    public void removeObserver(){
        if(candleChangeObserver == null){
            return ;
        }

        tradeCandles.removeObserver(candleChangeObserver);
    }


    /**
     * 마지막 캔들 지점 변경
     * 패턴발생시 패턴정보 객체 리턴
     * 패턴발생하지 않으면 null 리턴
     * @param lastEndCandle lastEndCandle
     */
    public void changeLastCandle(TradeCandle lastEndCandle) {
        if (lastEndCandle == null) {
            return;
        }

        if (lastEndCandle == lastCheckCandle) {
            return;
        }

        lastCheckCandle = lastEndCandle;

        TradeCandle[] candles = tradeCandles.getCandles();

        for (int i = candles.length - 1; i > -1; i--) {
            TradeCandle tradeCandle = candles[i];
            if(tradeCandle == lastEndCandle){
                CandlePatternPoint point = getPoint(candles, i, tradeCandles.getShortGapPercent());
                if(point != null){
                    //캔들 발생했을때 알림 받게 해야함
                    //매수 시점을 알려줘야함
                    lastPoint = point;
                }
                break;
            }
        }
    }

    /**
     * 캔들이 발생된 모든 지점 얻기
     * 시뮬레이터에 사용
     * @return 망치형 캔슬이 발생된 모든 지점( 배열)
     */
    @Override
    public CandlePatternPoint [] getPoints(){
        TradeCandle [] candles = tradeCandles.getCandles();
        List<CandlePatternPoint> pointList = null;

        for (int i = 5; i <candles.length ; i++) {
            CandlePatternPoint point = getPoint(candles, i, tradeCandles.getShortGapPercent());

            if(point == null)
                continue;

            if(pointList == null){
                pointList = new ArrayList<>();
            }
            pointList.add(point);
        }

        if(pointList==null){
            return CandlePatternPoint.EMPTY_POINT;
        }

        //noinspection ToArrayCallWithZeroLengthArrayArgument
        CandlePatternPoint [] result = pointList.toArray(new CandlePatternPoint[pointList.size()]);
        pointList.clear();
        return result;
    }

    /**
     * 최근 발생 지점
     * 실시간 분석에 사용
     * @return 최근 발생 지점 얻기
     */
    @Override
    public CandlePatternPoint getLastPoint(){
        return lastPoint;
    }

    /**
     * 캔들의 배열이 바뀔 수 있으므로 array 로 직접 받음
     * 패턴결과 패턴이 유효하지 않을경우 null 을 리턴
     * @param candles 캔들 배열
     * @param index 기준위치
     * @param shortGapPercent 짧은 캔들 기준 확률
     * @return 패턴결과
     */
    public abstract CandlePatternPoint getPoint(TradeCandle [] candles, int index, double shortGapPercent);
}
