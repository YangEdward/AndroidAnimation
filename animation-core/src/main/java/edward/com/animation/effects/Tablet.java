/*
 * Copyright 2014 Toxic Bakery
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edward.com.animation.effects;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;

import edward.com.animation.impl.EffectTransformer;


public class Tablet extends EffectTransformer {

    private static final Matrix mMatrix = new Matrix();
    private static final Camera mCamera = new Camera();
    private static final float[] mTempFloat = new float[2];

    @Override
    protected void onTransform(View view, float position) {
        final float rotation = (position < 0 ? 30f : -30f) * Math.abs(position);

        view.setTranslationX(getOffsetXForRotation(rotation, view.getWidth(), view.getHeight()));
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(0);
        view.setRotationY(rotation);
    }

    private float getOffsetXForRotation(float degrees, int width, int height) {
		mMatrix.reset();
		mCamera.save();
		mCamera.rotateY(Math.abs(degrees));
		mCamera.getMatrix(mMatrix);
		mCamera.restore();

		mMatrix.preTranslate(-width * 0.5f, -height * 0.5f);
		mMatrix.postTranslate(width * 0.5f, height * 0.5f);
        mTempFloat[0] = width;
        mTempFloat[1] = height;
		mMatrix.mapPoints(mTempFloat);
		return (width - mTempFloat[0]) * (degrees > 0.0f ? 1.0f : -1.0f);
	}

}
