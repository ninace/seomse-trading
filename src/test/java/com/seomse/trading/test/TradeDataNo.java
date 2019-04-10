package com.seomse.trading.test;

import com.seomse.jdbc.annotation.DateTime;
import com.seomse.jdbc.annotation.Table;

/**
 * <pre>
 *  파 일 명 : TradeDataNo.java
 *  설    명 : 거래데이터 정보
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.03
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
@Table(name="TB_COIN_TRADE_DATA_BM")
public class TradeDataNo {

    private Double PRC_COIN;
    @DateTime
    private Long DT_TRADE;
    private Double VL_TRADE;
    private String TP_TRADE;

    public Double getPRC_COIN() {
        return PRC_COIN;
    }

    public void setPRC_COIN(Double PRC_COIN) {
        this.PRC_COIN = PRC_COIN;
    }

    public Long getDT_TRADE() {
        return DT_TRADE;
    }

    public void setDT_TRADE(Long DT_TRADE) {
        this.DT_TRADE = DT_TRADE;
    }

    public Double getVL_TRADE() {
        return VL_TRADE;
    }

    public void setVL_TRADE(Double VL_TRADE) {
        this.VL_TRADE = VL_TRADE;
    }

    public String getTP_TRADE() {
        return TP_TRADE;
    }

    public void setTP_TRADE(String TP_TRADE) {
        this.TP_TRADE = TP_TRADE;
    }
}
