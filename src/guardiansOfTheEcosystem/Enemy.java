package guardiansOfTheEcosystem;

import javax.swing.JOptionPane;

public class Enemy {

    private int health;
    private int speed;


    private int posX = 1000;
    private int lane;
    private boolean isMoving = true;
    
    World gp;

    public Enemy(World gp, int health, int speed, int enelane) {
    	this.gp = gp;
    	this.health = health;
    	this.speed = speed;
    	this.lane = enelane;
    }
    
    public void move() {
        if (isMoving) {
            posX -= 1;
            
            if (posX < 0) {
                isMoving = false;
                JOptionPane.showMessageDialog(gp, "Enemy is destroying the ecosystem !" + '\n' + "Starting the level again");
                GameWindow.gw.dispose();
                GameWindow.gw = new GameWindow();
            }
        }
    }
    
    public static Enemy getEnemy(String type, World parent, int lane) {
        Enemy enemy = null;
        switch (type) {
            case "SawMan":
                enemy = new SawMan(parent, lane);
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