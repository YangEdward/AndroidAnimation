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

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import edward.com.animation.ViewGroupControl;
import edward.com.animation.effects.Direction;
import edward.com.animation.effects.Slide;

public class ViewGroupActivity extends Activity implements View.OnClickListener{

    private LinearLayout group1;
    private LinearLayout group2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_group);
        group1 = (LinearLayout)findViewById(R.id.group1);
        group2 = (LinearLayout)findViewById(R.id.group2);
        doAnimate();
        Button start = (Button)findViewById(R.id.start);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        doAnimate();
    }
    private void doAnimate(){
        ViewGroupControl control = ViewGroupControl.with(group1,0);
        control.setEffectForAllView(new Slide(Direction.RIGHT));
        control.setEffectForViewAt(1,new Slide(Direction.LEFT));
        control.start();
        ViewGroupControl control2 = ViewGroupControl.with(group2,0);
        control2.setEffectForAllView(new Slide(Direction.BOTTOM));
        control2.setEffectForViewAt(1,new Slide(Direction.TOP));
        control2.start();
    }
}
