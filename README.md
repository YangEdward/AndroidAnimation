### AndroidAnimation
Easy to use, easy to expand。Android animation for View，ViewGroup,ListView,GridView,ViewPager,RecyclerView

AndroidAnimation is an Open Source Android library that allows developers to easily use animations on View,ViewGroup,ListView,GridView,ViewPager and RecyclerView.
Feel free to use it all you want in your Android apps provided that you cite this project and include the license in your app.

Features
-----
AndroidAnimation provides the following features:
* View : Has Many animations for view.
* Appearance animations for items in `ListViews`, `GridViews`, and `RecyclerView`;
    * Built in animations include `Bounce`, `Fade`, `Flip`, `Roll`, `Rotate` ,`Slide` and `Zoom`.
    * every Appearance animations has eight directions include `Top`,`TopLeft`...
	* Other animations can easily be added
* Scroll Animation for items in `ListViews`, `GridViews`, and `RecyclerView`;
* ViewGroup : Easy to add animations for ViewGroup,such as `LinearLayout`,`RelativeLayout` and so on
* ViewPager : wonderful animations for ViewPager pager transformer
* worked above api14

### Animations
#### always for view
`DropOut`, `Flash`, `Landing`, `Pulse`, `RubberBand`, `Shake`, `StandUp`,
`Swing`, `Tada`, `TakingOff`,`Wave`,`Wobble`

#### Appearance animations(for all)
`Bounce`, `Fade`, `Flip`,`Roll`,`Rotate`,`Slide`,`Zoom`
Each of them has eight directions include:
`Top`, `TopLeft`, `TopLeft`,`Right`,`Bottom`,`BottomRight`,`BottomLeft`,`Left`
Each of them has two actions include:
`IN`, `OUT`
It's very easy to set action and direction.

Setup
-----

The library consists of separate modules:

* `animation-core`: The core of the library, and contains animations. it also contains many evaluators and interpolators,you
* can use them in you project.
* `animation-abslistview`: decorator adapter for abslistview
* `animation-recylerview`: decorator adapter for recyclerview.
* `example`: how to use the library.

Getting Started
-----
###Use
#### * view
for single view,need to put Effect4View. you can put all effect in it.
     
     AnimatorManager.with(view).putEffect(effect4View)
                .animate();
     
#### * ViewGroup
for ViewGroup,need to put Effect4View. you can put all effect in it.

    ViewGroupControl control = ViewGroupControl.with(group,0);
        control.setEffectForAllView(new Slide(Direction.RIGHT));
        control.setEffectForViewAt(1,new Slide(Direction.LEFT));
        control.start();
    
#### * * listview and gridview
for abslistview , need to put EffectHasDirection which can set action and Direction.

    MyListAdapter adapter = new MyListAdapter(this,
                new ArrayList<>(data));
        final AnimationAdapter animationAdapter = new AnimationAdapter(adapter,
                new Slide(Direction.RIGHT).setParent(listView).
                        setDuration(500));
        animationAdapter.setAbsListView(listView);
        animationAdapter.addScrollHelper();
        listView.setAdapter(animationAdapter);
    
#### * recyclerView
for recyclerView , need to put EffectHasDirection which can set action and Direction.

    RecyclerAdapter adapter = new RecyclerAdapter(this,
                new ArrayList<>(data));
        RecyclerAdapterDecorator decorator = new RecyclerAdapterDecorator(adapter,
                new Rotate(Direction.RIGHT),recyclerView);
        recyclerView.setAdapter(decorator);
    
#### * ViewPager
for recyclerView , need to put EffectTransformer.

    pager = (ViewPager) findViewById(R.id.animPager);
    pager.setPageTransformer(true, effect);
    
###Expand
if you have good ideas, please pull request, it's very easy.
#### * View ViewGroup
create class which implements Effect4View.if it has action,please extends NoDirection.
if it also has direction,please extends EffectHasDirection
#### * List Grid recycler
create class which extends EffectHasDirection
#### * ViewPager
create class which extends EffectTransformer
* after that,most of time,your class have perfect show in my library.

Contribute
-----
Please do! I'm happy to review and accept pull requests.  

Developed By
-----
* YangEdward

***

Special Thanks
-----
* Niek Haarman - inspired me
* ToxicBakery

License
-----

   Copyright (C) 2015 YangEdward

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
