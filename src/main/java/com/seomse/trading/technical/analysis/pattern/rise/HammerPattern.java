package com.seomse.trading.technical.analysis.pattern.rise;

import com.seomse.trading.technical.analysis.candle.candles.TradeCandles;
import com.seomse.trading.technical.analysis.pattern.CandlePattern;
import com.seomse.trading.technical.analysis.pattern.CandlePatternPoint;

/**
 * <pre>
 *  파 일 명 : HammerPattern.java
 *  설    명 : 망치형 캔들
 *            아래그림자 캔들의 한종류로 하락추세에서 아래그림자 캔들이 발생하면 망치형 캔들.
 *            망치형이 의미있는 상승반전 또는 조정 신호가 될려면 아랫꼬리가 몸통보다 길고 몸통은 약간 두꺼워야 한다.
 *            몸통이 음봉이든 양본이든 상관없이 망치형으로 부를 순 있으나 반드시 몸통이 양봉이었을때에만 상승 ㅂ나전 신호로 해설될 수 있다
 *            (음봉 망치형은 오히려 단기 하락을 부추김)
 *
 *
 *            필요기능 패턴발생지점, 발생스코어
 *            이후 데이터가 추가될때마다 즉각분석이 가능해야하므로
 *            이전 패턴발생 누적치 정보를 저장해야함 (실시간 분석기능 제공용)
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.06.17
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class HammerPattern implements CandlePattern {


    private TradeCandles tradeCandles;


    /**
     * 캔들 배열 설정
     * 캔들배열이 설정될
     * @param tradeCandles 캔들 배열
     */
    public void setCandles(TradeCandles tradeCandles){
        this.tradeCandles = tradeCandles;
    }

    private CandlePatternPoint lastPoint;


    /**
     * 마지막 지점 분석해 놓기
     */
    public void analysisLastPoint(){

    }

    /**
     * 최근 발생 지점
     * 실시간 분석에 사용
     * @return 최근 발생 지점 얻기
     */
    @Override
    public CandlePatternPoint  getLastPoint(){

        return lastPoint;
    }

    /**
     * 망치혈 캔들이 발생된 모든 지점 얻기
     * 시뮬레이터에 사용
     * @return 망치형 캔슬이 발생된 모든 지점( 배열)
     */
    @Override
    public CandlePatternPoint [] getPoints(){


        return CandlePatternPoint.EMPTY_POINT;
    }


}
