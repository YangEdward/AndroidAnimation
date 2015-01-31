package edward.com.animation.effects;

import android.support.v4.view.ViewPager;
import android.view.View;

import edward.com.animation.impl.Effect4ViewPager;
import edward.com.animation.utils.LayerUtil;

public class Stack implements Effect4ViewPager {
	protected static final float SCALE_MAX = 0.5f;
    private ViewPager pager;

    @Override
    public void setAnimations(View left, View right, float positionOffset, int positionOffsetPixels) {
        if (right != null) {
            LayerUtil.manageLayer(right, true);
            float mScale = (1-SCALE_MAX) * positionOffset + SCALE_MAX;
            float mTrans = -pager.getWidth()-pager.getPageMargin()+positionOffsetPixels;
            right.setScaleX(mScale);
            right.setScaleY(mScale);
            right.setTranslationX(mTrans);
        }
        if (left != null) {
            left.bringToFront();
        }
    }

    @Override
    public void setViewPager(ViewPager pager) {
        this.pager = pager;
    }
}
