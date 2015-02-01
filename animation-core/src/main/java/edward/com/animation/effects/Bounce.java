package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import edward.com.animation.enums.Action;
import edward.com.animation.enums.Direction;
import edward.com.animation.impl.Effect4View;
import edward.com.animation.impl.HasAction;
import edward.com.animation.impl.HasDirection;

/**
 * Now just for ViewPager and Bounce in, doesn't support Bounce out
 * Time 2015-2-1
 * Created by Edward on 2015/2/1.
 */
public class Bounce implements Effect4View,HasAction,HasDirection{

    private Direction direction;
    /*Support Action.In*/
    private Action action;

    public Bounce(Action action,Direction direction){
        this.action = action;
        this.direction = direction;
    }

    @Override
    public Animator[] getAnimators(View target) {
        if(action == null){
            return new Animator[]{ObjectAnimator.ofFloat(target, "translationY", 0, 0, -30, 0, -15, 0, 0)};
        }
        if (direction == null){
            return bounce(target);
        }
        return direction.getAnimators(target,this);
    }

    private Animator[] bounce(View target){
        return new Animator[]{
                ObjectAnimator.ofFloat(target,"alpha",0,1, 1 ,1),
                ObjectAnimator.ofFloat(target,"scaleX",0.3f,1.05f,0.9f,1),
                ObjectAnimator.ofFloat(target,"scaleY",0.3f,1.05f,0.9f,1)
        };
    }

    @Override
    public void reset(View target) {
        target.setAlpha(1);
        target.setScaleX(1);
        target.setScaleY(1);
        target.setTranslationX(0);
        target.setTranslationY(0);
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public Animator[] top(View target) {
        return new Animator[]{
                ObjectAnimator.ofFloat(target,"translationY",target.getMeasuredHeight(), -30,10,0),
                ObjectAnimator.ofFloat(target,"alpha",0,1,1,1)
        };
    }

    @Override
    public Animator[] topLeft(View target) {
        return new Animator[0];
    }

    @Override
    public Animator[] topRight(View target) {
        return new Animator[0];
    }

    @Override
    public Animator[] left(View target) {
        return new Animator[]{
                ObjectAnimator.ofFloat(target,"translationX",-target.getWidth(),30,-10,0),
                ObjectAnimator.ofFloat(target,"alpha",0,1,1,1)
        };
    }

    @Override
    public Animator[] right(View target) {
        return new Animator[]{
                ObjectAnimator.ofFloat(target,"translationX",target.getMeasuredWidth()+target.getWidth(),-30,10,0),
                ObjectAnimator.ofFloat(target,"alpha",0,1,1,1)
        };
    }

    @Override
    public Animator[] bottom(View target) {
        return new Animator[]{
                ObjectAnimator.ofFloat(target,"alpha",0,1,1,1),
                ObjectAnimator.ofFloat(target,"translationY",-target.getHeight(),30,-10,0)
        };
    }

    @Override
    public Animator[] bottomLeft(View target) {
        return new Animator[0];
    }

    @Override
    public Animator[] bottomRight(View target) {
        return new Animator[0];
    }
}
