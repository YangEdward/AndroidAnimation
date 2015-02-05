package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.view.View;

import edward.com.animation.evaluators.BaseEvaluator;
import edward.com.animation.evaluators.BounceOutEvaluator;

import static edward.com.animation.effects.AnimPropertyName.ALPHA;
import static edward.com.animation.effects.AnimPropertyName.SCALE_X;
import static edward.com.animation.effects.AnimPropertyName.TRANSLATION_Y;

public class DropOut extends NoDirection {
    public DropOut() {
    }

    public DropOut(long duration) {
        super(duration);
    }

    @Override
    public Animator[] getAnimators(View target) {
        BaseEvaluator evaluator = new BounceOutEvaluator();
        int distance = target.getTop() + target.getHeight();
        return new ObjectAnimator[]{
                new AnimatorBuilder(target,duration).setAnimator(ALPHA)
                        .getAnimator(),
                new AnimatorBuilder(target,duration).setAnimatorNoAction(TRANSLATION_Y, -distance, 0)
                        .setEvaluator(evaluator)
                        .getAnimator(),
        };
    }
}
