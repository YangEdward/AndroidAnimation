package edward.com.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Interpolator;

import java.util.ArrayList;
import java.util.List;

import edward.com.animation.impl.Effect4View;

/**
 * Created by Edward on 2015/2/1.
 */
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
    }

    public static AnimatorManager with(View target){
        return new AnimatorManager(target);
    }

    public void putEffect(@NonNull Effect4View effect){
        effects.add(effect);
        for (Animator animator : effect.getAnimators(target)){
            mAnimators.add(animator);
        }
    }

    public void animate() {
        if(effects.size() != 0){
            if(isNeedReset){
                reset();
            }
            prepare();
            start();
        }
    }

    private void reset(){
        for (Effect4View effect : effects){
            effect.reset(target);
        }
    };

    private void prepare(){
        animatorSet.playTogether(mAnimators);
    }

    /**
     * start to animate
     */
    public void start() {
        animatorSet.setDuration(duration);
        animatorSet.start();
    }

    public boolean isNeedReset() {
        return isNeedReset;
    }

    public AnimatorManager setNeedReset(boolean isNeedReset) {
        this.isNeedReset = isNeedReset;
        return this;
    }

    public AnimatorManager setDuration(long duration) {
        duration = duration;
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
}
