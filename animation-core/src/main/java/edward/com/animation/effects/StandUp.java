package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;

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
            ObjectAnimator.ofFloat(target, "pivotX", x, x, x, x, x),
                    ObjectAnimator.ofFloat(target,"pivotY",y,y,y,y,y),
                    ObjectAnimator.ofFloat(target,"rotationX",55,-30,15,-15,0)
        };
    }

}
