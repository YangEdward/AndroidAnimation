package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
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
    public Animator[] getAnimators(@NonNull View target) {
        return new Animator[]{new AnimatorBuilder(target,duration).
                setAnimatorNoAction(AnimPropertyName.SCALE_X,1,1.1f).
                setRepeatCount(repeatCount).
                setRepeatMode(ValueAnimator.REVERSE).
                getAnimator(),
                new AnimatorBuilder(target,duration).
                        setAnimatorNoAction(AnimPropertyName.SCALE_Y,1,1.1f).
                        setRepeatCount(repeatCount).
                        setRepeatMode(ValueAnimator.REVERSE).
                        getAnimator(),
        };
    }
}
