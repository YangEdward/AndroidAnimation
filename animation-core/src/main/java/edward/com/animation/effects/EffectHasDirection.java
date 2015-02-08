package edward.com.animation.effects;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import edward.com.animation.evaluators.BaseEvaluator;

public abstract class EffectHasDirection implements Effect4View{

    protected Direction direction;
    protected Action action = Action.IN;
    protected long duration = AnimatorBuilder.DEFAULT_DURATION;
    protected BaseEvaluator evaluator;
    protected ViewGroup parent;

    protected EffectHasDirection(Direction direction) {
        this(direction,Action.IN);
    }

    protected EffectHasDirection(Direction direction, Action action) {
        this.direction = direction;
        this.action = action;
    }

    public EffectHasDirection setDirection(Direction direction){
        this.direction = direction;
        return this;
    }

    public EffectHasDirection setAction(@NonNull Action action) {
        this.action = action;
        return this;
    }

    public EffectHasDirection setEvaluator(@NonNull BaseEvaluator evaluator) {
        this.evaluator = evaluator;
        return this;
    }

    public EffectHasDirection setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public long getDuration() {
        return duration;
    }

    public EffectHasDirection setParent(ViewGroup parent) {
        this.parent = parent;
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
    }
    public Animator[] topRight(View target){
        Animator[] first = top(target);
        Animator[] second = right(target);
        return generate(first,second);
    }

    public Animator[] bottomLeft(View target){
        Animator[] first = bottom(target);
        Animator[] second = left(target);
        return generate(first,second);
    }

    public Animator[] bottomRight(View target){
        Animator[] first = bottom(target);
        Animator[] second = right(target);
        return generate(first,second);
    }

    private Animator[] generate(Animator[] first,Animator[] second){
        Animator[] result = new Animator[first.length+second.length];
        System.arraycopy(first,0,result,0,first.length);
        System.arraycopy(second,0,result,first.length,second.length);
        return result;
    }

    protected void isParentNull(View target){
        if(parent == null){
            parent = (ViewGroup)target.getParent();
            if(parent == null){
                throw new IllegalStateException("Call setParent() on this Effect first!");
            }
        }
    }
}
