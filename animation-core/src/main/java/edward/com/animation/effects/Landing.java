package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.view.View;

import edward.com.animation.evaluators.DecelerateEvaluator;

public class Landing extends NoDirection {

    public Landing() {

    }

    public Landing(long duration) {
        super(duration);
    }

    @Override
    public Animator[] getAnimators(View target) {
        TypeEvaluator<Number> evaluator = new DecelerateEvaluator();
        ObjectAnimator[] animators =  new ObjectAnimator[]{
                ObjectAnimator.ofFloat(target, "scaleX", 1.5f,1f),
                ObjectAnimator.ofFloat(target, "scaleY", 1.5f,1f),
                ObjectAnimator.ofFloat(target, "alpha", 1, 0)
        };
        for(ObjectAnimator animator:animators){
            animator.setEvaluator(evaluator);
        }
        return animators;
    }
}
