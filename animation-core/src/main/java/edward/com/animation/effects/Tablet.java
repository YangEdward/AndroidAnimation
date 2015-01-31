package edward.com.animation.effects;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.support.v4.view.ViewPager;
import android.view.View;

import edward.com.animation.impl.Effect4ViewPager;
import edward.com.animation.utils.LayerUtil;


public class Tablet implements Effect4ViewPager {

	private Matrix mMatrix = new Matrix();
	private Camera mCamera = new Camera();
	private float[] mTempFloat2 = new float[2];
	
	@Override
	public void setAnimations(View left, View right, float positionOffset
    ,int positionOffsetPixels) {
		if (left != null) {
			LayerUtil.manageLayer(left, true);
			float mRot = 30.0f * positionOffset;
			float mTrans = getOffsetXForRotation(mRot, left.getMeasuredWidth(),
					left.getMeasuredHeight());
			left.setPivotX(left.getMeasuredWidth()/2);
			left.setPivotY(left.getMeasuredHeight()/2);
			left.setTranslationX(mTrans);
			left.setRotationY(mRot);
		}
		if (right != null) {
			LayerUtil.manageLayer(right, true);
			float mRot = -30.0f * (1-positionOffset);
			float mTrans = getOffsetXForRotation(mRot, right.getMeasuredWidth(), 
					right.getMeasuredHeight());
			right.setPivotX(right.getMeasuredWidth()/2);
			right.setPivotY(right.getMeasuredHeight()/2);
			right.setTranslationX(mTrans);
			right.setRotationY(mRot);
		}
	}

    @Override
    public void setViewPager(ViewPager pager) {

    }

    private float getOffsetXForRotation(float degrees, int width, int height) {
		mMatrix.reset();
		mCamera.save();
		mCamera.rotateY(Math.abs(degrees));
		mCamera.getMatrix(mMatrix);
		mCamera.restore();

		mMatrix.preTranslate(-width * 0.5f, -height * 0.5f);
		mMatrix.postTranslate(width * 0.5f, height * 0.5f);
		mTempFloat2[0] = width;
		mTempFloat2[1] = height;
		mMatrix.mapPoints(mTempFloat2);
		return (width - mTempFloat2[0]) * (degrees > 0.0f ? 1.0f : -1.0f);
	}

}
