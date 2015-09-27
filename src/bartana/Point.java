package bartana;

import java.io.Serializable;

public class Point implements Serializable{

	private static final long serialVersionUID = 4892663713350154647L;
	
	private Integer x;
	private Integer y;
	
	public Point(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
	
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	
}
