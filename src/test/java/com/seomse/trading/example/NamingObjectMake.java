
package com.seomse.trading.example;

import com.seomse.commons.config.Config;
import com.seomse.jdbc.naming.JdbcNaming;

/**
 * <pre>
 *  파 일 명 : NamingObjectMaker.java
 *  설    명 : NamingObject 생성
 *
 *  작 성 자 : macle
 *  작 성 일 : 2017.10
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */
public class NamingObjectMake {

	public static void main(String [] args){
		//설정 로드용
		//이상하네...
		try{
			Config.getConfig("a");
			String tableName = "TB_COIN_TRADE_DATA_BM";
			System.out.println("@Table(name=\"" +  tableName+ "\")\n");
			//다른DB사용시
	//		Connection conn =  ConnectionFactory.newConnection("oracle", "jdbc:oracle:thin:@192.168.0.24:1521:orcl", "yeonie", "xfJieurNUYcA");
	//		System.out.println(JdbcNaming.makeObjectValue(conn, tableName));

			System.out.println(JdbcNaming.makeObjectValue(tableName));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
