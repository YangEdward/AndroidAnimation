package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

public class Pulse extends NoDirection {

    public Pulse() {
        repeatCount = 2;
    }

    public Pulse(long duration) {
        super(duration);
        repeatCount = 2;
    }

    @Override
    public Animator[] getAnimators(View target) {
        return new Animator[]{new AnimatorBuilder(target,duration).
                setAnimator(AnimPropertyName.SCALE_X,1,1.1f).
                setRepeatCount(repeatCount).
                setRepeatMode(ValueAnimator.REVERSE).
                getAnimator(),
                new AnimatorBuilder(target,duration).
                        setAnimator(AnimPropertyName.SCALE_Y,1,1.1f).
                        setRepeatCount(repeatCount).
                        setRepeatMode(ValueAnimator.REVERSE).
                        getAnimator(),
        };
    }
}
