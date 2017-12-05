package wkolendo.drawingsender.views.dialogs;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import wkolendo.drawingsender.R;
import wkolendo.drawingsender.ViewUtils;

/**
 * @author Wojtek Kolendo
 */

public class ColorPickerDialog extends AlertDialog {

	private final static int CIRCLE_RADIUS = ViewUtils.dpToPx(80);
	private final static int PADDING = ViewUtils.dpToPx(5);

	private RecyclerView recyclerView;
	private GridLayoutManager layoutManager;
	private ColorsAdapter adapter;
	private SelectListener listener;

	public ColorPickerDialog(Context context) {
		super(context);
		initView();
	}

	public ColorPickerDialog(Context context, int theme) {
		super(context, theme);
		initView();
	}

	private void initView() {
		setTitle(R.string.paint_select_color);
		recyclerView = new RecyclerView(getContext());
		layoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
		adapter = new ColorsAdapter(new int[]{});
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(layoutManager);
		setView(recyclerView);
		recyclerView.post(new Runnable() {
			@Override
			public void run() {
				onViewAdded();
			}
		});
	}

	private void onViewAdded() {
		int dialogWidth = recyclerView.getWidth();
		int circlesInRow = dialogWidth / CIRCLE_RADIUS;
		int padding = (dialogWidth - (circlesInRow * CIRCLE_RADIUS)) / 2;
		layoutManager.setSpanCount(circlesInRow);
		recyclerView.setPadding(padding, PADDING, padding, PADDING);
	}

	public ColorPickerDialog setColors(int... colors) {
		adapter.setColors(colors);
		return this;
	}

	public ColorPickerDialog setOnSelectListener(SelectListener listener) {
		this.listener = listener;
		return this;
	}

	public interface SelectListener {
		void onColorSelected(@ColorInt int color);
	}

	private class ColorsAdapter extends RecyclerView.Adapter {

		private int[] colors;

		public ColorsAdapter(int[] colors) {
			this.colors = colors;
		}

		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			ImageView imageView = new ImageView(parent.getContext(), null, R.attr.borderlessButtonStyle);
			imageView.setImageResource(R.drawable.paint_circle_white);
			imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			imageView.setPadding(2 * PADDING, 2 * PADDING, 2 * PADDING, 2 * PADDING);
			RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(CIRCLE_RADIUS, CIRCLE_RADIUS);
			imageView.setLayoutParams(params);
			return new ViewHolder(imageView);
		}

		@Override
		public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
			final int color = colors[position];
			ImageView imageView = ((ImageView) holder.itemView);
			imageView.setColorFilter(colors[position]);
			imageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onColorSelected(color);
					dismiss();
				}
			});
		}

		@Override
		public int getItemCount() {
			return colors.length;
		}

		public void setColors(int[] colors) {
			this.colors = colors;
			notifyDataSetChanged();
		}
	}

	private class ViewHolder extends RecyclerView.ViewHolder {
		public ViewHolder(ImageView imageView) {
			super(imageView);
		}
	}
}
