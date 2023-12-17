package guardiansOfTheEcosystem;

public class SawMan extends Enemy {
	
	private static final int HEALTH = 1000;
	private static final int MOVE_SPEED = 1;
	
	public SawMan(World gp, int lane) {
		super(gp, HEALTH, MOVE_SPEED, lane);
	}
	
}
