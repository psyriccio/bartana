package bartana;

public class Result {
	
	private Scene scene;
	private Double score;
	private String operation;
	
	public Result(Scene scene, Double score, String operation) {
		super();
		this.scene = scene;
		this.score = score;
		this.operation = operation;
	}
	
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}

	

}
