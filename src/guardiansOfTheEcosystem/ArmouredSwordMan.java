package guardiansOfTheEcosystem;

public class ArmouredSwordMan extends Enemy {
	
	private static final int HEALTH = 1300;
	private static final int MOVE_SPEED = 1;
	
	public ArmouredSwordMan(World gp, int lane) {
		super(gp, HEALTH, MOVE_SPEED, lane);
	}

}