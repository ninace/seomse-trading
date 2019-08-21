package com.seomse.trading.fundamental.analysis;


/**
 * <pre>
 *  파 일 명 : FinancialStatements.java
 *  설    명 : 재무 제표
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.08.21
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class FinancialStatements {

    public enum Type{
        YEAR //년간
        , QUARTER //분기

    }

    //결산 년월
    private String ym;

    //결산 유형
    private Type type = Type.QUARTER;


    //추정여부 (예상 재무 제표)
    private boolean isEstimation = false;

    //매출액
    private Long sales;

    //영업이익
    private Long operatingProfit;

    //사전계속 사업이익
    //Continuing business profit before taxes
    private Long cbpf ;

    //당기 순이익
    //net profit during the term
    private Long netProfit ;

    //당기 순이익 지배
    //net profit during the term domination
    private Long netProfitDomination;

    //당기 순이익 비지배
    //net profit during the term not domination
    private Long netProfitNotDomination ;

    //자산 총계
    private Long assetTotal;

    //부채총계
    private Long deptTotal ;

    //자본총계
    private Long capitalTotal ;

    //자본총계 지배
    private Long capitalTotalDomination ;

    //자본총계 비지배
    private Long capitalTotalNotDomination ;

    //자본금
    private Long capital;

    //영업활동 현금흐름
    private Long operatingCashFlow;

    //투자활동 현금흐름
    private Long investingCashFlow;

    //재무활동 현금흐름
    private Long financingCashFlow;

    //미래 현금흐름
    private Long futureCashFlow;

    //CAPEX 미래의 이윤을 창출하기 위해 지출된 비용 Capital expenditures
    private Long capex;

    //이자발생부채
    private Long interestDebt;

    //영업이익률
    private Double operatingProfitRatio;

    //순이익률
    private Double netProfitRatio;

    //roe 자기자본이익률 return on equity
    private Double roe;

    //roa 총 자산 순이익률 Return On Assets
    private Double roa;

    //부채비율
    private Double debtRatio;

    //자본유보율
    private Double reserveRatio;

    //eps 주당순이익 금액이지만 double일 수 있음
    private Double eps;

    //per 주가수익 비율
    private Double per;

    //bps 주당순 자산가치
    private Double bps;

    //주가와 1주당 순자산을 비교하여 나타낸 비율(PBR = 주가 / 주당 순자산가치). 즉 주가가 순자산(자본금과 자본잉여금, 이익잉여금의 합계)에 비해 1주당 몇 배로 거래되고 있는지를 측정하는 지표
    private Double pbr;

    //주당 배당액
    private Long dps;

    //배당수익률
    private Double dividendYieldRatio;

    //배당성향
    private Double propensityToDividend;

    /**
     * 결산년월 얻기
     * @return 결산년월 ex->201912
     */
    public String getYm() {
        return ym;
    }

    /**
     * 결산년월 설정
     * @param ym 결산년월 ex->201912
     */
    public void setYm(String ym) {
        this.ym = ym;
    }

    /**
     * 결산유형 얻기
     * @return 결산유형 년,분기 YEAR, QUARTER
     */
    public Type getType() {
        return type;
    }

    /**
     * 결산유형 설정
     * @param type 결산유형 년,분기 YEAR, QUARTER
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * 추정여부
     * 예상 실적인지 아닌지
     * @return 추정여부
     */
    public boolean isEstimation() {
        return isEstimation;
    }

    /**
     * 추정여부 설정
     * 예상 실적인지 아닌지
     * @param estimation 추정여부
     */
    public void setEstimation(boolean estimation) {
        isEstimation = estimation;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public Long getOperatingProfit() {
        return operatingProfit;
    }

    public void setOperatingProfit(Long operatingProfit) {
        this.operatingProfit = operatingProfit;
    }

    /**
     * 세전계속 사업이익 얻기
     * Continuing business profit before taxes
     * @return Continuing business profit before taxes
     */
    public Long getCbpf() {
        return cbpf;
    }

    /**
     * 세전계속 사업이익 설정
     * Continuing business profit before taxes
     * @param cbpf Continuing business profit before taxes
     */
    public void setCbpf(Long cbpf) {
        this.cbpf = cbpf;
    }


    public Long getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Long netProfit) {
        this.netProfit = netProfit;
    }

    public Long getNetProfitDomination() {
        return netProfitDomination;
    }

    public void setNetProfitDomination(Long netProfitDomination) {
        this.netProfitDomination = netProfitDomination;
    }

    public Long getNetProfitNotDomination() {
        return netProfitNotDomination;
    }

    public void setNetProfitNotDomination(Long netProfitNotDomination) {
        this.netProfitNotDomination = netProfitNotDomination;
    }

    public Long getAssetTotal() {
        return assetTotal;
    }

    public void setAssetTotal(Long assetTotal) {
        this.assetTotal = assetTotal;
    }

    public Long getDeptTotal() {
        return deptTotal;
    }

    public void setDeptTotal(Long deptTotal) {
        this.deptTotal = deptTotal;
    }

    public Long getCapitalTotal() {
        return capitalTotal;
    }

    public void setCapitalTotal(Long capitalTotal) {
        this.capitalTotal = capitalTotal;
    }

    public Long getCapitalTotalDomination() {
        return capitalTotalDomination;
    }

    public void setCapitalTotalDomination(Long capitalTotalDomination) {
        this.capitalTotalDomination = capitalTotalDomination;
    }

    public Long getCapitalTotalNotDomination() {
        return capitalTotalNotDomination;
    }

    public void setCapitalTotalNotDomination(Long capitalTotalNotDomination) {
        this.capitalTotalNotDomination = capitalTotalNotDomination;
    }

    public Long getCapital() {
        return capital;
    }

    public void setCapital(Long capital) {
        this.capital = capital;
    }

    public Long getOperatingCashFlow() {
        return operatingCashFlow;
    }

    public void setOperatingCashFlow(Long operatingCashFlow) {
        this.operatingCashFlow = operatingCashFlow;
    }

    public Long getInvestingCashFlow() {
        return investingCashFlow;
    }

    public void setInvestingCashFlow(Long investingCashFlow) {
        this.investingCashFlow = investingCashFlow;
    }

    public Long getFinancingCashFlow() {
        return financingCashFlow;
    }

    public void setFinancingCashFlow(Long financingCashFlow) {
        this.financingCashFlow = financingCashFlow;
    }

    public Long getFutureCashFlow() {
        return futureCashFlow;
    }

    public void setFutureCashFlow(Long futureCashFlow) {
        this.futureCashFlow = futureCashFlow;
    }

    public Long getCapex() {
        return capex;
    }

    public void setCapex(Long capex) {
        this.capex = capex;
    }

    public Long getInterestDebt() {
        return interestDebt;
    }

    public void setInterestDebt(Long interestDebt) {
        this.interestDebt = interestDebt;
    }

    public Double getOperatingProfitRatio() {
        return operatingProfitRatio;
    }

    public void setOperatingProfitRatio(Double operatingProfitRatio) {
        this.operatingProfitRatio = operatingProfitRatio;
    }

    public Double getNetProfitRatio() {
        return netProfitRatio;
    }

    public void setNetProfitRatio(Double netProfitRatio) {
        this.netProfitRatio = netProfitRatio;
    }

    public Double getRoe() {
        return roe;
    }

    public void setRoe(Double roe) {
        this.roe = roe;
    }

    public Double getRoa() {
        return roa;
    }

    public void setRoa(Double roa) {
        this.roa = roa;
    }

    public Double getDebtRatio() {
        return debtRatio;
    }

    public void setDebtRatio(Double debtRatio) {
        this.debtRatio = debtRatio;
    }

    public Double getReserveRatio() {
        return reserveRatio;
    }

    public void setReserveRatio(Double reserveRatio) {
        this.reserveRatio = reserveRatio;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

    public Double getPer() {
        return per;
    }

    public void setPer(Double per) {
        this.per = per;
    }

    public Double getBps() {
        return bps;
    }

    public void setBps(Double bps) {
        this.bps = bps;
    }

    public Double getPbr() {
        return pbr;
    }

    public void setPbr(Double pbr) {
        this.pbr = pbr;
    }

    public Long getDps() {
        return dps;
    }

    public void setDps(Long dps) {
        this.dps = dps;
    }

    public Double getDividendYieldRatio() {
        return dividendYieldRatio;
    }

    public void setDividendYieldRatio(Double dividendYieldRatio) {
        this.dividendYieldRatio = dividendYieldRatio;
    }

    public Double getPropensityToDividend() {
        return propensityToDividend;
    }

    public void setPropensityToDividend(Double propensityToDividend) {
        this.propensityToDividend = propensityToDividend;
    }
}