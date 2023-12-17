package guardiansOfTheEcosystem;

public class Spike extends ThrowableMaterial {
	
	private static final int DAMAGE = 500;
	
	public Spike(World parent, int lane, int startX) {
		super(parent, lane, startX, DAMAGE);
	}
	
}
