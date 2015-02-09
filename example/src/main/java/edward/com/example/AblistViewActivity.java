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

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import edward.com.abslistview.AnimationAdapter;
import edward.com.animation.effects.Direction;
import edward.com.animation.effects.Slide;
import edward.com.example.adapter.MyAdapter;


public class AblistViewActivity extends ListActivity{

    private List<String> values = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abs_listveiw);
        for(int i=0;i<100;i++){
            values.add("Value" + i);
        }
        MyAdapter adapter = new MyAdapter(this,values);
        AnimationAdapter animationAdapter = new AnimationAdapter(adapter,
                new Slide(Direction.RIGHT).setParent(getListView()).
                        setDuration(500));
        animationAdapter.setAbsListView(getListView());
        animationAdapter.addScrollHelper();
        setListAdapter(animationAdapter);
    }
}
