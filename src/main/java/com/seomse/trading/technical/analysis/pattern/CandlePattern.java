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

import com.seomse.trading.PriceChangeType;
import com.seomse.trading.TrendChangeType;
import com.seomse.trading.technical.analysis.candle.candles.TradeCandles;

/**
 * 캔들형 패턴
 * @author macle
 */
public interface CandlePattern {


    /**
     * 캔들배열 설정
     * @param tradeCandles TradeCandles 캔들배열
     */
    void setCandles(TradeCandles tradeCandles);

    /**
     * 실시간 분석에 필요한 정보들을 초기화
     */
    void initRealTime();

    /**
     * 최근 발생 지점
     * 실시간 분석에 사용
     * @return CandlePatternPoint 최근 발생 지점 얻기
     */
    CandlePatternPoint  getLastPoint();

    /**
     * 캔들이 발생된 모든 지점 얻기
     * 시뮬레이터에 사용
     * @return CandlePatternPoint [] 캔슬이 발생된 모든 지점( 배열)
     */
    CandlePatternPoint [] getPoints();


    /**
     * 가격변화 예측유형
     * @return PriceChangeType 가격 변화 예층 유형( 상승, 하락, 보합)
     */
    PriceChangeType getPriceChangeType();

    /**
     * 추세 변환 예측 유형 얻기
     * @return TrendChangeType 추세 변환 예측유형 (지속, 반전)
     */
    TrendChangeType getTrendChangeType();
}
