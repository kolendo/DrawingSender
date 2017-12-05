package wkolendo.drawingsender.presenters.impl;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.humandevice.android.mvpframework.BasicPresenter;

import java.util.ArrayList;

import software.rsquared.androidlogger.Logger;
import wkolendo.drawingsender.models.Drawing;
import wkolendo.drawingsender.presenters.PaintPresenter;
import wkolendo.drawingsender.views.PaintView;
import wkolendo.drawingsender.views.custom.CustomDrawPathValue;

/**
 * @author Wojtek Kolendo
 */

public class PaintPresenterImpl extends BasicPresenter<PaintView> implements PaintPresenter {

	private static final String EXTRA_DRAWING_PATH = "extra_drawing_path";
	private static final int[] BRUSH_COLORS = new int[]{
			Color.BLACK, Color.GRAY, Color.LTGRAY, Color.RED, Color.MAGENTA, Color.BLUE, Color.CYAN, Color.GREEN, Color.YELLOW};

	private ObjectMapper objectMapper;

	/**
	 * For SaveInstanceState purpose only
	 */
	private ArrayList<CustomDrawPathValue> paths;


	@Override
	public void onCreate(@Nullable Bundle bundle) {
		if (bundle != null) {
			//noinspection unchecked
			paths = (ArrayList<CustomDrawPathValue>) bundle.getSerializable(EXTRA_DRAWING_PATH);
		}
		objectMapper = new ObjectMapper();
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
	}

	@Override
	public void onBindView(@NonNull PaintView view) {
		super.onBindView(view);
		if (paths != null) {
			view.setPaths(paths);
		}
	}

	@Override
	public void onSaveInstanceState(@NonNull Bundle bundle) {
		super.onSaveInstanceState(bundle);
		if (view != null) {
			ArrayList<CustomDrawPathValue> paths = view.getPaths();
			bundle.putSerializable(EXTRA_DRAWING_PATH, paths);
		}
	}

	@Override
	public void onSendClick() {
		if (view != null) {
			view.showSendConfirmDialog();
		}
	}

	@Override
	public void onInfoClick() {
		if (view != null) {
			view.showInfoDialog();
		}
	}

	@Override
	public void onClearClick() {
		if (view != null) {
			view.showClearConfirmDialog();
		}
	}

	@Override
	public void onUndoClick() {
		if (view != null) {
			view.undoView();
		}
	}

	@Override
	public void onColorPickClick() {
		if (view != null) {
			view.showColorPickerDialog(BRUSH_COLORS);
		}
	}

	@Override
	public void onSend() {
		if (view != null) {
			Drawing drawing = new Drawing(view.getScreenHeight(), view.getScreenWidth());
			drawing.setPaths(view.getPreparedPaths());
			sendViaSocket(serializeToJson(drawing));
		}
	}

	@Override
	public void onClear() {
		if (view != null) {
			view.clearView();
		}
	}

	private String serializeToJson(@NonNull Drawing drawing) {
		String json = null;
		try {
			json = objectMapper.writeValueAsString(drawing);
		} catch (JsonProcessingException e) {
			Logger.error(e);
		}
		Logger.debug(json);
		return json;
	}

	private void sendViaSocket(String json) {
		// TODO: 04/12/2017

	}
}
