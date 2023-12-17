package guardiansOfTheEcosystem;

public class WaterBall extends ThrowableMaterial {
	
	private static final int DAMAGE = 400;
	
	public WaterBall(World parent, int lane, int startX) {
		super(parent, lane, startX, DAMAGE);
	}
	
}
