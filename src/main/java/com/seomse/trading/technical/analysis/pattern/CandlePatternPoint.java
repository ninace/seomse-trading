package com.seomse.trading.technical.analysis.pattern;

import com.seomse.trading.technical.analysis.candle.TradeCandle;

/**
 * <pre>
 *  파 일 명 : CandlePatternPoint.java
 *  설    명 : 캔들 패턴 발생 지점
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.06.17
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class CandlePatternPoint {

    public static final CandlePatternPoint[] EMPTY_POINT = new CandlePatternPoint[0];

    private TradeCandle candle;
    private double score;

    /**
     * 생성자
     * @param candle 캔들
     * @param score 점수
     */
    public CandlePatternPoint(TradeCandle candle, double score){
        this.candle = candle;
        this.score = score;

    }

    /**
     * 캔들얻기
     * @return 트레이드 캔들(거래에 필요한 정보들이 추가로 있는 캔들)
     */
    public TradeCandle getCandle() {
        return candle;
    }

    /**
     * 점수 얻기
     * @return 점수
     */
    public double getScore() {
        return score;
    }
}
