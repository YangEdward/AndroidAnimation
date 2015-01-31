package edward.com.animation.effects;

import android.support.v4.view.ViewPager;
import android.view.View;


import edward.com.animation.impl.Effect4ViewPager;

public class Fade implements Effect4ViewPager {

	@Override
	public void setAnimations(View left, View right, float positionOffset,int positionOffsetPixels) {
		if (left != null) {
			left.setAlpha(1-positionOffset);
		}
		if (right != null) {
			right.setAlpha(positionOffset);
		}
	}

    @Override
    public void setViewPager(ViewPager pager) {
        return;
    }
}
