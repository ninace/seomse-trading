package com.seomse.trading.example;

import com.seomse.trading.technical.analysis.candle.CandleManager;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.technical.analysis.candle.candles.TradeCandles;
import com.seomse.trading.technical.analysis.pattern.CandlePatternPoint;
import com.seomse.trading.technical.analysis.pattern.lower.shadow.HammerPattern;
import com.seomse.trading.time.Times;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *  파 일 명 : PatternSimulator.java
 *  설    명 : 패턴 시뮬레이터
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.08.08
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class PatternSimulator {

    private static final Logger logger = LoggerFactory.getLogger(PatternSimulator.class);

    public static void main(String[] args) {


        CandleManager candleManager = CandleManagerExample.makeCandleManager();

        candleManager.setSaveCount(5000);

        long [] times = candleManager.getTimes();

        //정렬된 데이터라 순서정보가 명확하

        for(long time : times){


            TradeCandles tradeCandles = candleManager.getCandles(time);

            if(time < Times.HOUR_4) {

                //0.2% 보합
                tradeCandles.setSteadyGapPercent(0.002);
                //0.5% 짧은 캔들 기준은 0.5%
                tradeCandles.setShortGapPercent(0.005);
            }else if(time < Times.HOUR_12) {

                //0.2% 보합
                tradeCandles.setSteadyGapPercent(0.002);
                //1.5% 짧은 캔들 기준은 1.5%
                tradeCandles.setShortGapPercent(0.015);
            }else{

                //0.5% 보합
                tradeCandles.setSteadyGapPercent(0.005);
                //3% 짧은 캔들 기준은 0.5%
                tradeCandles.setShortGapPercent(0.03);
            }
            tradeCandles.setCandleType();

            HammerPattern hammerPattern = new HammerPattern();
            hammerPattern.setCandles(tradeCandles);
            CandlePatternPoint [] points = hammerPattern.getPoints();

            if(points.length == 0){
                logger.debug("candle time : " + time +", pattern point null");
                continue;
            }

            TradeCandle[] candles = tradeCandles.getCandles();
            logger.debug("candle time : " + time  + ", pattern count: " + points.length);

            int pattenCount = 0;
            int upCount = 0;

            for (int i = 0; i < points.length ; i++) {
                CandlePatternPoint candlePatternPoint = points[i];
                TradeCandle patternCandle = candlePatternPoint.getCandle();
                int candleIndex = -1;

                for (int index = 0; index <candles.length ; index++) {
                    if(patternCandle == candles[index]){
                        candleIndex = index;
                        break;
                    }
                }

                if(candleIndex == -1){
                    logger.error("candle index not search");
                    continue;
                }

                if(candleIndex == points.length-1){
                    continue;
                }


                TradeCandle nextCandle =  candles[candleIndex+1];
                pattenCount++;
                if(patternCandle.getClose() < nextCandle.getClose()){
                    upCount++;

                    logger.info("패턴 성공: " + patternCandle.getEndTime() + ", " + patternCandle.getClose() + ", " + nextCandle.getClose());


                }else{
                    logger.info("패턴 실패: " + patternCandle.getEndTime() + ", " + patternCandle.getClose() + ", " + nextCandle.getClose());
                }

            }


            logger.info("pattenCount " + pattenCount + "upCount " + upCount + " percent " + (double)upCount/(double)pattenCount);

        }

    }

}
