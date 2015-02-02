package edward.com.animation.effects;

import android.view.View;

import edward.com.animation.utils.LayerUtil;

public abstract class Cude {

	protected void setAnimations(View left, View right, float positionOffset,
                              boolean in) {
		if (left != null) {
			LayerUtil.manageLayer(left, true);
			float mRot = (in ? 90.0f : -90.0f) * positionOffset;
			left.setPivotX(left.getMeasuredWidth());
			left.setPivotY(left.getMeasuredHeight()*0.5f);
			left.setRotationY( mRot);
		}
		if (right != null) {
			LayerUtil.manageLayer(right, true);
			float mRot = -(in ? 90.0f : -90.0f) * (1-positionOffset);
			right.setPivotX(0);
			right.setPivotY(right.getMeasuredHeight()*0.5f);
			right.setRotationY(mRot);
		}
	}
}
