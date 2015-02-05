package edward.com.example;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edward.com.abslistview.AnimationAdapter;
import edward.com.animation.effects.Bounce;
import edward.com.animation.effects.Direction;
import edward.com.animation.effects.Slide;
import edward.com.animation.effects.Zoom;


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
