/*
 * Copyright (C) 2020 Seomse Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.seomse.trading.technical.analysis.candle.candles;

import com.seomse.trading.Trade;
import com.seomse.trading.TradeAdd;
import com.seomse.trading.technical.analysis.candle.CandleStick;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.time.Times;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * 여러개의 TradeCandle 정보
 * @author macle
 */
public class TradeCandles {

    private static final Logger logger = LoggerFactory.getLogger(TradeCandles.class);

    public static final int DEFAULT_SAVE_COUNT = 1000;

    private static final CandleChangeObserver [] EMPTY_OBSERVER = new CandleChangeObserver[0];

    public static final TradeCandle [] EMPTY_CANDLES = new TradeCandle[0];

    //24시간으로 나눌 수 있는 값만 설정 가능
    private final long timeGap ;

    private int saveCount = DEFAULT_SAVE_COUNT;

    TradeAdd tradeAdd ;

    List<TradeCandle> candleList = new LinkedList<>();
    TradeCandle [] candles = EMPTY_CANDLES;

    TradeCandle lastCandle = null;
    double shortGapPercent = -1.0;
    double steadyGapPercent = -1.0;

    boolean isEmptyCandleContinue = false;

    private final Object observerLock = new Object();
    private CandleChangeObserver [] observers = EMPTY_OBSERVER;

    private List<CandleChangeObserver> observerList = new LinkedList<>();


    /**
     *  캔들 변화 인지 옵져버 추가
     * @param candleChangeObserver CandleChangeObserver  candle change observer
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
     * 캔들 배열의 유형을 설정
     * shortGapPercent
     * steadyGapPercent
     * 설정하고 실행하여야 한다.
     */
    public void setCandleType(){
        if(steadyGapPercent == -1.0 || shortGapPercent == -1.0){
            logger.error("shortGapPercent, steadyGapPercent set: " +shortGapPercent +", " + steadyGapPercent);
            return;
        }

        TradeCandle [] candles = this.candles;
        for(TradeCandle candle : candles){
            candle.setType(shortGapPercent, steadyGapPercent );
        }

    }

    /**
     * 캔들 변화 인지 옵저버 제거
     * @param candleChangeObserver CandleChangeObserver candle change observer
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
     * @param timeGap long timeGap
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
     *@param emptyCandleContinue boolean isEmptyCandleContinue
     */
    public void setEmptyCandleContinue(boolean emptyCandleContinue) {
        isEmptyCandleContinue = emptyCandleContinue;
    }

    /**
     * 생성자
     * 처음부터 많은 켄들이 한번에 추가될 경우
     * @param timeGap long timeGap
     * @param candles TradeCandle ready candles
     * @param saveCount int save count
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
     * @param tradeCandle TradeCandle add trade candle
     */
    public void addCandle(TradeCandle tradeCandle){
        TradeCandle lastEndCandle = null;

        if(candles.length > 0){
            candles[candles.length-1].setEndTrade();

            //캔들유형 설정
            if(shortGapPercent != -1.0 && steadyGapPercent != -1.0 && candles[candles.length-1].getType() == CandleStick.Type.UNDEFINED){
                candles[candles.length-1].setType(shortGapPercent, steadyGapPercent);
            }

            if(tradeCandle.isEndTrade()){
                if(shortGapPercent != -1.0 && steadyGapPercent != -1.0){
                    candles[candles.length-1].setType(shortGapPercent, steadyGapPercent);
                }
                lastEndCandle = tradeCandle;
            }else{
                lastEndCandle =  candles[candles.length-1];
            }
        }

        candleList.add(tradeCandle);

        while(candleList.size() >= saveCount) {
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
     * @param trade Trade trade
     */
    public void addTrade(Trade trade){
        tradeAdd.addTrade(trade);
    }

    /**
     * 타임 갭 얻기
     * timeGap get
     * @return long timeGap
     */
    public long getTimeGap() {
        return timeGap;
    }

    /**
     * 캔들 저장 count 설정
     * candle save count set
     * @param saveCount int candle save count
     */
    public void setSaveCount(int saveCount) {
        this.saveCount = saveCount;
    }

    /**
     * 캔들을 추가로 생성하여 트레이드 정보 입력
     * @param trade Trade trade
     * @param startTime long startTime
     * @param endTime long endTime
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
     * @return int candle saveCount
     */
    public int getSaveCount() {
        return saveCount;
    }

    /**
     * 길어얻기
     * candles length
     * @return int candles length
     */
    public int length(){
        return candles.length;
    }

    /**
     * 캔들 배열 얻기
     * candles get
     * @return TradeCandle candles
     */
    public TradeCandle [] getCandles() {
        return candles;
    }

    /**
     * 짧은캔들 기준 변화률 설정
     * @param shortGapPercent double 짧은 캔들 기준 변화률
     */
    public void setShortGapPercent(double shortGapPercent) {
        this.shortGapPercent = shortGapPercent;
    }

    /**
     * 보합 기준 변화률 설정
     * @param steadyGapPercent double 보합 기준 변화률
     */
    public void setSteadyGapPercent(double steadyGapPercent) {
        this.steadyGapPercent = steadyGapPercent;
    }

    /**
     * 짧은캔들 gap percent
     * @return double shot gap percent
     */
    public double getShortGapPercent() {
        return shortGapPercent;
    }

    /**
     * 보합 gap percent
     * @return double steady gap percent
     */
    public double getSteadyGapPercent() {
        return steadyGapPercent;
    }
}