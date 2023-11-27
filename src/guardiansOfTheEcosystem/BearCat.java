package guardiansOfTheEcosystem;

import java.awt.Rectangle;

import javax.swing.Timer;

public class BearCat {
	
	private int posX;
    protected World gp;
    private int myLane;
	
	public BearCat(World parent, int lane, int startX) {
        this.gp = parent;
        this.myLane = lane;
        posX = startX;
    }
	
	
	public void advance() {
        Rectangle pRect = new Rectangle(posX, 130 + myLane * 120, 28, 28);
        for (int i = 0; i < gp.getEnemyLane().get(myLane).size(); i++) {
            Enemy enemy = gp.getEnemyLane().get(myLane).get(i);
            Rectangle enemyRect = new Rectangle(enemy.getPosX(), 109 + myLane * 120, 400, 120);
            if (pRect.intersects(enemyRect)) {
                enemy.setHealth(enemy.getHealth() - 300);
                boolean exit = false;
                if (enemy.getHealth() < 0) {
                    System.out.println("ZOMBIE DIED");

                    gp.getEnemyLane().get(myLane).remove(i);
                    World.setProgress(10);
                    exit = true;
                }
                gp.getEnemyLane().get(myLane).remove(this);
                if (exit) break;
            }
        }
        /*if(posX > 2000){
            gp.lanePeas.get(myLane).remove(this);
        }*/
        posX += 15;
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
