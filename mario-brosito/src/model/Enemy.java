package model;

public abstract class Enemy extends Figure {

	public Enemy(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
	}

	private Enemy nextEnemy;
	
	

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
