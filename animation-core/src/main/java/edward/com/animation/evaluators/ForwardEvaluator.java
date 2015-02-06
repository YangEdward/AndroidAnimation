package edward.com.animation.evaluators;

import android.support.annotation.NonNull;

public class ForwardEvaluator extends BaseEvaluator {

    private BaseEvaluator evaluator;
    private float percent;
    public ForwardEvaluator(@NonNull BaseEvaluator evaluator,float percent) {
        this.evaluator = evaluator;
        this.percent = percent;
    }

    @Override
    public float calculate(float time, float startValue, float offset, float duration) {
        if(time/duration > percent){
            return startValue + offset;
        }
        return evaluator.calculate(time,startValue,offset,percent*duration);
    }
}
