package edward.com.example;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edward.com.animation.AnimatorManager;
import edward.com.animation.Animators.BaseAnimators;
import edward.com.animation.effects.Bounce;
import edward.com.animation.effects.Fade;
import edward.com.animation.enums.Action;
import edward.com.animation.enums.AnimPropertyName;
import edward.com.animation.enums.Direction;
import edward.com.animation.evaluators.BounceOutEvaluator;

public class ViewActivity extends Activity implements View.OnClickListener {
    private ObjectAnimator animator1;
    private ObjectAnimator animator2;
    private TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_view);
        text1 = (TextView)findViewById(R.id.firstText);
        TextView text2 = (TextView)findViewById(R.id.secondText);
        Button mButton = (Button)findViewById(R.id.start1);
        mButton.setOnClickListener(this);
        mButton = (Button)findViewById(R.id.start2);
        mButton.setOnClickListener(this);
        animator2 = new BaseAnimators(text2,2500).setAnimator(AnimPropertyName.SCALE_X,0.3f,1)
                .setRepeatCount(5).setRepeatMode(ValueAnimator.REVERSE).
                        setEvaluator(new BounceOutEvaluator()).getAnimator();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start1:
                AnimatorManager.with(text1).putEffect(new Bounce(Action.IN).setDuration(2500)).animate();
                break;
            case R.id.start2:
                animator2.start();
                break;
        }
    }
}
