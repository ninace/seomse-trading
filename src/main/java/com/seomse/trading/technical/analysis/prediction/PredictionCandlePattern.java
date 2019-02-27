package com.seomse.trading.technical.analysis.prediction;

import com.seomse.trading.technical.analysis.candle.Candlestick;

/**
 * <pre>
 *  파 일 명 : PredictionCandlePattern.java
 *  설    명 : 예측 캔들 패턴
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.01
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public interface PredictionCandlePattern {

    /**
     * 학습
     * @param candleArray 캔들 배열
     * @param <T> 캔들
     */
    <T extends Candlestick> void learn(T [] candleArray);

    /**
     * 예측 시그널
     * @param candleArray 캔들 배열
     * @param minScore 최소 점수
     * @param maxLastGap 최대 갭 (마지막 인덱스와의)
     * @param <T> 캔들
     * @return 캔들 예측 정보
     */
    <T extends Candlestick> CandlePrediction signal(T [] candleArray, double minScore, int maxLastGap);

}
