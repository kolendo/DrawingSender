package wkolendo.drawingsender.views;

import com.humandevice.android.mvpframework.LayerView;

import java.util.ArrayList;

import wkolendo.drawingsender.models.Path;
import wkolendo.drawingsender.views.custom.CustomDrawPathValue;

/**
 * @author Wojtek Kolendo
 */

public interface PaintView extends LayerView {

	void showInfoDialog();

	void showSendConfirmDialog();

	void showClearConfirmDialog();

	void showColorPickerDialog(int[] colors);

	void clearView();

	void undoView();

	ArrayList<CustomDrawPathValue> getPaths();

	void setPaths(ArrayList<CustomDrawPathValue> paths);

	ArrayList<Path> getPreparedPaths();

	int getScreenWidth();

	int getScreenHeight();

}
