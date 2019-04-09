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

        if(timeGap < Times.HOUR_24){
            //24시간 보다 작은단위 gap 설정 유효성
            if( Times.HOUR_24 % timeGap == 0){
                return true;
            }else{
                return false;
            }
        }else{
            //그다음은 하루단위의 봉만 생성
            if(timeGap % Times.HOUR_24 == 0){
                return true;
            }else{
                return false;
            }
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
        if(timeGap < Times.HOUR_24){


            long gap = firstTradeTime - calendar.getTimeInMillis();

//            System.out.println("gap: " +  TimeUtil.getTimeValue(gap));

            long startTime = calendar.getTimeInMillis() + gap - gap%timeGap;

            return startTime;
        }else{


            return calendar.getTimeInMillis();
        }

    }


//        public static void main(String[] args) {
//
//
//            long time = 1554576718234L + Times.HOUR_1*4;
//
//            long timeGap = Times.HOUR_1*30;
//
//
//
//
//            //24시간은 표준시 문제로
//
//            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(getStartTime(timeGap, time ))));
//            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(time)));
//
//
//
//
//
//    }

}
