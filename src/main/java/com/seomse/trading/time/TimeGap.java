package com.seomse.trading.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 *  파 일 명 : TimeGap.java
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
public class TimeGap {


    /**
     * 유효한 설정인지 체크
     * @param timeGap timeGap
     * @return timeGap 유효성
     */
    public static boolean valid(long timeGap){

        if(timeGap < Times.HOUR_1){
            //1시간 보다 작은단위 gap 설정 유효성
            if( Times.HOUR_1 % timeGap == 0){
                return true;
            }else{
                return false;
            }
        }else if(timeGap < Times.HOUR_24){
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
     * @param startTime startTime
     * @return startTime
     */
    public static long getStartTime(long timeGap, long startTime){

        if(timeGap < Times.HOUR_1){
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(startTime);


            calendar.add(Calendar.HOUR_OF_DAY, 20);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);



            System.out.println(hour);


            calendar.getTime();

        }







        return 0;
    }

    public static void main(String[] args) {


        getStartTime(60000L, 1554576718234L);


        Date date  = new Date(1554576718234L);


       ;


        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date));





    }



}
