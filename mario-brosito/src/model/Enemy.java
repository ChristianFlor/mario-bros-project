package model;

public class Enemy extends Figure {

	private Enemy nextEnemy;
	
	public Enemy(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
		
	}

	/**
	 * @return the nextEnemy
	 */
	public Enemy getNextEnemy() {
		return nextEnemy;
	}

	/**
	 * @param nextEnemy the nextEnemy to set
	 */
	public void setNextEnemy(Enemy nextEnemy) {
		this.nextEnemy = nextEnemy;
	}
	
	

	
	
}
