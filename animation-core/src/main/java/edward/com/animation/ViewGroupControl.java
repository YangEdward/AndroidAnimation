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

package edward.com.animation;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;

import java.util.HashMap;

import edward.com.animation.effects.Effect4View;


public class ViewGroupControl {

    private ViewGroup target;
    private HashMap<View,AnimatorManager> map = new HashMap<>();
    private long startDelay;
    private boolean isReverse = false;

    private ViewGroupControl(@NonNull ViewGroup target,long startDelay){
        this.target = target;
        this.startDelay = startDelay;
    }

    public static ViewGroupControl with(@NonNull ViewGroup target){
        return new ViewGroupControl(target,500);
    }

    public static ViewGroupControl with(@NonNull ViewGroup target,long startDelay){
        return new ViewGroupControl(target,startDelay);
    }

    public void setEffectForAllView(@NonNull Effect4View effect){
        int count = target.getChildCount();
        map.clear();
        for (int i = 0; i < count; i++){
            long delay = startDelay * i;
            View child = target.getChildAt(i);
            map.put(child,AnimatorManager.with(child)
                    .putEffect(effect)
                    .setStartDelay(delay));
        }
    }

    public void setEffectForViewAt(int index,@NonNull Effect4View effect){
        View child = target.getChildAt(index);
        if(map.containsKey(child)){
            AnimatorManager manager = map.get(child);
            manager.removeAllEffect();
            manager.putEffect(effect);
        }else{
            map.put(child,AnimatorManager.with(child)
                    .putEffect(effect)
                    .setStartDelay(startDelay*index));
        }
    }

    public void setDuration(long duration){
        for (AnimatorManager manager : map.values()){
            manager.setDuration(duration);
        }
    }

    public void setAlphaAnimation(long duration){
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(duration);
        LayoutAnimationController controller = new LayoutAnimationController(animation, startDelay);
        target.setLayoutAnimation(controller);
    }

    public void setTranslateAnimation(long duration){
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f,Animation.RELATIVE_TO_SELF, 0.0f
        );
        animation.setDuration(duration);
        LayoutAnimationController controller = new LayoutAnimationController(animation, startDelay);
        target.setLayoutAnimation(controller);
    }

    public void start(){
        for (AnimatorManager manager : map.values()){
            manager.animate();
        }
    }

}
