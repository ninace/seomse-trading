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

package com.seomse.trading.technical.analysis.subindex.cross;

/**
 *
 * 골든 크로스 데드 크로스 잡기
 * @author macle
 */
public class Cross {
    public enum Type{
        GOLDEN // 골든크로스
        , DEAD // 데드크로스
    }

    /**
     * 크로스 지점 얻기
     * shot 5 이면 long 은 10 15 20 등.
     * 5일 이평선과 20일 이평선의 경우를 생각하면 됨
     * 두 배열의 크기는 반드시 일치 할것
     * @param shotArray 짧은 배열
     * @param longArray 긴배열
     * @return 크로스 발생 지점과 위치
     */
    public static CrossIndex getIndex(double [] shotArray, double [] longArray ){


        for (int i = longArray.length -1 ; i > 2 ; i--) {

            if(shotArray[i] > longArray[i]){
                //상향돌파 골든크로스

                int length = length(longArray, shotArray, i);

                if(length == -1){
                    continue;
                }

                CrossIndex crossIndex = new CrossIndex();
                crossIndex.type = Type.GOLDEN;
                crossIndex.index = i;
                crossIndex.length = length;
                return crossIndex;


            }else if(shotArray[i] < longArray[i]){
                //하향돌바 데드크로스
                int length = length(shotArray, longArray, i);

                if(length == -1){
                    continue;
                }
                CrossIndex crossIndex = new CrossIndex();
                crossIndex.type = Type.DEAD;
                crossIndex.index = i;
                crossIndex.length = length;
                return crossIndex;
            }

        }

        return null;
    }

    /**
     *
     * @param small 작은값
     * @param large 큰값
     * @param index 기준위치
     * @return 길이, 유효하지 않을경우 -1
     */
    private static int length(double [] small, double [] large, int index){

        int count = 0;

        int last = index;
        for (int i = index-1; i > -1 ; i--) {
            //큰값이 작아야함
            if(large[i] > small[i]){
                break;
            }

            if(large[i] < small[i]){
                count++;
            }
            last = i;
        }

        if(count < 3){
            return -1;
        }

        return index - last;
    }





}
