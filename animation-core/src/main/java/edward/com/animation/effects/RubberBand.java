package edward.com.animation.effects;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.view.View;

import edward.com.animation.evaluators.WaveEvaluator;

import static edward.com.animation.effects.AnimPropertyName.SCALE_X;
import static edward.com.animation.effects.AnimPropertyName.SCALE_Y;

public class RubberBand extends NoDirection {

    public RubberBand() {
    }

    public RubberBand(long duration) {
        super(duration);
    }

    @Override
    public Animator[] getAnimators(@NonNull View target) {
        return new Animator[]{
                new AnimatorBuilder(target,duration).setAnimatorNoAction(SCALE_X,1,1)
                        .setEvaluator(new WaveEvaluator(0.3f,3))
                        .getAnimator(),
                new AnimatorBuilder(target,duration).setAnimatorNoAction(SCALE_Y,1,1)
                        .setEvaluator(new WaveEvaluator(-0.3f,3))
                        .getAnimator()
        };
    }

}
