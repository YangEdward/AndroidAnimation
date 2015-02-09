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
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.View;

public class Flash extends NoDirection{

    public Flash() {
        repeatCount = 4;
    }

    public Flash(long duration) {
        super(duration);
        repeatCount = 4;
    }

    @Override
    public Animator[] getAnimators(@NonNull View target) {
        return new Animator[]{new AnimatorBuilder(target,duration).
                setAnimator(AnimPropertyName.ALPHA).
                setRepeatCount(repeatCount).
                setRepeatMode(ValueAnimator.REVERSE).
                getAnimator()};
    }

}
