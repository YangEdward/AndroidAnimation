package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import edward.com.animation.impl.Effect4View;

/**
 * Created by Edward on 2015/2/1.
 */
public class Flash implements Effect4View{
    @Override
    public Animator[] getAnimators(View target) {
        return new Animator[]{ObjectAnimator.ofFloat(target, "alpha", 1, 0, 1, 0, 1)};
    }

}
