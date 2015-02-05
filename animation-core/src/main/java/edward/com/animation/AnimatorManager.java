package edward.com.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Interpolator;

import java.util.ArrayList;
import java.util.List;

import edward.com.animation.effects.Fade;
import edward.com.animation.effects.Effect4View;

public class AnimatorManager {

    private List<Animator> mAnimators = new ArrayList<>();
    private List<Effect4View> effects = new ArrayList<>();
    private View target;
    private AnimatorSet animatorSet;
    private boolean isNeedReset = true;
    private long duration = 1000;

    private AnimatorManager(@NonNull View target){
        this.target = target;
        animatorSet = new AnimatorSet();
        /*Effect4View effect = new Fade();
        putEffect(effect);*/
    }

    public static AnimatorManager with(@NonNull View target){
        return new AnimatorManager(target);
    }

    public AnimatorManager putEffect(@NonNull Effect4View effect){
        if(!isContain(effect)){
            effects.add(effect);
            for (Animator animator : effect.getAnimators(target)){
                mAnimators.add(animator);
            }
        }
        return this;
    }

    public AnimatorManager putAnimators(@NonNull Animator[] animators){
        for (Animator animator : animators) {
            mAnimators.add(animator);
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
        target.setAlpha(1);
        target.setScaleX(1);
        target.setScaleY(1);
        target.setTranslationX(0);
        target.setTranslationY(0);
        target.setRotation(0);
        target.setRotationY(0);
        target.setRotationX(0);
        target.setPivotX(target.getMeasuredWidth() / 2.0f);
        target.setPivotY(target.getMeasuredHeight() / 2.0f);
    };

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

    public AnimatorManager setNeedReset(boolean isNeedReset) {
        this.isNeedReset = isNeedReset;
        return this;
    }

    public AnimatorManager setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public AnimatorManager setStartDelay(long delay) {
        animatorSet.setStartDelay(delay);
        return this;
    }

    public AnimatorManager setInterpolator(@NonNull Interpolator interpolator) {
        animatorSet.setInterpolator(interpolator);
        return this;
    }

    public AnimatorManager addAnimatorListener(@NonNull Animator.AnimatorListener l) {
        animatorSet.addListener(l);
        return this;
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
