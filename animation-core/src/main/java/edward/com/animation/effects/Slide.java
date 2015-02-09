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

package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import edward.com.animation.evaluators.AccelerateEvaluator;
import edward.com.animation.evaluators.ForwardEvaluator;
import edward.com.animation.evaluators.InertiaEvaluator;
import edward.com.animation.evaluators.LinearEvaluator;

import static edward.com.animation.effects.AnimPropertyName.ALPHA;

public class Slide extends EffectHasDirection  {

    public Slide(@NonNull Direction direction) {
        super(direction);
    }

    public Slide(@NonNull Action action,@NonNull Direction direction){
        super(direction,action);
    }

    @Override
    public Animator[] getAnimators(@NonNull View target) {
        return direction.getAnimators(target,this);
    }

    @Override
    public Animator[] top(View target) {
        isParentNull(target);
        float from = -parent.getHeight();
        //float from = -(target.getTop() + target.getHeight());
        float to = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                from = 0;
                to = -target.getBottom();
                break;
        }
        return generate(target,from,to,AnimPropertyName.TRANSLATION_Y);
    }

    @Override
    public Animator[] left(View target) {
        isParentNull(target);
        float from = target.getLeft() - parent.getWidth();
        float to = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                from = 0;
                to = -target.getRight();
                break;
        }
        return generate(target,from,to,AnimPropertyName.TRANSLATION_X);
    }

    @Override
    public Animator[] right(View target) {
        isParentNull(target);
        float from = parent.getWidth() - target.getLeft();
        float to = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                to = from;
                from = 0;
                break;
        }
        return generate(target,from,to,AnimPropertyName.TRANSLATION_X);
    }

    @Override
    public Animator[] bottom(View target) {
        isParentNull(target);
        float from = parent.getHeight() + target.getTop();
        float to = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                from = 0;
                to = parent.getHeight() + target.getTop();
                break;
        }
        return generate(target,from,to,AnimPropertyName.TRANSLATION_Y);
    }

    private Animator[] generate(View target,float from,float to,AnimPropertyName value){
        return new Animator[]{
                new AnimatorBuilder(target,duration,action).setAnimator(ALPHA)
                        .getAnimator(),
                new AnimatorBuilder(target,duration,action).setAnimatorNoAction(value,from,to)
                        .getAnimator()
        };
    }
}
