package com.seomse.trading.technical.analysis.candle.candles;

import com.seomse.trading.Trade;
import com.seomse.trading.TradeAdd;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.time.Times;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 *  파 일 명 : TradeCandles.java
 *  설    명 : TradeCandle N개의
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.03
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class TradeCandles {


    private static final Logger logger = LoggerFactory.getLogger(TradeCandles.class);

    public static final TradeCandle [] EMPTY_CANDLES = new TradeCandle[0];


    //24시간으로 나눌 수 있는 값만 설정 가능
    private long timeGap ;

    private int saveCount = 50;

    TradeAdd tradeAdd = new FirstTradeAdd(this);

    List<TradeCandle> candleList = new LinkedList<>();
    TradeCandle [] candles = EMPTY_CANDLES;

    TradeCandle lastCandle = null;

    /**
     * 생성자
     * @param timeGap
     */
    public TradeCandles(long timeGap ){
        //24시간 이하의 값에서는 24보다 낮은 값만 구할 수 있음
        if(timeGap < Times.HOUR_24 &&
                Times.HOUR_24%timeGap != 0){
            throw new RuntimeException("24 hour % timeGap 0: "  +  Times.HOUR_24%timeGap );
        }
        this.timeGap = timeGap;
    }

    /**
     * 거래정보 추가
     * @param trade
     */
    public void addTrade(Trade trade){
        tradeAdd.addTrade(trade);
    }

    /**
     * 타임 갭 얻기
     * @return
     */
    public long getTimeGap() {
        return timeGap;
    }
}
