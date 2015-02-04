package edward.com.animation.Animators;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Interpolator;

import edward.com.animation.enums.Action;
import edward.com.animation.enums.AnimPropertyName;
import edward.com.animation.evaluators.BaseEvaluator;

public class BaseAnimators {
    private static final long DEFAULT_DURATION = 1000;
    protected long duration;
    protected Action action;
    protected View target;
    private ObjectAnimator mAnimator;

    public BaseAnimators(@NonNull View target){
        this(target,DEFAULT_DURATION);
    }

    public BaseAnimators(@NonNull View target,long duration){
        this(target,duration,Action.IN);
    }

    public BaseAnimators(@NonNull View target,long duration,@NonNull Action action){
        this.duration = duration;
        this.action = action;
        this.target = target;
    }

    public BaseAnimators setAnimator(AnimPropertyName propertyName){
        return setAnimator(propertyName,0,1);
    }

    public BaseAnimators setAnimatorNoAction(AnimPropertyName propertyName){
        mAnimator =  ObjectAnimator.ofFloat(target,propertyName.getValue(),0,1);
        return this;
    }

    public BaseAnimators setAnimatorNoAction(AnimPropertyName propertyName,float from,float to){
        mAnimator =  ObjectAnimator.ofFloat(target,propertyName.getValue(),from,to);
        return this;
    }

    public @NonNull ObjectAnimator getmAnimator(){
        mAnimator.setDuration(duration);
        return mAnimator;
    }
    public BaseAnimators setAnimator(AnimPropertyName propertyName,float from,float to){
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

    public BaseAnimators setEvaluator(@NonNull BaseEvaluator evaluator){
        setEvaluator(evaluator, duration);
        return this;
    }

    public BaseAnimators setEvaluator(@NonNull BaseEvaluator evaluator,long duration){
        evaluator.setDuration(duration);
        mAnimator.setEvaluator(evaluator);
        return this;
    }
    public BaseAnimators setInterpolator(@NonNull Interpolator interpolator){
        mAnimator.setInterpolator(interpolator);
        return this;
    }

    public BaseAnimators setRepeatMode(int value){
        mAnimator.setRepeatMode(value);
        return this;
    }

    public BaseAnimators setRepeatCount(int value){
        mAnimator.setRepeatCount(value);
        return this;
    }

    public BaseAnimators setStartDelay(long startDelay){
        mAnimator.setStartDelay(startDelay);
        return this;
    }
}
