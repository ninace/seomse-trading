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
 * 다이버젼스 신호
 * @author macle
 */
public interface DivergenceSignal {

    /**
     * 상승 신호
     * @param candleSticks 캔들 배열
     * @param subIndexArray 보조지표 배열
     * @return 다이버젼스 정보
     */
    DivergenceIndex rise(CandleStick[] candleSticks, double [] subIndexArray);

    /**
     * 하락신호
     * @param candleSticks 캔들 배열
     * @param subIndexArray 보조지표 배열
     * @return 다이버젼스 정보
     */
    DivergenceIndex fall(CandleStick[] candleSticks, double [] subIndexArray);

}
