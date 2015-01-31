package edward.com.animation.effects;

import android.view.View;

import edward.com.animation.utils.LayerUtil;

public abstract class Zoom {
	protected static final float ZOOM_MAX = 0.5f;
	
	public void setAnimations(View left, View right, float positionOffset,boolean in) {
		if (left != null) {
			LayerUtil.manageLayer(left, true);
			float mScale = in ? ZOOM_MAX + (1-ZOOM_MAX)*(1-positionOffset) :
				1+ZOOM_MAX - ZOOM_MAX*(1-positionOffset);
            left.setPivotX(left.getMeasuredWidth()*0.5f);
            left.setPivotY(left.getMeasuredHeight()*0.5f);
            left.setScaleX(mScale);
            left.setScaleY(mScale);
		}
		if (right != null) {
			LayerUtil.manageLayer(right, true);
			float mScale = in ? ZOOM_MAX + (1-ZOOM_MAX)*positionOffset :
				1+ZOOM_MAX - ZOOM_MAX*positionOffset;
            right.setPivotX(right.getMeasuredWidth()*0.5f);
            right.setPivotY(right.getMeasuredHeight()*0.5f);
            right.setScaleX(mScale);
            right.setScaleY(mScale);
		}
	}
}
