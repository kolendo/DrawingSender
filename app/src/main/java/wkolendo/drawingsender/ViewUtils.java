package wkolendo.drawingsender;

import android.content.res.Resources;

/**
 * @author Wojtek Kolendo
 */

public class ViewUtils {

	public static int dpToPx(float dp) {
		return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
	}

	public static float pxToDp(int px) {
		return px / Resources.getSystem().getDisplayMetrics().density;
	}

}
