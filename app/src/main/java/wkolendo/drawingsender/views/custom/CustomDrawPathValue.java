package wkolendo.drawingsender.views.custom;


import java.io.Serializable;

/**
 * @author Wojtek Kolendo
 */

public class CustomDrawPathValue implements Serializable {

	public SerializablePath path;
	public SerializablePaint paint;

	public CustomDrawPathValue(SerializablePath path, SerializablePaint paint) {
		this.path = path;
		this.paint = paint;
	}
}
