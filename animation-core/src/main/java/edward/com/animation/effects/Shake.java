package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

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
}
