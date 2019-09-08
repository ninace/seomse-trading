package com.seomse.trading.technical.analysis.pattern.upper.shadow;

import com.seomse.trading.technical.analysis.candle.Candlestick;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.technical.analysis.pattern.CandlePatternPoint;
import com.seomse.trading.technical.analysis.trend.line.TrendLine;

/**
 * <pre>
 *  파 일 명 : UpperShadowPattern.java
 *  설    명 : 위 그림자 캔들 공통부분
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.09.08
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class UpperShadowPattern {

    /**
     * 위 그림자캔들 유효성 검사
     * @param tradeCandle 캔들
     * @return 유효성 여부
     */
   public static boolean isValid(TradeCandle tradeCandle){

        //위 그림자가 아니면
        if(tradeCandle.getType() != Candlestick.Type.UPPER_SHADOW){
            return false;
        }

        //몸통길이 계산하기
        //몸통길이는 종가 - 시가
        //몸통 길이 (변화량의 절대값)
        double change = tradeCandle.changeAbs();

        //몸통이 아래꼬리보다 긴걸로 계산한다
        //양봉이면 아래꼬리는
        double upperTail = tradeCandle.getUpperTail();
        if(change*2.0 > upperTail){
            //위꼬리가 몸통의 2배보다 커야한다
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
     * @param trendLine 추세선
     * @param candles 캔들 배열
     * @param index 체크할 index
     * @param shortGapPercent 짧은 캔들 기준 확률
     * @return 패턴 발생 여부 및 정형점수 ( 발생하지 않을경우 null)
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
