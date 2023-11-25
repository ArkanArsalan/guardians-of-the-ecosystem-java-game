package guardiansOfTheEcosystem;

import javax.swing.Timer;

public class Porcupine extends Guardian {

	private Timer shootTimer;
	
	public Porcupine(World gp, int x, int y, int health, int energyPrice) {
		super(gp, x, y, health, 200);
	}
	
	@Override
	public void stop() {
		shootTimer.stop();
	}

}
