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

import android.animation.Animator;
import android.view.View;


public enum Direction {
    TOP_LEFT{
        @Override
        public Animator[] getAnimators(View target,EffectHasDirection direction) {
            return direction.topLeft(target);
        }
    },
    TOP{
        @Override
        public Animator[] getAnimators(View target,EffectHasDirection direction) {
            return direction.top(target);
        }
    },
    TOP_RIGHT{
        @Override
        public Animator[] getAnimators(View target,EffectHasDirection direction) {
            return direction.topRight(target);
        }
    },
    RIGHT{
        @Override
        public Animator[] getAnimators(View target,EffectHasDirection direction) {
            return direction.right(target);
        }
    },
    BOTTOM_RIGHT{
        @Override
        public Animator[] getAnimators(View target,EffectHasDirection direction) {
            return direction.bottomRight(target);
        }
    },
    BOTTOM{
        @Override
        public Animator[] getAnimators(View target,EffectHasDirection direction) {
            return direction.bottom(target);
        }
    },
    BOTTOM_LEFT{
        @Override
        public Animator[] getAnimators(View target,EffectHasDirection direction) {
            return direction.bottomLeft(target);
        }
    },
    LEFT{
        @Override
        public Animator[] getAnimators(View target,EffectHasDirection direction) {
            return direction.left(target);
        }
    };

    public abstract Animator[] getAnimators(View target,EffectHasDirection direction);
}
