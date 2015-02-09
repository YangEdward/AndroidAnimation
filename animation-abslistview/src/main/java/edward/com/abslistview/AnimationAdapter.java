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

import android.animation.Animator;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import edward.com.abslistview.util.AnimatorUtil;
import edward.com.abslistview.util.ListViewWrapper;
import edward.com.animation.effects.Direction;
import edward.com.animation.effects.EffectHasDirection;
import edward.com.animation.effects.Slide;

/**
 * A {@link BaseAdapterDecorator} class which applies multiple {@link android.animation.Animator}s at once to views when they are first shown. The Animators applied include the animations specified
 * in {@link #getAnimators(android.view.ViewGroup, android.view.View)}, plus an alpha transition.
 */
public class AnimationAdapter extends BaseAdapterDecorator {

    private EffectHasDirection effect;
    private ScrollHelper helper;
    /**
     * Saved instance state key for the ViewAnimator
     */
    private static final String SAVED_INSTANCE_STATE_VIEW_ANIMATOR = "saved_instance_state_view_animator";

    /**
     * The ViewAnimator responsible for animating the Views.
     */
    @Nullable
    private ViewAnimator mViewAnimator;

    /**
     * Whether this instance is the root AnimationAdapter. When this is set to false, animation is not applied to the views, since the wrapper AnimationAdapter will take care of
     * that.
     */
    private boolean mIsRootAdapter;

    /**
     * If the AbsListView is an instance of GridView, this boolean indicates whether the GridView is possibly measuring the view.
     */
    private boolean mGridViewPossiblyMeasuring;

    /**
     * The position of the item that the GridView is possibly measuring.
     */
    private int mGridViewMeasuringPosition;

    /**
     * Creates a new AnimationAdapter, wrapping given BaseAdapter.
     *
     * @param baseAdapter the BaseAdapter to wrap.
     */
    public AnimationAdapter(@NonNull final BaseAdapter baseAdapter,@NonNull EffectHasDirection effect) {
        super(baseAdapter);
        this.effect = effect;
        mGridViewPossiblyMeasuring = true;
        mGridViewMeasuringPosition = -1;
        mIsRootAdapter = true;
        if (baseAdapter instanceof AnimationAdapter) {
            ((AnimationAdapter) baseAdapter).setIsWrapped();
        }
    }

    @Override
    public void setListViewWrapper(@NonNull final ListViewWrapper listViewWrapper) {
        super.setListViewWrapper(listViewWrapper);
        mViewAnimator = new ViewAnimator(listViewWrapper);
    }

    /**
     * Sets whether this instance is wrapped by another instance of AnimationAdapter. If called, this instance will not apply any animations to the views, since the wrapper
     * AnimationAdapter handles that.
     */
    private void setIsWrapped() {
        mIsRootAdapter = false;
    }

    /**
     * Call this method to reset animation status on all views. The next time {@link #notifyDataSetChanged()} is called on the base adapter, all views will animate again.
     */
    public void reset() {
        if (getListViewWrapper() == null) {
            throw new IllegalStateException("Call setAbsListView() on this AnimationAdapter first!");
        }

        assert mViewAnimator != null;
        mViewAnimator.reset();

        mGridViewPossiblyMeasuring = true;
        mGridViewMeasuringPosition = -1;

        if (getDecoratedBaseAdapter() instanceof AnimationAdapter) {
            ((AnimationAdapter) getDecoratedBaseAdapter()).reset();
        }
    }

    /**
     * Returns the {@link edward.com.abslistview.ViewAnimator} responsible for animating the Views in this adapter.
     */
    @Nullable
    public ViewAnimator getViewAnimator() {
        return mViewAnimator;
    }

    @NonNull
    @Override
    public final View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        if (mIsRootAdapter) {
            if (getListViewWrapper() == null) {
                throw new IllegalStateException("Call setAbsListView() on this AnimationAdapter first!");
            }

            assert mViewAnimator != null;
            if (convertView != null) {
                mViewAnimator.cancelExistingAnimation(convertView);
            }
        }

