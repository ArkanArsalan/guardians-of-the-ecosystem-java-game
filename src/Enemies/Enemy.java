package Enemies;

import Grid.Grid;
import Main.GameWindow;
import Panels.GameScreenPanel;
import Panels.World;

public class Enemy {

    private int health;
    private int speed;

    private int posX = 1000;
    private int lane;
    private boolean isMoving = true;
    
    World gp;

    public Enemy(World gp, int health, int speed, int enemylane) {
    	this.gp = gp;
    	this.health = health;
    	this.speed = speed;
    	this.lane = enemylane;
    }
    
    public void move() {
        if (isMoving) {
            boolean isCollide = false;
            Grid grid = null;
            for (int i = lane * 9; i < (lane + 1) * 9; i++) {
                if (gp.getGrids()[i].assignedGuardian != null && gp.getGrids()[i].isInsideGrid(posX)) {
                    isCollide = true;
                    grid = gp.getGrids()[i];
                }
            }
            
            // If enemy is in the grid of the guardian, attack the guardian, otherwise move
            if (isCollide) {
                grid.assignedGuardian.setHealth(grid.assignedGuardian.getHealth() - 10);
                if (grid.assignedGuardian.getHealth() < 0) {
                    grid.removeGuardian();
                }
            } else {
            	posX -= speed;
            }
            
            if (posX < 0) {
                isMoving = false;
                GameScreenPanel.worldPanel.reset();
                GameWindow.gp.showPanel("GameOver");   
            }
        }
    }
    
    public static Enemy getEnemy(String type, World parent, int lane) {
        Enemy enemy = null;
        switch (type) {
            case "SawMan":
                enemy = new SawMan(parent, lane);
                break;
            case "SwordMan":
                enemy = new SwordMan(parent, lane);
                break;
            case "ArmouredSwordMan":
                enemy = new ArmouredSwordMan(parent, lane);
                break;
        }
        return enemy;
    }
    
    // Setter and getter for health
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    // Setter and getter for speed
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    // setter and getter for positin in x coordinate
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
    
    // Setter and getter for lane
    public int getlane() {
        return lane;
    }

    public void setlane(int lane) {
        this.lane = lane;
    }
    
    // Setter and getter for isMoving
    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }
    
}