package edward.com.example;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edward.com.animation.viewpager.Accordion;
import edward.com.animation.viewpager.BackgroundToForeground;
import edward.com.animation.viewpager.CudeIn;
import edward.com.animation.viewpager.CudeOut;
import edward.com.animation.viewpager.DepthPage;
import edward.com.animation.viewpager.FlipHorizontal;
import edward.com.animation.viewpager.FlipVertical;
import edward.com.animation.viewpager.ForegroundToBackground;
import edward.com.animation.viewpager.RotateDown;
import edward.com.animation.viewpager.RotateUp;
import edward.com.animation.viewpager.Scale;
import edward.com.animation.viewpager.Stack;
import edward.com.animation.viewpager.Tablet;
import edward.com.animation.viewpager.ZoomIn;
import edward.com.animation.viewpager.ZoomOut;
import edward.com.animation.viewpager.ZoomOutSlide;
import edward.com.animation.viewpager.EffectTransformer;

public class AnimationActivity extends ActionBarActivity {

    private ViewPager pager;
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
        EffectTransformer effect = null;
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
            case "BackgroundToForeground":
                effect = new BackgroundToForeground();
                break;
            case "DepthPage":
                effect = new DepthPage();
                break;
            case "ForegroundToBackground":
                effect = new ForegroundToBackground();
                break;
            case "Scale":
                effect = new Scale();
                break;
            case "ZoomOutSlide":
                effect = new ZoomOutSlide();
                break;
        }
        setViewPagerEffect(effect);
        return true;
    }

    private void setViewPagerEffect(EffectTransformer effect) {
        pager = (ViewPager) findViewById(R.id.animPager);
        pager.setPageTransformer(true, effect);
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
            return text;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object obj) {
            /*if(container.ind){
                container.removeViewAt(position);
            }*/
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
