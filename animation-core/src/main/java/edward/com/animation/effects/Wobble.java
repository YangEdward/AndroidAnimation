package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import edward.com.animation.impl.Effect4View;

/**
 * Created by Edward on 2015/2/1.
 */
public class Wobble implements Effect4View {
    @Override
    public Animator[] getAnimators(View target) {
        float width = target.getWidth();
        float one = (float)(width/100.0);
        return new Animator[]{
                ObjectAnimator.ofFloat(target, "translationX", 0 * one, -25 * one, 20 * one, -15 * one, 10 * one, -5 * one, 0 * one, 0),
                ObjectAnimator.ofFloat(target, "rotation", 0, -5, 3, -3, 2, -1, 0)
        };
    }

    @Override
    public void reset(View target) {
        target.setTranslationX(0);
        target.setRotation(0);
    }
}
