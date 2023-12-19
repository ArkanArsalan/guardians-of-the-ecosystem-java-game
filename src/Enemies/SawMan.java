package Enemies;

import Panels.World;

public class SawMan extends Enemy {
	
	private static final int HEALTH = 1750;
	private static final int MOVE_SPEED = 1;
	
	public SawMan(World gp, int lane) {
		super(gp, HEALTH, MOVE_SPEED, lane);
	}
	
}
