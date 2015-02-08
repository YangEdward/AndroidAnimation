package edward.com.recyclerview;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;

import edward.com.animation.AnimatorManager;
import edward.com.animation.effects.Action;
import edward.com.animation.effects.EffectHasDirection;

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
        setEffectForAdd();
        animateView(holder,new DefaultAddVpaListener(holder));
        mAddAnimations.add(holder);
    }

    private void setEffectForRemove(){
        effectHasDirection.setDuration(getRemoveDuration());
        effectHasDirection.setAction(Action.OUT);
    }

    private void setEffectForAdd(){
        effectHasDirection.setDuration(getAddDuration());
        effectHasDirection.setAction(Action.IN);
    }

    private void animateView(RecyclerView.ViewHolder holder,Animator.AnimatorListener listener){
        AnimatorManager.with(holder.itemView)
                .putEffect(effectHasDirection)
                .addListener(listener)
                .animate();
    }
}
