/*
 * Copyright 2014 YangEdward
 * Thank you for Toxic Bakery
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
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

import edward.com.animation.evaluators.AccelerateDecelerateEvaluator;
import edward.com.animation.evaluators.ElasticOvershootEvaluator;
import edward.com.animation.evaluators.OvershootEvaluator;

import static edward.com.animation.effects.AnimPropertyName.ALPHA;

public class Flip extends HasDirection implements HasAction {

    private Action action = Action.IN;

    public Flip(Direction direction){
        super(direction);
    }

    public Flip(@NonNull Action action,Direction direction){
        super(direction);
        this.action = action;
    }

    @Override
    public Animator[] getAnimators(View target) {
        return direction.getAnimators(target,this);
    }

    @Override
    protected Animator[] top(View target) {
        float from = 90;
        float to = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                to = from;
                from = 0;
                break;
        }
        return generate(target,from,to,AnimPropertyName.ROTATION_X);
    }

    @Override
    protected Animator[] left(View target) {
        float from = -90;
        float to = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                to = from;
                from = 0;
                break;
        }
        return generate(target,from,to,AnimPropertyName.ROTATION_Y);
    }

    @Override
    protected Animator[] right(View target) {
        float from = 90;
        float to = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                to = from;
                from = 0;
                break;
        }
        return generate(target,from,to,AnimPropertyName.ROTATION_Y);
    }

    @Override
    protected Animator[] bottom(View target) {
        float from = -90;
        float to = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                to = from;
                from = 0;
                break;
        }
        return generate(target,from,to,AnimPropertyName.ROTATION_X);
    }

    @Override
    public void setAction(@NonNull Action action) {
        this.action = action;
    }

    private Animator[] generate(View target,float from,float to,AnimPropertyName value){
        evaluator = new AccelerateDecelerateEvaluator();
        return new Animator[]{
                new AnimatorBuilder(target,duration,action).setAnimator(ALPHA)
                        .setEvaluator(evaluator)
                        .getAnimator(),
                new AnimatorBuilder(target,duration,action).setAnimatorNoAction(value,from,to)
                        .setEvaluator(new OvershootEvaluator())
                        .getAnimator()
        };
    }
}
