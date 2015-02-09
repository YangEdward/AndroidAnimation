/*
 * Copyright (C) 2015 YangEdward
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edward.com.abslistview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import edward.com.animation.AnimatorManager;
import edward.com.animation.effects.Effect4View;
import edward.com.animation.effects.EffectHasDirection;

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
    private Effect4View effects;
    //public static final int DURATION = 600;
    private AbsListView.OnScrollListener mAdditionalOnScrollListener;

    public ScrollHelper() {
    }

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

    public void setEffect(EffectHasDirection effect) {
        effects = effect;
        //addEffect(effect);
    }


    private void doAnimationImpl(View item, int position, int scrollDirection) {
        /*scrollDirection = scrollDirection > 0 ? 1 : -1;*/
        AnimatorManager.with(item)
                .putEffect(effects)
                .animate();
        /*mEffect.initView(item, position, scrollDirection);
        mEffect.setupAnimation(item, position, scrollDirection, animator);
        animator.start();*/
    }

    public boolean isScrolling(){
        return mIsScrolling;
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
