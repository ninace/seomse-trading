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

package com.seomse.trading.technical.analysis.subindex.rsi;

import com.seomse.trading.PriceChangeRate;
import com.seomse.trading.technical.analysis.subindex.cross.Cross;
import com.seomse.trading.technical.analysis.subindex.cross.CrossIndex;

import java.util.Arrays;

/**
 * rsi 와 signal 배열정보
 * @author macle
 */
public class RsiSignalArray {

    private int n;
    private int signalN;
    //배열길이
    private int length = -1;

    /**
     * 생성자
     */
    public RsiSignalArray(){
        n = RSI.DEFAULT_N;
        signalN = RSI.DEFAULT_SIGNAL;
    }

    /**
     * RSI를 구성하는 n의 기간 실정
     * @param n rsi 구성할때 사용하는 수
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * signal을 구성할때 사용하는 수
     * @param signalN signal 구성에 사용되는 RSI 수
     */
    public void setSignalN(int signalN) {
        this.signalN = signalN;
    }

    /**
     * 만들고자 하는 배열의수
     * 설정하지 않으면 만들 수 잇는 최대수의 배열을 자동으로 산출한다
     * @param length 배열길이
     */
    public void setLength(int length) {
        this.length = length;
    }


    private double [] rsiArray;
    private double [] signalArray;

    /**
     * 가격변화 배열을 활용한 rsi와 signal 배열 생성
     * @param priceChangeRates 가격변화 배열
     */
    public void make(PriceChangeRate[] priceChangeRates){
        double [] doubles = new double[priceChangeRates.length];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = priceChangeRates[i].getChangeRate();
        }
        make(doubles);
    }

    /**
     * 가격변화 배열을 활용한 rsi와 signal 배열 생성
     * @param priceChangeRates 가격변화 배열
     */
    public void make(double [] priceChangeRates){

        //만들 수 있는 배열의 수 구하기
        int makeLength = priceChangeRates.length - n - signalN + 2;
        if(length != -1 && makeLength > length){
            makeLength = length;
        }


        double [] tempRsiArray = RSI.getScores(priceChangeRates, n ,makeLength+ signalN - 1);
        signalArray = RSI.getSignal(tempRsiArray, signalN, makeLength);

        this.rsiArray = Arrays.copyOfRange(tempRsiArray, signalN - 1, tempRsiArray.length);
        

    }

    /**
     * rsi 배열 얻기
     * @return rsi array
     */
    public double[] getRsiArray() {
        return rsiArray;
    }

    /**
     * rsi signal 배열 얻기
     * @return signal array
     */
    public double[] getSignalArray() {
        return signalArray;
    }

    /**
     * 골든 크로스 혹은 데드크로스 관련정보 생성
     * 없을경우 null
     * @param rate 돌파를 확인하는 비율 (겹친 정도로는 돌파로 보기 어려움) 백분율 percent
     * @return 골든크로스 혹인 데드 크로스 관련정보
     */
    public CrossIndex cross(double rate){
        return Cross.getIndex(rsiArray, signalArray, rate);
    }

    /**
     *
     * @return last rsi
     */
    public double getLastRsi(){
        return rsiArray[rsiArray.length-1];
    }

    /**
     * 
     * @return last signal
     */
    public double getLastSignal(){
        return signalArray[signalArray.length-1];
    }

}
