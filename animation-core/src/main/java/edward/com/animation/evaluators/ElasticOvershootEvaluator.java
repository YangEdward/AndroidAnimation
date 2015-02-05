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

public class ElasticOvershootEvaluator extends BaseEvaluator{

    private float factor = 0.6f;

    public ElasticOvershootEvaluator() {
    }

    public ElasticOvershootEvaluator(float factor) {
        this.factor = factor;
    }

    @Override
    public float calculate(float t, float b, float c, float d) {
        float x = t/d;
        return (float)(c*(Math.pow(2, -10 * x) * Math.sin((x - factor / 4) * (2 * Math.PI) / factor) + 1));
    }
}
