package com.seomse.trading.test;

import com.seomse.trading.Trade;
import com.seomse.trading.technical.analysis.candlestick.TradeCandle;

public class TradingTest {

    public static void main(String[] args) {

        Trade trade = new Trade(Trade.Type.BUY, 100,20);
        trade.setTime(System.currentTimeMillis());
        Trade trade2 = new Trade(Trade.Type.BUY, 300,1);
        trade2.setTime(System.currentTimeMillis());

        TradeCandle tradeCandle = new TradeCandle();
        tradeCandle.addTrade(trade);
        tradeCandle.addTrade(trade2);
        tradeCandle.setCandleToTrade();


        System.out.println(tradeCandle.getAverage());
        System.out.println(tradeCandle.getHigh());
        System.out.println(tradeCandle.height());
    }
}
