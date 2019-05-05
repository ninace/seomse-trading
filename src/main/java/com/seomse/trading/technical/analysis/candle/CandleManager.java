package com.seomse.trading.technical.analysis.candle;

import com.seomse.trading.Trade;
import com.seomse.trading.technical.analysis.candle.candles.TradeCandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *  파 일 명 : CandleManager.java
 *  설    명 : 캔들 관리자
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.05
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class CandleManager {

    private static final Logger logger = LoggerFactory.getLogger(CandleManager.class);

    private Map<Long, TradeCandles> candleMap = new HashMap<>();

    private TradeCandles [] tradeCandlesArray;
    /**
     * 생성자
     * @param trades 거래정보 배열
     * @param candleTimes 캔들 생성을위한 캔들 생성 기준 타임 배열
     */
    public CandleManager(Trade [] trades, long [] candleTimes){


        TradeCandles [] tradeCandlesArray = new TradeCandles[candleTimes.length];

        for (int i = 0; i <candleTimes.length ; i++) {
            tradeCandlesArray[i] = new TradeCandles(candleTimes[i]);
            candleMap.put(candleTimes[i], tradeCandlesArray[i]);
        }

        this.tradeCandlesArray = tradeCandlesArray;

        // 순서정보가 명확해야 할 경우를 위한 for
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i <trades.length ; i++) {
            addTrade(trades[i]);
        }
        logger.debug("candle manager create complete: " + trades.length + ", " + candleTimes.length);
    }


    /**
     * 거래정보 추가
     * @param trade 거래 정보
     */
    public void addTrade(Trade trade){
        for(TradeCandles tradeCandles : tradeCandlesArray){
            tradeCandles.addTrade(trade);
        }
    }

    /**
     * 캔들배열관리정보 얻기
     * @param time 기준시간
     * @return 기준시간에 맞는 캔들배열관리정보
     */
    public TradeCandles getCandles(long time){
        return candleMap.get(time);
    }

}
