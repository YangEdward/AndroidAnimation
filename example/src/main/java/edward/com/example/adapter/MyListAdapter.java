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

package edward.com.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edward.com.example.R;

public class MyListAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mDataSet;

    public MyListAdapter(Context context, List<String> dataSet) {
        mContext = context;
        mDataSet = dataSet;
    }
    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if(view == null){
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.layout_list_item, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView)view.findViewById(R.id.item_image);
            holder.text = (TextView)view.findViewById(R.id.text);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }
        Picasso.with(mContext).load(R.drawable.chip).into(holder.image);
        holder.text.setText(mDataSet.get(position));
        return view;
    }

    static class ViewHolder {

        public ImageView image;
        public TextView text;
    }
}
