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

package edward.com.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Interpolator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edward.com.animation.effects.Effect4View;

public class AnimatorManager {

    private List<Animator> mAnimators = new ArrayList<>();
    private List<Effect4View> effects = new ArrayList<>();
    private final View target;
    private AnimatorSet animatorSet;
    private final boolean isNeedReset;
    private long duration;

    /*private AnimatorManager(@NonNull View target){
        this.target = target;
        animatorSet = new AnimatorSet();
    }*/

    private AnimatorManager(@NonNull Builder builder){
        this.target = builder.target;
        this.duration = builder.duration;
        this.isNeedReset = builder.isNeedReset;
        animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(builder.interpolator);
        animatorSet.addListener(builder.listeners);
        animatorSet.setStartDelay(builder.startDelay);
        effects.addAll(builder.effects);
        mAnimators.addAll(builder.mAnimators);
    }

    public static class Builder{
        private Interpolator interpolator;
        private long startDelay;
        private View target;
        private Animator.AnimatorListener listeners;
        private List<Animator> mAnimators = new ArrayList<>();
        private List<Effect4View> effects = new ArrayList<>();
        private boolean isNeedReset = true;
        private long duration = 1000;

        public Builder(View target) {
            this.target = target;
        }

        public AnimatorManager build(){
            return new AnimatorManager(this);
        }

        public Builder putEffect(@NonNull Effect4View effect){
            if(!isContain(effect)){
                effects.add(effect);
                mAnimators.addAll(Arrays.asList(effect.getAnimators(target)));
            }
            return this;
        }

        public Builder putEffects(@NonNull List<Effect4View> effects){
            for (Effect4View effect : effects){
                if(!isContain(effect)){
                    effects.add(effect);
                    mAnimators.addAll(Arrays.asList(effect.getAnimators(target)));
                }
            }
            return this;
        }

        public Builder putAnimators(@NonNull Animator[] animators){
            mAnimators.addAll(Arrays.asList(animators));
            return this;
        }

        public Builder setNeedReset(boolean isNeedReset) {
            this.isNeedReset = isNeedReset;
            return this;
        }

        public Builder setDuration(long duration) {
            this.duration = duration;
            return this;
        }

        public Builder addListener(@NonNull Animator.AnimatorListener listener) {
            this.listeners = listener;
            return this;
        }

        public Builder setStartDelay(long delay) {
            startDelay = delay;
            return this;
        }

        public Builder setInterpolator(@NonNull Interpolator interpolator) {
            this.interpolator = interpolator;
            return this;
        }

        public boolean isContain(@NonNull Effect4View effect){
            return effects.contains(effect);
        }
    }

    public void setDuration(long duration) {
        this.duration = duration;
        animatorSet.setDuration(duration);
    }

    /*public static AnimatorManager with(@NonNull View target){
        return new AnimatorManager(target);
    }*/

    /*public AnimatorManager putEffect(@NonNull Effect4View effect){
        if(!isContain(effect)){
            effects.add(effect);
            mAnimators.addAll(Arrays.asList(effect.getAnimators(target)));
        }
        return this;
    }

    public AnimatorManager putEffects(@NonNull List<Effect4View> effects){
        for (Effect4View effect : effects){
            if(!isContain(effect)){
                effects.add(effect);
                mAnimators.addAll(Arrays.asList(effect.getAnimators(target)));
            }
        }
        return this;
    }

    public AnimatorManager putAnimators(@NonNull Animator[] animators){
        mAnimators.addAll(Arrays.asList(animators));
        return this;
    }*/

    public AnimatorManager putEffect(@NonNull Effect4View effect){
        if(!isContain(effect)){
            effects.add(effect);
            mAnimators.addAll(Arrays.asList(effect.getAnimators(target)));
        }
        return this;
    }

    public void removeEffect(@NonNull Effect4View effect){
        if(isContain(effect)){
            effects.remove(effect);
            for (Animator animator : effect.getAnimators(target)){
                mAnimators.remove(animator);
            }
        }
    }

    public void removeAllEffect(){
       if(!isEmpty()){
           effects.clear();
           mAnimators.clear();
       }

    }
    public void animate() {
        if(isNeedReset){
            reset();
        }
        prepare();
        start();
    }

    private void reset(){
        /*target.setAlpha(1);
        target.setScaleX(1);
        target.setScaleY(1);
        target.setTranslationX(0);
        target.setTranslationY(0);*/
        target.setRotation(0);
        target.setRotationY(0);
        target.setRotationX(0);
        target.setPivotX(target.getMeasuredWidth() / 2.0f);
        target.setPivotY(target.getMeasuredHeight() / 2.0f);
    }

    private void prepare(){
        animatorSet.playTogether(mAnimators);
    }


    /**
     * start to animate
     */
    private void start() {
        //animatorSet.setDuration(duration);
        //reset();
        animatorSet.start();
    }

    public boolean isContain(@NonNull Effect4View effect){
        return effects.contains(effect);
    }

    public boolean isNeedReset() {
        return isNeedReset;
    }


    public long getStartDelay() {
        return animatorSet.getStartDelay();
    }

    public void cancel(){
        animatorSet.cancel();
    }

    public boolean isRunning(){
        return animatorSet.isRunning();
    }

    public boolean isStarted(){
        return animatorSet.isStarted();
    }

    public void removeAnimatorListener(Animator.AnimatorListener l) {
        animatorSet.removeListener(l);
    }

    public void removeAllListener() {
        animatorSet.removeAllListeners();
    }

    public long getDuration() {
        return duration;
    }

    public AnimatorSet getAnimatorSet() {
        return animatorSet;
    }

    private boolean isEmpty(){
        return effects.isEmpty();
    }
}
