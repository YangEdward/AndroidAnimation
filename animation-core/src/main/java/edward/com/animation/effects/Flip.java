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
        float [] alpha = {0.25f, 0.5f, 0.75f, 1};
        float [] rotationX = {90, -15, 15, 0};
        switch (action){
            case IN:
                break;
            case OUT:
                alpha = new float[]{1,0};
                rotationX = new float[]{0,90};
                break;
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(target, "alpha", alpha),
                ObjectAnimator.ofFloat(target,"rotationX",rotationX),
        };
    }

    @Override
    protected Animator[] left(View target) {
        float [] alpha = {0.25f, 0.5f, 0.75f, 1};
        float [] rotationY = {-90, 15, -15, 0};
        switch (action){
            case IN:
                break;
            case OUT:
                alpha = new float[]{1,0};
                rotationY = new float[]{0,-90};
                break;
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(target, "alpha", alpha),
                ObjectAnimator.ofFloat(target,"rotationY",rotationY),
        };
    }

    @Override
    protected Animator[] right(View target) {
        float [] alpha = {0.25f, 0.5f, 0.75f, 1};
        float [] rotationY = {90, -15, 15, 0};
        switch (action){
            case IN:
                break;
            case OUT:
                alpha = new float[]{1,0};
                rotationY = new float[]{0,90};
                break;
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(target, "alpha", alpha),
                ObjectAnimator.ofFloat(target,"rotationY",rotationY),
        };
    }

    @Override
    protected Animator[] bottom(View target) {
        float [] alpha = {0.25f, 0.5f, 0.75f, 1};
        float [] rotationX = {-90, 15, -15, 0};
        switch (action){
            case IN:
                break;
            case OUT:
                alpha = new float[]{1,0};
                rotationX = new float[]{0,-90};
                break;
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(target, "alpha", alpha),
                ObjectAnimator.ofFloat(target,"rotationX",rotationX),
        };
    }

    @Override
    public void setAction(@NonNull Action action) {
        this.action = action;
    }
}
