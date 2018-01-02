package wkolendo.drawingsender.presenters;

import com.humandevice.android.mvpframework.Presenter;

import wkolendo.drawingsender.views.PaintView;

/**
 * @author Wojtek Kolendo
 */

public interface PaintPresenter extends Presenter<PaintView> {

	void onSettingsClick();

	void onSendClick();

	void onInfoClick();

	void onClearClick();

	void onUndoClick();

	void onColorPickClick();

	void onSend();

	void onClear();

	void setAddress(String ip, String port);

}
