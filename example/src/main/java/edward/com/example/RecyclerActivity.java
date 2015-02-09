package edward.com.example;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import edward.com.animation.effects.Direction;
import edward.com.animation.effects.EffectHasDirection;
import edward.com.animation.effects.Fade;
import edward.com.animation.effects.Flip;
import edward.com.animation.effects.Slide;
import edward.com.animation.effects.Zoom;
import edward.com.recyclerview.RecyclerAdapterDecorator;
import edward.com.recyclerview.RecyclerAnimator;

public class RecyclerActivity extends ActionBarActivity {

    enum Type {
        FadeIn(new Fade()),
        FadeInDown(new Fade(Direction.BOTTOM)),
        FadeInUp(new Fade(Direction.TOP)),
        FadeInLeft(new Fade(Direction.LEFT)),
        FadeInRight(new Fade(Direction.RIGHT)),
        //Landing(new LandingAnimator()),
        ScaleIn(new Zoom()),
        ScaleInTop(new Zoom(Direction.TOP)),
        ScaleInBottom(new Zoom(Direction.BOTTOM)),
        ScaleInLeft(new Zoom(Direction.LEFT)),
        ScaleInRight(new Zoom(Direction.RIGHT)),
        FlipInTopX(new Flip(Direction.TOP)),
        FlipInBottomX(new Flip(Direction.BOTTOM)),
        FlipInLeftY(new Flip(Direction.LEFT)),
        FlipInRightY(new Flip(Direction.RIGHT)),
        SlideInLeft(new Slide(Direction.LEFT)),
        SlideInRight(new Slide(Direction.RIGHT)),
        SlideInDown(new Slide(Direction.BOTTOM)),
        SlideInUp(new Slide(Direction.TOP));
        //OvershootInRight(new OvershootInRightAnimator()),
        //OvershootInLeft(new OvershootInLeftAnimator());

        private EffectHasDirection mAnimator;

        Type(EffectHasDirection animator) {
            mAnimator = animator;
        }

        public EffectHasDirection getAnimator() {
            return mAnimator;
        }
    }

    private static String[] data = new String[]{
            "Apple", "Ball", "Camera", "Day", "Egg", "Foo", "Google", "Hello", "Iron", "Japan",
            "Coke", "Dog", "Cat", "Yahoo", "Sony", "Canon", "Fujitsu", "USA", "Nexus", "LINE",
            "Haskell", "C++", "Java", "Go", "Swift", "Objective-c", "Ruby", "PHP", "Bash", "ksh",
            "C", "Groovy", "Kotlin"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final RecyclerAdapter adapter = new RecyclerAdapter(this,
                new ArrayList<>(Arrays.asList(data)));
        final RecyclerAdapterDecorator decorator = new RecyclerAdapterDecorator(adapter,
                new Slide(Direction.RIGHT),recyclerView);
        recyclerView.setAdapter(decorator);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (Type type : Type.values()) {
            spinnerAdapter.add(type.name());
        }
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                decorator.setEffectHasDirection(Type.values()[position].getAnimator());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add("newly added item", 1);
            }
        });

        findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.remove(1);
            }
        });
    }
}
