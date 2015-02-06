package edward.com.animation.evaluators;

public class LinearEvaluator extends BaseEvaluator {

    @Override
    public float calculate(float time, float startValue, float offset, float duration) {
        return startValue + offset*time/duration;
    }

}
