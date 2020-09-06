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

import com.seomse.trading.PriceChangeType;
import com.seomse.trading.TrendChangeType;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.technical.analysis.pattern.CandlePatternDefault;
import com.seomse.trading.technical.analysis.pattern.CandlePatternPoint;
import com.seomse.trading.technical.analysis.trend.line.TrendLine;


/**
 * 유성형 고점에서 발생하는 패턴
 * 역망치형(InvertedHammerPattern)과 동일한 형태이지만 상승세의 고점에서 출현한다.
 * 주식에서는 일반적으로 강력한 하락반전 신호로 알려져 있지만
 * 단기적으로 오히려 상승 신호가 될 수 있다
 * 몸통이 짧은 양봉일경우 보다 확실한 단기 상승 우세이고 윗꼬리가 적당히 길면 좋다
 * 음봉이고 윗꼬리가 너무길면 즉각적인 하락반전으로 이어지기도 한다.
 * ----------------------------------------------------------------------------
 * 상승신호와 하락신호를 나누어서구현
 * 일반적으로는 하락신호이므로 하락으로 구현하고
 * 단기상승하는 상승현은 하나더 구현
 * @author macle
 */
public class ShootingStar extends CandlePatternDefault {



    @Override
    public PriceChangeType getPriceChangeType() {
        return PriceChangeType.FALL;
    }

    @Override
    public TrendChangeType getTrendChangeType() {
        return TrendChangeType.REVERSE;
    }

    /**
     * 캔들의 배열이 바뀔 수 있으므로 array 로 직접 받음
     * @param candles 캔들 배열
     * @param index 기준위치
     * @param shortGapPercent 짧은 캔들 기준 확률
     * @return 패턴결과
     */
    public CandlePatternPoint getPoint(TradeCandle[] candles, int index, double shortGapPercent){
        TradeCandle tradeCandle = candles[index];

        //음봉이어야하고
        //시점의 가격이 마지막 가격보다 낮으면 음봉
        if(tradeCandle.getOpen() < tradeCandle.getClose()){
            //음봉이 아니면
            return null;
        }


        //위꼬리가 많이 길어야함
        //위꼬리가 몸통의 3배 이상인경우로 한다
        double change = tradeCandle.changeAbs();

        // 아래그림자와 다르게 위그림자 캔들은 몸통이 짧아야 하므로 비율을 좀더 준다
        double upperTail = tradeCandle.getUpperTail();
        if(change*3.0 > upperTail){
            //위꼬리가 몸통의 3배보다 커야한다
            return null;
        }

        double shortGapPrice = tradeCandle.getOpen()*shortGapPercent;
        if(shortGapPrice*2.0 > upperTail){
            //위꼬리가 짧은 기준의 2배보다 커야한다
            return null;
        }

        TrendLine trendLine = new TrendLine(TrendLine.Type.UP);
        return UpperShadowPattern.makePoint(trendLine,candles,index,shortGapPercent);
    }



}