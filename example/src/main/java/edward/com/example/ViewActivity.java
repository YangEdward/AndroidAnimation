package edward.com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edward.com.animation.AnimatorManager;
import edward.com.animation.effects.*;

public class ViewActivity extends Activity implements View.OnClickListener {
    private TextView text1;
    private TextView text2;
    private int i = 0;
    private Direction[] directions;
    private Action action = Action.IN;
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
        directions = Direction.values();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start1:
                AnimatorManager.with(text1).putEffect(new Pulse().setDuration(2000)).animate();
                break;
            case R.id.start2:
                AnimatorManager.with(text2).putEffect(new Landing().setDuration(2000)).animate();
                /*AnimatorManager.with(text2).putEffect(new Rotate(action).setDuration(2500)).animate();
                Toast.makeText(ViewActivity.this,directions[i].toString(),Toast.LENGTH_LONG).show();
                i++;
                if(i == 1){
                    i = 0;
                    if(action == Action.IN){
                        action = Action.OUT;
                    }else{
                        action = Action.IN;
                    }
                }*/
                break;
        }
    }
}
