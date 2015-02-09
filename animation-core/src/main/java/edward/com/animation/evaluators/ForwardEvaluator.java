/*
 * Copyright (C) 2015 YangEdward
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edward.com.animation.evaluators;

import android.support.annotation.NonNull;

public class ForwardEvaluator extends BaseEvaluator {

    private BaseEvaluator evaluator;
    private float percent;
    public ForwardEvaluator(@NonNull BaseEvaluator evaluator,float percent) {
        this.evaluator = evaluator;
        this.percent = percent;
    }

    @Override
    public float calculate(float time, float startValue, float offset, float duration) {
        if(time/duration > percent){
            return startValue + offset;
        }
        return evaluator.calculate(time,startValue,offset,percent*duration);
    }
}
