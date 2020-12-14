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
 * 1.일반 다이버전스
 * - 일반하락 다이버전스
 *  주가의 고점을 상승하고 있으나, 보조지표 고점은 하락하고 있는상태
 *  주가 고점이 상승하고 있으나, 보조지표 고점은 횡보하고 있는 상태도 하락 다이버전스임
 *  주가 고점이 횡보하고 있고, 보조지표 고점이 하락하고 있는 상태도 하락 다이버전스임.
 *
 * - 일반상승 다이버전스
 *  주가의 저점이 하락하고 있고, 보조지표 저점이 상승하고 있는 상태
 *  주가의 저점이 하락하고 있으니, 보조지표 저점은 횡보하고 있는 상태도 상승 다이버전스임.
 *  주가의 저점이 횡보하고 있고, 보조지표 저점이 상승하고 있는 상태도 상승 다이버전스임
 *  상승추세로 전환 가능성 높음
 *
 * @author macle
 */
public class RegularDivergence implements DivergenceSignal{
    @Override
    public DivergenceIndex rise(double[] priceArray, double[] subIndexArray) {
        return null;
    }

    @Override
    public DivergenceIndex fall(double[] priceArray, double[] subIndexArray) {
        return null;
    }
}
