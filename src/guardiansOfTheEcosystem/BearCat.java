package guardiansOfTheEcosystem;

import javax.swing.Timer;

public class BearCat extends Guardian {
	
	private Timer shootTimer;

	public BearCat(World gp, int x, int y, int health, int energyPrice) {
		super(gp, x, y, health, 100);
		

	}

	@Override
	public void stop() {
		shootTimer.stop();
	}
	
	
}
