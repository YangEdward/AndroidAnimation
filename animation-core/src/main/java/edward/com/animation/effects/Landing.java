package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.view.View;

import edward.com.animation.evaluators.BaseEvaluator;
import edward.com.animation.evaluators.DecelerateEvaluator;

import static edward.com.animation.effects.AnimPropertyName.ALPHA;
import static edward.com.animation.effects.AnimPropertyName.SCALE_X;
import static edward.com.animation.effects.AnimPropertyName.SCALE_Y;
import static edward.com.animation.effects.AnimPropertyName.TRANSLATION_Y;

public class Landing extends NoDirection {

    public Landing() {

    }

    public Landing(long duration) {
        super(duration);
    }

    @Override
    public Animator[] getAnimators(View target) {
        BaseEvaluator evaluator = new DecelerateEvaluator();
        return new ObjectAnimator[]{
                new AnimatorBuilder(target,duration).setAnimator(ALPHA)
                        .setEvaluator(evaluator)
                        .getAnimator(),
                new AnimatorBuilder(target,duration).setAnimatorNoAction(SCALE_X, 1.5f, 1f)
                        .setEvaluator(evaluator)
                        .getAnimator(),
                new AnimatorBuilder(target,duration).setAnimatorNoAction(SCALE_Y, 1.5f, 1f)
                        .setEvaluator(evaluator)
                        .getAnimator(),
        };
    }
}
