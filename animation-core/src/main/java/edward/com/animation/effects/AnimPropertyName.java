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
