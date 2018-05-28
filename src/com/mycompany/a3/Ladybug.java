package com.mycompany.a3;

import com.codename1.ui.Graphics;

import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Ladybug extends Moveable implements ISteerable, ICollider {
	private final int maxSpeed;
	private int maximumSpeed;
	private double foodLevel;
	private double foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	private int heading;
	private int speed;
	private int steeringDirection;
	private Vector<Object> colVector;
	
	public Ladybug(int size, double location_x, double location_y, int color, int heading, int speed, int maximumSpeed) {
		super(size, location_x, location_y, color, heading, speed);
		this.maximumSpeed = maximumSpeed;
		maxSpeed = maximumSpeed;
		foodLevel = 100;
		foodConsumptionRate = .02;
		healthLevel = 10;
		lastFlagReached = 0;
		steeringDirection = 0;
		colVector = new Vector<Object>();
	}
	
	public void accelerate() {
		speed = getSpeed();	
		speed += 1;
		if (speed < maximumSpeed) { 
			setSpeed(speed);
		} else if (speed > maximumSpeed) {
			speed = maximumSpeed;
			setSpeed(speed);
		}
	}
	
	public void decelerate() {
		speed = getSpeed();
		speed -= 1;
		if (speed > 0) {
			setSpeed(speed);
		} else if (speed <= 0) {
			speed = 0;
			setSpeed(speed);
		}
	}
	
	public void move() {
		if (speed > maximumSpeed) {
			speed = maximumSpeed;
		}
		if ((healthLevel > 0) && (foodLevel > 0)) {
			heading = getHeading();
			heading = heading + steeringDirection / 4;
			setHeading(heading);
		} else if (healthLevel < 100 || foodLevel < 0) {
			speed = 0;
			setSpeed(speed);
		}
		super.move();
		resetSteering();
	}
	
	public int getFlag() {
		return lastFlagReached;
	}
	
	public void setFlag(int flag) {
		lastFlagReached = flag;
	}
	
	public void collision() {
		if (healthLevel <= 10) {
			healthLevel -= 1;
			maximumSpeed -= 1;
		} else {
			healthLevel = 10;
			maximumSpeed = 10;
		}
	}
	
	public void foodConsumption() {
		if (foodLevel > 0) {
			foodLevel = foodLevel - foodConsumptionRate;
			if (foodLevel < 100) {
				maximumSpeed = (int)foodLevel / 10;
			}
		} else {
			foodLevel = 0;
		}
	}
	
	public double getFood() {
		return foodLevel;
	}
	
	public void setFood(double food) {
		foodLevel = food;
	}
	
	public int getHealth() {
		return healthLevel;
	}
	
	public void setHealth(int hp) {
		healthLevel = hp;
	}
	
	public void resetSpeed() {
		maximumSpeed = maxSpeed;
	}
	
	public String toString() {
		String ladybugDesc = " maxSpeed:" + maximumSpeed + ", foodConsumptionRate=" + foodConsumptionRate;
		String generalDesc = super.toString();
		String output = "Ladybug: " + generalDesc + ladybugDesc;
		return output;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(getColor());
		g.fillArc((int)getX() - (getSize() / 2) + (int)pCmpRelPrnt.getX(), (int)getY() - (getSize() / 2) + (int)pCmpRelPrnt.getY(), getSize(), getSize(), 0, 360);
	}
	
	public boolean collidesWith(GameObject obj) {
		boolean collided = false;
		double lbCenterX = this.getX() + getSize() / 2;
		double lbCenterY = this.getY() + getSize() / 2;
		double objCenterX = obj.getX() + getSize() / 2;
		double objCenterY = obj.getY() + getSize() / 2;
		
		double dx = lbCenterX - objCenterX;
		double dy = lbCenterY - objCenterY;
		
		double distSquared = (dx * dx + dy * dy);
		
		double lbRadius = getSize() / 2;
		double objRadius = getSize() / 2;
		double radSquared = ((lbRadius * lbRadius) + (2 * lbRadius * objRadius) + (objRadius * objRadius));
		
		if (distSquared <= radSquared) {
			collided = true;
		}
		
		return collided;
	}
	
	public void handleCollision(GameObject obj, GameObject curObj, GameWorld temp, Game game) {
		if (obj instanceof Spider) {
			this.setHealth(-10);
			temp.damage();
			game.playCollisionSound();
		} else if (obj instanceof FoodStations) {
			temp.collectFoodStation();
			game.playFoodSound();
		} else if (obj instanceof Flags) {
			temp.flagsReached(((Flags)obj).getFlag(), curObj);
		} 
	}
	
	public void steerLeft() {
		if (steeringDirection > -80) {
			steeringDirection -= 40;
		} else {
			System.out.println("Ladybug has reached max steering direction.");
		}
	}
	
	public void steerRight() {
		if (steeringDirection < 80) {
			steeringDirection += 40;
		} else {
			System.out.println("Ladybug has reached max steering direction.");
		}
	}
	
	public void setSteeringDirection(int steering) {
		this.steeringDirection = steering;
	}
	
	public void addToColSet(Object obj) {
		colVector.add(obj);
	}
	
	public void removeFromColSet(Object obj) {
		colVector.remove(obj);
	}
	
	public Vector<Object> getColSet() {
		return colVector;
	}
	
	public void resetSteering() {
		steeringDirection = 0;
	}
}