        View itemView = super.getView(position, convertView, parent);
        if (mIsRootAdapter && !(helper != null && helper.isScrolling())) {
            animateViewIfNecessary(position, itemView, parent);
        }
        return itemView;
    }

    /**
     * Animates given View if necessary.
     *
     * @param position the position of the item the View represents.
     * @param view     the View that should be animated.
     * @param parent   the parent the View is hosted in.
     */
    private void animateViewIfNecessary(final int position, @NonNull final View view, @NonNull final ViewGroup parent) {
        assert mViewAnimator != null;

        /* GridView measures the first View which is returned by getView(int, View, ViewGroup), but does not use that View.
           On KitKat, it does this actually multiple times.
           Therefore, we animate all these first Views, and reset the last animated position when we suspect GridView is measuring. */
        mGridViewPossiblyMeasuring = mGridViewPossiblyMeasuring && (mGridViewMeasuringPosition == -1 || mGridViewMeasuringPosition == position);

        if (mGridViewPossiblyMeasuring) {
            mGridViewMeasuringPosition = position;
            mViewAnimator.setLastAnimatedPosition(-1);
        }

        Animator[] childAnimators;

        if (getDecoratedBaseAdapter() instanceof AnimationAdapter) {
            AnimationAdapter animationAdapter = ((AnimationAdapter) getDecoratedBaseAdapter());
            childAnimators = animationAdapter.getAnimators(parent, view);
            /*if(animationAdapter.getHelper() != null){
                addOldHelperEffectsToNewHelper(animationAdapter.getEffects());
            }*/
        } else {
            childAnimators = new Animator[0];
        }
        setChildDuration(childAnimators);
        Animator[] animators = getAnimators(parent, view);
        Animator[] concatAnimators = AnimatorUtil.concatAnimators(childAnimators, animators);
        mViewAnimator.animateViewIfNecessary(position, view, concatAnimators);
    }

    /*private void addOldHelperEffectsToNewHelper(EffectHasDirection effectHasDirection){
        if(helper == null){
            addScrollHelper();
        }
        helper.addEffect(effectHasDirection);
    }*/

    private void setChildDuration(Animator[] childAnimators){
        if (childAnimators.length != 0){
            long duration = getDuration();
            for (Animator animator : childAnimators){
                animator.setDuration(duration);
            }
        }
    }
    /**
     * Returns the Animators to apply to the views. In addition to the returned Animators, an alpha transition will be applied to the view.
     *
     * @param parent The parent of the view
     * @param view   The view that will be animated, as retrieved by getView().
     */
    @NonNull
    public Animator[] getAnimators(@NonNull ViewGroup parent, @NonNull View view){
        effect.setParent(parent);
        return effect.getAnimators(view);
    }

    @NonNull
    public EffectHasDirection getEffects(){
        return effect;
    }

    public void setEffect(EffectHasDirection effect) {
        this.effect = effect;
        helper.setEffect(effect);
    }

    public long getDuration(){
        return effect.getDuration();
    }

    public void addScrollHelper() {
        helper = new ScrollHelper();
        helper.setEffect(effect);
        if (getListViewWrapper() == null) {
            throw new IllegalStateException("Call setAbsListView() on this AnimationAdapter first!");
        }
        ((AbsListView)getListViewWrapper().getListView())
                .setOnScrollListener(helper);
    }

    public ScrollHelper getHelper() {
        return helper;
    }

    /**
     * Returns a Parcelable object containing the AnimationAdapter's current dynamic state.
     */
    @NonNull
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();

        if (mViewAnimator != null) {
            bundle.putParcelable(SAVED_INSTANCE_STATE_VIEW_ANIMATOR, mViewAnimator.onSaveInstanceState());
        }

        return bundle;
    }

    /**
     * Restores this AnimationAdapter's state.
     *
     * @param parcelable the Parcelable object previously returned by {@link #onSaveInstanceState()}.
     */
    public void onRestoreInstanceState(@Nullable final Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            if (mViewAnimator != null) {
                mViewAnimator.onRestoreInstanceState(bundle.getParcelable(SAVED_INSTANCE_STATE_VIEW_ANIMATOR));
            }
        }
    }
}
