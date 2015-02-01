package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import edward.com.animation.impl.Effect4View;

/**
 * Created by Edward on 2015/2/1.
 */
public class Shake implements Effect4View{
    @Override
    public Animator[] getAnimators(View target) {
        return new Animator[]{
                ObjectAnimator.ofFloat(target, "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0)
        };
    }

    @Override
    public void reset(View target) {
        target.setTranslationX(0);
    }
}
