package com.seomse.trading.technical.analysis.candle.candles;

import com.seomse.trading.Trade;
import com.seomse.trading.TradeAdd;
import com.seomse.trading.technical.analysis.candle.CandleTimeGap;
import com.seomse.trading.technical.analysis.candle.TradeCandle;

/**
 * <pre>
 *  파 일 명 : NextTradeAdd.java
 *  설    명 : trade 정보 추가용 (2번째 부터)
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.04
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
class NextTradeAdd implements TradeAdd {
    private TradeCandles tradeCandles;

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

            double lastPrice = tradeCandles.lastCandle.getClose();

            if(tradeCandles.isEmptyCandleContinue) {
                do {
                    TradeCandle nextTradeCandle = new TradeCandle();

                    nextTradeCandle.setOpen(lastPrice);
                    nextTradeCandle.setClose(lastPrice);
                    nextTradeCandle.setHigh(lastPrice);
                    nextTradeCandle.setClose(lastPrice);
                    nextTradeCandle.setStartTime(nextStartTime);
                    nextTradeCandle.setEndTime(nextEndTime);

                    tradeCandles.addCandle(nextTradeCandle);
                    nextStartTime = nextEndTime;
                    nextEndTime = nextStartTime + timeGap;
                } while (trade.getTime() >= nextEndTime);
            }else{
                tradeCandles.addTradeNewCandle(trade, nextStartTime, nextEndTime);
                return;
            }
        }

        if(tradeCandles.isEmptyCandleContinue) {
            tradeCandles.addTradeNewCandle(trade, nextStartTime , nextEndTime);
        }else{
            long startTime = CandleTimeGap.getStartTime(timeGap, trade.getTime());
            tradeCandles.addTradeNewCandle(trade, startTime , startTime + timeGap);
        }




    }
}