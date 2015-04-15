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

/**
 * <p>This is the manager class for animations,you can use it to add animation effect into animations,and remove it.<br>
 *     you can start,cancel,set the duration of animations for target view.this class also has a static class which is<br>
 *         the builder of this class</p>
 *         @author YangEdward
 *         @version 1.0
 *         @since 1.0
 */
public class AnimatorManager {

    /**
     * the list contain animators
     * @see android.animation.Animator Animator
     *
     */
    private List<Animator> mAnimators = new ArrayList<>();
    /**
     * the list contain effect
     * @see edward.com.animation.effects.Effect4View Effect4View
     */
    private List<Effect4View> effects = new ArrayList<>();
    /**
     * target view which has animations
     */
    private final View target;
    /**
     * animator controller
     * @see android.animation.AnimatorSet AnimatorSet
     */
    private AnimatorSet animatorSet;
    /**
     * is target view need to reset when start animation
     */
    private final boolean isNeedReset;
    /**
     * duration of animations
     */
    private long duration;

    /**
     * private constructor,you can call is in self class.
     * @param builder builder of AnimatorManager
     * @see edward.com.animation.AnimatorManager.Builder AnimatorManager.Builder
     */
    private AnimatorManager(@NonNull Builder builder){
        this.target = builder.target;
        this.duration = builder.duration;
        this.isNeedReset = builder.isNeedReset;
        this.animatorSet = new AnimatorSet();
        if(builder.interpolator != null){
            this.animatorSet.setInterpolator(builder.interpolator);
        }
        if(builder.listeners != null){
            this.animatorSet.addListener(builder.listeners);
        }
        this.animatorSet.setStartDelay(builder.startDelay);
        this.effects.addAll(builder.effects);
        this.mAnimators.addAll(builder.mAnimators);
    }

    /**
     * Builder class for AnimatorManager
     * @author YangEdward
     * @version 1.0
     * @since 1.0
     */

    public static class Builder{
        /**
         * interpolator of animation
         */
        private Interpolator interpolator;
        /**
         * start delay time of animation
         */
        private long startDelay;
        /**
         * target view
         */
        private View target;
        /**
         * listener of animation
         */
        private Animator.AnimatorListener listeners;
        /**
         * the list contain animators
         * @see android.animation.Animator Animator
         */
        private List<Animator> mAnimators = new ArrayList<>();
        /**
         * the list contain effect
         * @see edward.com.animation.effects.Effect4View Effect4View
         */
        private List<Effect4View> effects = new ArrayList<>();
        /**
         * is target view need to reset when start animation,default is {@value}
         */
        private boolean isNeedReset = true;
        /**
         * duration of animations,default value is {@value}
         */
        private long duration = 1000;

        /**
         * public constructor of builder
         * @param target the view has animations
         */
        public Builder(View target) {
            this.target = target;
        }

        /**
         * finish the building process
         * @return new AnimatorManager object
         */

        public AnimatorManager build(){
            return new AnimatorManager(this);
        }

        /**
         * Add effect into animator manager.
         * @param effect the animation effect which want to add in animation list
         * @return Builder
         * @throws NullPointerException if {@code effect} is {@code null}
         */
        public Builder putEffect(@NonNull Effect4View effect){
            if(!isContain(effect)){
                this.effects.add(effect);
                this.mAnimators.addAll(Arrays.asList(effect.getAnimators(target)));
            }
            return this;
        }

        /**
         * Add effect into animator manager.
         * @param effects the animation effects which want to add in animation list
         * @return Builder
         * @throws NullPointerException if {@code effects} is {@code null}
         */
        public Builder putEffects(@NonNull List<Effect4View> effects){
            for (Effect4View effect : effects){
                if(!isContain(effect)){
                    this.effects.add(effect);
                    this.mAnimators.addAll(Arrays.asList(effect.getAnimators(target)));
                }
            }
            return this;
        }

        /**
         * Add animators into animator manager.
         * @param animators the animators which want to add in animation list
         * @return Builder
         * @throws NullPointerException if {@code animators} is {@code null}
         */
        public Builder putAnimators(@NonNull Animator[] animators){
            this.mAnimators.addAll(Arrays.asList(animators));
            return this;
        }

        /**
         * Set target view to reset when starting animation
         * @param isNeedReset reset flag
         * @return Builder
         */
        public Builder setNeedReset(boolean isNeedReset) {
            this.isNeedReset = isNeedReset;
            return this;
        }

