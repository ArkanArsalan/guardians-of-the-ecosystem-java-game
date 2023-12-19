package Guardians;

import java.awt.event.ActionEvent;

import javax.swing.Timer;

import Panels.World;
import ThrowableMaterials.WaterBall;

public class Crab extends Guardian{
	
	private Timer shootTimer;
	
	public static final int ENERGY_PRICE = 175;
	private static final int THROW_SPEED = 2200;
	
	public Crab(World gp, int x, int y, int health) {
		super(gp, x, y, health, ENERGY_PRICE);
		shootTimer = new Timer(THROW_SPEED, (ActionEvent e) -> {
            if (getGp().getEnemyLane().get(y).size() > 0) {
                getGp().getThrowableMaterialLane().get(y).add(new WaterBall(getGp(), y, 103 + this.getX() * 100));
            }
        });
        shootTimer.start();
	}
	
	@Override
	public void stop() {
		shootTimer.stop();
	}
}
