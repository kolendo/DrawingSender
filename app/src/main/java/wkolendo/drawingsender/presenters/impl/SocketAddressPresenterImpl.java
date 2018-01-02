package wkolendo.drawingsender.presenters.impl;

import com.humandevice.android.mvpframework.BasicPresenter;

import wkolendo.drawingsender.presenters.SocketAddressPresenter;
import wkolendo.drawingsender.views.SocketAddressView;

/**
 * @author Wojtek Kolendo
 */

public class SocketAddressPresenterImpl extends BasicPresenter<SocketAddressView> implements SocketAddressPresenter {

	@Override
	public void onSet() {
		if (view != null) {
			view.returnResult(view.getIpAddress(), view.getPort());
		}
	}
}
