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

public class Rotate extends EffectHasDirection {

    private float pivotX;
    private float pivotY;

    public Rotate(){
        super(null);
    }

    public Rotate(Action action) {
        super(null,action);
    }

    public Rotate(Direction direction) {
        super(direction);
    }

    public Rotate(Action action,Direction direction){
        super(direction,action);
    }

    @Override
    public Animator[] getAnimators(@NonNull View target) {
        if (direction == null){
            return rotate(target);
        }
        return direction.getAnimators(target,this);
    }

    private Animator[] rotate(View target){
        pivotX = target.getWidth()/2;
        pivotY = target.getHeight()/2;
        float from = 200,to = 0;
        return generate(target,from,to,AnimPropertyName.ROTATION);
    }

    @Override
    public Animator[] top(View target) {
        pivotX = target.getLeft();
        pivotY = target.getTop();
        float from = -90,to = 0;
        return generate(target,from,to,AnimPropertyName.ROTATION);
    }

    @Override
    public Animator[] topLeft(View target) {
        pivotX = target.getLeft();
        pivotY = target.getTop();
        float from = 90,to = 0;
        return generate(target,from,to,AnimPropertyName.ROTATION);
    }

    @Override
    public Animator[] topRight(View target) {
        pivotX = target.getRight();
        pivotY = target.getTop();
        float from = 90,to = 0;
        return generate(target,from,to,AnimPropertyName.ROTATION);
    }

    @Override
    public Animator[] left(View target) {
        pivotX = target.getLeft();
        pivotY = target.getBottom();
        float from = -90,to = 0;
        return generate(target,from,to,AnimPropertyName.ROTATION);
    }

    @Override
    public Animator[] right(View target) {
        pivotX = target.getRight();
        pivotY = target.getTop();
        float from = -90,to = 0;
        return generate(target,from,to,AnimPropertyName.ROTATION);
    }

    @Override
    public Animator[] bottom(View target) {
        pivotX = target.getRight();
        pivotY = target.getBottom();
        float from = -90,to = 0;
        return generate(target,from,to,AnimPropertyName.ROTATION);
    }

    @Override
    public Animator[] bottomLeft(View target) {
        pivotX = target.getLeft();
        pivotY = target.getBottom();
        float from = 90,to = 0;
        return generate(target,from,to,AnimPropertyName.ROTATION);
    }

    @Override
    public Animator[] bottomRight(View target) {
        pivotX = target.getRight();
        pivotY = target.getBottom();
        float from = 90,to = 0;
        return generate(target,from,to,AnimPropertyName.ROTATION);
    }

    private Animator[] generate(View target,float from,float to,AnimPropertyName value){
        if(action == Action.OUT){
            float temp = to;
            to = from;
            from = temp;
        }
        return new Animator[]{
                new AnimatorBuilder(target,duration,action).setAnimator(ALPHA)
                        .getAnimator(),
                new AnimatorBuilder(target,duration,action).setAnimatorNoAction(value,from,to)
                        .getAnimator(),
                new AnimatorBuilder(target).setAnimatorNoAction(AnimPropertyName.PIVOT_X,pivotX,pivotX)
                        .getAnimator(),
                new AnimatorBuilder(target).setAnimatorNoAction(AnimPropertyName.PIVOT_Y,pivotY,pivotY)
                        .getAnimator()
        };
    }
}
