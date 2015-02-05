package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.view.View;

import edward.com.animation.evaluators.DecelerateEvaluator;

/**
 * Created by Administrator on 2015/2/3.
 */
public class TakingOff implements Effect4View {

    private final long duration;

    public TakingOff() {
        duration = 1000;
    }

    public TakingOff(long duration) {
        this.duration = duration;
    }

    @Override
    public Animator[] getAnimators(View target) {
        TypeEvaluator<Number> evaluator = new DecelerateEvaluator();
        ObjectAnimator[] animators =  new ObjectAnimator[]{
            ObjectAnimator.ofFloat(target, "scaleX", 1f, 1.5f),
            ObjectAnimator.ofFloat(target, "scaleY", 1f, 1.5f),
            ObjectAnimator.ofFloat(target, "alpha", 1, 0)
        };
        for(ObjectAnimator animator:animators){
            animator.setEvaluator(evaluator);
        }
        return animators;
    }
}
