package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import edward.com.animation.impl.Effect4View;

/**
 * Created by Edward on 2015/2/1.
 */
public class Bounce implements Effect4View{

    @Override
    public Animator[] getAnimators(View target) {
        return new Animator[]{ObjectAnimator.ofFloat(target, "translationY", 0, 0, -30, 0, -15, 0, 0)};
    }

    @Override
    public void reset(View target) {
        target.setTranslationY(0);
    }
}
