package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public abstract class GameObject implements IDrawable {
	private int size;
	private double location_x;
	private double location_y;
	private int color;
	private int seqNum;
	
	public GameObject(int size, double x, double y, int col) {
		this.size = size;
		this.location_x = x;
		this.location_y = y;
		this.color = col;
	}
	
	public int getSize() {
		return size;
	}
	
	public double getX() {
		return location_x;
	}
	
	public void setX(double x) {
		location_x = x;
	}
	
	public double getY() {
		return location_y;
	}
	
	public void setY(double y) {
		location_y = y;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setColor(int r, int g, int b) {
		color = ColorUtil.rgb(r, g, b);
	}
	
	public int getSeqNum() {
		return seqNum;
	}
	
	public String toString() {
		String myDesc = "loc=" + location_x + ", " + location_y + " color=[" 
				+ ColorUtil.red(color) + ", " + ColorUtil.green(color) + ", " +
				ColorUtil.blue(color) + "] size=" + size;
		return myDesc;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt){
		
	}
}
