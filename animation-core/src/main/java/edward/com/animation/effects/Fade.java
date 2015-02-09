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
import android.support.annotation.NonNull;
import android.view.View;

import static edward.com.animation.effects.AnimPropertyName.*;

/**
 * Now just for ViewPager,Fade In and Fade Out
 * Time 2015-2-1
 * */
public class Fade extends EffectHasDirection {

    public Fade(){
        super(null);
    }

    public Fade(Direction direction) {
        super(direction);
    }

    public Fade(Action action,Direction direction){
        super(direction,action);
    }

    @Override
    public Animator[] getAnimators(View target) {
        if (direction == null){
            return fade(target);
        }
        return direction.getAnimators(target,this);
    }

    private Animator[] fade(View target){
        return new Animator[]{
                new AnimatorBuilder(target,duration,action).setAnimator(ALPHA)
                        .getAnimator()
        };
    }

    @Override
    public Animator[] top(View target) {
        isParentNull(target);
        float fromY = -parent.getHeight()/4;
        //float fromY = -target.getHeight()/4;
        float toY = 0;
        switch (action){
            case OUT:
                toY = fromY;
                fromY = 0;
                break;
        }
        return generate(target,fromY,toY,TRANSLATION_Y);
    }

    @Override
    public Animator[] left(View target) {
        isParentNull(target);
        float fromY = -parent.getWidth()/4;
        float toY = 0;
        switch (action){
            case OUT:
                toY = fromY;
                fromY = 0;
                break;
        }
        return generate(target, fromY, toY, TRANSLATION_X);
    }

    @Override
    public Animator[] right(View target) {
        //float fromY = target.getWidth()/4;
        isParentNull(target);
        float fromY = parent.getWidth()/4;
        float toY = 0;
        switch (action){
            case OUT:
                toY = fromY;
                fromY = 0;
                break;
        }
        return generate(target, fromY, toY, TRANSLATION_X);
    }

    @Override
    public Animator[] bottom(View target) {
        isParentNull(target);
        float fromY = parent.getHeight()/4;
        //float fromY = target.getHeight()/4;
        float toY = 0;
        switch (action){
            case OUT:
                toY = fromY;
                fromY = 0;
                break;
        }
        return generate(target,fromY,toY,TRANSLATION_Y);
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
