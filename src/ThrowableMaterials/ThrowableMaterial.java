package ThrowableMaterials;

import java.awt.Rectangle;

import javax.swing.Timer;

import Enemies.Enemy;
import Panels.World;

public class ThrowableMaterial {
	
	private int posX;
    protected World gp;
    private int myLane;
    private int damage;
	
	public ThrowableMaterial(World parent, int lane, int startX, int damage) {
        this.gp = parent;
        this.myLane = lane;
        this.posX = startX;
        this.damage = damage;
    }
	
	public void advance() {
        isHit();
        move();
    }
	
	private void move() {
		posX += 15;
	}
	
	private void isHit() {
		Rectangle tmRect = new Rectangle(posX, 130 + myLane * 120, 28, 28);
		
        for (int i = 0; i < gp.getEnemyLane().get(myLane).size(); i++) {
            Enemy enemy = gp.getEnemyLane().get(myLane).get(i);
            Rectangle enemyRect = new Rectangle(enemy.getPosX(), 109 + myLane * 120, 400, 120);
            
            if (tmRect.intersects(enemyRect)) {
                enemy.setHealth(enemy.getHealth() - damage);
                boolean exit = false;
                
                if (enemy.getHealth() <= 0) {
                    gp.getEnemyLane().get(myLane).remove(i);
                    gp.setPlayerScore(gp.getPlayerScore() + 10);
                    exit = true;
                }
                
                gp.getThrowableMaterialLane().get(myLane).remove(this);
                
                if (exit) {
                	break;
                };
            }
        }
	}

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getMyLane() {
        return myLane;
    }

    public void setMyLane(int myLane) {
        this.myLane = myLane;
    }
	
	
}
