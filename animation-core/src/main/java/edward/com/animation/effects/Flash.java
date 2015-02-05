package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by Edward on 2015/2/1.
 */
public class Flash implements Effect4View{

    private long duration = AnimatorBuilder.DEFAULT_DURATION;
    private final static int repeatCount = 4;

    public Flash() {
    }

    public Flash(long duration) {
        this.duration = duration;
    }

    @Override
    public Animator[] getAnimators(View target) {
        return new Animator[]{new AnimatorBuilder(target,duration).
                setAnimator(AnimPropertyName.ALPHA).
                setRepeatCount(repeatCount).
                setRepeatMode(ValueAnimator.REVERSE).
                getAnimator()};
    }

    public Effect4View setDuration(long duration) {
        this.duration = duration;
        return this;
    }
}
