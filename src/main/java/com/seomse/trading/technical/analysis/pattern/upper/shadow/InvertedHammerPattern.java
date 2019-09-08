package com.seomse.trading.technical.analysis.pattern.upper.shadow;

import com.seomse.trading.PricePredictType;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.technical.analysis.pattern.CandlePatternDefault;
import com.seomse.trading.technical.analysis.pattern.CandlePatternPoint;
import com.seomse.trading.technical.analysis.trend.line.TrendLine;

/**
 * <pre>
 *  파 일 명 : InvertedHammerPattern.java
 *  설    명 : 역망치형캔들
 *            윗꼬리는 몸통보다 길어야하고 몸통은 너무 두껍지 않아야 한다.
 *            몸통은 양봉일때 보다 유효하다.
 *            하락장에서 윗 꼬리가 길고 양봉일때
 *            상승 반전 또는 조정신호 이긴 하지만
 *            출현 위치 및 이전 패턴에 따라 무효화 되는 경우도 많다.
 *
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.09.08
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class InvertedHammerPattern extends CandlePatternDefault {

    @Override
    public PricePredictType getPricePredictType() {
        return PricePredictType.RISE;
    }


    @Override
    public CandlePatternPoint getPoint(TradeCandle[] candles, int index, double shortGapPercent) {

        TradeCandle tradeCandle = candles[index];


        //시점의 가격이 마지막 가격보다 낮으면 음봉
        if(tradeCandle.getOpen() > tradeCandle.getClose()){
            //양봉이 아니면
            return null;
        }

        TrendLine trendLine = new TrendLine(TrendLine.Type.DOWN);

        double downTrendLineScore= trendLine.score(candles, index, 7 , shortGapPercent);
        return UpperShadowPattern.makePoint(trendLine,candles,index,shortGapPercent);
    }


}
