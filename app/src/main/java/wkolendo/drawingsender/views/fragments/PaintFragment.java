package wkolendo.drawingsender.views.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.humandevice.android.mvpframework.PresenterFragment;

import java.util.ArrayList;

import wkolendo.drawingsender.R;
import wkolendo.drawingsender.models.Path;
import wkolendo.drawingsender.presenters.PaintPresenter;
import wkolendo.drawingsender.presenters.impl.PaintPresenterImpl;
import wkolendo.drawingsender.views.PaintView;
import wkolendo.drawingsender.views.custom.CustomDrawPathValue;
import wkolendo.drawingsender.views.custom.DrawingView;
import wkolendo.drawingsender.views.dialogs.ColorPickerDialog;


/**
 * @author Wojtek Kolendo
 */

public class PaintFragment extends PresenterFragment<PaintView, PaintPresenter> implements PaintView, View.OnClickListener {

	private DrawingView drawingView;
	private FloatingActionButton clearButton;
	private FloatingActionButton infoButton;
	private FloatingActionButton sendButton;
	private FloatingActionButton undoButton;
	private FloatingActionButton colorButton;

	public static PaintFragment newInstance() {
		return new PaintFragment();
	}

	@Override
	public Class<? extends PaintPresenter> getPresenterClass() {
		return PaintPresenterImpl.class;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_paint, container, false);
	}

	@Override
	protected void initView(View view) {
		drawingView = view.findViewById(R.id.drawing_view);
		infoButton = view.findViewById(R.id.info);
		sendButton = view.findViewById(R.id.send);
		clearButton = view.findViewById(R.id.clear);
		undoButton = view.findViewById(R.id.undo);
		colorButton = view.findViewById(R.id.color);

		infoButton.setOnClickListener(this);
		sendButton.setOnClickListener(this);
		clearButton.setOnClickListener(this);
		undoButton.setOnClickListener(this);
		colorButton.setOnClickListener(this);
	}

	@Override
	public void showInfoDialog() {
		new AlertDialog.Builder(getContext(), R.style.Theme_DrawingSender_Dialog)
				.setMessage(R.string.paint_info)
				.setPositiveButton(R.string.all_ok, null)
				.show();
	}

	@Override
	public void showSendConfirmDialog() {
		new AlertDialog.Builder(getContext(), R.style.Theme_DrawingSender_Dialog)
				.setMessage(R.string.paint_send_confirmation)
				.setNegativeButton(R.string.all_no, null)
				.setPositiveButton(R.string.all_yes, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						getPresenter().onSend();
					}
				})
				.show();
	}

	@Override
	public void showClearConfirmDialog() {
		new AlertDialog.Builder(getContext(), R.style.Theme_DrawingSender_Dialog)
				.setMessage(R.string.paint_clear_confirmation)
				.setNegativeButton(R.string.all_no, null)
				.setPositiveButton(R.string.all_yes, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						getPresenter().onClear();
					}
				})
				.show();
	}

	@Override
	public void showColorPickerDialog(int[] colors) {
		new ColorPickerDialog(getContext())
				.setColors(colors)
				.setOnSelectListener(new ColorPickerDialog.SelectListener() {
					@Override
					public void onColorSelected(@ColorInt int color) {
						drawingView.setColor(color);
					}
				})
				.show();
	}

	@Override
	public void clearView() {
		drawingView.clear();
	}

	@Override
	public void undoView() {
		drawingView.undo();
	}

	@Override
	public ArrayList<CustomDrawPathValue> getPaths() {
		return drawingView.getPaths();
	}

	@Override
	public void setPaths(ArrayList<CustomDrawPathValue> paths) {
		drawingView.setPaths(paths);
	}

	@Override
	public ArrayList<Path> getPreparedPaths() {
		return drawingView.getPreparedPaths();
	}

	@Override
	public int getScreenWidth() {
		DisplayMetrics metrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.widthPixels;
	}

	@Override
	public int getScreenHeight() {
		DisplayMetrics metrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.heightPixels;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.send:
				getPresenter().onSendClick();
				break;
			case R.id.info:
				getPresenter().onInfoClick();
				break;
			case R.id.clear:
				getPresenter().onClearClick();
				break;
			case R.id.undo:
				getPresenter().onUndoClick();
				break;
			case R.id.color:
				getPresenter().onColorPickClick();
				break;
		}
	}
}
