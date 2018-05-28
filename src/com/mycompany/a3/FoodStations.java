package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class FoodStations extends Fixed {
	private int capacity;
	private boolean selected = false;
	
	public FoodStations(int size, double location_x, double location_y, int color) {
		super(size, location_x, location_y, color);
		capacity = size;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String toString() {
		String generalDesc = super.toString();
		String output = "Food Station: " + generalDesc + ", Capacity: " + getCapacity();
		return output;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int xLoc = (int)getX() - (getSize() / 2) + (int)pCmpRelPrnt.getX();
		int yLoc = (int)getY() - (getSize() / 2) + (int)pCmpRelPrnt.getY();
		if (capacity > 0 && !isSelected()) {
			g.setColor(getColor());
			g.fillRect(xLoc, yLoc, getSize(), getSize());
			g.setColor(ColorUtil.BLACK);
			g.drawString(""+ capacity, xLoc, yLoc);
		} else if (isSelected()) {
			g.setColor(ColorUtil.BLACK);
			g.drawRect(xLoc, yLoc, getSize(), getSize());
			g.setColor(ColorUtil.BLACK);
			g.drawString(""+ capacity, xLoc, yLoc);
		}
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean bool) {
		this.selected = bool;
	}
	
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		float px = pPtrRelPrnt.getX();
		float py = pPtrRelPrnt.getY();
		float xLoc = pCmpRelPrnt.getX() + (float)getX();
		float yLoc = pCmpRelPrnt.getY() + (float)getY();
		if ((px >= xLoc) && (px <= xLoc + getSize()) && (py >= yLoc) && (py <= yLoc + getSize())) {
			return true;
		} else {
			return false;
		}
	}
}
