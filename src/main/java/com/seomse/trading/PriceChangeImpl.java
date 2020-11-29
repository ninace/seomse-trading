/*
 * Copyright (C) 2020 Wigo Inc.
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

package com.seomse.trading;

/**
 * 가격 변화 구현체
 * @author macle
 */
public class PriceChangeImpl implements PriceChange{

    double close;
    double change;
    double changeRate;
    double previous;


    /**
     * 생성자
     * @param close 종가
     * @param change 변화가격
     * @param changeRate 변화율
     * @param previous 전일가
     */
    public PriceChangeImpl(
             double close
            , double change
            , double changeRate
            , double previous
    ){
        this.close = close;
        this.change = change;
        this.changeRate = changeRate;
        this.previous = previous;
    }

    @Override
    public double getClose() {
        return close;
    }

    @Override
    public double getChange() {
        return change;
    }

    @Override
    public double getChangeRate() {
        return changeRate;
    }

    @Override
    public double getPrevious() {
        return previous;
    }

}
