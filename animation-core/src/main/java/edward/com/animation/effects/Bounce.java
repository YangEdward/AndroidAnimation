package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

import edward.com.animation.evaluators.OvershootEvaluator;

import static edward.com.animation.effects.AnimPropertyName.ALPHA;
import static edward.com.animation.effects.AnimPropertyName.SCALE_X;
import static edward.com.animation.effects.AnimPropertyName.SCALE_Y;

/**
 * Now just for ViewPager and Bounce in, doesn't support Bounce out
 * Time 2015-2-1
 * Created by Edward on 2015/2/1.
 */
public class Bounce extends EffectHasDirection implements HasAction{

    private Action action;
    private final static int repeatCount = 3;

    public Bounce(){
        super(null);
    }

    public Bounce(Action action) {
        super(null);
        this.action = action;
    }

    public Bounce(Direction direction){
        super(direction);
    }

    public Bounce(Action action,Direction direction){
        super(direction);
        this.action = action;
    }

    @Override
    public Animator[] getAnimators(View target) {
        if(action == null){
            return new Animator[]{ new AnimatorBuilder(target,duration).
                    setAnimatorNoAction(AnimPropertyName.TRANSLATION_Y,0,-30f).
                    setRepeatCount(repeatCount).
                    setRepeatMode(ValueAnimator.REVERSE).
                    getAnimator()
            };
        }
        if (direction == null){
            return bounce(target);
        }
        return direction.getAnimators(target,this);
    }

    private Animator[] bounce(View target){
        setEvaluator(new OvershootEvaluator());
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

    @Override
    public Animator[] top(View target) {
        float from = -target.getHeight();
        float to = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                to = from;
                from = 0;
                break;
        }
        return generate(target,from,to,AnimPropertyName.TRANSLATION_Y);
    }

    @Override
    public Animator[] left(View target) {
        float from = -target.getWidth();
        float to = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                to = from;
                from = 0;
                break;
        }
        return generate(target,from,to,AnimPropertyName.TRANSLATION_X);
    }

    @Override
    public Animator[] right(View target) {
        float from = target.getMeasuredWidth()+target.getWidth();
        float to = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                to = from;
                from = 0;
                break;
        }
        return generate(target,from,to,AnimPropertyName.TRANSLATION_X);

    }

    @Override
    public Animator[] bottom(View target) {
        float from = target.getMeasuredHeight();
        float to = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                to = from;
                from = 0;
                break;
        }
        return generate(target,from,to,AnimPropertyName.TRANSLATION_Y);
    }

    private Animator[] generate(View target,float from,float to,AnimPropertyName value){
        evaluator = new OvershootEvaluator();
        return new Animator[]{
                new AnimatorBuilder(target,duration,action).setAnimator(ALPHA)
                        .setEvaluator(evaluator)
                        .getAnimator(),
                new AnimatorBuilder(target,duration,action).setAnimatorNoAction(value,from,to)
                        .setEvaluator(evaluator)
                        .getAnimator()
        };
    }
}
