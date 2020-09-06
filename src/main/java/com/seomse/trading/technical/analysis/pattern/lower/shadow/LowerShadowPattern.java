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
package com.seomse.trading.technical.analysis.pattern.lower.shadow;

import com.seomse.trading.technical.analysis.candle.CandleStick;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.technical.analysis.pattern.CandlePatternPoint;
import com.seomse.trading.technical.analysis.trend.line.TrendLine;

/**
 * 아래 그림자 캔들
 * @author macle
 */
public class LowerShadowPattern {


    /**
     * 아래 그림자 캔들 유효성 검사
     * @param tradeCandle TradeCandle 캔들
     * @return boolean 유효성 여부
     */
    public static boolean isValid(TradeCandle tradeCandle){

        //아래 그림자가 아니면
        if(tradeCandle.getType() != CandleStick.Type.LOWER_SHADOW){
            return false;
        }

        //몸통길이 계산하기
        //몸통길이는 종가 - 시가
        //몸통 길이 (변화량의 절대값)
        double absChange = tradeCandle.changeAbs();

        //몸통이 아래꼬리보다 긴걸로 계산한다
        //양봉이면 아래꼬리는
        double lowerTail = tradeCandle.getLowerTail();

        if(absChange*2.0 > lowerTail){
            //아래꼬리가 몸통의 2배보다 커야한다
            return false;
        }

        double upperTail = tradeCandle.getUpperTail();
        //noinspection RedundantIfStatement
        if(upperTail*2 > absChange ){
            //위꼬리는 몸통보다 2배이상 작아야한다.
            return false;
        }

        return true;
    }

    /**
     * 아래 그림자 캔들 발생여부 및 정형 점수 생성
     * 발생하지 않을경우 null
     * 발생한 패턴지점은 위 유효성을 통과한 걸로 비교한다
     * @param trendLine TrendLine 추세선
     * @param candles TradeCandle [] 캔들 배열
     * @param index int 체크할 index
     * @param shortGapPercent double 짧은 캔들 기준 확률
     * @return CandlePatternPoint 패턴 발생 여부 및 정형점수 ( 발생하지 않을경우 null)
     */
    public static CandlePatternPoint makePoint(TrendLine trendLine, TradeCandle [] candles, int index, double shortGapPercent){

        TradeCandle tradeCandle = candles[index];

        if(!LowerShadowPattern.isValid(tradeCandle)){
            return null;
        }


        double trendLineScore= trendLine.score(candles, index, 7 , shortGapPercent);


        if(trendLineScore < 1.0){
            //1.0 미만일 경우는 추세 무효
            return null;
        }

        //추세(상승 or 하락) 점수 최대 가중치 1.5점
        if(trendLineScore > 1.5){
            trendLineScore = 1.5;
        }

        //몸통길이 계산하기
        //몸통길이는 종가 - 시가
        //몸통 길이 (변화량의 절대값)
        double absChange = tradeCandle.changeAbs();

        double lowerTail = tradeCandle.getLowerTail();

        double score = lowerTail/(absChange*2.0) * trendLineScore;
        return new CandlePatternPoint(candles[index], score);
    }


}
