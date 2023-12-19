package Enemies;

import Panels.World;

public class SwordMan extends Enemy {
	
	private static final int HEALTH = 1500;
	private static final int MOVE_SPEED = 2;
	
	public SwordMan(World gp, int lane) {
		super(gp, HEALTH, MOVE_SPEED, lane);
	}
	
}
