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
package com.seomse.trading.technical.analysis.pattern;


import com.seomse.trading.technical.analysis.candle.TradeCandle;
import com.seomse.trading.technical.analysis.candle.candles.CandleChangeObserver;
import com.seomse.trading.technical.analysis.candle.candles.TradeCandles;

import java.util.ArrayList;
import java.util.List;


/**
 * 캔들 패턴 기본형
 * @author macle
 */
public abstract class CandlePatternDefault implements CandlePattern{


    protected TradeCandles tradeCandles;

    protected CandlePatternPoint lastPoint;

    protected TradeCandle lastCheckCandle = null;

   
    @Override
    public void setCandles(TradeCandles tradeCandles){
        this.tradeCandles = tradeCandles;
    }

    @Override
    public void initRealTime(){
        TradeCandle [] candles = tradeCandles.getCandles();

        if(candles.length == 0){
            return ;
        }
        lastCheckCandle = candles[candles.length-1];

        for(int i=candles.length-1 ; i > -1 ; i--){
            CandlePatternPoint point = getPoint(candles, i, tradeCandles.getShortGapRatio());
            if(point != null){
                lastPoint = point;
                return ;
            }
        }

        setObserver();
    }


    protected CandleChangeObserver candleChangeObserver = null;

    /**
     * 옵져버 설정
     */
    public void setObserver(){
        candleChangeObserver = (lastEndCandle, newCandle) -> changeLastCandle(lastEndCandle);
        tradeCandles.addChangeObserver(candleChangeObserver);
    }

    /**
     * 옵져버 제거
     */
    public void removeObserver(){
        if(candleChangeObserver == null){
            return ;
        }

        tradeCandles.removeObserver(candleChangeObserver);
    }


    /**
     * 마지막 캔들 지점 변경
     * 패턴발생시 패턴정보 객체 리턴
     * 패턴발생하지 않으면 null 리턴
     * @param lastEndCandle TradeCandle lastEndCandle
     */
    public void changeLastCandle(TradeCandle lastEndCandle) {
        if (lastEndCandle == null) {
            return;
        }

        if (lastEndCandle == lastCheckCandle) {
            return;
        }

        lastCheckCandle = lastEndCandle;

        TradeCandle[] candles = tradeCandles.getCandles();

        for (int i = candles.length - 1; i > -1; i--) {
            TradeCandle tradeCandle = candles[i];
            if(tradeCandle == lastEndCandle){
                CandlePatternPoint point = getPoint(candles, i, tradeCandles.getShortGapRatio());
                if(point != null){
                    //캔들 발생했을때 알림 받게 해야함
                    //매수 시점을 알려줘야함
                    lastPoint = point;
                }
                break;
            }
        }
    }

    
    @Override
    public CandlePatternPoint [] getPoints(){
        TradeCandle [] candles = tradeCandles.getCandles();
        List<CandlePatternPoint> pointList = null;

        for (int i = 5; i <candles.length ; i++) {
            CandlePatternPoint point = getPoint(candles, i, tradeCandles.getShortGapRatio());

            if(point == null)
                continue;

            if(pointList == null){
                pointList = new ArrayList<>();
            }
            pointList.add(point);
        }

        if(pointList==null){
            return CandlePatternPoint.EMPTY_POINT;
        }

        //noinspection ToArrayCallWithZeroLengthArrayArgument
        CandlePatternPoint [] result = pointList.toArray(new CandlePatternPoint[pointList.size()]);
        pointList.clear();
        return result;
    }

  
    @Override
    public CandlePatternPoint getLastPoint(){
        return lastPoint;
    }

    /**
     * 캔들의 배열이 바뀔 수 있으므로 array 로 직접 받음
     * 패턴결과 패턴이 유효하지 않을경우 null 을 리턴
     * @param candles TradeCandle [] 캔들 배열
     * @param index int 기준위치
     * @param shortGapRate double 짧은 캔들 기준 비율
     * @return CandlePatternPoint 패턴결과
     */
    public abstract CandlePatternPoint getPoint(TradeCandle [] candles, int index, double shortGapRate);
}
