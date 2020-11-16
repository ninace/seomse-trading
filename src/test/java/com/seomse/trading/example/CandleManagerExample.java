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
package com.seomse.trading.example;

import com.seomse.commons.utils.time.Times;
import com.seomse.trading.Trade;
import com.seomse.trading.candle.CandleManager;
import com.seomse.trading.candle.CandleTimeGap;
import com.seomse.trading.candle.TradeCandle;
import com.seomse.trading.candle.candles.TradeCandles;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CandleManager 를 활용한 예제
 * @author macle
 */
public class CandleManagerExample {

    public static CandleManager makeCandleManager(){

        CandleManager candleManager = new CandleManager(CandleTimeGap.DEFAULT_SCALPING);

        String tradeFilePath = "meta/trades";

        BufferedReader br = null;
        String line;

        //noinspection TryFinallyCanBeTryWithResources
        try{

            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(tradeFilePath))));

            while ((line = br.readLine()) != null) {

                String [] values = line.split(",");

                if(values.length <4){
                    continue;
                }

                Trade.Type type;

                if(values[0].equals("B")){
                    type = Trade.Type.BUY;
                }else{
                    type = Trade.Type.SELL;
                }

                long time =  Long.parseLong(values[1]);

                double price = Double.parseDouble(values[2]);

                double volume = Double.parseDouble(values[3]);

                Trade trade = new Trade(type, price, volume, time);

                candleManager.addTrade(trade);


            }

        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            //noinspection CatchMayIgnoreException
            try{if(br != null)br.close();}catch(Exception e){}
        }

        return candleManager;
    }

    public static void main(String[] args) {
        CandleManager candleManager = makeCandleManager();
        TradeCandles TradeCandles = candleManager.getCandles(Times.MINUTE_10);
        TradeCandle[] candles = TradeCandles.getCandles();
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i<candles.length ; i++) {

            //0.5%
            double shortGap = candles[i].getOpen()*0.005;
            double steadyGap = candles[i].getOpen()*0.002;

            candles[i].setType(shortGap,steadyGap );

            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(candles[i].getStartTime()))
                    +" " + candles[i].getOpen() +", " + candles[i].getClose() + ", " + candles[i].getHigh() + ", " + candles[i].getLow() + ", " + candles[i].getVolume() + ", " + candles[i].getTradeCount() + ", "
                    +       String.format("%.2f", candles[i].getAverage())+ ", "  + candles[i].getChange() + " "+ String.format("%.5f", candles[i].getChangePercent()*100.0) + "%, " +  String.format("%.2f",candles[i].strength()*100.0)  + "%, " + candles[i].getType().toString());

        }

    }


}
