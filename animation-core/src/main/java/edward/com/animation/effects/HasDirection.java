package edward.com.animation.effects;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.view.View;

import edward.com.animation.evaluators.BaseEvaluator;

public abstract class HasDirection implements Effect4View{

    protected Direction direction;
    protected long duration = AnimatorBuilder.DEFAULT_DURATION;
    protected BaseEvaluator evaluator;

    protected HasDirection(Direction direction) {
        this.direction = direction;
    }

    protected void setDirection(Direction direction){
        this.direction = direction;
    };

    public void setEvaluator(@NonNull BaseEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public Effect4View setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    protected abstract Animator[] top(View target);
    protected abstract Animator[] left(View target);
    protected abstract Animator[] right(View target);
    protected abstract Animator[] bottom(View target);

    public Animator[] topLeft(View target){
        Animator[] first = top(target);
        Animator[] second = left(target);
        return generate(first,second);
    };
    public Animator[] topRight(View target){
        Animator[] first = top(target);
        Animator[] second = right(target);
        return generate(first,second);
    };

    public Animator[] bottomLeft(View target){
        Animator[] first = bottom(target);
        Animator[] second = left(target);
        return generate(first,second);
    };

    public Animator[] bottomRight(View target){
        Animator[] first = bottom(target);
        Animator[] second = right(target);
        return generate(first,second);
    };

    private Animator[] generate(Animator[] first,Animator[] second){
        Animator[] result = new Animator[first.length+second.length];
        System.arraycopy(first,0,result,0,first.length);
        System.arraycopy(second,0,result,first.length,second.length);
        return result;
    }
}
