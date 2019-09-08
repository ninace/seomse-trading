package com.seomse.trading.technical.analysis.pattern.upper.shadow;

import com.seomse.trading.technical.analysis.candle.Candlestick;
import com.seomse.trading.technical.analysis.candle.TradeCandle;

/**
 * <pre>
 *  파 일 명 : UpperShadowPattern.java
 *  설    명 : 위 그림자 캔들 공통부분
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


}
