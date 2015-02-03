package edward.com.animation.interpolators;

import android.view.animation.Interpolator;

/**
 * Created by Administrator on 2015/2/3.
 */
public class CubicHermiteInterpolator implements Interpolator {

    private final float startValue = 0;
    private final float endValue = 1;
    private final float tangent0 ;
    private final float tangent1;

    public CubicHermiteInterpolator() {
        tangent0 = 4;
        tangent1 = 4;
    }

    public CubicHermiteInterpolator(float tangent0, float tangent1) {
        this.tangent0 = tangent0;
        this.tangent1 = tangent1;
    }

    @Override
    public float getInterpolation(float input) {
        float t2 = input*input;
        float t3 = t2*input;
        return (2*t3 - 3*t2 + 1)*startValue +
                (t3-2*t2+input)*tangent0 +
                (-2*t3+3*t2)*endValue +
                (t3-t2)*tangent1;
    }
}
