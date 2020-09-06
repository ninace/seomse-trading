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
package com.seomse.trading.technical.analysis.pattern.upper.shadow;

import com.seomse.trading.technical.analysis.candle.CandleStick;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.technical.analysis.pattern.CandlePatternPoint;
import com.seomse.trading.technical.analysis.trend.line.TrendLine;

/**
 * 외 그림자 캔들
 * @author macle
 */
public class UpperShadowPattern {

    /**
     * 위 그림자캔들 유효성 검사
     * @param tradeCandle TradeCandle 캔들
     * @return boolean 유효성 여부
     */
   public static boolean isValid(TradeCandle tradeCandle){

        //위 그림자가 아니면
        if(tradeCandle.getType() != CandleStick.Type.UPPER_SHADOW){
            return false;
        }

        //몸통길이 계산하기
        //몸통길이는 종가 - 시가
        //몸통 길이 (변화량의 절대값)
        double change = tradeCandle.changeAbs();

        // 아래그림자와 다르게 위그림자 캔들은 몸통이 짧아야 하므로 비율을 좀더 준다
        double upperTail = tradeCandle.getUpperTail();
        if(change*2.5 > upperTail){
            //위꼬리가 몸통의 2.5배보다 커야한다
            return false;
        }

        double lowerTail = tradeCandle.getLowerTail();
        //noinspection RedundantIfStatement
        if(lowerTail*2 > change ){
            //아래꼬리는 몸통보다 2배이상 작아야한다.
            return false;
        }

        return true;
    }

    /**
     * 위 그림자 캔들 발생여부 및 정형 점수 생성
     * 발생하지 않을경우 null
     * @param trendLine TrendLine 추세선
     * @param candles TradeCandle [] 캔들 배열
     * @param index int 체크할 index
     * @param shortGapPercent double 짧은 캔들 기준 확률
     * @return CandlePatternPoint 패턴 발생 여부 및 정형점수 ( 발생하지 않을경우 null)
     */
   public static CandlePatternPoint makePoint(TrendLine trendLine, TradeCandle [] candles, int index, double shortGapPercent){

        TradeCandle tradeCandle = candles[index];

        if(!UpperShadowPattern.isValid(tradeCandle)){
            return null;
        }

        double trendLineScore = trendLine.score(candles, index, 7 , shortGapPercent);

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

        double upperTail = tradeCandle.getUpperTail();

        double score = upperTail/(absChange*2.0) * trendLineScore;
        return new CandlePatternPoint(candles[index], score);
   }

}
