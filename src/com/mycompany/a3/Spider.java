package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class Spider extends Moveable {
	private Random rand = new Random();
	private double location_x;
	private double location_y;
	private double deltaX;
	private double deltaY;
	private int heading;
	private int speed;
	
	public Spider(int size, double location_x, double location_y, int color, int heading, int speed) {
		super(size, location_x, location_y, color, heading, speed);
		this.heading = heading;
		this.speed = speed;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt){
		g.setColor(getColor());
		g.drawPolygon(new int [] { ((int)getX()) + (int)pCmpRelPrnt.getX(), 
					(int)getX() - (getSize()/2) + (int)pCmpRelPrnt.getX(), 
					(int)getX() + (getSize()/2) + (int)pCmpRelPrnt.getX()},
					new int [] { (int)getY() + (getSize()/2) + (int)pCmpRelPrnt.getY(), 
					(int)getY() - (getSize()) + (int)pCmpRelPrnt.getY(), 
					(int)getY() - (getSize()) + (int)pCmpRelPrnt.getY()}, 3);
	}
	
	@Override
	public void move() {
		location_x = getX(); 
		location_y = getY();		
		
		deltaX = Math.cos(Math.toRadians(90 - heading)) * (speed);
		deltaY = Math.sin(Math.toRadians(90 - heading)) * (speed);
		
		double newX = location_x + deltaX;
		double newY = location_y + deltaY;
		
		//changes the spider's heading to a new heading
		//within -5 to +5 degrees
		if(rand.nextInt(2) == 1 ){
			if(rand.nextInt(2) == 1 ){
				if(rand.nextInt(2) == 1 ){
					if(rand.nextInt(2) == 1 ){
						if(rand.nextInt(2) == 1 ){
							if(rand.nextInt(2) == 1 ){
								heading = heading + (rand.nextInt(120) - 5);
							}
						}
					}
				}
			}
		}
		while(newX < 60 || newX > GameWorld.getWidth() - 60|| newY < 80 || newY > GameWorld.getHeight() - 55){
			heading = rand.nextInt(360);
			deltaX = Math.cos(Math.toRadians(90-heading))*speed;
			deltaY = Math.sin(Math.toRadians(90-heading))*speed;
			newX = location_x + deltaX;
			newY = location_y + deltaY;
		}
		super.setX(newX);
		super.setY(newY);
	}
	
	public String toString() {
		String generalDesc = super.toString();
		String output = "Spider: " + generalDesc;
		return output;
	} 
}
