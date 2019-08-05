package com.seomse.trading.technical.analysis.candle.candles;

import com.seomse.trading.Trade;
import com.seomse.trading.TradeAdd;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.time.Times;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 *  파 일 명 : TradeCandles.java
 *  설    명 : TradeCandle N개의
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.08.05
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class TradeCandles {

    public static final int DEFAULT_SAVE_COUNT = 1000;

    private static final CandleChangeObserver [] EMPTY_OBSERVER = new CandleChangeObserver[0];

    public static final TradeCandle [] EMPTY_CANDLES = new TradeCandle[0];

    //24시간으로 나눌 수 있는 값만 설정 가능
    private long timeGap ;

    private int saveCount = DEFAULT_SAVE_COUNT;

    TradeAdd tradeAdd ;

    List<TradeCandle> candleList = new LinkedList<>();
    TradeCandle [] candles = EMPTY_CANDLES;

    TradeCandle lastCandle = null;
    double shortGapPercent;
    double steadyGapPercent;

    boolean isEmptyCandleContinue = false;


    private final Object observerLock = new Object();
    private CandleChangeObserver [] observers = EMPTY_OBSERVER;

    private List<CandleChangeObserver> observerList = new LinkedList<>();

    /**
     * 캔들 변화 인지 옵져버 추가
     * @param candleChangeObserver candle change observer
     */
    public void addChangeObserver(CandleChangeObserver candleChangeObserver){
        synchronized (observerLock) {
            if (observerList.contains(candleChangeObserver)) {
                return;
            }
            observerList.add(candleChangeObserver);
            observers = observerList.toArray(new CandleChangeObserver[0]);
        }
    }

    /**
     * 캔들 변화 인지 옵저버 제거
     * @param candleChangeObserver candle change observer
     */
    public void removeObserver(CandleChangeObserver candleChangeObserver){
        synchronized (observerLock) {
            if (!observerList.contains(candleChangeObserver)) {
                return;
            }
            observerList.remove(candleChangeObserver);
            observers = observerList.toArray(new CandleChangeObserver[0]);
        }
    }

    /**
     * 생성자
     * @param timeGap timeGap
     */
    public TradeCandles(long timeGap ){
        //24시간 이하의 값에서는 24보다 낮은 값만 구할 수 있음
        if(timeGap < Times.DAY_1 &&
                Times.DAY_1%timeGap != 0){
            throw new RuntimeException("24 hour % timeGap 0: "  +  Times.DAY_1%timeGap );
        }
        this.timeGap = timeGap;
        tradeAdd = new FirstTradeAdd(this);
    }

    /**
     * 빈켄들 정보로 이어지게 할지 여부 설정
     *@param emptyCandleContinue isEmptyCandleContinue
     */
    public void setEmptyCandleContinue(boolean emptyCandleContinue) {
        isEmptyCandleContinue = emptyCandleContinue;
    }

    /**
     * 생성자
     * 처음부터 많은 켄들이 한번에 추가될 경우
     * @param timeGap timeGap
     * @param candles ready candles
     */
    public TradeCandles(long timeGap, TradeCandle [] candles, int saveCount ){
        if(timeGap < Times.DAY_1 &&
                Times.DAY_1%timeGap != 0){
            throw new RuntimeException("24 hour % timeGap 0: "  +  Times.DAY_1%timeGap );
        }

        this.timeGap = timeGap;
        this.saveCount = saveCount;


        if(candles == null || candles.length == 0){
            tradeAdd = new FirstTradeAdd(this);
            return;
        }

        lastCandle = candles[candles.length-1];


        tradeAdd = new NextTradeAdd(this);
        if(candles.length <= saveCount) {
            candleList.addAll(Arrays.asList(candles));
            this.candles = candles;
        }else{

            //noinspection ManualArrayToCollectionCopy
            for (int i = candles.length - saveCount ; i < candles.length; i++) {
                candleList.add(candles[i]);
            }
            this.candles = candleList.toArray(new  TradeCandle[0]);
        }
    }

    /**
     * add candle
     * @param tradeCandle add trade candle
     */
    public void addCandle(TradeCandle tradeCandle){
        TradeCandle lastEndCandle = null;

        if(candles.length  > 0){
            candles[candles.length-1].setEndTrade();

            if(tradeCandle.isEndTrade()){
                lastEndCandle = tradeCandle;
            }else{
                lastEndCandle =  candles[candles.length-1];
            }
        }

        candleList.add(tradeCandle);

        while(candleList.size() <= saveCount) {
            candleList.remove(0);
        }
        this.candles = candleList.toArray(new TradeCandle[0]);
        lastCandle = tradeCandle;

        CandleChangeObserver [] observers = this.observers;

        for(CandleChangeObserver observer : observers){

            observer.changeCandle(lastEndCandle, tradeCandle);
        }
    }


    /**
     * 거래정보 추가
     * trade add
     * @param trade trade
     */
    public void addTrade(Trade trade){
        tradeAdd.addTrade(trade);
    }

    /**
     * 타임 갭 얻기
     * timeGap get
     * @return timeGap
     */
    public long getTimeGap() {
        return timeGap;
    }

    /**
     * 캔들 저장 count 설정
     * candle save count set
     * @param saveCount candle save count
     */
    public void setSaveCount(int saveCount) {
        this.saveCount = saveCount;
    }

    /**
     * 캔들을 추가로 생성하여 트레이드 정보 입력
     * @param trade trade
     * @param startTime startTime
     * @param endTime endTime
     */
    void addTradeNewCandle(Trade trade, long startTime, long endTime){

        TradeCandle tradeCandle = new TradeCandle();
        tradeCandle.setStartTime(startTime);
        tradeCandle.setEndTime(endTime);
        tradeCandle.addTrade(trade);
        addCandle(tradeCandle);
    }

    /**
     * 설정된 캔들 저장 건수 얻기
     * candle save count get
     * @return candle saveCount
     */
    public int getSaveCount() {
        return saveCount;
    }

    /**
     * 길어얻기
     * candles length
     * @return candles length
     */
    public int length(){
        return candles.length;
    }

    /**
     * 캔들 배열 얻기
     * candles get
     * @return candles
     */
    public TradeCandle [] getCandles() {
        return candles;
    }

    /**
     * 짧은캔들 기준 변화률 설정
     * @param shortGapPercent 짧은 캔들 기준 변화률
     */
    public void setShortGapPercent(double shortGapPercent) {
        this.shortGapPercent = shortGapPercent;
    }

    /**
     * 보합 기준 변화률 설정
     * @param steadyGapPercent 보합 기준 변화률
     */
    public void setSteadyGapPercent(double steadyGapPercent) {
        this.steadyGapPercent = steadyGapPercent;
    }

    /**
     * 짧은캔들 gap percent
     * @return shot gap percent
     */
    public double getShortGapPercent() {
        return shortGapPercent;
    }

    /**
     * 보합 gap percent
     * @return steady gap percent
     */
    public double getSteadyGapPercent() {
        return steadyGapPercent;
    }
}
