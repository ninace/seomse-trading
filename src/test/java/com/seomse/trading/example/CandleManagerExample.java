package com.seomse.trading.example;

import com.seomse.commons.utils.ExceptionUtil;
import com.seomse.trading.technical.analysis.candle.CandleManager;
import com.seomse.trading.technical.analysis.candle.CandleTimeGap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * <pre>
 *  파 일 명 : CandleManagerExample.java
 *  설    명 : CandleManager 활용
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.05
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @atuhor Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class CandleManagerExample {


    private static final Logger logger = LoggerFactory.getLogger(CandleManagerExample.class);


    public static void main(String[] args) {


        CandleManager candleManager = new CandleManager(CandleTimeGap.DEFAULT_SCALPING);

        String tradeFilePath = "meta/trades";

        BufferedReader br = null;

        try{
            String line;
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(tradeFilePath))));

            while ((line = br.readLine()) != null) {




                System.out.println(line);
            }

        }catch(Exception e){
            logger.error(ExceptionUtil.getStackTrace(e));
        }finally{
            try{if(br != null)br.close();}catch(Exception e){logger.error(ExceptionUtil.getStackTrace(e));}
        }





    }


}
