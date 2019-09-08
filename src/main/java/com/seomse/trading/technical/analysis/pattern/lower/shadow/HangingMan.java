package com.seomse.trading.technical.analysis.pattern.lower.shadow;

import com.seomse.trading.PriceChangeType;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.technical.analysis.candle.candles.CandleChangeObserver;
import com.seomse.trading.technical.analysis.candle.candles.TradeCandles;
import com.seomse.trading.technical.analysis.pattern.CandlePattern;
import com.seomse.trading.technical.analysis.pattern.CandlePatternPoint;
import com.seomse.trading.technical.analysis.trend.line.TrendLine;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  파 일 명 : HangingMan.java
 *  설    명 : 교수형 캔들
 *            상승세의 고점에서 출현해야만 하고 꼬리 길이에 비해 ㅁ몸통 크기가 적가나 반대로 꼬리 길이에 비해 몸통
 *            망치형과 동일한 형태이지만 상승세의 고점에서 출현해야만하고 꼬리 길이에 비해 몸통 크기가 작거나 반대로 꼬리 길이에 비해 몸통크기가 길때유효하다
 *            -- 몸통길이가 꼬리에비해 2배이상 작거나 혹은 2배이상 커야 하나봄.. 이건 테스트해보면서 체크해야할듯.
 *            음봉이든 양봉이든 고점에서는 의밍있는 하락 반전 또는 조정 신호가 될 수 있다.
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.09.03
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class HangingMan implements CandlePattern {

    private TradeCandles tradeCandles;

    @Override
    public PriceChangeType getPriceChangeType() {
        return PriceChangeType.FALL;
    }


    /**
     * 캔들 배열 설정
     * 캔들배열이 설정될
     * @param tradeCandles 캔들 배열
     */
    @Override
    public void setCandles(TradeCandles tradeCandles){
        this.tradeCandles = tradeCandles;
    }

    private CandlePatternPoint lastPoint;

    private TradeCandle lastCheckCandle = null;


    @Override
    public void initRealTime() {
        TradeCandle [] candles = tradeCandles.getCandles();
        if(candles.length == 0){
            return ;
        }
        lastCheckCandle = candles[candles.length-1];

        for(int i=candles.length-1 ; i > -1 ; i--){
            CandlePatternPoint point = HangingMan.getPoint(candles, i, tradeCandles.getShortGapPercent());
            if(point != null){
                lastPoint = point;
                return ;
            }
        }

        setObserver();
    }

    private CandleChangeObserver candleChangeObserver = null;
    //옵져버 설정
    void setObserver(){
        candleChangeObserver = (lastEndCandle, newCandle) -> changeLastCandle(lastEndCandle);
        tradeCandles.addChangeObserver(candleChangeObserver);
    }

    //설정된 옵져버 제거
    void removeObserver(){
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
                CandlePatternPoint point = HangingMan.getPoint(candles, i, tradeCandles.getShortGapPercent());
                if(point != null){
                    //캔들 발생했을때 알림 받게 해야함
                    //매수 시점을 알려줘야함
                    lastPoint = point;
                }
                break;
            }
        }
    }

    @Override
    public CandlePatternPoint getLastPoint() {
        return lastPoint;
    }

    @Override
    public CandlePatternPoint[] getPoints() {
        TradeCandle [] candles = tradeCandles.getCandles();
        List<CandlePatternPoint> pointList = null;

        for (int i = 5; i <candles.length ; i++) {
            CandlePatternPoint point = HangingMan.getPoint(candles, i, tradeCandles.getShortGapPercent());

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
     * 캔들의 배열이 바뀔 수 있으므로 array 로 직접 받음
     * @param candles 캔들 배열
     * @param index 기준위치
     * @param shortGapPercent 짧은 캔들 기준 확률
     * @return 패턴결과
     */
    public static CandlePatternPoint getPoint(TradeCandle [] candles, int index, double shortGapPercent){

        TradeCandle tradeCandle = candles[index];
        if(!LowerShadowPattern.isValid(tradeCandle)){
            return null;
        }
        TrendLine trendLine = new TrendLine(TrendLine.Type.UP);
        return LowerShadowPattern.makePoint(trendLine,candles,index,shortGapPercent);
    }



}
