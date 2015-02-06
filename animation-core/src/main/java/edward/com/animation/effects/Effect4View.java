package edward.com.animation.effects;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.view.View;

public interface Effect4View {
    Animator[] getAnimators(@NonNull View target);
    long getDuration();
}
