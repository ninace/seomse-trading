package com.seomse.trading.technical.analysis.pattern;
/**
 * <pre>
 *  파 일 명 : CandlePattern.java
 *  설    명 : 캔들형 패턴

 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.06.17
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public interface CandlePattern {

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

}
