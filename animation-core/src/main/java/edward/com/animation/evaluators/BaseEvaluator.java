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

import android.animation.TypeEvaluator;

import java.util.ArrayList;

public abstract class BaseEvaluator implements TypeEvaluator<Number>{

    protected float mDuration;

    private ArrayList<ErasingListener> mListeners = new ArrayList<>();

    public interface ErasingListener {
        public void on(float time, float value, float start, float end, float duration);
    }

    public void addErasingListener(ErasingListener l){
        mListeners.add(l);
    }

    public void addErasingListeners(ErasingListener ...ls){
        for(ErasingListener l : ls){
            mListeners.add(l);
        }
    }

    public void removeErasingListener(ErasingListener l){
        mListeners.remove(l);
    }

    public void clearErasingListeners(){
        mListeners.clear();
    }

    public void setDuration(float duration) {
        mDuration = duration;
    }
    @Override
    public final Float evaluate(float fraction, Number startValue, Number endValue){
        float t = mDuration * fraction;
        float b = startValue.floatValue();
        float c = endValue.floatValue() - startValue.floatValue();
        float d = mDuration;
        float result = calculate(t,b,c,d);
        for(ErasingListener l : mListeners){
            l.on(t,result,b,c,d);
        }
        return result;
    }

    public abstract float calculate(float time, float startValue, float offset, float duration);

}
