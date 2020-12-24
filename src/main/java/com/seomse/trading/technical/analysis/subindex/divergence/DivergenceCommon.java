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

import java.util.List;

/**
 * 다이버전스 구현에 사용한 공통 메스드 모음
 * @author macle
 */
public class DivergenceCommon {
    static void addDivergenceIndex(DivergenceSignal divergenceIndex, List<DivergenceSignal> divergenceList  ){
        if(divergenceIndex != null){
            divergenceList.add(divergenceIndex);
        }
        
    }


}
