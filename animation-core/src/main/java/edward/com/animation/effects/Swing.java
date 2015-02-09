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

import edward.com.animation.evaluators.WaveEvaluator;

import static edward.com.animation.effects.AnimPropertyName.PIVOT_X;
import static edward.com.animation.effects.AnimPropertyName.PIVOT_Y;
import static edward.com.animation.effects.AnimPropertyName.ROTATION;

public class Swing extends NoDirection{
    public Swing() {
    }

    public Swing(long duration) {
        super(duration);
    }

    @Override
    public Animator[] getAnimators(@NonNull View target) {
        return new Animator[]{
                new AnimatorBuilder(target,duration).setAnimatorNoAction(ROTATION,0,0)
                        .setEvaluator(new WaveEvaluator(10,4))
                        .getAnimator()
        };
    }

}
