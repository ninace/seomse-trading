package com.seomse.trading.technical.analysis.candle.candles;

import com.seomse.trading.Trade;
import com.seomse.trading.TradeAdd;
import com.seomse.trading.technical.analysis.candle.CandleTimeGap;

/**
 * <pre>
 *  파 일 명 : FirstTradeAdd.java
 *  설    명 : trade 정보 처음 추가용
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.04
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */

class FirstTradeAdd implements TradeAdd {

    private TradeCandles tradeCandles;

    /**
     * 생성자
     * @param tradeCandles tradeCandles
     */
    FirstTradeAdd(TradeCandles tradeCandles){
        this.tradeCandles = tradeCandles;
    }

    public void addTrade(Trade trade){
        if(tradeCandles.candleList.size() != 0){
            //이미 추가된 켄들이 있을경우
            tradeCandles.tradeAdd = new NextTradeAdd(tradeCandles);
            tradeCandles.tradeAdd.addTrade(trade);
            return;
        }
        long timeGap = tradeCandles.getTimeGap();

        long startTime = CandleTimeGap.getStartTime(timeGap, trade.getTime());
        tradeCandles.addTradeNewCandle(trade, startTime, startTime + timeGap);
        tradeCandles.tradeAdd = new NextTradeAdd(tradeCandles);

    }
}