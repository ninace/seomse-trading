/*
 * Copyright (C) 2020 Wigo Inc.
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

package com.seomse.trading.technical.analysis.util;

import com.seomse.trading.PriceChangeRate;
import com.seomse.trading.technical.analysis.candle.Candle;

/**
 * candle 에서 사용하는 정보 일부를 double 형으로 변환 시켜주는 유틸성 클래스
 * @author macle
 */
public class CandleDoubleChange {


    /**
     * 종가를 double 형 배열로 만들어서 돌려준다
     * @param candles 캔들 배열
     * @return 종가 double 배열
     */
    public static double [] getCloseArray(Candle[] candles){
        double [] doubles = new double[candles.length];
        
        for (int i = 0; i <doubles.length ; i++) {
            doubles[i] = candles[i].getClose();
        }
        return doubles;

    }

    /**
     * 가격 변화율 배열로 변환
     * @param priceChangeRateArray 가격 변화율 배열
     * @return 가격 변화율 double 배열
     */
    public static double [] getChangeRateArray(PriceChangeRate[] priceChangeRateArray){
        double [] doubles = new double[priceChangeRateArray.length];

        for (int i = 0; i <doubles.length ; i++) {
            doubles[i] = priceChangeRateArray[i].getChangeRate();
        }
        return doubles;

    }


}


