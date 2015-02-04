package edward.com.animation.effects;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.view.View;

import edward.com.animation.Animators.BaseAnimators;
import edward.com.animation.enums.Action;
import edward.com.animation.enums.AnimPropertyName;
import edward.com.animation.enums.Direction;
import edward.com.animation.impl.Effect4View;
import edward.com.animation.impl.EffectTransformer;
import edward.com.animation.impl.HasAction;
import edward.com.animation.impl.HasDirection;
import static edward.com.animation.enums.AnimPropertyName.*;

/**
 * Now just for ViewPager,Fade In and Fade Out
 * Time 2015-2-1
 * */
public class Fade extends EffectTransformer implements Effect4View,HasAction,HasDirection{

    private Direction direction;
    private Action action = Action.IN;
    private long duration = BaseAnimators.DEFAULT_DURATION;
    public Fade(){

    }

    public Fade(Action action,Direction direction){
        this.direction = direction;
        this.action = action;
    }

    @Override
    protected void onTransform(View page, float position) {

    }

    @Override
    public Animator[] getAnimators(View target) {
        if (direction == null){
            return fade(target);
        }
        return direction.getAnimators(target,this);
    }

    private Animator[] fade(View target){
        return new Animator[]{
                new BaseAnimators(target,duration,action).setAnimator(ALPHA)
                        .getAnimator()
        };
    }

    @Override
    public void setAction(@NonNull Action action) {
        this.action = action;
    }

    @Override
    public void setDirection(@NonNull Direction direction) {
        this.direction = direction;
    }

    public Fade setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public Animator[] top(View target) {
        float fromY = -target.getHeight()/4;
        float toY = 0;
        switch (action){
            case OUT:
                fromY = 0;
                toY = -target.getHeight()/4;
                break;
        }
        return generate(target,fromY,toY,TRANSLATION_Y);
    }

    @Override
    public Animator[] topLeft(View target) {
        Animator[] first = top(target);
        Animator[] second = left(target);
        return generate(first,second);
    }

    @Override
    public Animator[] topRight(View target) {
        Animator[] first = top(target);
        Animator[] second = right(target);
        return generate(first,second);
    }

    @Override
    public Animator[] left(View target) {
        float fromY = -target.getWidth()/4;
        float toY = 0;
        switch (action){
            case OUT:
                fromY = 0;
                toY = -target.getWidth()/4;
                break;
        }
        return generate(target, fromY, toY, TRANSLATION_X);
    }

    @Override
    public Animator[] right(View target) {
        float fromY = target.getWidth()/4;
        float toY = 0;
        switch (action){
            case OUT:
                fromY = 0;
                toY = target.getWidth()/4;
                break;
        }
        return generate(target, fromY, toY, TRANSLATION_X);
    }

    @Override
    public Animator[] bottom(View target) {
        float fromY = target.getHeight()/4;
        float toY = 0;
        switch (action){
            case OUT:
                fromY = 0;
                toY = target.getHeight()/4;
                break;
        }
        return generate(target,fromY,toY,TRANSLATION_Y);
    }

    @Override
    public Animator[] bottomLeft(View target) {
        Animator[] first = bottom(target);
        Animator[] second = left(target);
        return generate(first,second);
    }

    @Override
    public Animator[] bottomRight(View target) {
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

    private Animator[] generate(View target,float from,float to,AnimPropertyName value){
        return new Animator[]{
                new BaseAnimators(target,duration,action).setAnimator(ALPHA)
                        .getAnimator(),
                new BaseAnimators(target,duration,action).setAnimatorNoAction(value,from,to)
                        .getAnimator()
        };
    }
}
