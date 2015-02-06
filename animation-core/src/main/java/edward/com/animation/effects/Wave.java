package edward.com.animation.effects;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.view.View;

import edward.com.animation.evaluators.WaveEvaluator;

import static edward.com.animation.effects.AnimPropertyName.PIVOT_X;
import static edward.com.animation.effects.AnimPropertyName.PIVOT_Y;
import static edward.com.animation.effects.AnimPropertyName.ROTATION;

public class Wave extends NoDirection {
    public Wave() {
    }

    public Wave(long duration) {
        super(duration);
    }

    @Override
    public Animator[] getAnimators(@NonNull View target) {
        float x = (target.getWidth() - target.getPaddingLeft() - target.getPaddingRight())/2
                + target.getPaddingLeft();
        float y = target.getHeight() - target.getPaddingBottom();
        return new Animator[]{
                new AnimatorBuilder(target,duration).setAnimatorNoAction(ROTATION,0,0)
                        .setEvaluator(new WaveEvaluator(12,4))
                        .getAnimator(),
                new AnimatorBuilder(target,duration).setAnimatorNoAction(PIVOT_X, x, x)
                        .getAnimator(),
                new AnimatorBuilder(target,duration).setAnimatorNoAction(PIVOT_Y, y, y)
                        .getAnimator()
        };
    }

}
