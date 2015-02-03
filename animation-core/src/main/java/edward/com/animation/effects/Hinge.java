package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.view.View;

import edward.com.animation.evaluators.AccelerateDecelerateEvaluator;
import edward.com.animation.impl.Effect4View;

public class Hinge implements Effect4View {

    private final long duration;

    public Hinge() {
        duration = 1000;
    }

    public Hinge(long duration) {
        this.duration = duration;
    }

    @Override
    public Animator[] getAnimators(View target) {
        TypeEvaluator<Number> evaluator = new AccelerateDecelerateEvaluator(duration);
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

    @Override
    public void reset(View target) {

    }
}
