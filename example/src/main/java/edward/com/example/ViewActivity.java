/*
 * Copyright (C) 2015 YangEdward
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edward.com.example;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import edward.com.animation.AnimatorManager;
import edward.com.animation.effects.*;

public class ViewActivity extends Activity implements View.OnClickListener {
    private TextView text1;
    private TextView text2;
    private int i = 0;
    private Direction[] directions;
    private Action action = Action.IN;
    private LayoutTransition mTransitioner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_view);
        LinearLayout layout = (LinearLayout)findViewById(R.id.layout);
        text1 = (TextView)findViewById(R.id.firstText);
        text2 = (TextView)findViewById(R.id.secondText);
        Button mButton = (Button)findViewById(R.id.start1);
        mButton.setOnClickListener(this);
        mButton = (Button)findViewById(R.id.start2);
        mButton.setOnClickListener(this);
        directions = Direction.values();
        mTransitioner = new LayoutTransition();
        setupCustomAnimations();
        layout.setLayoutTransition(mTransitioner);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start1:
                //AnimatorManager.with(text1).putEffect(new Slide(Direction.RIGHT).setDuration(2000)).animate();
                text1.setVisibility(View.GONE);
                break;
            case R.id.start2:
                text1.setVisibility(View.VISIBLE);
                //AnimatorManager.with(text2).putEffect(new Roll(Action.OUT).setDuration(2000)).animate();
                /*AnimatorManager.with(text2).putEffect(new Roll(action,directions[i]).setDuration(2500)).animate();
                Toast.makeText(ViewActivity.this, directions[i].toString(), Toast.LENGTH_LONG).show();
                i++;
                if(i == 8){
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

    // 生成自定义动画
    private void setupCustomAnimations() {
        // 动画：CHANGE_APPEARING
        // Changing while Adding
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 1);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0,
                1);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom",
                0, 1);
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX",
                1f, 0f, 1f);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY",
                1f, 0f, 1f);

        final ObjectAnimator changeIn = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX,
                pvhScaleY).setDuration(
                mTransitioner.getDuration(LayoutTransition.CHANGE_APPEARING));
        mTransitioner.setAnimator(LayoutTransition.CHANGE_APPEARING, changeIn);
        changeIn.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setScaleX(1f);
                view.setScaleY(1f);
            }
        });

        // 动画：CHANGE_DISAPPEARING
        // Changing while Removing
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.9999f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe(
                "rotation", kf0, kf1, kf2);
        final ObjectAnimator changeOut = ObjectAnimator
                .ofPropertyValuesHolder(this, pvhLeft, pvhTop, pvhRight,
                        pvhBottom, pvhRotation)
                .setDuration(
                        mTransitioner
                                .getDuration(LayoutTransition.CHANGE_DISAPPEARING));
        mTransitioner.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,
                changeOut);
        changeOut.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotation(0f);
            }
        });

        // 动画：APPEARING
        // Adding
        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "rotationY", 90f,
                0f).setDuration(
                mTransitioner.getDuration(LayoutTransition.APPEARING));
        mTransitioner.setAnimator(LayoutTransition.APPEARING, animIn);
        animIn.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationY(0f);
            }
        });

        // 动画：DISAPPEARING
        // Removing
        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "rotationX", 0f,
                90f).setDuration(
                mTransitioner.getDuration(LayoutTransition.DISAPPEARING));
        mTransitioner.setAnimator(LayoutTransition.DISAPPEARING, animOut);
        animOut.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationX(0f);
            }
        });

    }
}
