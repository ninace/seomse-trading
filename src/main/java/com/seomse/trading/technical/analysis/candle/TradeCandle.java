

package com.seomse.trading.technical.analysis.candle;

import com.seomse.trading.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  파 일 명 : TradeCandle.java
 *  설    명 : 거래 분석에 사용할 수 있는 캔들정보
 *            - 기본정보외에 분석에 필요한 거래정보 추가
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.01
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class TradeCandle extends Candlestick {


    private static final Logger logger = LoggerFactory.getLogger(TradeCandle.class);

    /**
     * 평균가
     */
    private double average= -1.0;


    /**
     * 거래량
     */
    private double volume= -1.0;

    /**
     * 평균가격 얻기
     * @return 평균가격
     */
    @Deprecated
    public double getAverage() {
        return average;
    }

    /**
     * 평균가격설정
     * @param average 평균가격
     */
    public void setAverage(double average) {
        this.average = average;
    }

    /**
     * 거래량 얻기
     * @return 거래량
     */
    public double getVolume() {
        return volume;
    }

    /**
     * 거래량 설정
     * @param volume 거래량
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * 거래 횟수(건수)
     * 한명이 대량 거래를 한건지를 측정하기위한 변수
     */
    private int tradeCount = -1;

    /**
     * 거래 정보 리스트
     */
    private List<Trade> tradeList = null;

    /**
     * 거래정보 추가
     * @param trade 거래정보
     */
    public void addTrade(Trade trade){

        if(tradeList == null){
            tradeList = new ArrayList<>();
        }

        tradeList.add(trade);
    }

    /**
     * 거래 회수(건수) 얻기
     * 거래량과 다름 회수별 거래량이 존재
     * @return 거래회수(건수)
     */
    public int getTradeCount() {
        return tradeCount;
    }

    /**
     * 거래정보를 이용하여 캔들 데이터 설정
     */
    public void setCandleToTrade(){
        if(tradeList == null  || tradeList.size() == 0){
            logger.error("trade data not set");
            return;
        }

        double priceTotal = 0.0;

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
        }

        if(volume > 0.0 && priceTotal > 0.0) {
            average = priceTotal / volume;
        }else{
            logger.error("volume or price total error volume -> " + volume + ", price total -> " + priceTotal);
        }


        setHigh(high);
        setLow(low);
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
     * @param tradeCount 거래회수(건수)
     */
    public void setTradeCount(int tradeCount) {
        this.tradeCount = tradeCount;
    }


}