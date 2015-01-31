package edward.com.animation.utils;

import android.view.View;
import android.view.ViewGroup;

public class LayerUtil {

	private LayerUtil(){
		new AssertionError();
	}
	
	public static void manageLayer(View v, boolean enableHardware) {
		int layerType = enableHardware ? View.LAYER_TYPE_HARDWARE : View.LAYER_TYPE_NONE;
		v.setLayerType(layerType, null);   
	}
	
	public static void disableHardwareLayer(ViewGroup group) {
		View v;
		int length = group.getChildCount();
		for (int i = 0; i < length; i++) {
			v = group.getChildAt(i);
			v.setLayerType(View.LAYER_TYPE_NONE, null);
		}
	}
}
