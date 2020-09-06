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
import com.seomse.trading.technical.analysis.candle.CandleTimeGap;
import com.seomse.trading.technical.analysis.candle.TradeCandle;


/**
 * trade 정보 추가용 (2번째 부터)
 * @author macle
 */
class NextTradeAdd implements TradeAdd {
    private final TradeCandles tradeCandles;

    /**
     * 생성자
     * @param tradeCandles tradeCandles
     */
    NextTradeAdd(TradeCandles tradeCandles){
        this.tradeCandles = tradeCandles;
    }

    public void addTrade(Trade trade){
        if(trade.getTime() < tradeCandles.lastCandle.getEndTime()){
            //트레이드 정보 추가
            tradeCandles.lastCandle.addTrade(trade);
            return;
        }
        long timeGap = tradeCandles.getTimeGap();
        long nextStartTime =  tradeCandles.lastCandle.getEndTime();
        long nextEndTime  = nextStartTime + timeGap;
        if(trade.getTime() < nextEndTime){
            tradeCandles.addTradeNewCandle(trade, nextStartTime, nextEndTime);
            return;
        }

        double lastPrice = tradeCandles.lastCandle.getClose();

        if(tradeCandles.isEmptyCandleContinue) {
            do {
                TradeCandle nextTradeCandle = new TradeCandle();

                nextTradeCandle.setOpen(lastPrice);
                nextTradeCandle.setClose(lastPrice);
                nextTradeCandle.setHigh(lastPrice);
                nextTradeCandle.setLow(lastPrice);
                nextTradeCandle.setStartTime(nextStartTime);
                nextTradeCandle.setEndTime(nextEndTime);

                tradeCandles.addCandle(nextTradeCandle);
                nextStartTime = nextEndTime;
                nextEndTime = nextStartTime + timeGap;
            } while (trade.getTime() >= nextEndTime);

            tradeCandles.addTradeNewCandle(trade, nextStartTime , nextEndTime);
        }else{
            long startTime = CandleTimeGap.getStartTime(timeGap, trade.getTime());
            tradeCandles.addTradeNewCandle(trade, startTime , startTime + timeGap);
        }






    }
}