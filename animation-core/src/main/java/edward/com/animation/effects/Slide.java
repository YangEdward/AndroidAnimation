package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import static edward.com.animation.effects.AnimPropertyName.ALPHA;

public class Slide extends HasDirection implements HasAction {

    private Action action = Action.IN;

    public Slide(@NonNull Direction direction) {
        super(direction);
    }

    public Slide(@NonNull Action action,@NonNull Direction direction){
        super(direction);
        this.action = action;
    }

    @Override
    public Animator[] getAnimators(View target) {
        return direction.getAnimators(target,this);
    }


    @Override
    public void setAction(@NonNull Action action) {
        this.action = action;
    }

    @Override
    public void setDirection(@NonNull Direction direction) {
        this.direction = direction;
    }

    @Override
    public Animator[] top(View target) {
        float from = -(target.getTop() + target.getHeight());
        float to = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                from = 0;
                to = -target.getBottom();
                break;
        }
        return generate(target,from,to,AnimPropertyName.TRANSLATION_Y);
    }

    @Override
    public Animator[] left(View target) {
        ViewGroup parent = (ViewGroup)target.getParent();
        float from = target.getLeft() - parent.getWidth();
        float to = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                from = 0;
                to = -target.getRight();
                break;
        }
        return generate(target,from,to,AnimPropertyName.TRANSLATION_X);
    }

    @Override
    public Animator[] right(View target) {
        ViewGroup parent = (ViewGroup)target.getParent();
        float from = parent.getWidth() - target.getLeft();
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
        ViewGroup parent = (ViewGroup)target.getParent();
        float from = parent.getHeight() - target.getTop();
        float to = 0;
        switch (action){
            case IN:
                break;
            case OUT:
                from = 0;
                to = parent.getHeight() - target.getTop();
                break;
        }
        return generate(target,from,to,AnimPropertyName.TRANSLATION_Y);
    }

    private Animator[] generate(View target,float from,float to,AnimPropertyName value){
        return new Animator[]{
                new AnimatorBuilder(target,duration,action).setAnimator(ALPHA)
                        .getAnimator(),
                new AnimatorBuilder(target,duration,action).setAnimatorNoAction(value,from,to)
                        .getAnimator()
        };
    }
}
