package edward.com.animation.utils;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;

import edward.com.animation.evaluators.BaseEvaluator;

public class EvaluatorUtil {

    private EvaluatorUtil(){
        new AssertionError();
    }

    public static ValueAnimator decorate(BaseEvaluator evaluator, ValueAnimator animator){
        return decorate(evaluator,animator,new BaseEvaluator.ErasingListener[0]);
    }

    public static ValueAnimator decorate(BaseEvaluator evaluator, ValueAnimator animator,@NonNull BaseEvaluator.ErasingListener ... listeners){
        BaseEvaluator t = evaluator;
        evaluator.setDuration(animator.getDuration());
        if(listeners!=null && listeners.length != 0)
            t.addErasingListeners(listeners);
        animator.setEvaluator(t);
        return animator;
    }

    public static PropertyValuesHolder decorate(BaseEvaluator evaluator, float duration, PropertyValuesHolder propertyValuesHolder){
        evaluator.setDuration(duration);
        propertyValuesHolder.setEvaluator(evaluator);
        return propertyValuesHolder;
    }

}
