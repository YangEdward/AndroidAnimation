package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;

import static edward.com.animation.effects.AnimPropertyName.ALPHA;

public class Roll extends EffectHasDirection {

    private Action action = Action.IN;

    public Roll(){
        super(Direction.TOP_LEFT);
    }

    public Roll(@NonNull Action action){
        super(Direction.TOP_LEFT);
        this.action = action;
    }
    public Roll(@NonNull Action action,@NonNull Direction direction){
        super(direction);
        this.action = action;
    }

    @Override
    public Animator[] getAnimators(@NonNull View target) {
        return direction.getAnimators(target,this);
    }

    @Override
    public Animator[] topLeft(View target) {
        float [] translationX = {-target.getWidth(),0};
        float [] rotation = {-120,0};
        switch (action){
            case IN:
                break;
            case OUT:
                translationX = new float[]{0,-target.getWidth()};
                rotation = new float[] {0,-120};
                break;
        }
        return generate(target,translationX,rotation);
    }

    @Override
    public Animator[] topRight(View target) {
        float [] translationX = {target.getWidth(),0};
        float [] rotation = {120,0};
        switch (action){
            case IN:
                break;
            case OUT:
                translationX = new float[]{0,target.getWidth()};
                rotation = new float[]{0,120};
                break;
        }
        return generate(target,translationX,rotation);
    }

    @Override
    public Animator[] bottomLeft(View target) {
        float [] translationX = {-target.getWidth(),0};
        float [] rotation = {120,0};
        switch (action){
            case IN:
                break;
            case OUT:
                translationX = new float[]{0,-target.getWidth()};
                rotation = new float[] {0,120};
                break;
        }
        return generate(target,translationX,rotation);
    }

    @Override
    public Animator[] bottomRight(View target) {
        float [] translationX = {target.getWidth(),0};
        float [] rotation = {-120,0};
        switch (action){
            case IN:
                break;
            case OUT:
                translationX = new float[]{0,target.getWidth()};
                rotation = new float[]{0,-120};
                break;
        }
        return generate(target,translationX,rotation);
    }

    @Override
    protected Animator[] top(View target) {
        return new Animator[0];
    }

    @Override
    protected Animator[] left(View target) {
        return new Animator[0];
    }

    @Override
    protected Animator[] right(View target) {
        return new Animator[0];
    }

    @Override
    protected Animator[] bottom(View target) {
        return new Animator[0];
    }


    private Animator[] generate(View target,float[] translationX,float[] rotation){
        return new Animator[]{
                new AnimatorBuilder(target,duration,action).setAnimator(ALPHA)
                        .getAnimator(),
                new AnimatorBuilder(target,duration,action).setAnimatorNoAction(AnimPropertyName.TRANSLATION_X,
                        translationX[0],translationX[1])
                        .getAnimator(),
                new AnimatorBuilder(target,duration,action).setAnimatorNoAction(AnimPropertyName.ROTATION,
                        rotation[0],rotation[1])
                        .getAnimator()
        };
    }
}
