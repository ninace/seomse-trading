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

package com.seomse.trading.technical.analysis.subindex.divergence;

import com.seomse.trading.technical.analysis.candle.Candle;
import com.seomse.trading.technical.analysis.candle.CandleStick;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.일반 다이버전스
 * - 일반하락 다이버전스
 *  주가의 고점을 상승하고 있으나, 보조지표 고점은 하락하고 있는상태
 *  주가 고점이 상승하고 있으나, 보조지표 고점은 횡보하고 있는 상태도 하락 다이버전스임
 *  주가 고점이 횡보하고 있고, 보조지표 고점이 하락하고 있는 상태도 하락 다이버전스임.
 *
 * - 일반상승 다이버전스
 *  주가의 저점이 하락하고 있고, 보조지표 저점이 상승하고 있는 상태
 *  주가의 저점이 하락하고 있으니, 보조지표 저점은 횡보하고 있는 상태도 상승 다이버전스임.
 *  주가의 저점이 횡보하고 있고, 보조지표 저점이 상승하고 있는 상태도 상승 다이버전스임
 *  상승추세로 전환 가능성 높음
 *
 * 참고자료
 * https://m.blog.naver.com/PostView.nhn?blogId=pengyou_&logNo=221267419722&targetKeyword=&targetRecommendationCode=1
 * @author macle
 */
public class RegularDivergence implements DivergenceSignalSearch {





    @Override
    public DivergenceSignal rise(Candle[] priceCandles, Candle [] subIndexCandles, double steadyRate, int candleCount) {


        // 최근에 주가의 저점이 하락하고 있는 구간 찾기
        //


        // 기간대비 하락률로 해야하나..
        // 기울기와 두점사이의 거리 활용하기
        // 기울기
        // https://terms.naver.com/entry.nhn?docId=3350308&cid=60210&categoryId=60210
        

        //01 케이스
        // 주가의 저점이 하락하고 있고, 보조지표 저점이 상승하고 있는 상태
//        List<DivergenceSignal> divergenceList = new ArrayList<>();
//
//        //캔들 저점 모음
//        List<Integer> candleIndexList = new ArrayList<>();
//
//
//        //캔들 건수로 봤을때 최저점인 지점 찾기
//        List<Integer> candleIndexList = new ArrayList<>();
//
//
//
//
//        int lastIndex = 0;
//        double lastLow = candleSticks[0].getLow();
//        for (int i = 1; i <candleSticks.length; i++) {
//            //저점이 낮아지는지 체크한다
//            if(lastIndex + candleCount < i  ){
//                if(continueIndexList.size() > 2) {
//                    DivergenceCommon.addDivergenceIndex(riseSubIndexLowUp(subIndexArray, continueIndexList, steadyRate,  candleCount), divergenceList);
//                }
//            }
//
//            //기간내 저점이 갱신된 정보
//            if(candleSticks[0].getLow() < lastLow){
//                continueIndexList.add(i);
//                lastLow = candleSticks[0].getLow();
//                lastIndex = i;
//            }
//        }
//
//
        return null;
    }

    //보조지표 저점 횡보 및 상승구간 찾기
    private DivergenceSignal riseSubIndexLowUp(double[] subIndexArray, List<Integer> indexList, double steadyRate, int candleCount){
        
        //전체 구간의 상승률
        //기간내 보조지표 저점 상승기간이 있는지


        for (int i = subIndexArray.length -2; i > -1 ; i--) {
            
        }
        






        indexList.clear();
        return null;
    }

 

    @Override
    public DivergenceSignal fall(Candle[] priceCandles, Candle [] subIndexCandles, double steadyRate, int candleCount) {
        return null;
    }
}
