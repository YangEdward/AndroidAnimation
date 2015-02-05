/*
 * Copyright 2014 YangEdward
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

package edward.com.animation.evaluators;

public class BounceInOutEvaluator extends BaseEvaluator{

    private BounceOutEvaluator mBounceOutEvaluator;
    private BounceInEvaluator mBounceInEvaluator;

    public BounceInOutEvaluator(){
        mBounceInEvaluator = new BounceInEvaluator();
        mBounceOutEvaluator = new BounceOutEvaluator();
    }

    @Override
    public void setDuration(float duration) {
        super.setDuration(duration);
        mBounceInEvaluator.setDuration(duration);
        mBounceOutEvaluator.setDuration(duration);
    }

    @Override
    public float calculate(float t, float b, float c, float d) {
        if (t < d/2)
            return mBounceInEvaluator.calculate (t*2, 0, c, d) * .5f + b;
        else
            return mBounceOutEvaluator.calculate (t*2-d, 0, c, d) * .5f + c*.5f + b;
    }
}
