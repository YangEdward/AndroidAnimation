package edward.com.animation.effects;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.view.View;

import static edward.com.animation.effects.AnimPropertyName.*;

/**
 * Now just for ViewPager,Fade In and Fade Out
 * Time 2015-2-1
 * */
public class Fade extends EffectHasDirection implements HasAction{

    private Action action = Action.IN;

    public Fade(){
        super(null);
    }

    public Fade(Action action,Direction direction){
        super(direction);
        this.action = action;
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
                new AnimatorBuilder(target,duration,action).setAnimator(ALPHA)
                        .getAnimator()
        };
    }

    @Override
    public void setAction(@NonNull Action action) {
        this.action = action;
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

    private Animator[] generate(View target,float from,float to,AnimPropertyName value){
        return new Animator[]{
                new AnimatorBuilder(target,duration,action).setAnimator(ALPHA)
                        .getAnimator(),
                new AnimatorBuilder(target,duration,action).setAnimatorNoAction(value,from,to)
                        .getAnimator()
        };
    }
}
