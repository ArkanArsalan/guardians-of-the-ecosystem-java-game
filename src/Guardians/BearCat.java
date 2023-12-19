package Guardians;

import java.awt.event.ActionEvent;

import javax.swing.Timer;

import Panels.World;
import ThrowableMaterials.Rock;

public class BearCat extends Guardian {
	
	private Timer shootTimer;

	public BearCat(World gp, int x, int y, int health, int energyPrice) {
		super(gp, x, y, health, energyPrice);
		shootTimer = new Timer(2000, (ActionEvent e) -> {
            if (getGp().getEnemyLane().get(y).size() > 0) {
                getGp().getThrowableMaterialLane().get(y).add(new Rock(getGp(), y, 103 + this.getX() * 100));
            }
        });
        shootTimer.start();
	}
	
	@Override
	public void stop() {
		shootTimer.stop();
	}
	
}