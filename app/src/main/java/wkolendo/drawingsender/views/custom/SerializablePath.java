package wkolendo.drawingsender.views.custom;

import android.graphics.Path;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wojtek Kolendo
 */

public class SerializablePath extends Path implements Serializable {

	private transient ArrayList<PathObject> objects;
	private transient ArrayList<Point> points;

	public SerializablePath() {
		super();
		objects = new ArrayList<>();
		points = new ArrayList<>();
	}

	@Override
	public void quadTo(float x1, float y1, float x2, float y2) {
		super.quadTo(x1, y1, x2, y2);
		objects.add(new Quad(x1, y1, x2, y2));
		points.add(new Point(x1, y1));
		points.add(new Point(x2, y2));
	}

	@Override
	public void reset() {
		super.reset();
		objects = new ArrayList<>();
	}

	@Override
	public void moveTo(float x, float y) {
		super.moveTo(x, y);
		objects.add(new Point(x, y));
		points.add(new Point(x, y));
	}

	public ArrayList<PathObject> getObjects() {
		return objects;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public interface PathObject extends Serializable {
	}

	public class Quad implements PathObject {
		float x1, y1, x2, y2;

		Quad(float x1, float y1, float x2, float y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		@Override
		public String toString() {
			return "[(" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ")]";
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Point implements PathObject {

		@JsonProperty("coordinate_x")
		float x;

		@JsonProperty("coordinate_y")
		float y;

		Point(float x, float y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "[" + x + ", " + y + "]";
		}
	}
}
