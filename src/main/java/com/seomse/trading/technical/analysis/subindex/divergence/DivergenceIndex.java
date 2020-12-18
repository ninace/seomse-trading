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

import com.seomse.trading.PriceChangeType;

/**
 * 다이버젼스 발생지점
 * @author macle
 */
public class DivergenceIndex {

    //가겨변화유형
    //상승하락
    PriceChangeType priceChangeType;

    Divergence.Type type;

    int index;

    int length;
    
    int gap;
    /**
     * 가격변화유형
     * @return 상승, 하락
     */
    public PriceChangeType getPriceChangeType() {
        return priceChangeType;
    }

    /**
     * 다이버젼스 유형
     * @return 일반, 히든, 과장된
     */
    public Divergence.Type getType() {
        return type;
    }

    /**
     * 최근 크로스 발생지점 얻기
     * @return 최근 크로스 발생지점
     */
    public int index() {
        return index;
    }

    /**
     * 다이버 젼스 길이  다이버젼스 시작점부터 의 길이
     * @return 다이버젼스 길이
     */
    public int length() {
        return length;
    }

    /**
     * 마지막 발생시점으로 부터의 거리 얻기
     * @return 마지막 발생시점으로 부터의 거리
     */
    public int gap() {
        return gap;
    }
}
