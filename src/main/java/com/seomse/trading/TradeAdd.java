package com.seomse.trading;
/**
 * <pre>
 *  파 일 명 : TradeAdd.java
 *  설    명 : 거래정보 추가 추상체
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.03
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public interface TradeAdd {

    /**
     * trade 추가
     * @param trade trade
     */
    void addTrade(Trade trade);
}
