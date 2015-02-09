package edward.com.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import edward.com.animation.AnimatorManager;
import edward.com.animation.effects.Action;
import edward.com.animation.effects.EffectHasDirection;

public class RecyclerAdapterDecorator extends RecyclerView.Adapter{

    @NonNull
    private final RecyclerView.Adapter mDecoratedAdapter;
    private EffectHasDirection effectHasDirection;
    private RecyclerView recyclerView;

    public RecyclerAdapterDecorator(RecyclerView.Adapter adapter,
                                    EffectHasDirection effectHasDirection,
                                    RecyclerView recyclerView) {
        super();
        mDecoratedAdapter = adapter;
        effectHasDirection.setParent(recyclerView);
        this.effectHasDirection = effectHasDirection;
        this.recyclerView = recyclerView;
        setRecyclerViewItemAnimator(effectHasDirection);
    }

    private void setRecyclerViewItemAnimator(EffectHasDirection effectHasDirection){
        RecyclerAnimator recyclerAnimator = new RecyclerAnimator(effectHasDirection);
        recyclerView.setItemAnimator(recyclerAnimator);
    }

    @Override
    public int getItemViewType(int position) {
        return mDecoratedAdapter.getItemViewType(position);
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        mDecoratedAdapter.setHasStableIds(hasStableIds);
    }

    @Override
    public long getItemId(int position) {
        return mDecoratedAdapter.getItemId(position);
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void setEffectHasDirection(EffectHasDirection effectHasDirection) {
        effectHasDirection.setParent(recyclerView);
        this.effectHasDirection = effectHasDirection;
        setRecyclerViewItemAnimator(effectHasDirection);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        mDecoratedAdapter.onViewRecycled(holder);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mDecoratedAdapter.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        mDecoratedAdapter.onViewDetachedFromWindow(holder);
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        mDecoratedAdapter.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        mDecoratedAdapter.unregisterAdapterDataObserver(observer);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mDecoratedAdapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mDecoratedAdapter.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return mDecoratedAdapter.onCreateViewHolder(viewGroup,i);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        mDecoratedAdapter.onBindViewHolder(viewHolder,i);
        AnimatorManager.with(viewHolder.itemView)
                .setNeedReset(false)
                .putEffect(effectHasDirection.setAction(Action.IN))
                .animate();
    }

    @Override
    public int getItemCount() {
        return mDecoratedAdapter.getItemCount();
    }
}
