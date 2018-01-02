package wkolendo.drawingsender.views;

import com.humandevice.android.mvpframework.LayerView;

/**
 * @author Wojtek Kolendo
 */

public interface SocketAddressView extends LayerView {

	String getIpAddress();

	String getPort();

	void returnResult(String ip, String port);

}
