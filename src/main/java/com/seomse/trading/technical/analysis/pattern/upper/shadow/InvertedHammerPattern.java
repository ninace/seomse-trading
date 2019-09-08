package com.seomse.trading.technical.analysis.pattern.upper.shadow;

import com.seomse.trading.PriceChangeType;
import com.seomse.trading.technical.analysis.candle.Candlestick;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.technical.analysis.candle.candles.CandleChangeObserver;
import com.seomse.trading.technical.analysis.candle.candles.TradeCandles;
import com.seomse.trading.technical.analysis.pattern.CandlePattern;
import com.seomse.trading.technical.analysis.pattern.CandlePatternPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  파 일 명 : InvertedHammerPattern.java
 *  설    명 : 역망치형캔들
 *            윗꼬리는 아래꼬리보다 길어야하고
 *
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.07.22
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class InvertedHammerPattern implements CandlePattern {


    private TradeCandles tradeCandles;

    /**
     * 캔들 배열 설정
     * 캔들배열이 설정될
     * @param tradeCandles 캔들 배열
     */
    public void setCandles(TradeCandles tradeCandles){
        this.tradeCandles = tradeCandles;
    }

    private CandlePatternPoint lastPoint;

    private TradeCandle lastCheckCandle = null;

    /**
     * 마지막 지점 분석해 놓기
     */
    public CandlePatternPoint analysisLastPoint(){
        //최신 데이터 분석에는 마지막 지점만 필요하므로 마지막 지점만 분석해놓는다
        //초기 생성할때 실행
        TradeCandle [] candles = tradeCandles.getCandles();

        if(candles.length == 0){
            return null;
        }
        lastCheckCandle = candles[candles.length-1];

        for(int i=candles.length-1 ; i > -1 ; i--){
            CandlePatternPoint point = getPoint(candles, i, tradeCandles.getShortGapPercent());
            if(point != null){
                lastPoint = point;
                return lastPoint ;
            }
        }
        return lastPoint;
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


    @Override
    public void initRealTime() {

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

    @Override
    public PriceChangeType getPriceChangeType() {
        return null;
    }


    /**
     * 캔들의 배열이 바뀔 수 있으므로 array 로 직접 받음
     * @param candles 캔들 배열
     * @param index 기준위치
     * @param shortGapPercent 짧은 캔들 기준 확률
     * @return 패턴결과
     */
    public static CandlePatternPoint getPoint(TradeCandle [] candles, int index, double shortGapPercent){

        //5개의 기록이 없으면 패턴을 인식할 수 없음
        if(index < 5){
            return null;
        }

        TradeCandle tradeCandle = candles[index];
        //아래 그림자가 아니면
        if(tradeCandle.getType() != Candlestick.Type.LOWER_SHADOW){
            return null;
        }

        //시점의 가격이 마지막 가격보다 낮으면 음봉
        if(tradeCandle.getOpen() > tradeCandle.getClose()){
            //양봉이 아니면
            //망치형 캔들은 정확도 높지않아서 양봉이 아니면 무효화 시키는게 좋을것 같음
            return null;
        }

        //몸통길이 계산하기
        //몸통길이는 종가 - 시가
        //몸통 길이 (변화량의 절대값)
        double absChange = Math.abs(tradeCandle.change());


        //몸통이 아래꼬리보다 긴걸로 계산한다
        //양봉이면 아래꼬리는
        double lowerTail = tradeCandle.getLowerTail();

        if(absChange < lowerTail *2.0){
            //몸통이 아래꼬리보다 2배이상 커야한다
            return null;
        }

        //위꼬리는 몸통보다 2배이상 커야한다다
        double upperTail = tradeCandle.getUpperTail();
        if(upperTail < absChange*2.0){
            return null;
        }

        //과거의 모양이 지속적인 하락이었는 지를 인식하기 (최근 7개로 계산하기)
        int startIndex = index - 7;

        if(startIndex  < 0){
            startIndex = 0;
        }

        //긴음봉 3개이상 (short gap 보다 큰거)
        //전체적인 하락율이 short gap*5 보다 커야하고
        //음봉 4개이상
        double startPrice = candles[0].getOpen();
        double endPrice = candles[index-1].getClose();

        double validGap = startPrice * shortGapPercent * 5.0;

        if(endPrice > startPrice - validGap){
            //시작가격의 short gap * 5 보다 하락율이 높지 않으면
            return null;
        }

        //shrotGap 보다 큰 하락율을 기록한 건수
        int minusShortGapCount = 0;
        int minusCount =0;
        for(int i=startIndex ; i<index ; i++){

            if(candles[i].getClose() > candles[i].getOpen()){
                continue;
            }
            minusCount++;

            if(candles[i].getOpen() - candles[i].getOpen()*shortGapPercent > candles[i].getClose()){
                //종가가 shortGap보다 하락률이 클경우
                minusShortGapCount++;
            }
        }

        if(minusCount < 4 || minusShortGapCount < 3) {
            //음봉 건수 유효성성
            return null;
        }

        double score = upperTail/(absChange*2.0);
        return new CandlePatternPoint(candles[index], score);
    }
}
