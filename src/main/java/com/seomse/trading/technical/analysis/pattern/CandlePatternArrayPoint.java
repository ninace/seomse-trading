package com.seomse.trading.technical.analysis.pattern;

import com.seomse.trading.technical.analysis.candle.TradeCandle;
/**
 * <pre>
 *  파 일 명 : CandlePatternArrayPoint.java
 *  설    명 : 캔들 패턴 발생 지점
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.09.16
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class CandlePatternArrayPoint extends CandlePatternPoint{



    private TradeCandle [] candles;
    /**
     * 생성자
     * @param candles 캔들 배열
     * @param score  점수
     */
    public CandlePatternArrayPoint(TradeCandle [] candles, double score) {
        super(candles[candles.length-1], score);
    }
}
