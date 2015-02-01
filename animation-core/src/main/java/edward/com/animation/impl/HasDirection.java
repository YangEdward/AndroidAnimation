package edward.com.animation.impl;

import android.animation.Animator;
import android.view.View;

import edward.com.animation.enums.Direction;

/**
 * Created by Edward on 2015/2/1.
 */
public interface HasDirection {
    void setDirection(Direction direction);
    Animator[] top(View target);
    Animator[] topLeft(View target);
    Animator[] topRight(View target);
    Animator[] left(View target);
    Animator[] right(View target);
    Animator[] bottom(View target);
    Animator[] bottomLeft(View target);
    Animator[] bottomRight(View target);
}
