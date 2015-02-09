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
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity{

    private int [] inAnims = {
            R.animator.accordion_right_in,
            R.animator.card_flip_horizontal_right_in,
            R.animator.card_flip_vertical_right_in,
            R.animator.cube_right_in,
            R.animator.rotatedown_right_in,
            R.animator.stack_right_in,
            R.animator.zoom_from_left_corner_right_in,
    };

    private int [] outAnims = {
            R.animator.accordion_left_out,
            R.animator.card_flip_horizontal_left_out,
            R.animator.card_flip_vertical_left_out,
            R.animator.cube_left_out,
            R.animator.rotateup_left_out,
            R.animator.stack_left_out,
            R.animator.zoom_from_right_corner_left_out
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Baby[] data = new Baby[]{
                new Baby(getString(R.string.view),ViewActivity.class),
                new Baby(getString(R.string.view_group),ViewGroupActivity.class),
                new Baby(getString(R.string.viewpager),AnimationActivity.class),
                new Baby(getString(R.string.list_view),ListViewActivity.class),
                new Baby(getString(R.string.grid_view),GridViewActivity.class),
                new Baby(getString(R.string.recycler_view),RecyclerActivity.class),
        };
        ArrayAdapter<Baby> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Baby baby = (Baby)l.getItemAtPosition(position);
        Intent intent = new Intent(this,baby.getClassZ());
        startActivity(intent);
        //overridePendingTransition(inAnims[position],outAnims[position]);
    }

    class Baby{
        private String name;
        private Class<?> classZ;

        private Baby(String name, Class<?> classZ) {
            this.name = name;
            this.classZ = classZ;
        }

        public Class<?> getClassZ() {
            return classZ;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
