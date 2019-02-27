package com.seomse.trading.technical.analysis.prediction;

import com.seomse.trading.PriceChangeType;
import com.seomse.trading.technical.analysis.candle.Candlestick;

/**
 * <pre>
 *  파 일 명 : CandlePrediction.java
 *  설    명 : 캔들을 이용한 예측정보
 *              캔들위치 , 예측유형, 예측 점수
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.01
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class CandlePrediction {

    private Candlestick candlestick;

    private PriceChangeType predictionType = PriceChangeType.UNDEFINED;

    private double score;

    private int index;

    /**
     * 생성자
     * @param candlestick 캔들
     */
    public CandlePrediction(Candlestick candlestick){
        this.candlestick = candlestick;
    }

    /**
     * 캔들 얻기
     * @return 캔들
     */
    public Candlestick getCandlestick() {
        return candlestick;
    }

    /**
     * 에측유형 얻기 (상승/하락)
     * @return 예측유형
     */
    public PriceChangeType getPredictionType() {
        return predictionType;
    }

    /**
     * 예측유형 설정 (상승/하락)
     * @param predictionType 예측유형
     */
    public void setPredictionType(PriceChangeType predictionType) {
        this.predictionType = predictionType;
    }

    /**
     * 예측 점수 얻기
     * @return 예측 점수
     */
    public double getScore() {
        return score;
    }

    /**
     * 예측 점수 설정
     * @param score 예측 점수
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * 결과가 배열 or 리스트 일때의 인덱스(위치정보) 정보 얻기
     * @return 배열 또는 리스트일때의 순서
     */
    public int getIndex() {
        return index;
    }

    /**
     * 결과가 배열 or 리스트 일때의 인덱스(위치정보) 정보 설정
     * @param index 배열 또는 리스트일때의 순서
     */
    public void setIndex(int index) {
        this.index = index;
    }
}
