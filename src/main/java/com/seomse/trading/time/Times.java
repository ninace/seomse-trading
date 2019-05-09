package com.seomse.trading.time;
/**
 * <pre>
 *  파 일 명 : Times.java
 *  설    명 : 시간관련 공통정보
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.03
 *  버    전 : 1.1
 *  수정이력 : 2019.05
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
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
