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

package edward.com.animation.interpolators;

import android.view.animation.Interpolator;

public class SpringInterpolators implements Interpolator{

    private final float mFactor;

    public SpringInterpolators() {
        mFactor = 0.4f;

    }

    public SpringInterpolators(float mFactor) {
        this.mFactor = mFactor;
    }

    @Override
    public float getInterpolation(float input) {
        return (float)(Math.pow(2, -10 * input) *
                Math.sin((input - mFactor / 4) * (2 * Math.PI) / mFactor)
                + 1);
    }
}
