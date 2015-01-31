package edward.com.animation.impl;

import android.support.v4.view.ViewPager;
import android.view.View;

public interface Effect4ViewPager {
	void setAnimations(View left,View right,float positionOffset,int positionOffsetPixels);
    void setViewPager(ViewPager pager);
}
