package bartana;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Genecy {
	
	public static String targetImage = "";

	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		
		Initializer.init();
		
		List<Result> scenes = null;

		Scene championScene = new Scene("white", 5, 15);
		Double championScore = Double.MAX_VALUE;
		
		Integer triangleSize = 150;
		
		
		while(true){
			
			scenes = new ArrayList<Result>();
			
			Integer initialPointX = (int) ((Scene.width -triangleSize) * Math.random());
			Integer initialPointY = (int) ((Scene.height - triangleSize) * Math.random());
		
			Scene scene1 = championScene.copy();
			scene1.addShape(new Triangle(new Point(initialPointX, initialPointY), new Point(initialPointX + triangleSize, initialPointY), new Point(initialPointX + triangleSize, initialPointY + triangleSize), "rgb(" + (int) (Math.random() * 256) + "," + (int) (Math.random() * 256) + "," + (int) (Math.random() * 256) + ")"));
			scene1.renderScene();
			Double score1 = Comparator.compareBlackAndWhitePixelDifference(Scene.tempPath + scene1.hashCode() + "", targetImage);
			scenes.add(new Result(scene1, score1, "add"));
			
			for (int i = 0; i < (championScene.getShapesQuantity() * 0.25); i++) {
				if (championScene.getShapesQuantity() >= 2) {
					Scene scene2 = championScene.copy();
					scene2.removeShape((int) (scene2.getShapesQuantity() * Math.random()));
					scene2.renderScene();
					Double score2 = Comparator.compareBlackAndWhitePixelDifference(Scene.tempPath + scene2.hashCode() + "", targetImage);
					scenes.add(new Result(scene2, score2, "remove"));
				}
			}
			
			for (int i = 0; i < (championScene.getShapesQuantity() * 0.25); i++) {
				if (championScene.getShapesQuantity() >= 1){
					Scene scene3 = championScene.copy();
					scene3.moveShape((int) (scene3.getShapesQuantity() * Math.random()));
					scene3.renderScene();
					Double score3 = Comparator.compareBlackAndWhitePixelDifference(Scene.tempPath + scene3.hashCode() + "", targetImage);
					scenes.add(new Result(scene3, score3, "move"));
				}
			}
			
			for (Result result : scenes) {
				
				if (result.getScore() < championScore || (result.getScore() == championScore && result.getOperation() == "remove") ){
					championScore = result.getScore();
					championScene = result.getScene();
					championScene.renderScene("championScene.png");
					System.out.println("New lowest score: " + championScore + " due to " + result.getOperation());
				}else{
					result.getScene().deleteRenderedImage();
				}
			}
		}
	}
	
}
