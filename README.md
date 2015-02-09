AndroidAnimation
Easy to use, easy to expand。Android animation for View，ViewGroup,ListView,GridView,ViewPager,RecyclerView
===========

AndroidAnimation is an Open Source Android library that allows developers to easily use animations on View,ViewGroup,ListView,
GridView,ViewPager and RecyclerView.
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

### Animations
#### it's for view
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

* `animation-core`: The core of the library, and contains animations.
* `animation-abslistview`: decorator adapter for abslistview
* `animation-recylerview`: decorator adapter for recyclerview.
* `example`: how to use the library.

Getting Started
-----
please see examples

Contribute
-----
Please do! I'm happy to review and accept pull requests.  

Developed By
-----
* YangEdward

***

Special Thanks
-----
* DevBytes - Drag-and-Drop reordering is done by a rewritten version of their [DynamicListView][5].
* Jake Warthon - To support devices pre-HC (<3.0), a copy of [NineOldAndroids][2] is included.
* [Contributors][7]

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