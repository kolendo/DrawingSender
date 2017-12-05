package wkolendo.drawingsender.views.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

import software.rsquared.androidlogger.Logger;
import wkolendo.drawingsender.models.Path;

/**
 * @author Wojtek Kolendo
 */

public class DrawingView extends View {

	private final PointF lastPoint = new PointF();
	private ArrayList<CustomDrawPathValue> paths = new ArrayList<>();
	private ArrayList<Path> preparedPaths = new ArrayList<>();
	private SerializablePath drawPath;
	private SerializablePaint drawPaint;

	public DrawingView(Context context) {
		super(context);
		setupDrawing();
	}

	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupDrawing();
	}

	public DrawingView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setupDrawing();
	}

	private void setupDrawing() {
		drawPath = new SerializablePath();
		drawPaint = new SerializablePaint();
		drawPaint.setColor(Color.BLACK);
		drawPaint.setAntiAlias(true);
		drawPaint.setStrokeWidth(3 * getContext().getResources().getDisplayMetrics().density);
		drawPaint.setStyle(Paint.Style.STROKE);
		drawPaint.setStrokeJoin(Paint.Join.ROUND);
		drawPaint.setStrokeCap(Paint.Cap.ROUND);
	}

	public void clear() {
		paths.clear();
		invalidate();
	}

	public ArrayList<CustomDrawPathValue> getPaths() {
		return paths;
	}

	public void setPaths(ArrayList<CustomDrawPathValue> paths) {
		if (paths == null) {
			return;
		}
		this.paths = paths;
		invalidate();
	}

	public ArrayList<Path> getPreparedPaths() {
		return preparedPaths;
	}

	@ColorInt
	public int getColor() {
		return drawPaint.getColor();
	}

	public void setColor(@ColorInt int color) {
		drawPaint.setColor(color);
	}

	public boolean undo() {
		if (paths.size() > 0) {
			paths.remove(paths.size() - 1);
			invalidate();
			return true;
		} else {
			return false;
		}
	}

	public int getCountPaths() {
		return paths.size();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float touchX = event.getX();
		float touchY = event.getY();

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				drawPath.reset();
				drawPath.moveTo(touchX, touchY);
				break;
			case MotionEvent.ACTION_MOVE: {
				final float x2 = (touchX + lastPoint.x) / 2f;
				final float y2 = (touchY + lastPoint.y) / 2f;
				drawPath.quadTo(lastPoint.x, lastPoint.y, x2, y2);
				break;
			}
			case MotionEvent.ACTION_UP: {
				final float x2 = (touchX + lastPoint.x) / 2f;
				final float y2 = (touchY + lastPoint.y) / 2f;
				drawPath.quadTo(lastPoint.x, lastPoint.y, x2, y2);
				paths.add(new CustomDrawPathValue(drawPath, copyPaint(drawPaint)));
				preparedPaths.add(new Path(getColor(), drawPath.getPoints()));
				drawPath = new SerializablePath();
				break;
			}
			default:
				return false;
		}
		lastPoint.set(touchX, touchY);
		invalidate();
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		for (CustomDrawPathValue value : paths) {
			canvas.drawPath(value.path, value.paint);
		}
		canvas.drawPath(drawPath, drawPaint);
	}

	protected SerializablePaint copyPaint(Paint paint) {
		SerializablePaint copy = new SerializablePaint();
		copy.setColor(paint.getColor());
		copy.setAntiAlias(true);
		copy.setStrokeWidth(paint.getStrokeWidth());
		copy.setStyle(Paint.Style.STROKE);
		copy.setStrokeJoin(Paint.Join.ROUND);
		copy.setStrokeCap(Paint.Cap.ROUND);
		return copy;
	}
}
