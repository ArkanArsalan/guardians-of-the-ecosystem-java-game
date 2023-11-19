package guardiansOfTheEcosystem;

import javax.swing.Timer;
import java.awt.event.ActionEvent;

public class BearCat extends Guardian {
	
	private Timer shootTimer;

	public BearCat(World gp, int x, int y, int health) {
		super(gp, x, y, health, 100);
		
        shootTimer = new Timer(2000, (ActionEvent e) -> {
            if (gp.getEnemyLane().get(y).size() > 0) {
                gp.getBulletLane().get(y).add(new Bullet(gp, y, 103 + this.getX() * 100));
            }
        });
        
        shootTimer.start();
	}

	@Override
	public void stop() {
		shootTimer.stop();
	}
	
}