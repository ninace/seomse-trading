package com.seomse.trading.technical.analysis.pattern.lower.shadow;

import com.seomse.trading.technical.analysis.candle.Candlestick;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.technical.analysis.pattern.CandlePatternPoint;
import com.seomse.trading.technical.analysis.trend.line.TrendLine;

/**
 * <pre>
 *  파 일 명 : LowerShadowPattern.java
 *  설    명 : 아래 그림자 캔들 공통부분
 *
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.09.05
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class LowerShadowPattern {


    /**
     * 아래 그림자 캔들 유효성 검사
     * @param tradeCandle 캔들
     * @return 유효성 여부
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isValid(TradeCandle tradeCandle){

        //아래 그림자가 아니면
        if(tradeCandle.getType() != Candlestick.Type.LOWER_SHADOW){
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
     * 아래 그림자 캔들 정형 점수 생성
     * @param trendLine 추세선
     * @param candles 캔들 배열
     * @param index 체크할 index
     * @param shortGapPercent 짧은 캔들 기준 확률
     * @return 패턴 발생 여부 및 정형점수 ( 발생하지 않을경우 null)
     */
    public static CandlePatternPoint makePoint(TrendLine trendLine, TradeCandle [] candles, int index, double shortGapPercent){

        TradeCandle tradeCandle = candles[index];

        double trendLineScore= trendLine.score(candles, index, 7 , shortGapPercent);


        if(trendLineScore < 1.0){

            return null;
        }

        //하락추세점수 가중치 1.5점
        if(trendLineScore > 1.5){
            trendLineScore = 1.5;
        }

        //몸통길이 계산하기
        //몸통길이는 종가 - 시가
        //몸통 길이 (변화량의 절대값)
        double absChange = Math.abs(tradeCandle.change());

        //몸통이 아래꼬리보다 긴걸로 계산한다
        //양봉이면 아래꼬리는
        double lowerTail = tradeCandle.getLowerTail();


        double score = lowerTail/(absChange*2.0) * trendLineScore;
        return new CandlePatternPoint(candles[index], score);
    }


}
