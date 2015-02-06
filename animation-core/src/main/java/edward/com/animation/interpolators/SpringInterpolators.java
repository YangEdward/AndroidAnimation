package edward.com.animation.interpolators;

import android.view.animation.Interpolator;

public class SpringInterpolators implements Interpolator{

    private final float mFactor;

    public SpringInterpolators() {
        mFactor = 0.4f;

    }

    public SpringInterpolators(float mFactor) {
        this.mFactor = mFactor;
    }

    @Override
    public float getInterpolation(float input) {
        return (float)(Math.pow(2, -10 * input) *
                Math.sin((input - mFactor / 4) * (2 * Math.PI) / mFactor)
                + 1);
    }
}
