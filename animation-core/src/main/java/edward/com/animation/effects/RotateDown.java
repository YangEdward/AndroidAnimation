package edward.com.animation.effects;

import android.support.v4.view.ViewPager;
import android.view.View;

import edward.com.animation.impl.Effect4ViewPager;

public class RotateDown extends Rotate implements Effect4ViewPager {

    @Override
    public void setAnimations(View left, View right, float positionOffset, int positionOffsetPixels) {
        super.setAnimations(left, right, positionOffset, false);
    }

    @Override
    public void setViewPager(ViewPager pager){
        super.setViewPager(pager);
    }
}
