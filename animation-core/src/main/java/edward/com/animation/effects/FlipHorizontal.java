package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

import edward.com.animation.enums.Action;
import edward.com.animation.impl.Effect4View;
import edward.com.animation.impl.Effect4ViewPager;
import edward.com.animation.impl.HasAction;
import edward.com.animation.utils.LayerUtil;

/**
 * Created by Edward on 2015/1/31.
 */
public class FlipHorizontal implements Effect4ViewPager,Effect4View,HasAction{

    private ViewPager pager;
    private Action action = Action.IN;

    public FlipHorizontal(){

    }

    public FlipHorizontal(@NonNull Action action){
        this.action = action;
    }

    @Override
    public void setAnimations(View left, View right, float positionOffset, int positionOffsetPixels) {
        if (left != null) {
            LayerUtil.manageLayer(left, true);
            float mRot = 180.0f * positionOffset;
            if (mRot > 90.0f) {
                left.setVisibility(View.INVISIBLE);
            } else {
                if (left.getVisibility() == View.INVISIBLE)
                    left.setVisibility(View.VISIBLE);
                int mTrans = positionOffsetPixels;
                left.setPivotX(left.getMeasuredWidth()*0.5f);
                left.setPivotY(left.getMeasuredHeight()*0.5f);
                left.setTranslationX(mTrans);
                left.setRotationY(mRot);
            }
        }
        if (right != null) {
            LayerUtil.manageLayer(right, true);
            float mRot = -180.0f * (1-positionOffset);
            if (mRot < -90.0f) {
                right.setVisibility(View.INVISIBLE);
            } else {
                if (right.getVisibility() == View.INVISIBLE)
                    right.setVisibility(View.VISIBLE);
                int mTrans = -pager.getWidth()-pager.getPageMargin()+positionOffsetPixels;
                right.setPivotX(right.getMeasuredWidth()*0.5f);
                right.setPivotY(right.getMeasuredHeight()*0.5f);
                right.setTranslationX(mTrans);
                right.setRotationY(mRot);
            }
        }
    }

    @Override
    public void setViewPager(ViewPager pager) {
        this.pager = pager;
    }

    @Override
    public Animator[] getAnimators(View target) {
        float [] alpha = {0.25f, 0.5f, 0.75f, 1};
        float [] rotationX = {90, -15, 15, 0};
        switch (action){
            case IN:
                break;
            case OUT:
                alpha = new float[]{1,0};
                rotationX = new float[]{0,90};
                break;
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(target, "alpha", alpha),
                ObjectAnimator.ofFloat(target,"rotationX",rotationX),
        };
    }

    @Override
    public void reset(View target) {
        target.setAlpha(1);
        target.setRotationX(0);
    }

    @Override
    public void setAction(@NonNull Action action) {
        this.action = action;
    }
}
