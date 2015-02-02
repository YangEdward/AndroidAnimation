package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import edward.com.animation.enums.Action;
import edward.com.animation.enums.Direction;
import edward.com.animation.impl.Effect4View;
import edward.com.animation.impl.HasAction;
import edward.com.animation.impl.HasDirection;

public class Rotate implements Effect4View,HasAction,HasDirection{

    private static final float ROT_MAX = 15.0f;
    private Direction direction;
    private Action action = Action.IN;

    public Rotate(){

    }

    public Rotate(Direction direction,Action action){
        this.direction = direction;
        this.action = action;
    }

    @Override
    public Animator[] getAnimators(View target) {
        if (direction == null){
            return rotate(target);
        }
        return direction.getAnimators(target,this);
    }

    private Animator[] rotate(View target){
        int from = 0,to = 1;
        float fromRot = -200,toRot = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                from = 1;
                to = 0;
                fromRot = 0;
                toRot = 200;
                break;
        }
        return new Animator[]{
            ObjectAnimator.ofFloat(target, "rotation", fromRot, toRot),
            ObjectAnimator.ofFloat(target, "alpha", from, to)
        };
    }
    @Override
    public void reset(View target) {
        target.setAlpha(1);
        target.setRotation(0);
        target.setPivotX(target.getMeasuredWidth() / 2.0f);
        target.setPivotY(target.getMeasuredHeight() / 2.0f);
    }

    @Override
    public void setAction(Action action) {

    }

    @Override
    public void setDirection(Direction direction) {

    }

    @Override
    public Animator[] top(View target) {
        return new Animator[0];
    }

    @Override
    public Animator[] topLeft(View target) {
        float x = target.getPaddingLeft();
        float y = target.getHeight() - target.getPaddingBottom();
        int from = 0,to = 1;
        float fromRot = 90,toRot = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                from = 1;
                to = 0;
                fromRot = 0;
                toRot = -90;
                break;
        }
        return new Animator[]{
            ObjectAnimator.ofFloat(target, "rotation", fromRot, toRot),
            ObjectAnimator.ofFloat(target, "alpha", from, to),
            ObjectAnimator.ofFloat(target, "pivotX", x, x),
            ObjectAnimator.ofFloat(target, "pivotY", y, y)
        };
    }

    @Override
    public Animator[] topRight(View target) {
        float x = target.getWidth() - target.getPaddingRight();
        float y = target.getHeight() - target.getPaddingBottom();
        int from = 0,to = 1;
        float fromRot = -90,toRot = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                from = 1;
                to = 0;
                fromRot = 0;
                toRot = 90;
                break;
        }
        return new Animator[]{
            ObjectAnimator.ofFloat(target, "rotation", fromRot, toRot),
            ObjectAnimator.ofFloat(target, "alpha", from, to),
            ObjectAnimator.ofFloat(target, "pivotX", x, x),
            ObjectAnimator.ofFloat(target, "pivotY", y, y)
        };
    }

    @Override
    public Animator[] left(View target) {
        return new Animator[0];
    }

    @Override
    public Animator[] right(View target) {
        return new Animator[0];
    }

    @Override
    public Animator[] bottom(View target) {
        return new Animator[0];
    }

    @Override
    public Animator[] bottomLeft(View target) {
        float x = target.getPaddingLeft();
        float y = target.getHeight() - target.getPaddingBottom();
        int from = 0,to = 1;
        float fromRot = -90,toRot = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                from = 1;
                to = 0;
                fromRot = 0;
                toRot = 90;
                break;
        }
        return new Animator[]{
            ObjectAnimator.ofFloat(target, "rotation", fromRot, toRot),
            ObjectAnimator.ofFloat(target, "alpha", from, to),
            ObjectAnimator.ofFloat(target, "pivotX", x, x),
            ObjectAnimator.ofFloat(target, "pivotY", y, y)
        };
    }

    @Override
    public Animator[] bottomRight(View target) {
        float x = target.getWidth() - target.getPaddingRight();
        float y = target.getHeight() - target.getPaddingBottom();
        int from = 0,to = 1;
        float fromRot = 90,toRot = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                from = 1;
                to = 0;
                fromRot = 0;
                toRot = -90;
                break;
        }
        return new Animator[]{
            ObjectAnimator.ofFloat(target, "rotation", fromRot, toRot),
            ObjectAnimator.ofFloat(target, "alpha", from, to),
            ObjectAnimator.ofFloat(target, "pivotX", x, x),
            ObjectAnimator.ofFloat(target, "pivotY", y, y)
        };
    }
}
