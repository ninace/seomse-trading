package com.seomse.trading.technical.analysis.trend.line;

import com.seomse.trading.technical.analysis.candle.TradeCandle;

/**
 * <pre>
 *  파 일 명 : TrendLineDown.java
 *  설    명 : 하락 추세선
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.09.05
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class TrendLineDown implements TrendLineCase {

    @Override
    public boolean isCountValid(TradeCandle tradeCandle) {
        //양봉이거나 같으면 유효하지 않음
        return tradeCandle.getClose() < tradeCandle.getOpen();
    }
}
