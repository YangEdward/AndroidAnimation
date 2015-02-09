package edward.com.recyclerview;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;

import edward.com.animation.AnimatorManager;
import edward.com.animation.effects.Action;
import edward.com.animation.effects.EffectHasDirection;
import edward.com.animation.utils.LayerUtil;

public class RecyclerAnimator extends BaseItemAnimator {

    private EffectHasDirection effectHasDirection;

    public RecyclerAnimator(EffectHasDirection effectHasDirection) {
        this.effectHasDirection = effectHasDirection;
    }

    @Override
    protected void animateRemoveImpl(RecyclerView.ViewHolder holder) {
        setEffectForRemove();
        animateView(holder,new DefaultRemoveVpaListener(holder));
        mRemoveAnimations.add(holder);
    }

    @Override
    protected void animateAddImpl(RecyclerView.ViewHolder holder) {
    }

    private void setEffectForRemove(){
        effectHasDirection.setAction(Action.OUT);
    }

    private void animateView(RecyclerView.ViewHolder holder,Animator.AnimatorListener listener){
        LayerUtil.manageLayer(holder.itemView,true);
        AnimatorManager.with(holder.itemView)
                .setNeedReset(false)
                .putEffect(effectHasDirection)
                .addListener(listener)
                .animate();
        LayerUtil.manageLayer(holder.itemView,false);
    }
}
