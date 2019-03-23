package com.seomse.trading.technical.analysis.candle.candles;

import com.seomse.trading.Trade;
import com.seomse.trading.TradeAdd;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.time.Times;

import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 *  파 일 명 : TradeCandles.java
 *  설    명 : TradeCandle N개의
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

   //24시간으로 나눌 수 있는 값만 설정 가능
   private long timeGap ;

   TradeAdd tradeAdd = new FirstTradeAdd(this);

   List<TradeCandle> candleList = new LinkedList<>();

   TradeCandle lastCandle = null;

    /**
     * 생성자
     * @param timeGap
     */
    public TradeCandles(long timeGap ){
        //24시간으로 나뉘어 떠러지는 값으로만 캔들을 구성할 수 있음
        if(Times.HOUR_24%timeGap != 0){
            throw new RuntimeException("24 hour % timeGap 0: "  +  Times.HOUR_24%timeGap );
        }

        this.timeGap = timeGap;
    }


    /**
     * 거래정보 추가
     * @param trade
     */
    public void addTrade(Trade trade){
        tradeAdd.addTrade(trade);
    }

    /**
     * 타임 갭 얻기
     * @return
     */
    public long getTimeGap() {
        return timeGap;
    }
}
