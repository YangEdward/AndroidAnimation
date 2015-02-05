package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

public class Flash extends NoDirection{

    public Flash() {
        repeatCount = 4;
    }

    public Flash(long duration) {
        super(duration);
        repeatCount = 4;
    }

    @Override
    public Animator[] getAnimators(View target) {
        return new Animator[]{new AnimatorBuilder(target,duration).
                setAnimator(AnimPropertyName.ALPHA).
                setRepeatCount(repeatCount).
                setRepeatMode(ValueAnimator.REVERSE).
                getAnimator()};
    }

}
