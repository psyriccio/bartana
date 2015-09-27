package bartana;

import java.util.ArrayList;
import java.util.List;

import bartana.Scene.Direction;

public class Square extends Shape{

	private static final long serialVersionUID = -391747923878252192L;
	
	private Point x;
	private Point y;
	private Point z;
	private Point w;
	
	@Override
	public List<String> getIMTranslation() {
		ArrayList<String> list = new ArrayList<String>(); 
		list.add("-draw"); 
		list.add("fill "+color+" polygon  "+x.getX()+","+x.getY()+" "+y.getX()+","+y.getY()+" "+z.getX()+","+z.getY()+" "+w.getX()+","+w.getY());
		return list;
	}

	@Override
	public void move(Direction direction, Integer distance) {
		
		switch (direction) {
		case UP:
			x.setY(x.getY() - distance);
			y.setY(y.getY() - distance);
			z.setY(z.getY() - distance);
			w.setY(w.getY() - distance);
			break;
		case DOWN:
			x.setY(x.getY() + distance);
			y.setY(y.getY() + distance);
			z.setY(z.getY() + distance);
			w.setY(w.getY() + distance);
			break;
		case RIGHT:
			x.setX(x.getX() + distance);
			y.setX(y.getX() + distance);
			z.setX(z.getX() + distance);
			w.setX(w.getX() + distance);
			break;
		case LEFT:
			x.setX(x.getX() - distance);
			y.setX(y.getX() - distance);
			z.setX(z.getX() - distance);
			w.setX(w.getX() - distance);
			break;
		default:
			break;
		}
		
	}
	
	public Square(Point x, Point y, Point z, Point w, String color) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		this.color = color;
	}

	@Override
	public List<Point> getAllCoordinates() {
		
		List<Point> list = new ArrayList<Point>();
		
		list.add(x);
		list.add(y);
		list.add(z);
		list.add(w);
		
		return list;
	}
}
