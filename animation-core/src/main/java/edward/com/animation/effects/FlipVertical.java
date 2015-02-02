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

import edward.com.animation.enums.Action;
import edward.com.animation.impl.Effect4View;
import edward.com.animation.impl.EffectTransformer;
import edward.com.animation.impl.HasAction;

public class FlipVertical extends EffectTransformer implements Effect4View,HasAction {
    private Action action = Action.IN;

    public FlipVertical(){
    }

    public FlipVertical(@NonNull Action action){
        this.action = action;
    }

    @Override
    protected void onTransform(View view, float position) {
        final float rotation = -180f * position;

        view.setAlpha(rotation > 90f || rotation < -90f ? 0f : 1f);
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setRotationX(rotation);
    }

    @Override
    public Animator[] getAnimators(View target) {
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
    public void reset(View target) {
        target.setAlpha(1);
        target.setRotationY(0);
    }

    @Override
    public void setAction(@NonNull Action action) {
        this.action = action;
    }
}
