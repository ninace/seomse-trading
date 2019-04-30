package com.seomse.trading.technical.analysis.candle;

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
 *  작 성 일 : 2019.04
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class CandleManager {

    private static final Logger logger = LoggerFactory.getLogger(CandleManager.class);


    private Map<Long, TradeCandles> candleMap = new HashMap<>();

    /**
     * 생성자
     */
    public CandleManager(){

    }


    /**
     * 캔들추가
     * @param time 캔들 기준시간
     * @return 추가 성공 여부
     */
    public boolean addCandle(long time){




        return false;
    }









}
