package com.seomse.trading;

/**
 * <pre>
 *  파 일 명 : PriceChangeType.java
 *  설    명 :  가격 변화 유형
 *            기본적분석(가치분석등), 기술적분서등에 모두 이용될 수 있기때문에 최상위에 위치
 *  작 성 자 : macle
 *  작 성 일 : 2019.09
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public enum PriceChangeType {
    UNDEFINED // 정의되지않음
    , RISE //상승
    , FALL //하락
    , HOLD //보합

}
