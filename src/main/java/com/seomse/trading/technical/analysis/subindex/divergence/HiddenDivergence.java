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
 * 2. 히든 다이버전스
 *  - 히든 하락 다이버전스
 *   주가의 고점이 낮아지고 보조지표 고점이 상승하는 현상을 히든 하락 다이버전스라고 함
 *   하락추세로 전환 가능성 높음
 *
 *  - 히든 상승 다이버전스
 *   주가의 저점이 높아지고, 보조지표 저점이 하락하는 현상을 히든 상승 다이버전스라고 함
 *   상승추세로 전환 가능성 높음
 * 참고자료
 * https://m.blog.naver.com/PostView.nhn?blogId=pengyou_&logNo=221267419722&targetKeyword=&targetRecommendationCode=1
 * @author macle
 */
public class HiddenDivergence implements DivergenceSignal{
    @Override
    public DivergenceIndex rise(CandleStick[] candleSticks, double[] subIndexArray) {
        return null;
    }

    @Override
    public DivergenceIndex fall(CandleStick[] candleSticks, double[] subIndexArray) {
        return null;
    }
}
