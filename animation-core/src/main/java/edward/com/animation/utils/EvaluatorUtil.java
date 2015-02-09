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

package edward.com.animation.utils;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;

import edward.com.animation.evaluators.BaseEvaluator;

public class EvaluatorUtil {

    private EvaluatorUtil(){
        new AssertionError();
    }

    public static ValueAnimator decorate(BaseEvaluator evaluator, ValueAnimator animator){
        return decorate(evaluator,animator,new BaseEvaluator.ErasingListener[0]);
    }

    public static ValueAnimator decorate(BaseEvaluator evaluator, ValueAnimator animator,@NonNull BaseEvaluator.ErasingListener ... listeners){
        BaseEvaluator t = evaluator;
        evaluator.setDuration(animator.getDuration());
        if(listeners!=null && listeners.length != 0)
            t.addErasingListeners(listeners);
        animator.setEvaluator(t);
        return animator;
    }

    public static PropertyValuesHolder decorate(BaseEvaluator evaluator, float duration, PropertyValuesHolder propertyValuesHolder){
        evaluator.setDuration(duration);
        propertyValuesHolder.setEvaluator(evaluator);
        return propertyValuesHolder;
    }

}
