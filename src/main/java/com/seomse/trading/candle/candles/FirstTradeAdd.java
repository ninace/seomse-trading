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

package com.seomse.trading.candle.candles;

import com.seomse.trading.Trade;
import com.seomse.trading.TradeAdd;
import com.seomse.trading.candle.CandleTimeGap;


/**
 * trade 정보 처음 추가용
 * @author macle
 */
class FirstTradeAdd implements TradeAdd {

    private final TradeCandles tradeCandles;

    /**
     * 생성자
     * @param tradeCandles TradeCandles
     */
    FirstTradeAdd(TradeCandles tradeCandles){
        this.tradeCandles = tradeCandles;
    }

    @Override
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