package bartana;

import java.io.Serializable;
import java.util.List;

import bartana.Scene.Direction;

public abstract class Shape implements Serializable {
	
	private static final long serialVersionUID = 6421423616739668309L;
	
	String color;
	
	public abstract void move(Direction direction, Integer distance);
	public abstract List<String> getIMTranslation();
	public abstract List<Point> getAllCoordinates();
	
}
