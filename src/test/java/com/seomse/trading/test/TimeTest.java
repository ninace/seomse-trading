package com.seomse.trading.test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 *  파 일 명 : TradeDataNo.java
 *  설    명 : 거래데이터 정보
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.03
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class TimeTest {

    public static void main(String[] args) {


        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(0)));

    }


}
