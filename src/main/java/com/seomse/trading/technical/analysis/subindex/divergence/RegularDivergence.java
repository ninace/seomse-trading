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
public class RegularDivergence implements DivergenceSignal{





    @Override
    public DivergenceIndex rise(CandleStick[] candleSticks, double[] subIndexArray) {


        // 최근에 주가의 저점이 하락하고 있는 구간 찾기
        //


        // 기간대비 하락률로 해야하나..
        // 기울기와 두점사이의 거리 활용하기
        // 기울기
        // https://terms.naver.com/entry.nhn?docId=3350308&cid=60210&categoryId=60210



        //긴구간 부터 체크한다
        for(int candleCount : Divergence.CANDLE_COUNT_ARRAY){



            if(candleCount >= candleSticks.length + 5){
                continue;
            }



            List<Integer> indexList = new ArrayList<>();

            for (int i = 0; i <candleSticks.length; i++) {

            }
            


            //구간별 최저점 찾기

        }




        CandleStick candleStick = candleSticks[candleSticks.length-1];
        for (int i = candleSticks.length - 2; i > -1 ; i--) {
            candleSticks[i].getLow();


        }

        return null;
    }

    @Override
    public DivergenceIndex fall(CandleStick[] candleSticks, double[] subIndexArray) {
        return null;
    }
}
