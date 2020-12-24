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

package com.seomse.trading.technical.analysis.candle;

/**
 * @author macle
 */
public interface Candle {

    /**
     * 시가 얻기
     * @return 시가 (시작가)
     */
    double getOpen();

    /**
     * 종가 얻기
     * @return 종가
     */
    double getClose();

    /**
     * 고가 얻기
     * @return 고가
     */
    double getHigh();
    /**
     * 저가 얻기
     * @return 저가
     */
    double getLow();
}