        /**
         * <p>Set the duration of animations</p>
         * @param duration the time of animation costs
         *                 @return Builder
         * @see android.animation.AnimatorSet#setDuration(long) duration
         */
        public Builder setDuration(long duration) {
            this.duration = duration;
            return this;
        }

        /**
         * Add listener to animatorSet
         * @param listener listener add into animatorSet
         *                 @see android.animation.AnimatorSet#addListener(android.animation.Animator.AnimatorListener)
         * @return Builder
         */
        public Builder addListener(@NonNull Animator.AnimatorListener listener) {
            this.listeners = listener;
            return this;
        }

        /**
         * Set start delay time
         * @param delay delay time of animation start
         *              @see android.animation.AnimatorSet#setStartDelay(long)
         * @return Builder
         */
        public Builder setStartDelay(long delay) {
            this.startDelay = delay;
            return this;
        }

        /**
         * Set interpolator of animation
         * @param interpolator interpolator of animation
         * @see android.animation.AnimatorSet#setInterpolator(android.animation.TimeInterpolator) interpolator
         * @return Builder
         */
        public Builder setInterpolator(@NonNull Interpolator interpolator) {
            this.interpolator = interpolator;
            return this;
        }

        /**
         * <p>Return true if animations contains effect </p>
         * @param effect the effect is be judged
         * @return boolean
         */
        public boolean isContain(@NonNull Effect4View effect){
            return this.effects.contains(effect);
        }
    }


    /**
     * <p>Set the duration of animations</p>
     * @param duration the time of animation costs
     * @see android.animation.AnimatorSet#setDuration(long) duration
     */
    public void setDuration(long duration) {
        this.duration = duration;
        this.animatorSet.setDuration(duration);
    }

    /**
     * Add effect into animator manager.
     * @param effect the animation effect which want to add in animation list
     * @return AnimatorManager is a object which manager animators
     * @throws NullPointerException if {@code effect} is {@code null}
     */
    public AnimatorManager putEffect(@NonNull Effect4View effect){
        if(!isContain(effect)){
            this.effects.add(effect);
            this.mAnimators.addAll(Arrays.asList(effect.getAnimators(target)));
        }
        return this;
    }

    /**
     * Remove effect from animator manager.
     * @param effect the animation effect which will be removed
     * @throws NullPointerException if {@code effect} is {@code null}
     */

    public void removeEffect(@NonNull Effect4View effect){
        if(isContain(effect)){
            this.effects.remove(effect);
            for (Animator animator : effect.getAnimators(target)){
                this.mAnimators.remove(animator);
            }
        }
    }

    /**
     * clear animation effects
     */

    public void removeAllEffect(){
       if(!isEmpty()){
           this.effects.clear();
           this.mAnimators.clear();
       }

    }

    /**
     * Start the animation
     */
    public void animate() {
        if(this.isNeedReset){
            reset();
        }
        prepare();
        start();
    }

    /**
     * reset situation of target view
     */
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

    /**
     * prepare animator for target view
     */
    private void prepare(){
        animatorSet.playTogether(mAnimators);
    }


    /**
     * start to animate
     */
    private void start() {
        animatorSet.start();
    }

    /**
     * <p>do judgement effect is or not contain in animations</p>
     * @param effect the effect is be judged
     * @return boolean true is contain,false is not contain
     */
    public boolean isContain(@NonNull Effect4View effect){
        return effects.contains(effect);
    }

    /**
     * Get time of animation delay
     * @return long
     */
    public long getStartDelay() {
        return animatorSet.getStartDelay();
    }

    /**
     * cancel animations of target view
     */
    public void cancel(){
        animatorSet.cancel();
    }

    /**
     * Return true if animation is running
     * @return boolean
     */
    public boolean isRunning(){
        return animatorSet.isRunning();
    }

    /**
     * Return true if animation is started
     * @since 1.0
     * @return boolean
     */
    public boolean isStarted(){
        return animatorSet.isStarted();
    }

    /**
     * Remove special listener from animatorSet
     * @param l the listener need to remove
     */
    public void removeAnimatorListener(Animator.AnimatorListener l) {
        animatorSet.removeListener(l);
    }

    /**
     * Remove all listeners from animatorSet
     */
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
