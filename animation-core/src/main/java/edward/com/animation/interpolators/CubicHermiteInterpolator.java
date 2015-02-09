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

public class CubicHermiteInterpolator implements Interpolator {

    private final float startValue = 0;
    private final float endValue = 1;
    private final float tangent0 ;
    private final float tangent1;

    public CubicHermiteInterpolator() {
        tangent0 = 4;
        tangent1 = 4;
    }

    public CubicHermiteInterpolator(float tangent0, float tangent1) {
        this.tangent0 = tangent0;
        this.tangent1 = tangent1;
    }

    @Override
    public float getInterpolation(float input) {
        float t2 = input*input;
        float t3 = t2*input;
        return (2*t3 - 3*t2 + 1)*startValue +
                (t3-2*t2+input)*tangent0 +
                (-2*t3+3*t2)*endValue +
                (t3-t2)*tangent1;
    }
}
