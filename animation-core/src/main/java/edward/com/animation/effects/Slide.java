package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import edward.com.animation.enums.Action;
import edward.com.animation.enums.Direction;
import edward.com.animation.impl.Effect4View;
import edward.com.animation.impl.HasAction;
import edward.com.animation.impl.HasDirection;

/**
 * Created by Administrator on 2015/2/2.
 */
public class Slide implements Effect4View,HasAction,HasDirection {

    private Direction direction = Direction.TOP;
    private Action action = Action.IN;

    public Slide(@NonNull Direction direction,@NonNull Action action){
        this.direction = direction;
        this.action = action;
    }

    @Override
    public Animator[] getAnimators(View target) {
        return direction.getAnimators(target,this);
    }

    @Override
    public void reset(View target) {
        target.setTranslationX(0);
        target.setTranslationY(0);
        target.setAlpha(1);
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
        float [] alpha = {0,1};
        ViewGroup parent = (ViewGroup)target.getParent();
        int distance = parent.getHeight() - target.getTop();
        float [] translationY = {distance,0};
        switch (action){
            case IN:
                break;
            case OUT:
                alpha = new float[]{1,0};
                translationY = new float[] {0,-target.getBottom()};
                break;
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(target, "alpha", alpha),
                ObjectAnimator.ofFloat(target,"translationY",translationY)
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
        float [] alpha = {0,1};
        ViewGroup parent = (ViewGroup)target.getParent();
        int distance = parent.getWidth() - target.getLeft();
        float [] translationX = {-distance,0};
        switch (action){
            case IN:
                break;
            case OUT:
                alpha = new float[]{1,0};
                translationX = new float[] {0,-target.getRight()};
                break;
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(target, "alpha", alpha),
                ObjectAnimator.ofFloat(target,"translationX",translationX)
        };
    }

    @Override
    public Animator[] right(View target) {
        float [] alpha = {0,1};
        ViewGroup parent = (ViewGroup)target.getParent();
        int distance = parent.getWidth() - target.getLeft();
        float [] translationX = {distance,0};
        switch (action){
            case IN:
                break;
            case OUT:
                alpha = new float[]{1,0};
                translationX = new float[] {0,distance};
                break;
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(target, "alpha", alpha),
                ObjectAnimator.ofFloat(target,"translationX",translationX)
        };
    }

    @Override
    public Animator[] bottom(View target) {
        float [] alpha = {0,1};
        ViewGroup parent = (ViewGroup)target.getParent();
        int distance = target.getTop() + target.getHeight();
        float [] translationY = {-distance,0};
        switch (action){
            case IN:
                break;
            case OUT:
                alpha = new float[]{1,0};
                distance = parent.getHeight() - target.getTop();
                translationY = new float[] {0,distance};
                break;
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(target, "alpha", alpha),
                ObjectAnimator.ofFloat(target,"translationY",translationY)
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