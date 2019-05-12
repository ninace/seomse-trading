package com.seomse.trading.example;

import com.seomse.commons.utils.ExceptionUtil;
import com.seomse.trading.Trade;
import com.seomse.trading.technical.analysis.candle.CandleManager;
import com.seomse.trading.technical.analysis.candle.CandleTimeGap;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.technical.analysis.candle.candles.TradeCandles;
import com.seomse.trading.time.Times;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 *  파 일 명 : CandleManagerExample.java
 *  설    명 : CandleManager 활용
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.05
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @atuhor Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class CandleManagerExample {


    private static final Logger logger = LoggerFactory.getLogger(CandleManagerExample.class);


    public static void main(String[] args) {


        CandleManager candleManager = new CandleManager(CandleTimeGap.DEFAULT_SCALPING);

        String tradeFilePath = "meta/trades";

        BufferedReader br = null;
        String line = null;
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
            logger.error(ExceptionUtil.getStackTrace(e));
        }finally{
            try{if(br != null)br.close();}catch(Exception e){logger.error(ExceptionUtil.getStackTrace(e));}
        }



        TradeCandles TradeCandles = candleManager.getCandles(Times.MINUTE_10);

        TradeCandle[] candles = TradeCandles.getCandles();

        for (int i = 0; i<candles.length ; i++) {

            //0.5%
            double shortGap = candles[i].getOpen()*0.0005;
            double steadyGap = candles[i].getOpen()*0.0002;

            candles[i].setType(shortGap,steadyGap );

            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(candles[i].getStartTime()))
                    +" " + candles[i].getOpen() +", " + candles[i].getClose() + ", " + candles[i].getHigh() + ", " + candles[i].getLow() + ", " + candles[i].getVolume() + ", " + candles[i].getTradeCount() + ", "
                    +       String.format("%.2f", candles[i].getAverage())+ ", "  + candles[i].change() + " "+ String.format("%.5f", candles[i].getChangePercent()*100.0) + "%, " +  String.format("%.2f",candles[i].strength()*100.0)  + "%, " + candles[i].getType().toString());

        }


    }


}
