package edward.com.animation.effects;

import android.support.v4.view.ViewPager;
import android.view.View;

import edward.com.animation.impl.Effect4ViewPager;
import edward.com.animation.utils.LayerUtil;

/**
 * Created by Edward on 2015/1/31.
 */
public class FlipVertical implements Effect4ViewPager{
    private ViewPager pager;
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
                left.setRotationX(mRot);
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
                right.setRotationX(mRot);
            }
        }
    }

    @Override
    public void setViewPager(ViewPager pager) {
        this.pager = pager;
    }
}
