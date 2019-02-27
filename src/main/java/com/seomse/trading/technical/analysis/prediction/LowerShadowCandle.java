

package com.seomse.trading.technical.analysis.prediction;

import com.seomse.trading.technical.analysis.candle.Candlestick;
import java.util.List;

/**
 * <pre>
 *  파 일 명 : LowerShadowCandle.java
 *  설    명 : 아래그림자 캔들
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.01
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class LowerShadowCandle {

    //망치형이 의미있는 상승 반전 또는 조정 신호가 될려면 아랫꼬리가 몸통보다 길고 몸통은 약간 두꺼워야 한다
    //몸통이 음봉이든 양봉이든 상관없이 망치형으로 부를 순 있으나 반드시 음봉이 양봉이었을때만 항승 반전 신호로 해석 될 수 있다( 음봉 망치형은 오히려 단기 하락을 부추김)
    public <T extends Candlestick> List<CandlePrediction> signalRise(T [] candleArray){





        return null;
    }





}
