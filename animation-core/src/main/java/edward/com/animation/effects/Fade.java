package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;

import edward.com.animation.enums.Action;
import edward.com.animation.enums.Direction;
import edward.com.animation.impl.Effect4View;
import edward.com.animation.impl.EffectTransformer;
import edward.com.animation.impl.HasAction;
import edward.com.animation.impl.HasDirection;

/**
 * Now just for ViewPager,Fade In and Fade Out
 * Time 2015-2-1
 * */
public class Fade extends EffectTransformer implements Effect4View,HasAction,HasDirection{

    private Direction direction;
    private Action action = Action.IN;

    public Fade(){

    }

    public Fade(Direction direction,Action action){
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
        int from = 0,to = 1;
        switch (action){
            case IN:
                break;
            case OUT:
                from = 1;
                to = 0;
                break;
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(target,"alpha",from,to)
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

    @Override
    public Animator[] top(View target) {
        int from = 0,to = 1;
        float fromY = target.getHeight()/4;
        float toY = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                from = 1;
                to = 0;
                fromY = 0;
                toY = -target.getHeight()/4;
                break;
        }
        return new Animator[]{
            ObjectAnimator.ofFloat(target, "alpha", from, to),
            ObjectAnimator.ofFloat(target, "translationY", fromY, toY)
        };
    }

    @Override
    public Animator[] topLeft(View target) {
        return new Animator[0];
    }

    @Override
    public Animator[] topRight(View target) {
        return new Animator[0];
    }

    @Override
    public Animator[] left(View target) {
        int from = 0,to = 1;
        float fromY = -target.getWidth()/4;
        float toY = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                from = 1;
                to = 0;
                fromY = 0;
                toY = -target.getWidth()/4;
                break;
        }
        return new Animator[]{
            ObjectAnimator.ofFloat(target, "alpha", from, to),
            ObjectAnimator.ofFloat(target, "translationX", fromY, toY)
        };
    }

    @Override
    public Animator[] right(View target) {
        int from = 0,to = 1;
        float fromY = target.getWidth()/4;
        float toY = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                from = 1;
                to = 0;
                fromY = 0;
                toY = target.getWidth()/4;
                break;
        }
        return new Animator[]{
            ObjectAnimator.ofFloat(target, "alpha", from, to),
            ObjectAnimator.ofFloat(target, "translationX", fromY, toY)
        };
    }

    @Override
    public Animator[] bottom(View target) {
        int from = 0,to = 1;
        float fromY = -target.getHeight()/4;
        float toY = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                from = 1;
                to = 0;
                fromY = 0;
                toY = target.getHeight()/4;
                break;
        }
        return new Animator[]{
            ObjectAnimator.ofFloat(target, "alpha", from, to),
            ObjectAnimator.ofFloat(target, "translationY", fromY, toY)
        };
    }

    @Override
    public Animator[] bottomLeft(View target) {
        return new Animator[0];
    }

    @Override
    public Animator[] bottomRight(View target) {
        return new Animator[0];
    }
}
