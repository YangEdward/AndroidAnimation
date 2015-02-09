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

package edward.com.animation.effects;

/**
 * Created by Administrator on 15-2-4.
 */
public enum AnimPropertyName {
    ALPHA("alpha"),
    SCALE_X("scaleX"),
    SCALE_Y("scaleY"),
    TRANSLATION_X("translationX"),
    TRANSLATION_Y("translationY"),
    ROTATION("rotation"),
    ROTATION_X("rotationX"),
    ROTATION_Y("rotationY"),
    PIVOT_X("pivotX"),
    PIVOT_Y("pivotY");

    private String value;
    AnimPropertyName(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
