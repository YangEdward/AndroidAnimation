package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * Created by Edward on 2015/2/1.
 */
public abstract class SingleViewAnimator {

    private AnimatorSet mAnimatorSet;
    private long mDuration = 1000;

    public SingleViewAnimator(){
        mAnimatorSet = new AnimatorSet();
    }


    protected abstract Animator[] getAnimators(View target);

    /**
     * reset the view to default status
     *
     * @param target
     */
    protected abstract void reset(View target);

    public void animate(View target) {
        reset(target);
        mAnimatorSet.playTogether(getAnimators(target));
        start();
    }

    /*public void reset(View target) {
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
    }*/

    /**
     * start to animate
     */
    public void start() {
        mAnimatorSet.setDuration(mDuration);
        mAnimatorSet.start();
    }

    public void resume(){
        mAnimatorSet.resume();
    }
    public SingleViewAnimator setDuration(long duration) {
        mDuration = duration;
        return this;
    }

    public SingleViewAnimator setStartDelay(long delay) {
        mAnimatorSet.setStartDelay(delay);
        return this;
    }

    public long getStartDelay() {
        return mAnimatorSet.getStartDelay();
    }

    public SingleViewAnimator addAnimatorListener(Animator.AnimatorListener l) {
        mAnimatorSet.addListener(l);
        return this;
    }

    public void cancel(){
        mAnimatorSet.cancel();
    }

    public boolean isRunning(){
        return mAnimatorSet.isRunning();
    }

    public boolean isStarted(){
        return mAnimatorSet.isStarted();
    }

    public void removeAnimatorListener(Animator.AnimatorListener l) {
        mAnimatorSet.removeListener(l);
    }

    public void removeAllListener() {
        mAnimatorSet.removeAllListeners();
    }

    public SingleViewAnimator setInterpolator(Interpolator interpolator) {
        mAnimatorSet.setInterpolator(interpolator);
        return this;
    }

    public long getDuration() {
        return mDuration;
    }

    public AnimatorSet getAnimatorAgent() {
        return mAnimatorSet;
    }
}
