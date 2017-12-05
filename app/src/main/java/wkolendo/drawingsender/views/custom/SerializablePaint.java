package wkolendo.drawingsender.views.custom;

import android.graphics.Paint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Wojtek Kolendo
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SerializablePaint extends Paint implements Serializable {

	@JsonProperty("color")
	private int color;
	private boolean antiAlias;
	private float strokeWidth;
	private Paint.Style style;
	private Paint.Join strokeJoin;
	private Paint.Cap strokeCap;

	public SerializablePaint() {
	}

	public SerializablePaint(int flags) {
		super(flags);
	}

	public SerializablePaint(Paint paint) {
		super(paint);
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		setColor(color);
		setAntiAlias(antiAlias);
		setStrokeWidth(strokeWidth);
		setStyle(style);
		setStrokeJoin(strokeJoin);
		setStrokeCap(strokeCap);
	}

	@Override
	public void setColor(int color) {
		super.setColor(color);
		this.color = color;
	}

	@Override
	public void setAntiAlias(boolean aa) {
		super.setAntiAlias(aa);
		antiAlias = aa;
	}

	@Override
	public void setStrokeWidth(float width) {
		super.setStrokeWidth(width);
		strokeWidth = width;
	}

	@Override
	public void setStyle(Style style) {
		super.setStyle(style);
		this.style = style;
	}

	@Override
	public void setStrokeJoin(Join join) {
		super.setStrokeJoin(join);
		strokeJoin = join;
	}

	@Override
	public void setStrokeCap(Cap cap) {
		super.setStrokeCap(cap);
		strokeCap = cap;
	}
}
