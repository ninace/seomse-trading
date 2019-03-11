package com.seomse.trading.technical.analysis.candle;

import com.seomse.trading.Trade;

import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 *  파 일 명 : CandleManager.java
 *  설    명 : 캔들 관리자
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.03
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class TradeCandles {

    private int saveCount = 50;

    //시간간
   private long timeGap ;

   private List<TradeCandle> candleList = new LinkedList<>();

    /**
     * 생성자
     */
    public TradeCandles(long timeGap ){
        this.timeGap = timeGap;
    }


    /**
     * 거래정보 추가
     * @param trade
     */
    public void addTrade(Trade trade){

    }





    public static void main(String[] args) {
        //1분봉 테스트
        TradeCandles tradeCandles  = new TradeCandles(1000*60);

    }



}
