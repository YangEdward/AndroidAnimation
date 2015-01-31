package edward.com.animation;

import java.util.HashMap;
import java.util.LinkedHashMap;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import edward.com.animation.effects.Fade;
import edward.com.animation.impl.Effect4ViewPager;
import edward.com.animation.utils.LayerUtil;
import edward.com.animationviewpager.R;


public class AnimViewPager extends ViewPager {

	private boolean mEnabled = true;
	private boolean mFadeEnabled = false;
	private Effect4ViewPager effect;
	private HashMap<Integer, Object> mObjs = new LinkedHashMap<>();


	public AnimViewPager(Context context) {
		this(context, null);
	}

	public AnimViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		setClipChildren(false);
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AnimViewPager);
		setFadeEnabled(ta.getBoolean(R.styleable.AnimViewPager_fadeEnabled, false));
		ta.recycle();
	}

	public void setPagingEnabled(boolean enabled) {
		mEnabled = enabled;
	}

	public void setFadeEnabled(boolean enabled) {
		mFadeEnabled = enabled;
	}
	
	public boolean getFadeEnabled() {
		return mFadeEnabled;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return mEnabled && super.onInterceptTouchEvent(arg0);
	}

	private State mState;
	private int oldPage;

	private enum State {
		IDLE,
		GOING_LEFT,
		GOING_RIGHT
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		if (mState == State.IDLE && positionOffset > 0) {
			oldPage = getCurrentItem();
			mState = position == oldPage ? State.GOING_RIGHT : State.GOING_LEFT;
		}
		boolean goingRight = position == oldPage;				
		if (mState == State.GOING_RIGHT && !goingRight)
			mState = State.GOING_LEFT;
		else if (mState == State.GOING_LEFT && goingRight)
			mState = State.GOING_RIGHT;
		float effectOffset = isSmall(positionOffset) ? 0 : positionOffset;
		View mLeft = findViewFromObject(position);
        View mRight = findViewFromObject(position+1);
		
		if (mFadeEnabled)
			new Fade().setAnimations(mLeft, mRight, effectOffset,positionOffsetPixels);
		if (mState != State.IDLE && effect != null) {
            effect.setViewPager(this);
			effect.setAnimations(mLeft, mRight, effectOffset,positionOffsetPixels);
		}
		super.onPageScrolled(position, positionOffset, positionOffsetPixels);
		if (effectOffset == 0) {
			LayerUtil.disableHardwareLayer(this);
			mState = State.IDLE;
		}
	}

	private boolean isSmall(float positionOffset) {
		return Math.abs(positionOffset) < 0.0001;
	}
	
	public void setObjectForPosition(Object obj, int position) {
		mObjs.put(position, obj);
	}
	
	public View findViewFromObject(int position) {
		Object o = mObjs.get(Integer.valueOf(position));
		if (o == null) {
			return null;
		}
		PagerAdapter a = getAdapter();
		View v;
		for (int i = 0; i < getChildCount(); i++) {
			v = getChildAt(i);
			if (a.isViewFromObject(v, o))
				return v;
		}
		return null;
	}

    public Effect4ViewPager getEffect() {
        return effect;
    }

    public void setEffect(Effect4ViewPager effect) {
        this.effect = effect;
    }
}