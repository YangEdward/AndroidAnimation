package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;

public class Shake extends NoDirection{
    @Override
    public Animator[] getAnimators(@NonNull View target) {
        return new Animator[]{
                ObjectAnimator.ofFloat(target, "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0)
        };
    }

    public Shake() {
    }

    public Shake(long duration) {
        super(duration);
    }
}
