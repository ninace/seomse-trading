
package com.seomse.trading.technical.analysis.candle;

import com.seomse.trading.PriceChangeType;

/**
 * <pre>
 *  파 일 명 : Candlestick.java
 *  설    명 : 캔들 일반적인 캔들 요소들만 정의
 *            - 더 많은 요소 클래스는 상속받아서 구현
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.01
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class Candlestick {



    //자세한 모양은 구글시트 참조
    //https://docs.google.com/spreadsheets/d/13T8OR02ESmGTsD6uAI5alYPdRg6ekrfnVnkCdqpoAvE/edit#gid=1228683334
    public enum Type {
        UNDEFINED // 정의되지않음
        , STEADY //보합세 캔들 시가 == 종가 or 시가종가가 비슷한 캔들
        , LONG // 긴 캔들
        , SHORT // 짧은 캔들
        , UPPER_SHADOW //위 그림자 캔들 -- 유성형 역망치형
        , LOWER_SHADOW //아래 그림자 캔들 -- 망치형(Hammer)저점 --  교수형(Hanging man)고점
        , HIGH_WAVE //위 아래에 그림자가 있는캔들 (긴거)
        , SPINNING_TOPS //위 아래에 그림자가 있는 캔들 (짧은거)
        , @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"}) DOJI // 십자캔들

    }


    /**
     * 캔들 유형
     */
    private Type type = Type.UNDEFINED;

    /**
     * 캔들 유형 얻기
     * @return 유형
     */
    public Type getType() {
        return type;
    }


    /**
     * 유형설정
     * @param shortGap 짧은 캔들 gap
     * @param steadyGap 보합세 gap
     */
    public void setType(double shortGap, double steadyGap){

        double height = height();
        //길이가 보합세보다 작을때
        if(height <= steadyGap){
            type = Type.STEADY;
            return;
        }

        double change = change();

        double absChange = Math.abs(change);


        double upperShadow;
        double lowerShadow;


        if(change < 0){
            upperShadow = high - open;
            lowerShadow = close - low;
        }else{
            upperShadow = high - close;
            lowerShadow = open - low;
        }

        //위 그림자 캔들
        if(upperShadow > lowerShadow
                //위그림자가 아래꼬리보다 크고
        && upperShadow > absChange
               //위그림자가 몸통보다 크고
        && upperShadow >  steadyGap
                //위그림자가 보합갭 보다 크고
        ){
            if(lowerShadow < steadyGap){
                //아래그림자가 보합갭보다 짧으면
                type = Type.UPPER_SHADOW;
                return;
            }

            double rate = upperShadow/lowerShadow;
            if(rate > 2.0){
                //위꼬리가 아래그림자보다 2배이상 길면
                type = Type.UPPER_SHADOW;
                return;

            }

        }


        //아래그림자 캔들
        if(lowerShadow > upperShadow
                //아래그림자가 위꼬리보다 크고
        && lowerShadow > absChange
                //아래그림자가 몸통보다 크고
        && lowerShadow > steadyGap
                //아래그림자가 보합보다 크고
        ){
            if(upperShadow < steadyGap){
                //위그림자가 보합갭보다 짧으면
                type = Type.LOWER_SHADOW;
                return;
            }


            double rate = lowerShadow/upperShadow;
            if(rate > 2.0){
                //아래그림자가 위꼬리보다 2배이상 길면
                type = Type.LOWER_SHADOW;
                return;
            }

        }


        //위 아래 그림자캔들
        if(
                lowerShadow > absChange
                //아래그림자가 몸통보다 길고
                && upperShadow > absChange
                //위그림자가 몸통보다 길고
                && lowerShadow > steadyGap
                //아래그림자가 보합길이보다 길고
                && upperShadow > steadyGap
                // 위그림자가 보합길이보다 길고
        ){

            if(absChange < steadyGap){
                //몸통이 보합걸이보다 작으면
                type = Type.DOJI;
                return;
            }

            double upperRate = upperShadow/absChange;
            double lowerRate = lowerShadow/absChange;

            if(upperRate > 2.0 && lowerRate > 2.0){
                // 위아래꼬리가 몸통보다 많이길면 길면
                type = Type.HIGH_WAVE;
            }else{
                // 위아래꼬리가 길면
                type = Type.SPINNING_TOPS;
            }
            return;
        }

        //유형을 정하지 못하고 이 부분까지 온경우
        //긴캔들 짧은캔들
        if(absChange <= shortGap){
            //몸통길이가 sortGap 짧으면 짧은캔들
            type = Type.SHORT;
        }else{
            //몸통길이가 sortGap 길면 긴캔들
            type = Type.LONG;
        }
    }

    /**
     * 가격 변화 유형
     */
    private PriceChangeType priceChangeType = PriceChangeType.UNDEFINED;

    /**
     * 가격변화유형 얻기
     * @return 가격변화유형
     */
    public PriceChangeType getPriceChangeType() {
        return priceChangeType;
    }

    /**
     * 가격변화유형 설정
     * @param priceChangeType 가격변화유형
     */
    public void setPriceChangeType(PriceChangeType priceChangeType) {
        this.priceChangeType = priceChangeType;
    }

    /**
     * 시가
     */
    protected double open = -1.0;

    /**
     * 종가
     */
    protected double close = -1.0;


    /**
     * 고가
     */
    protected double high = -1.0;


    /**
     * 저가
     */
    protected double low = -1.0;

    /**
     * 시가 얻기
     * 설정되지않으면 -1.0
     * @return 시가
     */
    public double getOpen() {
        return open;
    }

    /**
     * 시가 설정
     * @param open 시가
     */
    public void setOpen(double open) {
        this.open = open;
    }

    /**
     * 종가 얻기
     * @return 종가
     */
    public double getClose() {
        return close;
    }

    /**
     * 종가 설정
     * @param close 종가
     */
    public void setClose(double close) {
        this.close = close;
    }

    /**
     * 고가 얻기
     * @return 고가
     */
    public double getHigh() {
        return high;
    }

    /**
     * 고가 설정
     * @param high 고가
     */
    public void setHigh(double high) {
        this.high = high;
    }

    /**
     * 저가 얻기
     * @return 저가
     */
    public double getLow() {
        return low;
    }

    /**
     * 저가 설정
     * @param low 저가
     */
    public void setLow(double low) {
        this.low = low;
    }


    /**
     * 높이 얻기 (세로길이)
     * @return 높이(세로길이)
     */
    public double height() {
        return high - low;
    }

    /**
     * 가격 변화량 얻기
     *
     * @return 변화량
     */
    public double change() {
        return close - open;
    }

    public double getChangePercent(){
        return change()/open;

    }

    /**
     * 위꼬리 길이 얻기
     * @return 위 꼬리 길이
     */
    public double getUpperTail(){
        return high - Math.max(open, close);
    }

    /**
     * 아래꼬리 길이 얻기
     * @return 아래 꼬리 길이
     */
    public double getLowerTail(){
        return Math.min(open, close) - low;
    }


    /**
     * 시작시간
     */
    private long startTime;

    /**
     * 끝시간
     */
    private long endTime;

    /**
     * 기준시간
     */
    private long standardTime;

    /**
     * 시작시간 얻기
     *
     * @return 시작시간
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * 시작시간 설정
     *
     * @param startTime 시작시간
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * 끝시간 얻기
     *
     * @return 끝시간
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * 끝시간 설정
     *
     * @param endTime 끝시간
     */
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    /**
     * 기준시간 얻기
     * @return 기준시간
     */
    public long getStandardTime() {
        return standardTime;
    }

    /**
     * 기준시간 설정
     * 분봉이라고 하면 10시21분을 나타내는기준시간 (key)
     * @param standardTime 기준시간
     */
    public void setStandardTime(long standardTime) {
        this.standardTime = standardTime;
    }


}

