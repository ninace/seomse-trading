package com.seomse.trading.technical.analysis.trend.line;

import com.seomse.trading.technical.analysis.candle.TradeCandle;

/**
 * <pre>
 *  파 일 명 : TrendLineCase.java
 *  설    명 : 추세선 유형별처리
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.09.05
 *
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public interface TrendLineCase {

    /**
     * 건수를 누적할지에대한 여부
     * @param tradeCandle tradeCandle
     * @return isCount
     */
    boolean isCountValid(TradeCandle tradeCandle);
}
