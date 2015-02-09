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

/**
 * Created by Administrator on 15-2-6.
 */
public class WaveEvaluator extends BaseEvaluator {

    private float amplitude;
    private float startPiValue = 0;
    private int endPiValue;

    private AccelerateEvaluator accelerateEvaluator;

    public WaveEvaluator(float amplitude,int endPiValue) {
        this.amplitude = amplitude;
        this.endPiValue = endPiValue;
        accelerateEvaluator = new AccelerateEvaluator();
    }
    @Override
    public void setDuration(float duration) {
        super.setDuration(duration);
        accelerateEvaluator.setDuration(duration);
    }

    @Override
    public float calculate(float time, float startValue, float offset, float duration) {
        if(offset != 0){
            amplitude = -offset;
            startPiValue = 0.5f;
        }
        float amp = accelerateEvaluator.calculate(time,amplitude,0,duration);
        float piValue = accelerateEvaluator.calculate(time,(float)Math.PI*startPiValue,
                (float)Math.PI*endPiValue,duration);
        return (float)(amp*Math.sin(piValue)) + startValue + offset;
    }
}
