package bartana;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Scene implements Serializable{
	
	private static final long serialVersionUID = -2301032658328967613L;
	
	public static Integer width;
	public static Integer height;
	private static String backgroundColor;
	private static Integer minMovingDistance;
	private static Integer maxMovingDistance;
	
	public static String tempPath = "";
	
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	enum Direction {
	    UP, DOWN, LEFT, RIGHT 
	}
	
	public Scene(String backgroundColor, Integer minMovingDistance, Integer maxMovingDistance) {
		Scene.backgroundColor = backgroundColor;
		Scene.minMovingDistance = minMovingDistance;
		Scene.maxMovingDistance = maxMovingDistance;
	}
	
	public void addShape(Shape shape){
		shapes.add(shape);
	}
	
	public void removeShape(Integer index){
		try{
			shapes.remove(index.intValue());
		}catch(Exception exception){
			
		}
	}
	
	public Integer getShapesQuantity(){
		return shapes.size();
	}
	
	private List<String> getIMTranslation() {
		List<String> list = new ArrayList<String>();
		list.add("convert");
		list.add("-size");
		list.add(width + "x" + height);
		list.add("xc:" + backgroundColor);
		
		for (Shape shape : shapes) {
			
			list.addAll(shape.getIMTranslation());
		}
		
		list.add( "png:" + tempPath + new String(Integer.toString(this.hashCode())) );
		
		return list;
	}
	
	public void renderScene() throws IOException, InterruptedException {
		
		String command[] = {};
		Process process = Runtime.getRuntime().exec(this.getIMTranslation().toArray(command));
		
		process.waitFor();
		
	}
	
	public void renderScene(String fileName) throws IOException, InterruptedException {
		
		String command[] = {};
		
		String[] array = this.getIMTranslation().toArray(command);
		array[array.length - 1] = "png:" + tempPath + fileName;
		
		Process process = Runtime.getRuntime().exec(array);
		
		process.waitFor();
		
	}	
	
	public void moveShape(Integer shapeIndex){
		
		Shape shape;
		try {
			shape = shapes.get(shapeIndex);
		} catch (Exception e) {
			return;
		}
		
		Boolean isValidMove = false;
		
		do{
		
			Integer movingDistance = (int) (minMovingDistance + ( Math.random() * (maxMovingDistance - minMovingDistance) ));
			
			Integer randDirectionIndex = (int) (Math.random() * 4);
			Direction direction;
			switch (randDirectionIndex) {
			case 0:
				direction = Direction.UP;
				break;
			case 1:
				direction = Direction.DOWN;
				break;
			case 2:
				direction = Direction.RIGHT;
				break;
			case 3:
				direction = Direction.LEFT;
				break;
			default:
				direction = Direction.UP;
				break;
			}
			
			shape.move(direction, movingDistance);
			
			isValidMove = isShapePositionValid(shape);
			
			if(!isValidMove){ //move back, try again
				shape.move(direction, movingDistance * -1);
			}
			
		} while (!isValidMove);
		
	}
	
	public void deleteRenderedImage() throws IOException{
		
		try {
			Files.delete(new File(Scene.tempPath + this.hashCode()).toPath());
		} catch (Exception e) {

		}
	}
	
	
	
	private Boolean isShapePositionValid(Shape shape) {

		List<Point> coordinates = shape.getAllCoordinates();
		
		for (Point coordinate : coordinates) {
			
			if(coordinate.getX() > width || coordinate.getX() < 0 || coordinate.getY() > height || coordinate.getY() < 0 ) {
				return false;
			}
		}
		
		return true;
	}
	
	public Scene copy() throws IOException, ClassNotFoundException{
		
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);
        
        ByteArrayInputStream byteArrayInputStream = new   ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Scene scene = (Scene) objectInputStream.readObject();
        
        return scene;
	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException, CloneNotSupportedException, ClassNotFoundException {
		
	}

}
