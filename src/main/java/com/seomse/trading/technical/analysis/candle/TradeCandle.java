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

package com.seomse.trading.technical.analysis.candle;

import com.seomse.trading.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *거래 분석에 사용할 수 있는 캔들
 * 기본정보외에 분석에 필요한 거래정보 추가
 * @author macle
 */
public class TradeCandle extends CandleStick {

    private static final Logger logger = LoggerFactory.getLogger(TradeCandle.class);

    /**
     * 거래량
     */
    private double volume = 0.0;
    /**
     * 매수량
     */
    private double buyVolume = 0.0;

    /**
     * 매도량
     */
    private double sellVolume = 0.0;


    /**
     * 평균가격 얻기
     * @return double 평균가격
     */
    public double getAverage() {
        return priceTotal / volume;
    }


    /**
     * 거래량 얻기
     * @return double 거래량
     */
    public double getVolume() {
        return volume;
    }

    /**
     * 거래량 설정
     * @param volume double 거래량
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * 거래 횟수(건수)
     * 한명이 대량 거래를 한건지를 측정하기위한 변수
     */
    private int tradeCount = 0;

    double priceTotal = 0.0;
    /**
     * 거래 정보 리스트
     */
    private List<Trade> tradeList = null;

    /**
     * 거래정보 추가
     * @param trade Trade 거래정보
     */
    public void addTrade(Trade trade){

        if(tradeList == null){
            tradeList = new ArrayList<>();
            setOpen(trade.getPrice());
            setClose(trade.getPrice());
            high = trade.getPrice();
            low = trade.getPrice();
        }else{

            if(high < trade.getPrice()){
                high = trade.getPrice();
            } else if(low > trade.getPrice()){
                low =  trade.getPrice();
            }
            setClose(trade.getPrice());
        }
        tradeList.add(trade);
        tradeCount = tradeList.size();

        if(trade.getType() == Trade.Type.BUY){
            buyVolume+=trade.getVolume();
        }else{
            sellVolume+=trade.getVolume();
        }

        volume += trade.getVolume();
        priceTotal += trade.getVolume() * trade.getPrice();
    }

    /**
     * 거래 회수(건수) 얻기
     * 거래량과 다름 회수별 거래량이 존재
     * @return int 거래회수(건수)
     */
    public int getTradeCount() {
        return tradeCount;
    }

    /**
     * 거래정보를 이용하여 캔들 데이터 설정
     * trade 를 한번에 추가했을때 사용
     */
    public void setCandleToTrade(){
        if(tradeList == null  || tradeList.size() == 0){
            logger.error("trade data not set");
            return;
        }

        priceTotal = 0.0;

        tradeCount = tradeList.size();


        Trade firstTrade = tradeList.get(0);
        Trade endTrade = tradeList.get(tradeList.size()-1);

        setOpen(firstTrade.getPrice());
        setClose(endTrade.getPrice());

//        setStartTime(firstTrade.getTime());
//        setEndTime(endTrade.getTime());

        volume = 0.0;
        double high = firstTrade.getPrice();
        double low = firstTrade.getPrice();

        for (Trade trade:
                tradeList) {

            volume += trade.getVolume();


            priceTotal += trade.getVolume() * trade.getPrice();

            if(high < trade.getPrice()){
                high = trade.getPrice();
            }

            if(low > trade.getPrice()){
                low =  trade.getPrice();
            }

            if(trade.getType() == Trade.Type.BUY){
                buyVolume+=trade.getVolume();
            }else{
                sellVolume+=trade.getVolume();
            }
        }
        setHigh(high);
        setLow(low);
    }

    //1.0 == 100% , 100.0 == 10000%
    public static final double MAX_STRENGTH = 100.0;

    /**
     * 체결강도 얻기
     * max  MAX_STRENGTH
     * @return double 체결 강도
     */
    public double strength(){

        if(sellVolume <= 0){
            //10000%
            return MAX_STRENGTH;
        }

        double strength = buyVolume/sellVolume;
        //noinspection ManualMinMaxCalculation
        if(strength > MAX_STRENGTH){

            return MAX_STRENGTH;
        }

        return strength;

    }

    /**
     * 거래정보 초기화
     * 메모리 관리용 메소드
     */
    public void clearTrade(){
        if(tradeList == null){
            return;
        }

        tradeList.clear();
        tradeList = null;
    }

    /**
     * 거래회수 설정
     * 거래량과 다름 회수별 거래량이 존재
     * @param tradeCount int 거래회수(건수)
     */
    public void setTradeCount(int tradeCount) {
        this.tradeCount = tradeCount;
    }



    private boolean isEndTrade = false;

    /**
     * 거래종료여부
     * @return boolean 거래종료여부
     */
    public boolean isEndTrade() {
        return isEndTrade;
    }

    /**
     * 거래종료여부 설정
     */
    public void setEndTrade() {
        isEndTrade = true;
    }
}