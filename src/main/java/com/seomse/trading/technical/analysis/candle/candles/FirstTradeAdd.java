package com.seomse.trading.technical.analysis.candle.candles;

import com.seomse.trading.Trade;
import com.seomse.trading.TradeAdd;
import com.seomse.trading.technical.analysis.candle.TradeCandle;

/**
 * <pre>
 *  파 일 명 : FirstTradeAdd.java
 *  설    명 : trade정보 처음 추가용
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.03
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
     * @param tradeCandles
     */
    FirstTradeAdd(TradeCandles tradeCandles){
        this.tradeCandles = tradeCandles;
    }

    public void addTrade(Trade trade){


        trade.getTime();
//        tradeCandles.get

        TradeCandle tradeCandle = new TradeCandle();
        tradeCandle.addTrade(trade);

        tradeCandles.lastCandle = tradeCandle;
        tradeCandles.candleList.add(tradeCandle);
        tradeCandles.tradeAdd = new NextTradeAdd(tradeCandles);
    }
}