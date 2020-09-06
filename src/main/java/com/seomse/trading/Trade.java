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

package com.seomse.trading;

/**
 * 거래정보
 * @author macle
 */
public class Trade {

    /**
     * 거래유형 정의
     */
    public enum Type{
        BUY //구매
        , SELL //판매
    }

    /**
     * 거래유형
     */
    private final Type type;

    /**
     * 거래량
     */
    private final double volume;

    /**
     * 가격
     */
    private final double price;

    /**
     * 시간
     */
    private final long time;


    /**
     * 생성자
     * @param type Type
     * @param price double
     * @param volume double
     * @param time long
     */
    public Trade(Type type, double price, double volume, long time ){
        this.type = type;
        this.price = price;
        this.volume = volume;
        this.time = time;
    }

    /**
     * 거래유형 얻기 BUY, SELL
     * @return Type 거래유형
     */
    public Type getType() {
        return type;
    }

    /**
     * 거래량 얻기
     * @return double 거래량
     */
    public double getVolume() {
        return volume;
    }

    /**
     * 가격얻기
     * @return double 가격
     */
    public double getPrice() {
        return price;
    }

    /**
     * 거래시간 얻기
     * @return long 시간
     */
    public long getTime() {
        return time;
    }


}
