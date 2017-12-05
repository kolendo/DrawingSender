package wkolendo.drawingsender.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

/**
 * @author Wojtek Kolendo
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"screen_height", "screen_width", "paths"})
public class Drawing {

	@JsonProperty("screen_height")
	private int height;

	@JsonProperty("screen_width")
	private int width;

	@JsonProperty("paths")
	private ArrayList<Path> paths;

	public Drawing() {
	}

	public Drawing(int height, int width) {
		this.height = height;
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public ArrayList<Path> getPaths() {
		return paths;
	}

	public void setPaths(ArrayList<Path> paths) {
		this.paths = paths;
	}
}
