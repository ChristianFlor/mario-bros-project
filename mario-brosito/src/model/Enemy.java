package model;

public abstract class Enemy extends Figure {

    private String state;
    
    public Enemy(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        state = Mario.ISSTANDINGSTILL;
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
    
    public String getState() {
        return state;
    }

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

    
    
}