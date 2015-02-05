package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.view.View;

import edward.com.animation.evaluators.BounceOutEvaluator;

public class DropOut extends NoDirection {
    public DropOut() {
    }

    public DropOut(long duration) {
        super(duration);
    }

    @Override
    public Animator[] getAnimators(View target) {
        TypeEvaluator<Number> evaluator = new BounceOutEvaluator();
        int distance = target.getTop() + target.getHeight();
        ObjectAnimator[] animators =  new ObjectAnimator[]{
                ObjectAnimator.ofFloat(target, "translationY", -distance, 0),
                ObjectAnimator.ofFloat(target, "alpha", 0, 1)
        };
        animators[0].setEvaluator(evaluator);
        return animators;
    }
}
