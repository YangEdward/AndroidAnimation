package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;

import edward.com.animation.evaluators.WaveEvaluator;

import static edward.com.animation.effects.AnimPropertyName.PIVOT_X;
import static edward.com.animation.effects.AnimPropertyName.PIVOT_Y;
import static edward.com.animation.effects.AnimPropertyName.ROTATION;

public class Swing extends NoDirection{
    public Swing() {
    }

    public Swing(long duration) {
        super(duration);
    }

    @Override
    public Animator[] getAnimators(@NonNull View target) {
        return new Animator[]{
                new AnimatorBuilder(target,duration).setAnimatorNoAction(ROTATION,0,0)
                        .setEvaluator(new WaveEvaluator(10,4))
                        .getAnimator()
        };
    }

}
