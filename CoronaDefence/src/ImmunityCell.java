
public class ImmunityCell extends Cell {
	public ImmunityCell() {
		super();
		cellID = "immunityCell";
		cellImage = null;
		hp = 50;
		level = 1;
		attackForce = 10;
		attackSpeed = 5;
		name = "ImmunityCell";	
	}
	
	public void levelUp() {
		level++;
	}
}
