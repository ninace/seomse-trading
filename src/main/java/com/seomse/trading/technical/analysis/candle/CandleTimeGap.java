package com.seomse.trading.technical.analysis.candle;

import com.seomse.trading.time.Times;

import java.util.Calendar;

/**
 * <pre>
 *  파 일 명 : CandleTimeGap.java
 *  설    명 : 시간갭
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.03
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class CandleTimeGap {



    public static final long [] DEFAULT_MINUTES = {

            Times.MINUTE_1
            , Times.MINUTE_2
            , Times.MINUTE_3
            , Times.MINUTE_4
            , Times.MINUTE_5
            , Times.MINUTE_6
            , Times.MINUTE_10
            , Times.MINUTE_12
            , Times.MINUTE_15
            , Times.MINUTE_30
    };


    public static final long [] DEFAULT_HOURS = {
            Times.HOUR_1 //1시간
            , Times.HOUR_2 // 2시간
            , Times.HOUR_3 // 3시간
            , Times.HOUR_4 // 4시간
            , Times.HOUR_6 // 6시간
            , Times.HOUR_12 // 12시간
    };


    public static final long [] DEFAULT_DAYS = {
            Times.DAY_1
            , Times.DAY_3
            , Times.DAY_5
    };


    public static final long [] DEFAULT_SCALPING =makeScalpingTime();


    private static long [] makeScalpingTime(){
        long [] scalpingTimes = new long[DEFAULT_MINUTES.length + DEFAULT_HOURS.length + DEFAULT_DAYS.length + 1];
        System.arraycopy(DEFAULT_MINUTES, 0, scalpingTimes, 0, DEFAULT_MINUTES.length);
        System.arraycopy(DEFAULT_HOURS, 0, scalpingTimes, DEFAULT_MINUTES.length, DEFAULT_HOURS.length);
        System.arraycopy(DEFAULT_DAYS, 0, scalpingTimes, DEFAULT_MINUTES.length + DEFAULT_HOURS.length, DEFAULT_DAYS.length);
        scalpingTimes[scalpingTimes.length-1] = Times.WEEK_1;
        return scalpingTimes;
    }



    /**
     * 유효한 설정인지 체크
     * @param timeGap timeGap
     * @return timeGap 유효성
     */
    public static boolean valid(long timeGap){

//        if(timeGap < Times.HOUR_1){
//            //1시간 보다 작은단위 gap 설정 유효성
//            if( Times.HOUR_1 % timeGap == 0){
//                return true;
//            }else{
//                return false;
//            }
//        }else

        if(timeGap < Times.DAY_1){
            //24시간 보다 작은단위 gap 설정 유효성
            return Times.DAY_1 % timeGap == 0;
        }else{
            //그다음은 하루단위의 봉만 생성
            return timeGap % Times.DAY_1 == 0;
        }

    }

    /**
     * 처음 시작할때의 시작시간 얻기
     * @param timeGap timeGap
     * @param firstTradeTime tradeStartTime
     * @return startTime
     */
    public static long getStartTime(long timeGap, long firstTradeTime){

//        if(timeGap < Times.HOUR_1){
//            long hourStart = firstTradeTime - firstTradeTime%Times.HOUR_1;
//
//            long gap = firstTradeTime - hourStart;
//
//
//            long startTime = hourStart + gap - gap%timeGap;
//
//            return startTime;
//
//        }else

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(firstTradeTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if(timeGap < Times.DAY_1){
            long gap = firstTradeTime - calendar.getTimeInMillis();
            return calendar.getTimeInMillis() + gap - gap%timeGap;
        }else{
            //이건 주봉 월봉 년봉 쓰기시작하면 하기
            //우선은 일단위 봉생성 타임만 저장
            //주
//            calendar.set(Calendar.DAY_OF_WEEK, 0);
            //월
//            calendar.set(Calendar.DAY_OF_WEEK, 0);
            //년
//            calendar.set(Calendar.DAY_OF_YEAR, 1);
            return calendar.getTimeInMillis();
        }

    }

}
