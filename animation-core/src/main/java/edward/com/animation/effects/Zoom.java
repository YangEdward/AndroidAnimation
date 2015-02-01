package edward.com.animation.effects;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

import edward.com.animation.enums.Action;
import edward.com.animation.enums.Direction;
import edward.com.animation.impl.Effect4View;
import edward.com.animation.impl.HasAction;
import edward.com.animation.impl.HasDirection;
import edward.com.animation.utils.LayerUtil;

public class Zoom implements Effect4View,HasAction,HasDirection {
	protected static final float ZOOM_MAX = 0.5f;
    private Direction direction;
    private Action action = Action.IN;

    public Zoom(){

    }

    public Zoom(Direction direction,Action action){
        this.direction = direction;
        this.action = action;
    }
	
	public void setAnimations(View left, View right, float positionOffset,boolean in) {
		if (left != null) {
			LayerUtil.manageLayer(left, true);
			float mScale = in ? ZOOM_MAX + (1-ZOOM_MAX)*(1-positionOffset) :
				1+ZOOM_MAX - ZOOM_MAX*(1-positionOffset);
            left.setPivotX(left.getMeasuredWidth()*0.5f);
            left.setPivotY(left.getMeasuredHeight()*0.5f);
            left.setScaleX(mScale);
            left.setScaleY(mScale);
		}
		if (right != null) {
			LayerUtil.manageLayer(right, true);
			float mScale = in ? ZOOM_MAX + (1-ZOOM_MAX)*positionOffset :
				1+ZOOM_MAX - ZOOM_MAX*positionOffset;
            right.setPivotX(right.getMeasuredWidth()*0.5f);
            right.setPivotY(right.getMeasuredHeight()*0.5f);
            right.setScaleX(mScale);
            right.setScaleY(mScale);
		}
	}

    @Override
    public Animator[] getAnimators(View target) {
        if (direction == null){
            return zoom(target);
        }
        return direction.getAnimators(target,this);
    }

    private Animator[] zoom(View target){
        float [] alpha = {0,1};
        float [] scaleX = {0.45f,1};
        float [] scaleY = {0.45f,1};
        switch (action){
            case IN:
                break;
            case OUT:
                alpha = new float[]{1,0,0};
                scaleX = new float[]{1,0.3f,0};
                scaleY = new float[]{1,0.3f,0};
                break;
        }
        return new Animator[]{
            ObjectAnimator.ofFloat(target,"scaleX",scaleX),
            ObjectAnimator.ofFloat(target,"scaleY",scaleY),
            ObjectAnimator.ofFloat(target,"alpha",alpha)
        };
    }
    @Override
    public void reset(View target) {

    }

    @Override
    public void setAction(Action action) {

    }

    @Override
    public void setDirection(Direction direction) {

    }

    @Override
    public Animator[] top(View target) {
        float [] alpha = {0,1,1};
        float [] scaleX = {0.1f,0.475f,1};
        float [] scaleY = {0.1f,0.475f,1};
        ViewGroup parent = (ViewGroup)target.getParent();
        int distance = parent.getHeight() - target.getTop();
        float [] translationY = {distance,-60,0};
        switch (action){
            case IN:
                break;
            case OUT:
                alpha = new float[]{1,1,0};
                scaleX = new float[]{1,0.475f,0.1f};
                scaleY = new float[]{1,0.475f,0.1f};
                translationY = new float[] {0,60,-target.getBottom()};
                break;
        }
        return new Animator[]{
            ObjectAnimator.ofFloat(target,"alpha",alpha),
            ObjectAnimator.ofFloat(target,"scaleX",scaleX),
            ObjectAnimator.ofFloat(target,"scaleY",scaleY),
            ObjectAnimator.ofFloat(target,"translationY",translationY)
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
        float [] alpha = {0,1,1};
        float [] scaleX = {0.1f,0.475f,1};
        float [] scaleY = {0.1f,0.475f,1};
        float [] translationX = {-target.getRight(),48,0};
        switch (action){
            case IN:
                break;
            case OUT:
                alpha = new float[]{1,1,0};
                scaleX = new float[]{1,0.475f,0.1f};
                scaleY = new float[]{1,0.475f,0.1f};
                translationX = new float[] {0,42,-target.getRight()};
                break;
        }
        return new Animator[]{
            ObjectAnimator.ofFloat(target, "scaleX", scaleX),
            ObjectAnimator.ofFloat(target,"scaleY",scaleY),
            ObjectAnimator.ofFloat(target,"translationX",translationX),
            ObjectAnimator.ofFloat(target,"alpha",alpha)
        };
    }

    @Override
    public Animator[] right(View target) {
        float [] alpha = {0,1,1};
        float [] scaleX = {0.1f,0.475f,1};
        float [] scaleY = {0.1f,0.475f,1};
        float [] translationX = {target.getWidth() + target.getPaddingRight(),-48,0};
        switch (action){
            case IN:
                break;
            case OUT:
                ViewGroup parent = (ViewGroup)target.getParent();
                int distance = parent.getWidth() - parent.getLeft();
                alpha = new float[]{1,1,0};
                scaleX = new float[]{1,0.475f,0.1f};
                scaleY = new float[]{1,0.475f,0.1f};
                translationX = new float[] {0,-42,distance};
                break;
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(target, "scaleX", scaleX),
                ObjectAnimator.ofFloat(target,"scaleY",scaleY),
                ObjectAnimator.ofFloat(target,"translationX",translationX),
                ObjectAnimator.ofFloat(target,"alpha",alpha)
        };
    }

    @Override
    public Animator[] bottom(View target) {
        float [] alpha = {0,1,1};
        float [] scaleX = {0.1f,0.475f,1};
        float [] scaleY = {0.1f,0.475f,1};
        float [] translationX = {-target.getBottom(),60,0};
        switch (action){
            case IN:
                break;
            case OUT:
                ViewGroup parent = (ViewGroup)target.getParent();
                int distance = parent.getHeight() - target.getTop();
                alpha = new float[]{1,1,0};
                scaleX = new float[]{1,0.475f,0.1f};
                scaleY = new float[]{1,0.475f,0.1f};
                translationX = new float[] {0,-60,distance};
                break;
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(target, "scaleX", scaleX),
                ObjectAnimator.ofFloat(target,"scaleY",scaleY),
                ObjectAnimator.ofFloat(target,"translationX",translationX),
                ObjectAnimator.ofFloat(target,"alpha",alpha)
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
