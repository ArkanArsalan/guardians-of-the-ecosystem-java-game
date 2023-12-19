package Enemies;

import Panels.World;

public class ArmouredSwordMan extends Enemy {
	
	private static final int HEALTH = 2300;
	private static final int MOVE_SPEED = 1;
	
	public ArmouredSwordMan(World gp, int lane) {
		super(gp, HEALTH, MOVE_SPEED, lane);
	}

}
