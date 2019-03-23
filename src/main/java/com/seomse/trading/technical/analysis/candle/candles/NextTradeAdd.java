package com.seomse.trading.technical.analysis.candle.candles;

import com.seomse.trading.Trade;
import com.seomse.trading.TradeAdd;
/**
 * <pre>
 *  파 일 명 : NextTradeAdd.java
 *  설    명 : trade정보 추가용 (처음 다음부터)
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.03
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
     * @param tradeCandles
     */
    NextTradeAdd(TradeCandles tradeCandles){
        this.tradeCandles = tradeCandles;
    }

    public void addTrade(Trade trade){

    }
}