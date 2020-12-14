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

/**
 * @author macle
 */
public class Divergence {
    public enum Type{
        REGULAR //일반 다이버젼스
        , HIDDEN // 히든 다이버젼스
        , EXAGGERATED //과장된 다이버 젼스
    }

    /**
     * 다이버젼스 신호 얻기
     * @param priceArray 가격배열
     * @param subIndexArray 보조지표 배열
     * @return 다이버젼스 정보
     */
    public static DivergenceIndex signal(double [] priceArray, double [] subIndexArray){

        int lastIndex = -1;
        
        for (DivergenceSignal divergenceSignal : instance.signals){
            divergenceSignal.rise(priceArray, subIndexArray);


        }

        
        return null;
    }
    
    private static final Divergence instance = new Divergence();
    
    private final DivergenceSignal [] signals;
    
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
