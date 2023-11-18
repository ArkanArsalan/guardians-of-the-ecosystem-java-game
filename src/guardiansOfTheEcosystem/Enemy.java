package guardiansOfTheEcosystem;

public class Enemy {

    private int health;
    private int speed;


    private int posX = 1000;
    private int lane;
    private boolean isMoving = true;
    
    World gp;

    public Enemy(World gp, int health, int speed, int enemyLane) {
    	this.gp = gp;
    	this.health = health;
    	this.speed = speed;
    	this.lane = enemyLane;
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