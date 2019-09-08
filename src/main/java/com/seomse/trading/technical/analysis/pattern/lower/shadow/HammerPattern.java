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
 *  파 일 명 : HammerPattern.java
 *  설    명 : 망치형 캔들
 *            아래 그림자 캔들의 한종류로 하락추세에서 아래 그림자 캔들이 발생하면 망치형 캔들.
 *            망치형이 의미있는 상승반전 또는 조정 신호가 될려면 아래 꼬리가 몸통보다 길고 몸통은 약간 두꺼워야 한다.
 *            몸통이 음봉이든 양본이든 상관없이 망치형으로 부를 순 있으나 반드시 몸통이 양봉이었을때에만 상승 반전 신호로 해설될 수 있다
 *            (음봉 망치형은 오히려 단기 하락을 부추김)
 *
 *
 *            필요기능 패턴발생지점, 발생스코어
 *            이후 데이터가 추가될때마다 즉각분석이 가능해야하므로
 *            이전 패턴발생 누적치 정보를 저장해야함 (실시간 분석기능 제공용)
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.08.19
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class HammerPattern implements CandlePattern {

    private TradeCandles tradeCandles;
    @Override
    public PriceChangeType getPriceChangeType() {
        return PriceChangeType.RISE;
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
            CandlePatternPoint point = HammerPattern.getPoint(candles, i, tradeCandles.getShortGapPercent());
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
                CandlePatternPoint point = HammerPattern.getPoint(candles, i, tradeCandles.getShortGapPercent());
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
     * 최근 발생 지점
     * 실시간 분석에 사용
     * @return 최근 발생 지점 얻기
     */
    @Override
    public CandlePatternPoint getLastPoint(){
        return lastPoint;
    }

    /**
     * 망치혈 캔들이 발생된 모든 지점 얻기
     * 시뮬레이터에 사용
     * @return 망치형 캔슬이 발생된 모든 지점( 배열)
     */
    @Override
    public CandlePatternPoint [] getPoints(){
        TradeCandle [] candles = tradeCandles.getCandles();
        List<CandlePatternPoint> pointList = null;

        for (int i = 5; i <candles.length ; i++) {
            CandlePatternPoint point = HammerPattern.getPoint(candles, i, tradeCandles.getShortGapPercent());

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

        //시점의 가격이 마지막 가격보다 낮으면 음봉
        if(tradeCandle.getOpen() > tradeCandle.getClose()){
            //양봉이 아니면
            //망치형 캔들은 정확도 높지않아서 양봉이 아니면 무효화 시키는게 좋을것 같음
            return null;
        }

        TrendLine trendLine = new TrendLine(TrendLine.Type.DOWN);

        double downTrendLineScore= trendLine.score(candles, index, 7 , shortGapPercent);
        return LowerShadowPattern.makePoint(trendLine,candles,index,shortGapPercent);
    }
}