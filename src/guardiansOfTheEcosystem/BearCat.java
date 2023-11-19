package guardiansOfTheEcosystem;

import javax.swing.Timer;
import java.awt.event.ActionEvent;

public class BearCat extends Guardian {
	
	private Timer shootTimer;

	public BearCat(World gp, int x, int y, int health) {
		super(gp, x, y, health, 100);
		

	}

	@Override
	public void stop() {
		shootTimer.stop();
	}
	
}
