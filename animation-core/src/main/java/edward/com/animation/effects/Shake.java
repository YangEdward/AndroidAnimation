package edward.com.animation.effects;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.view.View;

import edward.com.animation.evaluators.WaveEvaluator;

import static edward.com.animation.effects.AnimPropertyName.TRANSLATION_X;

public class Shake extends NoDirection{
    @Override
    public Animator[] getAnimators(@NonNull View target) {
        return new Animator[]{
                new AnimatorBuilder(target,duration).setAnimatorNoAction(TRANSLATION_X,0,0)
                        .setEvaluator(new WaveEvaluator(25,8))
                        .getAnimator()
        };
    }

    public Shake() {
    }

    public Shake(long duration) {
        super(duration);
    }
}
