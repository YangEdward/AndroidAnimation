package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import edward.com.animation.evaluators.BaseEvaluator;
import edward.com.animation.evaluators.ElasticOvershootEvaluator;

import static edward.com.animation.effects.AnimPropertyName.ALPHA;
import static edward.com.animation.effects.AnimPropertyName.SCALE_X;
import static edward.com.animation.effects.AnimPropertyName.SCALE_Y;

/**
 * Now just for ViewPager and Bounce in, doesn't support Bounce out
 * Time 2015-2-1
 * Created by Edward on 2015/2/1.
 */
public class Bounce extends HasDirection implements HasAction{

    /*Support Action.In*/
    private Action action;
    private long duration;
    private BaseEvaluator evaluator = new ElasticOvershootEvaluator();

    public Bounce(){
        super(null);
    }

    public Bounce(Action action) {
        super(null);
        this.action = action;
    }

    public Bounce(Action action,Direction direction){
        super(direction);
        this.action = action;
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
                new AnimatorBuilder(target,duration,action).setAnimator(ALPHA)
                        .setEvaluator(evaluator)
                        .getAnimator(),
                new AnimatorBuilder(target,duration,action).setAnimator(SCALE_X, 0.3f, 1)
                        .setEvaluator(evaluator)
                        .getAnimator(),
                new AnimatorBuilder(target,duration,action).setAnimator(SCALE_Y,0.3f,1)
                        .setEvaluator(evaluator)
                        .getAnimator()
        };
    }

    @Override
    public void setAction(Action action) {
        this.action = action;
    }

    public Bounce setDuration(long duration) {
        this.duration = duration;
        return this;
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
