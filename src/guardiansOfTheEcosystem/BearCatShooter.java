package guardiansOfTheEcosystem;

import java.awt.event.ActionEvent;

import javax.swing.Timer;

public class BearCatShooter extends Guardian {
	
	private Timer shootTimer;

	public BearCatShooter(World gp, int x, int y, int health, int energyPrice) {
		super(gp, x, y, health, energyPrice);
		shootTimer = new Timer(2000, (ActionEvent e) -> {
            //System.out.println("SHOOT");
            if (getGp().getEnemyLane().get(y).size() > 0) {
                getGp().getbearCatLane().get(y).add(new BearCat(getGp(), y, 103 + this.getX() * 100));
            }
        });
        shootTimer.start();
	}
	
	
	@Override
	public void stop() {
		shootTimer.stop();
	}
	
	
}
