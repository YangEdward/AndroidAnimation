package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;

import edward.com.animation.evaluators.WaveEvaluator;

import static edward.com.animation.effects.AnimPropertyName.ALPHA;
import static edward.com.animation.effects.AnimPropertyName.ROTATION;
import static edward.com.animation.effects.AnimPropertyName.TRANSLATION_X;

public class Wobble extends NoDirection {

    public Wobble() {
    }

    public Wobble(long duration) {
        super(duration);
    }

    @Override
    public Animator[] getAnimators(@NonNull View target) {
        float width = target.getWidth();
        float one = (float)(30*width/100.0);
        return new Animator[]{
                new AnimatorBuilder(target,duration).setAnimatorNoAction(TRANSLATION_X,0,0)
                        .setEvaluator(new WaveEvaluator(one,5))
                        .getAnimator(),
                new AnimatorBuilder(target,duration).setAnimatorNoAction(ROTATION,0,0)
                        .setEvaluator(new WaveEvaluator(5,5))
                        .getAnimator(),
        };
    }

}
