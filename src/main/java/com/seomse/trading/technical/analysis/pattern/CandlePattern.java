package com.seomse.trading.technical.analysis.pattern;

import com.seomse.trading.PriceChangeType;
import com.seomse.trading.technical.analysis.candle.candles.TradeCandles;

/**
 * <pre>
 *  파 일 명 : CandlePattern.java
 *  설    명 : 캔들형 패턴

 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.09.08
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public interface CandlePattern {


    /**
     * 캔들배열 설정
     * @param tradeCandles 캔들배열
     */
    void setCandles(TradeCandles tradeCandles);

    /**
     * 실시간 분석에 필요한 정보들을 초기화
     */
    void initRealTime();

    /**
     * 최근 발생 지점
     * 실시간 분석에 사용
     * @return 최근 발생 지점 얻기
     */
    CandlePatternPoint  getLastPoint();

    /**
     * 캔들이 발생된 모든 지점 얻기
     * 시뮬레이터에 사용
     * @return 캔슬이 발생된 모든 지점( 배열)
     */
    CandlePatternPoint [] getPoints();


    /**
     * 가격변화 예측유형
     * @return 가격 변화 예층 유형( 상승, 하락, 보합)
     */
    PriceChangeType getPriceChangeType();

}
