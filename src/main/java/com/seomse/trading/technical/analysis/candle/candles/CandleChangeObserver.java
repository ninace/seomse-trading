package com.seomse.trading.technical.analysis.candle.candles;

import com.seomse.trading.technical.analysis.candle.TradeCandle;

/**
 * <pre>
 *  파 일 명 : CandleChangeObserver.java
 *  설    명 : 캔들 변화 감시
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.08.05
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public interface CandleChangeObserver {
    /**
     * 캔들 변화
     * 추가된 캔들과 마무리된 캔들은 같을 수 있음
     * @param lastEndCandle 마지막으로 마무리된 캔들
     * @param newCandle 추가된 캔들
     */
    void changeCandle(TradeCandle lastEndCandle, TradeCandle newCandle);
}
