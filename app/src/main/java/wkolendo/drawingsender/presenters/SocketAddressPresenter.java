package wkolendo.drawingsender.presenters;

import com.humandevice.android.mvpframework.Presenter;

import wkolendo.drawingsender.views.SocketAddressView;

/**
 * @author Wojtek Kolendo
 */

public interface SocketAddressPresenter extends Presenter<SocketAddressView> {

	void onSet();

}
