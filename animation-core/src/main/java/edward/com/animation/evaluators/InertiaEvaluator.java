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

package edward.com.animation.evaluators;


public class InertiaEvaluator extends BaseEvaluator{

    private float percent;
    private float amplitude = -25;
    public InertiaEvaluator(float percent,float amplitude) {
        this.percent = percent;
        this.amplitude = amplitude;
    }
    @Override
    public float calculate(float time, float startValue, float offset, float duration) {
        if(time < duration * percent){
            return startValue;
        }
        if (offset != 0){
            return new OvershootEvaluator(10f).calculate((time-duration*percent),
                    startValue,offset,duration-duration*percent);
        }
        float t = (1+percent)/2;
        float thisPercent = time/duration;
        float multi = 2/(1-percent);
        return amplitude*(1 - (float)Math.pow(multi*(thisPercent-t),2));
    }
}
