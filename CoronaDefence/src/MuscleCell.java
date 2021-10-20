
public class MuscleCell extends Cell {
	public MuscleCell() {
		super();
		cellImage = null;
		hp = 100;
		level = 1;
		attckForce = 5;
		attackSpeed = 3;
		name = "MuscleCell";	
	}
	
	public void levelUp() {
		level++;		
	}
}
