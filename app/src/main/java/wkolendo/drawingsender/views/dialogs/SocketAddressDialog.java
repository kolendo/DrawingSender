package wkolendo.drawingsender.views.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.humandevice.android.mvpframework.PresenterDialogFragment;

import wkolendo.drawingsender.R;
import wkolendo.drawingsender.presenters.SocketAddressPresenter;
import wkolendo.drawingsender.presenters.impl.SocketAddressPresenterImpl;
import wkolendo.drawingsender.views.SocketAddressView;

/**
 * @author Wojtek Kolendo
 */

public class SocketAddressDialog extends PresenterDialogFragment<SocketAddressView, SocketAddressPresenter> implements SocketAddressView {

	public static final String EXTRA_IP_ADDRESS = "extra_ip_address";
	public static final String EXTRA_PORT = "extra_port";

	private View dialogView;
	private EditText ipAddressEditText;
	private EditText portEditText;

	private String ip, port;

	public static SocketAddressDialog newInstance(String ip, String port) {
		Bundle args = new Bundle();
		args.putString(EXTRA_IP_ADDRESS, ip);
		args.putString(EXTRA_PORT, port);
		SocketAddressDialog fragment = new SocketAddressDialog();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public Class<? extends SocketAddressPresenter> getPresenterClass() {
		return SocketAddressPresenterImpl.class;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.ip = getArguments().getString(EXTRA_IP_ADDRESS);
		this.port = getArguments().getString(EXTRA_PORT);
	}

	@Override
	protected Dialog createDialog(Bundle savedInstanceState) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		dialogView = inflater.inflate(R.layout.dialog_socket_address, null);
		initDialogView(dialogView);
		builder.setView(dialogView)
				.setTitle(R.string.socket_title_dialog)
				.setPositiveButton(R.string.all_ok, null)
				.setNegativeButton(R.string.all_cancel, null);
		AlertDialog dialog = builder.create();
		dialog.setOnShowListener(new DialogInterface.OnShowListener() {
			@Override
			public void onShow(DialogInterface dialog) {
				Button positiveButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
				Button negativeButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);

				positiveButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						hideKeyboard();
						getPresenter().onSet();
					}
				});

				negativeButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						hideKeyboard();
						dismiss();
					}
				});
			}
		});
		return dialog;
	}

	private void hideKeyboard() {
		View currentFocus = getDialog().getCurrentFocus();
		if (currentFocus != null) {
			currentFocus.clearFocus();
			InputMethodManager inputMethodManager = (InputMethodManager) currentFocus.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
			if (inputMethodManager != null) {
				inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
			}
		}
	}

	private void initDialogView(View view) {
		ipAddressEditText = view.findViewById(R.id.ip_address);
		portEditText = view.findViewById(R.id.port);

		ipAddressEditText.setText(ip);
		portEditText.setText(port);
	}

	@Override
	protected void initView(View view) {

	}

	@Override
	public String getIpAddress() {
		return ipAddressEditText.getText().toString();
	}

	@Override
	public String getPort() {
		return portEditText.getText().toString();
	}

	@Override
	public void returnResult(String ip, String port) {
		Intent intent = new Intent();
		intent.putExtra(EXTRA_IP_ADDRESS, ip);
		intent.putExtra(EXTRA_PORT, port);
		getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
		dismiss();
	}
}
