package edward.com.example;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edward.com.animation.AnimViewPager;
import edward.com.animation.effects.*;
import edward.com.animation.impl.Effect4ViewPager;

public class AnimationActivity extends ActionBarActivity {

    private AnimViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        setViewPagerEffect(new Tablet());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String[] effects = this.getResources().getStringArray(R.array.effects);
        for (String effect : effects)
            menu.add(effect);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String effectStr = item.getTitle().toString();
        Effect4ViewPager effect = null;
        switch (effectStr){
            case "Standard":
                break;
            case "Tablet":
                effect = new Tablet();
                break;
            case "CubeIn":
                effect = new CudeIn();
                break;
            case "CubeOut":
                effect = new CudeOut();
                break;
            case "FlipVertical":
                effect = new FlipVertical();
                break;
            case "FlipHorizontal":
                effect = new FlipHorizontal();
                break;
            case "Stack":
                effect = new Stack();
                break;
            case "ZoomIn":
                effect = new ZoomIn();
                break;
            case "ZoomOut":
                effect = new ZoomOut();
                break;
            case "RotateUp":
                effect = new RotateUp();
                break;
            case "RotateDown":
                effect = new RotateDown();
                break;
            case "Accordion":
                effect = new Accordion();
                break;
        }
        setViewPagerEffect(effect);
        return true;
    }

    private void setViewPagerEffect(Effect4ViewPager effect) {
        pager = (AnimViewPager) findViewById(R.id.animPager);
        pager.setEffect(effect);
        pager.setAdapter(new MainAdapter());
        pager.setPageMargin(30);
    }

    private class MainAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            TextView text = new TextView(AnimationActivity.this);
            text.setGravity(Gravity.CENTER);
            text.setTextSize(30);
            text.setTextColor(Color.WHITE);
            text.setText("Page " + position);
            text.setPadding(30, 30, 30, 30);
            int bg = Color.rgb((int) Math.floor(Math.random() * 128) + 64,
                    (int) Math.floor(Math.random() * 128) + 64,
                    (int) Math.floor(Math.random() * 128) + 64);
            text.setBackgroundColor(bg);
            container.addView(text, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            pager.setObjectForPosition(text, position);
            return text;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object obj) {
            container.removeView(pager.findViewFromObject(position));
        }
        @Override
        public int getCount() {
            return 10;
        }
        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }
}
