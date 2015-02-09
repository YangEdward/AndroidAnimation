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

import edward.com.animation.evaluators.BaseEvaluator;
import edward.com.animation.evaluators.BounceOutEvaluator;

import static edward.com.animation.effects.AnimPropertyName.ALPHA;
import static edward.com.animation.effects.AnimPropertyName.TRANSLATION_Y;

public class DropOut extends NoDirection {
    public DropOut() {
    }

    public DropOut(long duration) {
        super(duration);
    }

    @Override
    public Animator[] getAnimators(@NonNull View target) {
        BaseEvaluator evaluator = new BounceOutEvaluator();
        int distance = target.getTop() + target.getHeight();
        return new ObjectAnimator[]{
                new AnimatorBuilder(target,duration).setAnimator(ALPHA)
                        .getAnimator(),
                new AnimatorBuilder(target,duration).setAnimatorNoAction(TRANSLATION_Y, -distance, 0)
                        .setEvaluator(evaluator)
                        .getAnimator(),
        };
    }
}
