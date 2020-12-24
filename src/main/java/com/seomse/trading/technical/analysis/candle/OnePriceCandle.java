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
 * 단일가 캔들
 * 다이버전스에 활용됨
 * @author macle
 */
public class OnePriceCandle implements Candle{

    private final double price;

    /**
     * 생성자
     * @param price 단일가
     */
    public OnePriceCandle(double price){
        this.price = price;    
    }
    
    @Override
    public double getOpen() {
        return price;
    }

    @Override
    public double getClose() {
        return price;
    }

    @Override
    public double getHigh() {
        return price;
    }

    @Override
    public double getLow() {
        return price;
    }
}
