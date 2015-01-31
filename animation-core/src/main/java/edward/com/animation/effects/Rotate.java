package edward.com.animation.effects;

import android.support.v4.view.ViewPager;
import android.view.View;

import edward.com.animation.utils.LayerUtil;

public abstract class Rotate {
	private static final float ROT_MAX = 15.0f;
    private ViewPager pager;
	protected void setAnimations(View left, View right, float positionOffset,boolean up) {
		if (left != null) {
			LayerUtil.manageLayer(left, true);
			float mRot = (up ? 1 : -1) * (ROT_MAX * positionOffset);
			float mTrans = (up ? -1 : 1) * (float) (pager.getMeasuredHeight() - pager.getMeasuredHeight()*Math.cos(mRot*Math.PI/180.0f));
            left.setPivotX(left.getMeasuredWidth()*0.5f);
            left.setPivotY(up ? 0 : left.getMeasuredHeight());
            left.setTranslationY(mTrans);
            left.setRotation(mRot);
		}
		if (right != null) {
			LayerUtil.manageLayer(right, true);
			float mRot = (up ? 1 : -1) * (-ROT_MAX + ROT_MAX*positionOffset);
			float mTrans = (up ? -1 : 1) * (float) (pager.getMeasuredHeight() - pager.getMeasuredHeight()*Math.cos(mRot*Math.PI/180.0f));
            right.setPivotX(right.getMeasuredWidth()*0.5f);
            right.setPivotY(up ? 0 : right.getMeasuredHeight());
            right.setTranslationY(mTrans);
            right.setRotation(mRot);
		}
	}

    protected void setViewPager(ViewPager pager){
        this.pager = pager;
    }
}
