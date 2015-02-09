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
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import edward.com.abslistview.AnimationAdapter;
import edward.com.animation.effects.Direction;
import edward.com.animation.effects.Fade;
import edward.com.animation.effects.Rotate;
import edward.com.animation.effects.Slide;
import edward.com.example.adapter.DirectionType;
import edward.com.example.adapter.MyListAdapter;
import edward.com.example.adapter.RecyclerAdapter;
import edward.com.recyclerview.RecyclerAdapterDecorator;

public class ListViewActivity extends ActionBarActivity {
    private ListView listView;
    private List<String> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abs_listveiw);
        listView = (ListView)findViewById(R.id.viewList);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        for (int i = 0; i < 100; i++){
            data.add("Item" + i);
        }

        MyListAdapter adapter = new MyListAdapter(this,
                new ArrayList<>(data));
        final AnimationAdapter animationAdapter = new AnimationAdapter(adapter,
                new Slide(Direction.RIGHT).setParent(listView).
                        setDuration(500));
        animationAdapter.setAbsListView(listView);
        animationAdapter.addScrollHelper();
        listView.setAdapter(animationAdapter);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (DirectionType type : DirectionType.values()) {
            spinnerAdapter.add(type.name());
        }
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //animationAdapter.setEffect(DirectionType.values()[position].getAnimator());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
