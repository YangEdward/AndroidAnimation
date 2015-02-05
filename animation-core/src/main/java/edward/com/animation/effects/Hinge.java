package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.view.View;

import edward.com.animation.evaluators.AccelerateDecelerateEvaluator;

public class Hinge extends NoDirection {

    public Hinge() {

    }

    public Hinge(long duration) {
        super(duration);
    }

    @Override
    public Animator[] getAnimators(View target) {
        TypeEvaluator<Number> evaluator = new AccelerateDecelerateEvaluator();
        float x = target.getPaddingLeft();
        float y = target.getPaddingTop();
        ObjectAnimator[] animators =  new ObjectAnimator[]{
                ObjectAnimator.ofFloat(target,"rotation",0,80,60,80,60,60),
                ObjectAnimator.ofFloat(target, "translationY", 0, 0, 0, 0, 0, 700),
                ObjectAnimator.ofFloat(target, "alpha", 1, 1, 1, 1, 1, 0),
                ObjectAnimator.ofFloat(target, "pivotX", x, x, x, x, x, x),
                ObjectAnimator.ofFloat(target, "pivotY", y, y, y, y, y, y)
        };
        animators[0].setEvaluator(evaluator);
        return animators;
    }
}
