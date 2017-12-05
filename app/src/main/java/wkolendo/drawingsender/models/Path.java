package wkolendo.drawingsender.models;

import android.support.annotation.ColorInt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import wkolendo.drawingsender.views.custom.SerializablePath;

/**
 * @author Wojtek Kolendo
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Path {

	@ColorInt
	@JsonProperty("color")
	private int color;

	@JsonProperty("points")
	private ArrayList<SerializablePath.Point> points;

	public Path() {

	}

	public Path(int color, ArrayList<SerializablePath.Point> points) {
		this.color = color;
		this.points = points;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public ArrayList<SerializablePath.Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<SerializablePath.Point> points) {
		this.points = points;
	}
}
