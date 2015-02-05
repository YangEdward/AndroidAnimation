package edward.com.animation.effects;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Interpolator;

import edward.com.animation.evaluators.BaseEvaluator;

public class AnimatorBuilder {
    public static final long DEFAULT_DURATION = 1000;
    protected long duration;
    protected Action action;
    protected View target;
    private ObjectAnimator mAnimator;

    public AnimatorBuilder(@NonNull View target){
        this(target,DEFAULT_DURATION);
    }

    public AnimatorBuilder(@NonNull View target,long duration){
        this(target,duration,Action.IN);
    }

    public AnimatorBuilder(@NonNull View target,long duration,@NonNull Action action){
        this.duration = duration;
        this.action = action;
        this.target = target;
    }

    public AnimatorBuilder setAnimator(AnimPropertyName propertyName){
        return setAnimator(propertyName,0,1);
    }

    public AnimatorBuilder setAnimatorNoAction(AnimPropertyName propertyName){
        mAnimator =  ObjectAnimator.ofFloat(target,propertyName.getValue(),0,1);
        return this;
    }

    public AnimatorBuilder setAnimatorNoAction(AnimPropertyName propertyName,float from,float to){
        mAnimator =  ObjectAnimator.ofFloat(target,propertyName.getValue(),from,to);
        return this;
    }

    public @NonNull ObjectAnimator getAnimator(){
        mAnimator.setDuration(duration);
        return mAnimator;
    }
    public AnimatorBuilder setAnimator(AnimPropertyName propertyName,float from,float to){
        float fromValue = 0,toValue = 1;
        switch (action){
            case IN:
                if(from > to){
                    fromValue = to;
                    toValue = from;
                }
                break;
            case OUT:
                if(from < to){
                    fromValue = to;
                    toValue = from;
                }
                break;
        }
        mAnimator =  ObjectAnimator.ofFloat(target,propertyName.getValue(),fromValue,toValue);
        return this;
    };

    public AnimatorBuilder setEvaluator(@NonNull BaseEvaluator evaluator){
        setEvaluator(evaluator, duration);
        return this;
    }

    public AnimatorBuilder setEvaluator(@NonNull BaseEvaluator evaluator,long duration){
        evaluator.setDuration(duration);
        mAnimator.setEvaluator(evaluator);
        return this;
    }
    public AnimatorBuilder setInterpolator(@NonNull Interpolator interpolator){
        mAnimator.setInterpolator(interpolator);
        return this;
    }

    public AnimatorBuilder setRepeatMode(int value){
        mAnimator.setRepeatMode(value);
        return this;
    }

    public AnimatorBuilder setRepeatCount(int value){
        mAnimator.setRepeatCount(value);
        return this;
    }

    public AnimatorBuilder setStartDelay(long startDelay){
        mAnimator.setStartDelay(startDelay);
        return this;
    }
}
