package edward.com.animation.evaluators;


public class InertiaEvaluator extends BaseEvaluator{

    private float percent;
    private float amplitude = -25;
    public InertiaEvaluator(float percent,float amplitude) {
        this.percent = percent;
        this.amplitude = amplitude;
    }
    @Override
    public float calculate(float time, float startValue, float offset, float duration) {
        if(time < duration * percent){
            return startValue;
        }
        if (offset != 0){
            return new OvershootEvaluator(10f).calculate((time-duration*percent),
                    startValue,offset,duration-duration*percent);
        }
        float t = (1+percent)/2;
        float thisPercent = time/duration;
        float multi = 2/(1-percent);
        return amplitude*(1 - (float)Math.pow(multi*(thisPercent-t),2));
    }
}
