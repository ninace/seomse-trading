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

import com.seomse.trading.PriceChangeType;
import com.seomse.trading.TrendChangeType;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.technical.analysis.pattern.CandlePatternDefault;
import com.seomse.trading.technical.analysis.pattern.CandlePatternPoint;
import com.seomse.trading.technical.analysis.trend.line.TrendLine;

/**
 * 망치형 캔들
 * 아래 그림자 캔들의 한종류로 하락추세에서 아래 그림자 캔들이 발생하면 망치형 캔들.
 * 망치형이 의미있는 상승반전 또는 조정 신호가 될려면 아래 꼬리가 몸통보다 길고 몸통은 약간 두꺼워야 한다.
 * 몸통이 음봉이든 양본이든 상관없이 망치형으로 부를 순 있으나 반드시 몸통이 양봉이었을때에만 상승 반전 신호로 해설될 수 있다
 * (음봉 망치형은 오히려 단기 하락을 부추김)
 *
 *
 * 필요기능 패턴발생지점, 발생스코어
 * 이후 데이터가 추가될때마다 즉각분석이 가능해야하므로
 * 이전 패턴발생 누적치 정보를 저장해야함 (실시간 분석기능 제공용)
 * @author macle
 */
public class HammerPattern extends CandlePatternDefault {

    @Override
    public PriceChangeType getPriceChangeType() {
        return PriceChangeType.RISE;
    }

    @Override
    public TrendChangeType getTrendChangeType() {
        return TrendChangeType.REVERSE;
    }

    @Override
    public CandlePatternPoint getPoint(TradeCandle[] candles, int index, double shortGapRate){

        TradeCandle tradeCandle = candles[index];


        //시점의 가격이 마지막 가격보다 낮으면 음봉
        if(tradeCandle.getOpen() > tradeCandle.getClose()){
            //양봉이 아니면
            //망치형 캔들은 정확도 높지않아서 양봉이 아니면 무효화 시키는게 좋을것 같음
            return null;
        }

        //몸통이 약간은 있어야하므로 너무작은경우 체크 추가
        if(Math.abs(tradeCandle.getChangeRate()) * 2 < shortGapRate){
            //몸통길이가 짧은 캔들 기준값을 절반은 되어야함
            return null;
        }

        TrendLine trendLine = new TrendLine(TrendLine.Type.DOWN);

        double downTrendLineScore= trendLine.score(candles, index, 7 , shortGapRate);
        return LowerShadowPattern.makePoint(trendLine,candles,index, shortGapRate);
    }
}