package com.seomse.trading.test;

import com.seomse.commons.file.FileUtil;
import com.seomse.jdbc.naming.JdbcNaming;

import java.util.List;

/**
 * <pre>
 *  파 일 명 : TradeFileMake.java
 *  설    명 : 거래데이터 파일로 생성
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.04
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class TradeFileMake {

    public static void main(String[] args) {



        String tradeFilePath = "meta/trades";

        List<TradeDataNo> tradeDataList = JdbcNaming.getObjList(TradeDataNo.class
                ,"DT_TRADE BETWEEN TO_DATE('20190101','YYYYMMDD') AND TO_DATE('20190721','YYYYMMDD') "
                ," DT_TRADE ASC");


        int size = tradeDataList.size();

        StringBuilder sb = new StringBuilder();
        for(int i=0 ;i<size ; i++){

            TradeDataNo tradeDataNo = tradeDataList.get(i);
            sb.append("\n").append(tradeDataNo.getTP_TRADE()).append(",").append(tradeDataNo.getDT_TRADE()).append(",").append(tradeDataNo.getPRC_COIN()).append(",").append(tradeDataNo.getVL_TRADE());



        }


        FileUtil.fileOutput(sb.toString(), tradeFilePath, false);












    }

}
