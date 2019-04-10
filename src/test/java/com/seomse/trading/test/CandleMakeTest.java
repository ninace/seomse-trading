package com.seomse.trading.test;

import com.seomse.jdbc.naming.JdbcNaming;
import com.seomse.trading.Trade;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.technical.analysis.candle.candles.TradeCandles;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
/**
 * <pre>
 *  파 일 명 : CandleMakeTest.java
 *  설    명 : 캔들 생성 테스트
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.03
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class CandleMakeTest {

    public static void main(String[] args) {



        //1분봉 만들기
        TradeCandles tradeCandles = new TradeCandles(60000L);

        tradeCandles.setEmptyCandleContinue(true);
        List<TradeDataNo> tradeDataList = JdbcNaming.getObjList(TradeDataNo.class
                ,"DT_TRADE BETWEEN TO_DATE('20190207','YYYYMMDD') AND TO_DATE('20190218','YYYYMMDD')"
                ," DT_TRADE ASC");




        int size = tradeDataList.size();
        for(int i=0 ; i<size ; i++){
            TradeDataNo tradeDataNo  =  tradeDataList.get(i);

            Trade.Type tpye = Trade.Type.BUY;

            try{
                if(tradeDataNo.getTP_TRADE().equals("S")){
                    tpye = Trade.Type.SELL;
                }

            }catch(Exception e){
                e.printStackTrace();;
            }

            Trade trade = new Trade(tpye, tradeDataNo.getPRC_COIN(), tradeDataNo.getVL_TRADE());
            trade.setTime(tradeDataNo.getDT_TRADE());
            tradeCandles.addTrade(trade);
        }


        System.out.println("tradeCandles " + tradeCandles.length());

        TradeCandle[] candles = tradeCandles.getCandles();

        for (int i = 0; i <candles.length ; i++) {

            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(candles[i].getStartTime()))
            +" " + candles[i].getOpen() +", " + candles[i].getClose() + ", " + candles[i].getHigh() + ", " + candles[i].getLow());
        }


    }

}
