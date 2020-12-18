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

package com.seomse.trading.technical.analysis.subindex.divergence;

import com.seomse.trading.technical.analysis.candle.CandleStick;

/**
 * 다이버젼스
 * @author macle
 */
public class Divergence {
        
    
    public static final int MIN_LENGTH = 5;
    public static final double HOLD_RATE = 0.02;
    public static final int [] CANDLE_COUNT_ARRAY = {
            260 //5*52 52주 거래일수로 계산
            , 200
            , 150
            , 120
            , 90
            , 60
            , 30
            , 25
            , 20
            , 15
            , 10
    };

    public enum Type{
        REGULAR //일반 다이버젼스
        , HIDDEN // 히든 다이버젼스
        , EXAGGERATED //과장된 다이버 젼스
    }

    /**
     * 다이버젼스 신호 얻기
     * @param candleSticks 캔들 배열
     * @param subIndexArray 보조지표 배열
     * @return 다이버젼스 정보
     */
    public static DivergenceIndex signal(CandleStick[] candleSticks, double [] subIndexArray){

        DivergenceIndex lastDivergenceIndex  = null;

        int lastIndex = -1;

        //하락 다이버전스를 먼저 실행시키는건 혹시나 둘다 동시 출현시 하락에 대한 우선순위룰 두기 위함
        //동시출현할일은 없을 것 같지만 검증해보지는 않아서 이렇게 처리
        for (DivergenceSignal divergenceSignal : instance.signals){
            //하락 다이버젼스
            DivergenceIndex divergenceIndex = divergenceSignal.fall(candleSticks, subIndexArray);
            if(divergenceIndex == null){
                continue;
            }

            if(lastDivergenceIndex == null ){
                lastDivergenceIndex = divergenceIndex;
            }else{
                if(lastDivergenceIndex.index < divergenceIndex.index ){
                    lastDivergenceIndex = divergenceIndex;
                }
            }
        }
        
        for (DivergenceSignal divergenceSignal : instance.signals){
            //상승 다이버젼스
            DivergenceIndex divergenceIndex = divergenceSignal.rise(candleSticks, subIndexArray);

            if(divergenceIndex == null){
                continue;
            }

            if(lastDivergenceIndex == null ){
                lastDivergenceIndex = divergenceIndex;
            }else{
                if(lastDivergenceIndex.index < divergenceIndex.index ){
                    lastDivergenceIndex = divergenceIndex;
                }
            }
        }

        return lastDivergenceIndex;
    }

    private final DivergenceSignal [] signals;

    //동일 패키지
    private static final Divergence instance = new Divergence();


    /**
     * 생성자
     */
    private Divergence(){
        signals = new DivergenceSignal[3];
        signals[0] = new RegularDivergence();
        signals[1] = new HiddenDivergence();
        signals[2] = new ExaggeratedDivergence();

    }

}
