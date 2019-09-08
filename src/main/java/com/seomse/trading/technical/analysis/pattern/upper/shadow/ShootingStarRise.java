package com.seomse.trading.technical.analysis.pattern.upper.shadow;

import com.seomse.trading.PriceChangeType;
import com.seomse.trading.TrendChangeType;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.technical.analysis.pattern.CandlePatternDefault;
import com.seomse.trading.technical.analysis.pattern.CandlePatternPoint;
import com.seomse.trading.technical.analysis.trend.line.TrendLine;

/**
 * <pre>
 *  파 일 명 : ShootingStarRise.java
 *  설    명 : 유성형 고점에서 발생하는 패턴
 *             역망치형(InvertedHammerPattern)과 동일한 형태이지만 상승세의 고점에서 출현한다.
 *             주식에서는 일반적으로 강력한 하락반전 신호로 알려져 있지만
 *             단기적으로 오히려 상승 신호가 될 수 있다
 *             몸통이 짧은 양봉일경우 보다 확실한 단기 상승 우세이고 윗꼬리가 적당히 길면 좋다

 *
 *  작 성 자 : macle
 *  작 성 일 :
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class ShootingStarRise extends CandlePatternDefault {

    @Override
    public PriceChangeType getPriceChangeType() {
        return PriceChangeType.RISE;
    }

    @Override
    public TrendChangeType getTrendChangeType() {
        return TrendChangeType.CONTINUE;
    }


    @Override
    public CandlePatternPoint getPoint(TradeCandle[] candles, int index, double shortGapPercent) {

        TradeCandle tradeCandle = candles[index];


        //시점의 가격이 마지막 가격보다 낮으면 음봉
        if(tradeCandle.getOpen() > tradeCandle.getClose()){
            //양봉이 아니면
            return null;
        }

        if(tradeCandle.getChangeAbsPercent() > shortGapPercent){
            //몸통은 짧은캔들보다 작아야함
            return null;
        }

        double change = tradeCandle.changeAbs();
        double upperTail = tradeCandle.getUpperTail();

        double shortGapPrice = tradeCandle.getOpen()*shortGapPercent;
        if(shortGapPrice*4.0 < upperTail){
            //위꼬리가 너무 길면 유효하지 않음
            return null;
        }

        TrendLine trendLine = new TrendLine(TrendLine.Type.UP);

        double downTrendLineScore= trendLine.score(candles, index, 7 , shortGapPercent);
        return UpperShadowPattern.makePoint(trendLine,candles,index,shortGapPercent);
    }

}
