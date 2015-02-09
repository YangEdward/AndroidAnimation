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

import static edward.com.animation.effects.AnimPropertyName.ALPHA;

public class Roll extends EffectHasDirection {


    public Roll(){
        super(Direction.TOP_LEFT);
    }

    public Roll(@NonNull Action action){
        super(Direction.TOP_LEFT,action);
    }
    public Roll(@NonNull Action action,@NonNull Direction direction){
        super(direction,action);
    }

    @Override
    public Animator[] getAnimators(@NonNull View target) {
        return direction.getAnimators(target,this);
    }

    @Override
    public Animator[] topLeft(View target) {
        float [] translationX = {-target.getWidth(),0};
        float [] rotation = {-120,0};
        switch (action){
            case IN:
                break;
            case OUT:
                translationX = new float[]{0,-target.getWidth()};
                rotation = new float[] {0,-120};
                break;
        }
        return generate(target,translationX,rotation);
    }

    @Override
    public Animator[] topRight(View target) {
        float [] translationX = {target.getWidth(),0};
        float [] rotation = {120,0};
        switch (action){
            case IN:
                break;
            case OUT:
                translationX = new float[]{0,target.getWidth()};
                rotation = new float[]{0,120};
                break;
        }
        return generate(target,translationX,rotation);
    }

    @Override
    public Animator[] bottomLeft(View target) {
        float [] translationX = {-target.getWidth(),0};
        float [] rotation = {120,0};
        switch (action){
            case IN:
                break;
            case OUT:
                translationX = new float[]{0,-target.getWidth()};
                rotation = new float[] {0,120};
                break;
        }
        return generate(target,translationX,rotation);
    }

    @Override
    public Animator[] bottomRight(View target) {
        float [] translationX = {target.getWidth(),0};
        float [] rotation = {-120,0};
        switch (action){
            case IN:
                break;
            case OUT:
                translationX = new float[]{0,target.getWidth()};
                rotation = new float[]{0,-120};
                break;
        }
        return generate(target,translationX,rotation);
    }

    @Override
    protected Animator[] top(View target) {
        return new Animator[0];
    }

    @Override
    protected Animator[] left(View target) {
        return new Animator[0];
    }

    @Override
    protected Animator[] right(View target) {
        return new Animator[0];
    }

    @Override
    protected Animator[] bottom(View target) {
        return new Animator[0];
    }


    private Animator[] generate(View target,float[] translationX,float[] rotation){
        return new Animator[]{
                new AnimatorBuilder(target,duration,action).setAnimator(ALPHA)
                        .getAnimator(),
                new AnimatorBuilder(target,duration,action).setAnimatorNoAction(AnimPropertyName.TRANSLATION_X,
                        translationX[0],translationX[1])
                        .getAnimator(),
                new AnimatorBuilder(target,duration,action).setAnimatorNoAction(AnimPropertyName.ROTATION,
                        rotation[0],rotation[1])
                        .getAnimator()
        };
    }
}
