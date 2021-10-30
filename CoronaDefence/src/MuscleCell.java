
public class MuscleCell extends Cell {
	public MuscleCell(int x, int y) {
		super(x,y);
		cellID = "muscleCell";
		cellImage = null;
		hp = 100;
		level = 1;
		attackForce = 5;
		attackSpeed = 3;
		name = "MuscleCell";	
	}
	
	public void levelUp() {
		level++;		
	}
}
