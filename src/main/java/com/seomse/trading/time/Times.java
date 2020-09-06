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
package com.seomse.trading.time;

/**
 * 트레이딩에서의 기준 시간정보
 * @author macle
 */
public class Times {


    //분정리
    public static final long MINUTE_1 = 1000L*60L;
    public static final long MINUTE_2 = MINUTE_1 * 2L;
    public static final long MINUTE_3 = MINUTE_1 * 3L;
    public static final long MINUTE_4 = MINUTE_1 * 4L;
    public static final long MINUTE_5 = MINUTE_1 * 5L;
    public static final long MINUTE_6 = MINUTE_1 * 6L;
    public static final long MINUTE_10 = MINUTE_1 * 10L;
    public static final long MINUTE_12 = MINUTE_1 * 12L;
    public static final long MINUTE_15 = MINUTE_1 * 15L;
    public static final long MINUTE_30 = MINUTE_1 * 30L;



    //시간정리
    public static final long HOUR_1 = MINUTE_1 * 60L;
    public static final long HOUR_2 = HOUR_1 * 2L;
    public static final long HOUR_3 = HOUR_1 * 3L;
    public static final long HOUR_4 = HOUR_1 * 4L;
    public static final long HOUR_6 = HOUR_1 * 6L;
    public static final long HOUR_12 = HOUR_1 * 12L;

    //일정리
    public static final long DAY_1 = HOUR_1 * 24L;
    public static final long DAY_3= DAY_1 * 3L;
    public static final long DAY_5= DAY_1 * 5L;


    //주
    public static final long WEEK_1= DAY_1 * 7L;


}
