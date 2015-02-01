package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Edward on 2015/2/1.
 */
public class Bounce extends SingleViewAnimator{
    @Override
    protected Animator[] getAnimators(View target) {
        return new Animator[]{ObjectAnimator.ofFloat(target, "translationY", 0, 0, -30, 0, -15, 0, 0)};
    }

    @Override
    protected void reset(View target) {
        target.setTranslationY(0);
    }
}
