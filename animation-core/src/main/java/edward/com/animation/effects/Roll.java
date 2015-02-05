package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by Administrator on 2015/2/2.
 */
public class Roll extends NoDirection implements HasAction {

    private Action action = Action.IN;
    public Roll(@NonNull Action action){
        this.action = action;
    }
    public Roll(@NonNull Action action,long duration){
        super(duration);
        this.action = action;
    }
    @Override
    public Animator[] getAnimators(View target) {
        float [] alpha = {0, 1};
        float [] translationX = {-(target.getWidth()-target.getPaddingLeft() - target.getPaddingRight()),0};
        float [] rotation = {-120,0};
        switch (action){
            case IN:
                break;
            case OUT:
                alpha = new float[]{1,0};
                translationX = new float[]{0,target.getWidth()};
                rotation = new float[]{0,120};
                break;
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(target,"alpha",alpha),
                ObjectAnimator.ofFloat(target,"translationX",translationX),
                ObjectAnimator.ofFloat(target,"rotation",rotation)
        };
    }

    @Override
    public void setAction(@NonNull Action action) {
        this.action = action;
    }
}
