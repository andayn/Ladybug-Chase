package com.mycompany.a3;

import java.lang.Math;
import java.util.Random;

public abstract class Moveable extends GameObject {

	private int heading;
	private int speed;
	private double location_x;
	private double location_y;
	private double deltaX;
	private double deltaY;
	private Random rand = new Random();
	
	public Moveable(int size, double x, double y, int color, int heading, int speed) {
		super(size, x, y, color);
		this.heading = heading;
		this.speed = speed;
	}
	
	public void move() {
		location_x = getX();
		location_y = getY();
		
		deltaX = Math.cos(Math.toRadians(90-heading)) * (speed);
		deltaY = Math.sin(Math.toRadians(90-heading)) * (speed);
		
		double newX = deltaX + location_x;
		double newY = deltaY + location_y;
		
		while (newX < 40 || newX > GameWorld.getWidth() - 40 || newY < 40 || newY > GameWorld.getHeight() - 40) {
			heading = rand.nextInt(360);
			deltaX = Math.cos(Math.toRadians(90-heading)) * (speed);
			deltaY = Math.sin(Math.toRadians(90-heading)) * (speed);
			newX = location_x + deltaX;
			newY = location_y + deltaY;
		}
		
		super.setX(newX);
		super.setY(newY);
	}
	
	public int getHeading() {
		return heading;
	}
	
	public void setHeading(int head) {
		this.heading = head;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public String toString() {
		String moveDesc = ", heading: " + heading + ", speed: " + speed;
		String generalDesc = super.toString();
		String xandy = ", X:" + getX() + ", Y:" + getY();
		return generalDesc + moveDesc + xandy;
	}
}
