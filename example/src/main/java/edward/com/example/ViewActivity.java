package edward.com.example;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edward.com.animation.AnimatorManager;
import edward.com.animation.effects.Action;
import edward.com.animation.effects.Direction;
import edward.com.animation.effects.Flash;
import edward.com.animation.effects.Flip;

public class ViewActivity extends Activity implements View.OnClickListener {
    private TextView text1;
    private TextView text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_view);
        text1 = (TextView)findViewById(R.id.firstText);
        text2 = (TextView)findViewById(R.id.secondText);
        Button mButton = (Button)findViewById(R.id.start1);
        mButton.setOnClickListener(this);
        mButton = (Button)findViewById(R.id.start2);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start1:
                AnimatorManager.with(text1).putEffect(new Flash().setDuration(300)).animate();
                break;
            case R.id.start2:
                AnimatorManager.with(text2).putEffect(new Flip(Action.IN, Direction.LEFT).setDuration(2500)).animate();
                break;
        }
    }
}
