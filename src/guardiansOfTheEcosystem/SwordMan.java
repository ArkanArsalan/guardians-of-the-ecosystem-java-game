package guardiansOfTheEcosystem;

public class SwordMan extends Enemy {
	
	private static final int HEALTH = 800;
	private static final int MOVE_SPEED = 2;
	
	public SwordMan(World gp, int lane) {
		super(gp, HEALTH, MOVE_SPEED, lane);
	}
	
}
