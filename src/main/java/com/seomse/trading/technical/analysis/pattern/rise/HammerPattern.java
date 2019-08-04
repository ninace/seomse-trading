package com.seomse.trading.technical.analysis.pattern.rise;

import com.seomse.trading.technical.analysis.candle.Candlestick;
import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.technical.analysis.candle.candles.TradeCandles;
import com.seomse.trading.technical.analysis.pattern.CandlePattern;
import com.seomse.trading.technical.analysis.pattern.CandlePatternPoint;

/**
 * <pre>
 *  파 일 명 : HammerPattern.java
 *  설    명 : 망치형 캔들
 *            아래그림자 캔들의 한종류로 하락추세에서 아래그림자 캔들이 발생하면 망치형 캔들.
 *            망치형이 의미있는 상승반전 또는 조정 신호가 될려면 아랫꼬리가 몸통보다 길고 몸통은 약간 두꺼워야 한다.
 *            몸통이 음봉이든 양본이든 상관없이 망치형으로 부를 순 있으나 반드시 몸통이 양봉이었을때에만 상승 반전 신호로 해설될 수 있다
 *            (음봉 망치형은 오히려 단기 하락을 부추김)
 *
 *
 *            필요기능 패턴발생지점, 발생스코어
 *            이후 데이터가 추가될때마다 즉각분석이 가능해야하므로
 *            이전 패턴발생 누적치 정보를 저장해야함 (실시간 분석기능 제공용)
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.07.22
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
        //최신 데이터 분석에는 마지막 지점만 필요하므로 마지막 지점만 분석해놓는다
        //초기 생성할때 실행


    }

    /**
     * 최근 발생 지점
     * 실시간 분석에 사용
     * @return 최근 발생 지점 얻기
     */
    @Override
    public CandlePatternPoint  getLastPoint(){
        //패턴 발생한 마지막 지점 얻기

        return lastPoint;
    }

    /**
     * 망치혈 캔들이 발생된 모든 지점 얻기
     * 시뮬레이터에 사용
     * @return 망치형 캔슬이 발생된 모든 지점( 배열)
     */
    @Override
    public CandlePatternPoint [] getPoints(){
        TradeCandle [] candles = tradeCandles.getCandles();


        for (int i = 5; i <candles.length ; i++) {
            TradeCandle tradeCandle = candles[i];
            //아래그림자가 아니면
            if(tradeCandle.getType() != Candlestick.Type.LOWER_SHADOW){
                continue;
            }

//            TradeCandle lastCandle = candles[i-1];

            //과거의 모양이 지속적인 하락이었는 지를 인식하기
            //시점의 가격이 마지막 가격보다 낮으면 음봉
            if(tradeCandle.getOpen() > tradeCandle.getClose()){
                //양봉이 아니면
                continue;
            }

            //몸통길이 계산하기


        }

        return CandlePatternPoint.EMPTY_POINT;
    }



    public static CandlePatternPoint getPoint(TradeCandle [] candles, int index){

        TradeCandle tradeCandle = candles[index];
        //아래그림자가 아니면
        if(tradeCandle.getType() != Candlestick.Type.LOWER_SHADOW){
            return null;
        }

//            TradeCandle lastCandle = candles[i-1];


        //시점의 가격이 마지막 가격보다 낮으면 음봉
        if(tradeCandle.getOpen() > tradeCandle.getClose()){
            //양봉이 아니면
            return null;
        }

        //몸통길이 계산하기
        //몸통길이는 종가 - 시가

        //몸통 길이
        double trunkLength = tradeCandle.getClose() - tradeCandle.getOpen();


        //아래 꼬리길이
        double lowerTailLength = 0.0;


        //꼬리는 시가나 종가중 낮은것 - 저가 = 시가 종가
        //양봉이어야 하므로







        //과거의 모양이 지속적인 하락이었는 지를 인식하기


        return null;
    }


}
