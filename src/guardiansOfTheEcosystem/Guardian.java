package guardiansOfTheEcosystem;

public abstract class Guardian {
	
    private int health;
    private int x;
    private int y;
    private int energyPrice;

    private World gp;

    public Guardian(World gp, int x, int y, int health, int energyPrice) {
        this.x = x;
        this.y = y;
        this.gp = gp;
        this.health = health;
        this.energyPrice = energyPrice;
    }
    
    public abstract void stop();
    
    // Setter and getter for health
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    // Setter and getter for x coordinate
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    // Setter and getter for y coordinate
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getEnergyPrice() {
    	return energyPrice;
    }
    
    public World getGp() {
        return gp;
    }

    public void setGp(World gp) {
        this.gp = gp;
    }
}
