package edward.com.abslistview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import java.util.HashSet;

import edward.com.animation.AnimatorManager;
import edward.com.animation.effects.Effect4View;

public class ScrollHelper implements AbsListView.OnScrollListener {

    private boolean mIsScrolling = false;
    private int mFirstVisibleItem = -1;
    private int mLastVisibleItem = -1;
    private int mPreviousFirstVisibleItem = 0;
    private long mPreviousEventTime = 0;
    private double mSpeed = 0;
    private int mMaxVelocity = 0;
    public static final int MAX_VELOCITY_OFF = 0;
    private boolean mOnlyAnimateNewItems;
    private boolean mOnlyAnimateOnFling;
    private boolean mIsFlingEvent;
    private boolean mSimulateGridWithList;
    private final HashSet<Integer> mAlreadyAnimatedItems = new HashSet<>();
    private Effect4View mEffect = null;
    public static final int DURATION = 600;
    private AbsListView.OnScrollListener mAdditionalOnScrollListener;

    public void setOnScrollListener(AbsListView.OnScrollListener l) {
        // hijack the scroll listener setter and have this list also notify the additional listener
        mAdditionalOnScrollListener = l;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch(scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                mIsScrolling = false;
                mIsFlingEvent = false;
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                mIsFlingEvent = true;
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                mIsScrolling = true;
                mIsFlingEvent = false;
                break;
            default: break;
        }
        notifyAdditionalOnScrollStateChangedListener(view, scrollState);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        boolean shouldAnimateItems = (mFirstVisibleItem != -1 && mLastVisibleItem != -1);

        int lastVisibleItem = firstVisibleItem + visibleItemCount - 1;
        if (mIsScrolling && shouldAnimateItems) {
            setVelocity(firstVisibleItem, totalItemCount);
            int indexAfterFirst = 0;
            while (firstVisibleItem + indexAfterFirst < mFirstVisibleItem) {
                View item = view.getChildAt(indexAfterFirst);
                doAnimation(item, firstVisibleItem + indexAfterFirst, -1);
                indexAfterFirst++;
            }

            int indexBeforeLast = 0;
            while (lastVisibleItem - indexBeforeLast > mLastVisibleItem) {
                View item = view.getChildAt(lastVisibleItem - firstVisibleItem - indexBeforeLast);
                doAnimation(item, lastVisibleItem - indexBeforeLast, 1);
                indexBeforeLast++;
            }
        } else if (!shouldAnimateItems) {
            for (int i = firstVisibleItem; i < visibleItemCount; i++) {
                mAlreadyAnimatedItems.add(i);
            }
        }

        mFirstVisibleItem = firstVisibleItem;
        mLastVisibleItem = lastVisibleItem;

        notifyAdditionalOnScrollListener(view, firstVisibleItem, visibleItemCount, totalItemCount);
    }

    /**
     * Should be called in onScroll to keep take of current Velocity.
     *
     * @param firstVisibleItem
     *            The index of the first visible item in the ListView.
     */
    private void setVelocity(int firstVisibleItem, int totalItemCount) {
        if (mMaxVelocity > MAX_VELOCITY_OFF && mPreviousFirstVisibleItem != firstVisibleItem) {
            long currTime = System.currentTimeMillis();
            long timeToScrollOneItem = currTime - mPreviousEventTime;
            if (timeToScrollOneItem < 1) {
                double newSpeed = ((1.0d / timeToScrollOneItem) * 1000);
                // We need to normalize velocity so different size item don't
                // give largely different velocities.
                if (newSpeed < (0.9f * mSpeed)) {
                    mSpeed *= 0.9f;
                } else if (newSpeed > (1.1f * mSpeed)) {
                    mSpeed *= 1.1f;
                } else {
                    mSpeed = newSpeed;
                }
            } else {
                mSpeed = ((1.0d / timeToScrollOneItem) * 1000);
            }

            mPreviousFirstVisibleItem = firstVisibleItem;
            mPreviousEventTime = currTime;
        }
    }

    /**
     *
     * @return Returns the current Velocity of the ListView's scrolling in items
     *         per second.
     */
    private double getVelocity() {
        return mSpeed;
    }

    /**
     * Initializes the item view and triggers the animation.
     *
     * @param item The view to be animated.
     * @param position The index of the view in the list.
     * @param scrollDirection Positive number indicating scrolling down, or negative number indicating scrolling up.
     */
    private void doAnimation(View item, int position, int scrollDirection) {
        if (mIsScrolling) {
            if (mOnlyAnimateNewItems && mAlreadyAnimatedItems.contains(position))
                return;

            if (mOnlyAnimateOnFling && !mIsFlingEvent)
                return;

            if (mMaxVelocity > MAX_VELOCITY_OFF && mMaxVelocity < getVelocity())
                return;

            if (mSimulateGridWithList) {
                ViewGroup itemRow = (ViewGroup) item;
                for (int i = 0; i < itemRow.getChildCount(); i++)
                    doAnimationImpl(itemRow.getChildAt(i), position, scrollDirection);
            } else {
                doAnimationImpl(item, position, scrollDirection);
            }

            mAlreadyAnimatedItems.add(position);
        }
    }

    public void setEffect(Effect4View effect) {
        mEffect = effect;
    }

    private void doAnimationImpl(View item, int position, int scrollDirection) {
        /*ViewPropertyAnimator animator = item.animate()
                .setDuration(DURATION)
                .setInterpolator(new AccelerateDecelerateInterpolator());

        scrollDirection = scrollDirection > 0 ? 1 : -1;*/
        AnimatorManager.with(item)
                .setDuration(DURATION)
                .putEffect(mEffect)
                .animate();
        /*mEffect.initView(item, position, scrollDirection);
        mEffect.setupAnimation(item, position, scrollDirection, animator);
        animator.start();*/
    }

    public void setShouldOnlyAnimateNewItems(boolean onlyAnimateNew) {
        mOnlyAnimateNewItems = onlyAnimateNew;
    }

    public void setShouldOnlyAnimateFling(boolean onlyFling) {
        mOnlyAnimateOnFling = onlyFling;
    }

    public void setMaxAnimationVelocity(int itemsPerSecond) {
        mMaxVelocity = itemsPerSecond;
    }

    public void setSimulateGridWithList(boolean simulateGridWithList) {
        mSimulateGridWithList = simulateGridWithList;
    }

    /**
     * Notifies the OnScrollListener of an onScroll event, since JazzyListView is the primary listener for onScroll events.
     */
    private void notifyAdditionalOnScrollListener(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mAdditionalOnScrollListener != null) {
            mAdditionalOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    /**
     * Notifies the OnScrollListener of an onScrollStateChanged event, since JazzyListView is the primary listener for onScrollStateChanged events.
     */
    private void notifyAdditionalOnScrollStateChangedListener(AbsListView view, int scrollState) {
        if (mAdditionalOnScrollListener != null) {
            mAdditionalOnScrollListener.onScrollStateChanged(view, scrollState);
        }
    }
}
