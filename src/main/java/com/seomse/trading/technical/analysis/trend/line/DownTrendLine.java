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


    public double tilt(TradeCandle[] candles, int index, int leftCount ){
//        int minusShortGapCount = 0;
//        int minusCount =0;
//        for(int i=startIndex ; i<index ; i++){
//
//            if(candles[i].getClose() > candles[i].getOpen()){
//                continue;
//            }
//            minusCount++;
//
//            if(candles[i].getOpen() - candles[i].getOpen()*shortGapPercent > candles[i].getClose()){
//                //종가가 shortGap보다 하락률이 클경우
//                minusShortGapCount++;
//            }
//        }
//
//        if(minusCount < 4 || minusShortGapCount < 3) {
//            //음봉 건수 유효성성
//            return -1.0;
//        }
//
//        double score = upperTail/(absChange*2.0);
//        return score;

        return 0.0;
    }

}
