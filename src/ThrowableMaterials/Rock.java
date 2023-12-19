package ThrowableMaterials;

import Panels.World;

public class Rock extends ThrowableMaterial {
	
	private static final int DAMAGE = 300;
	
	public Rock(World parent, int lane, int startX) {
		super(parent, lane, startX, DAMAGE);
	}
	
}
