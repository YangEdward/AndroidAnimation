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

import edward.com.animation.effects.Direction;
import edward.com.animation.effects.EffectHasDirection;
import edward.com.animation.effects.Fade;
import edward.com.animation.effects.Flip;
import edward.com.animation.effects.Rotate;
import edward.com.animation.effects.Slide;
import edward.com.animation.effects.Zoom;

public enum DirectionType {
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
    SlideInUp(new Slide(Direction.TOP)),
    RotateLeft(new Rotate(Direction.LEFT)),
    RotateRight(new Rotate(Direction.RIGHT)),
    RotateBottom(new Rotate(Direction.BOTTOM)),
    RotateTop(new Rotate(Direction.TOP));

    private EffectHasDirection mAnimator;

    DirectionType(EffectHasDirection animator) {
        mAnimator = animator;
    }

    public EffectHasDirection getAnimator() {
        return mAnimator;
    }
}
