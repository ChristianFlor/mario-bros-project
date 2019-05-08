package model;

public class MisteryBlock extends Obstacle implements DestructibleBrick {
	
	private Coin coin;
	private PowerUp power;

	public MisteryBlock(double posX, double posY, double width, double height, Coin coin, PowerUp power) {
		super(posX, posY, width, height);
		
		this.coin = coin;
		this.power = power;
	}

}
