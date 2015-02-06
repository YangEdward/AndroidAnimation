package edward.com.animation.evaluators;

/**
 * Created by Administrator on 15-2-6.
 */
public class WaveEvaluator extends BaseEvaluator {

    private float amplitude;
    private float startPiValue = 0;
    private int endPiValue;

    private AccelerateEvaluator accelerateEvaluator;

    public WaveEvaluator(float amplitude,int endPiValue) {
        this.amplitude = amplitude;
        this.endPiValue = endPiValue;
        accelerateEvaluator = new AccelerateEvaluator();
    }
    @Override
    public void setDuration(float duration) {
        super.setDuration(duration);
        accelerateEvaluator.setDuration(duration);
    }

    @Override
    public float calculate(float time, float startValue, float offset, float duration) {
        if(offset != 0){
            amplitude = -offset;
            startPiValue = 0.5f;
        }
        float amp = accelerateEvaluator.calculate(time,amplitude,0,duration);
        float piValue = accelerateEvaluator.calculate(time,(float)Math.PI*startPiValue,
                (float)Math.PI*endPiValue,duration);
        return (float)(amp*Math.sin(piValue)) + startValue + offset;
    }
}
