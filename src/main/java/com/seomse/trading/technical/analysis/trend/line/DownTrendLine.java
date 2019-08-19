package com.seomse.trading.technical.analysis.trend.line;

import com.seomse.trading.technical.analysis.candle.TradeCandle;

/**
 * <pre>
 *  파 일 명 : DownTrendLine.java
 *  설    명 : 하락 추세선
 *
 *
 *  작 성 자 : macle
 *  작 성 일 :
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class DownTrendLine {


    /**
     * 하락 기울기
     * 1.0이면 하락 패턴
     * 1.0 보다 커지면 하락기울기가 가파름
     * @param candles 캔듭배열
     * @param index 기준인덱스
     * @param leftCount 좌측 건수
     * @param shortGapPercent 짧은캔들 확률
     * @return 기울기
     */
    public static double score(TradeCandle[] candles, int index, int leftCount, double shortGapPercent ){

        if(index < 5){
            //기울기를 파악하려고하는 최소건수
            return -1.0;
        }

        int minCount = (int)((double)leftCount*0.6);

        if(index < minCount){
            return -1.0;
        }

        int startIndex = index - leftCount;
        if(startIndex  < 0){
            startIndex = 0;
        }

        int minusShortGapCount = 0;
        int minusCount =0;


        //평균 하락율 구하기
        double changePercentSum = 0.0;


        for(int i=startIndex ; i<index ; i++){

            changePercentSum += candles[i].getChangePercent();

            if(candles[i].getClose() >= candles[i].getOpen()){
                //양봉이거나 같으면 넘어감
                continue;
            }

            //음봉이면
            minusCount++;
            if(candles[i].getChangePercent() *-1.0 > shortGapPercent){
                //종가가 shortGap보다 하락률이 클경우
                minusShortGapCount++;
            }
        }

        int minShortCount = (int)((double)leftCount*0.45);
        if(minusCount < minCount || minusShortGapCount < minShortCount) {
            //음봉 건수 유효성성
            return -1.0;
        }

        int count = index - startIndex;


        double avg = changePercentSum*-1.0/(double)(count);
        double half = shortGapPercent*0.5;

        if(half >  avg){
            return -1.0;
        }

        return ((candles[index-1].getClose() - candles[startIndex].getOpen()) *-1.0) / half*(double)count;
    }

}
