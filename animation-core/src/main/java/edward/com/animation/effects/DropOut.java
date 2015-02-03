package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.view.View;

import edward.com.animation.evaluators.BounceOutEvaluator;
import edward.com.animation.impl.Effect4View;

public class DropOut implements Effect4View {
    private final long duration;

    public DropOut() {
        duration = 1000;
    }

    public DropOut(long duration) {
        this.duration = duration;
    }

    @Override
    public Animator[] getAnimators(View target) {
        TypeEvaluator<Number> evaluator = new BounceOutEvaluator(duration);
        int distance = target.getTop() + target.getHeight();
        ObjectAnimator[] animators =  new ObjectAnimator[]{
                ObjectAnimator.ofFloat(target, "translationY", -distance, 0),
                ObjectAnimator.ofFloat(target, "alpha", 0, 1)
        };
        animators[0].setEvaluator(evaluator);
        return animators;
    }

}
