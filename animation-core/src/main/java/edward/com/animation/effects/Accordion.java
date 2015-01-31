package edward.com.animation.effects;

import android.support.v4.view.ViewPager;
import android.view.View;

import edward.com.animation.impl.Effect4ViewPager;
import edward.com.animation.utils.LayerUtil;


public class Accordion implements Effect4ViewPager {

    @Override
    public void setAnimations(View left, View right, float positionOffset, int positionOffsetPixels) {
        if (left != null) {
            LayerUtil.manageLayer(left, true);
            left.setPivotX(left.getMeasuredWidth());
            left.setPivotY(0);
            left.setScaleX(1-positionOffset);
        }
        if (right != null) {
            LayerUtil.manageLayer(right, true);
            right.setPivotX(0);
            right.setPivotY(0);
            right.setScaleX(positionOffset);
        }
    }

    @Override
    public void setViewPager(ViewPager pager) {

    }
}
