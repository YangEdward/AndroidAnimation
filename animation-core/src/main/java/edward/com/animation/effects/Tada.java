package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Edward on 2015/2/1.
 */
public class Tada implements Effect4View{
    @Override
    public Animator[] getAnimators(View target) {
        return new Animator[]{
                ObjectAnimator.ofFloat(target, "scaleX", 1, 0.9f, 0.9f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1),
                ObjectAnimator.ofFloat(target,"scaleY",1,0.9f,0.9f,1.1f,1.1f,1.1f,1.1f,1.1f,1.1f,1),
                ObjectAnimator.ofFloat(target,"rotation",0 ,-3 , -3, 3, -3, 3, -3,3,-3,0)
        };
    }

}
