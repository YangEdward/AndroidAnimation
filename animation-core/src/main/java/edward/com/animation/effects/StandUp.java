package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;

import edward.com.animation.evaluators.OvershootEvaluator;
import edward.com.animation.evaluators.WaveEvaluator;

import static edward.com.animation.effects.AnimPropertyName.PIVOT_X;
import static edward.com.animation.effects.AnimPropertyName.PIVOT_Y;
import static edward.com.animation.effects.AnimPropertyName.ROTATION;
import static edward.com.animation.effects.AnimPropertyName.ROTATION_X;

/**
 * Created by Edward on 2015/2/1.
 */
public class StandUp extends NoDirection {
    public StandUp() {
    }

    public StandUp(long duration) {
        super(duration);
    }

    @Override
    public Animator[] getAnimators(@NonNull View target) {
        float x = (target.getWidth() - target.getPaddingLeft() - target.getPaddingRight())/2
                + target.getPaddingLeft();
        float y = target.getHeight() - target.getPaddingBottom();
        return new Animator[]{
                new AnimatorBuilder(target,duration).setAnimatorNoAction(ROTATION_X,55,0)
                        .setEvaluator(new OvershootEvaluator())
                        .getAnimator(),
                new AnimatorBuilder(target,duration).setAnimatorNoAction(PIVOT_X, x, x)
                        .getAnimator(),
                new AnimatorBuilder(target,duration).setAnimatorNoAction(PIVOT_Y, y, y)
                        .getAnimator()
        };
    }

}
