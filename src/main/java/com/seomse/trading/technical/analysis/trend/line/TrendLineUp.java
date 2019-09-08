package com.seomse.trading.technical.analysis.trend.line;

import com.seomse.trading.technical.analysis.candle.TradeCandle;

/**
 * <pre>
 *  파 일 명 : TrendLineUp.java
 *  설    명 : 상승 추세선
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
public class TrendLineUp implements TrendLineCase {
    @Override
    public boolean isCountValid(TradeCandle tradeCandle) {
        return false;
    }
}
