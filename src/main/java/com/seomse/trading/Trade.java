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
 * <pre>
 *  파 일 명 : Trade.java
 *  설    명 : 거래정보
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.01
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
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
    private Type type;

    /**
     * 거래량
     */
    private double volume;

    /**
     * 가격
     */
    private double price;

    /**
     * 시간
     */
    private long time;

    /**
     * 생성자
     * @param type 유형
     * @param price 가격
     * @param volume 거래량
     */
    public Trade(Type type, double price, double volume, long time ){
        this.type = type;
        this.price = price;
        this.volume = volume;
        this.time = time;
    }

    /**
     * 거래유형 얻기 BUY, SELL
     * @return 거래유형
     */
    public Type getType() {
        return type;
    }

    /**
     * 거래량 얻기
     * @return 거래량
     */
    public double getVolume() {
        return volume;
    }

    /**
     * 가격얻기
     * @return 가격
     */
    public double getPrice() {
        return price;
    }

    /**
     * 거래시간 얻기
     * @return 시간
     */
    public long getTime() {
        return time;
    }


}
