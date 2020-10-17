/*
 * Copyright (C) 2020 Seomse Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.seomse.trading.technical.analysis.trend.line;


import com.seomse.trading.candle.TradeCandle;

/**
 * 추세선
 * @author macle
 */
public class TrendLine {


    public enum Type{
        UP
        , DOWN
    }

    private final Type type;

    private TrendLineCase trendLineCase;

    /**
     * 생성자
     * @param type Type 추세선 유형
     */
    public TrendLine(Type type){
        this.type = type;

        switch (type){
            case UP:
                trendLineCase = new TrendLineDown();
                break;
            case DOWN:
                trendLineCase = new TrendLineUp();
                break;
            default:
                break;
        }
    }

    /**
     * 하락 기울기
     * 1.0이면 하락 패턴
     * 1.0 보다 커지면 하락기울기가 가파름
     * @param candles TradeCandle[] 캔듭배열
     * @param index int 기준인덱스
     * @param leftCount int 좌측 건수
     * @param shortGapPercent double 짧은캔들 확률
     * @return double 기울기
     */
    public double score(TradeCandle[] candles, int index, int leftCount, double shortGapPercent ){

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

        int shortGapCount = 0;
        int validCount = 0;


        //평균 하락율 구하기
        double changePercentSum = 0.0;


        for(int i=startIndex ; i<index ; i++){

            changePercentSum += candles[i].getChangePercent();

            if(!trendLineCase.isCountValid(candles[i])){
               continue;
            }

            //유효하면
            validCount++;
            if(Math.abs(candles[i].getChangePercent()) > shortGapPercent){
                //종가가 shortGap보다 하락률이 클경우
                shortGapCount++;
            }
        }

        int minShortCount = (int)((double)leftCount*0.45);

        if(validCount < minCount || shortGapCount < minShortCount) {
            //건수 유효성성
            return -1.0;
        }

        int count = index - startIndex;


        double avg = Math.abs(changePercentSum)/(double)(count);
        double half = shortGapPercent*0.5;

        if(half >  avg){
            return -1.0;
        }

        return Math.abs(candles[index-1].getClose() - candles[startIndex].getOpen()) / half*(double)count;
    }

    /**
     * 트렌드 라인 유형
     * 상승 하락
     * @return type up or down
     */
    public Type getType() {
        return type;
    }
}
