package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Flags extends Fixed {
	private int seqNum;
	private boolean selected = false;
	
	public Flags(int size, double location_x, double location_y, int color, int seqNum) {
		super(size, location_x, location_y, color);
		this.seqNum = seqNum;
	}
	
	public int getFlag() {
		return seqNum;
	}
	
	public String toString() {
		String generalDesc = super.toString();
		String output = "Flags: " + generalDesc + "Seq Num: " + getFlag();
		return output;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt){
		g.setColor(getColor());
		int xLoc = ((int)getX()) + (int)pCmpRelPrnt.getX();
		int yLoc = (int)getY() +  (int)pCmpRelPrnt.getY();
		if (isSelected()) {
			g.drawPolygon(new int [] { xLoc, xLoc - (getSize()/2), xLoc + (getSize()/2)},
					
					new int [] { (yLoc + (getSize()/2)), yLoc - (getSize()), 
								yLoc - (getSize())}, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawString("" + seqNum, xLoc - 5, yLoc - 40);
		} else {
			g.fillPolygon(new int [] { xLoc, xLoc - (getSize()/2), xLoc + (getSize()/2)},
					
					new int [] { (yLoc + (getSize()/2)), yLoc - (getSize()), 
								yLoc - (getSize())}, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawString("" + seqNum, xLoc - 5, yLoc - 40);
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
		if ((px >= xLoc-15) && (px <= xLoc + getSize() - 40) && (py >= yLoc - 60) && (py <= yLoc + getSize() - 30)) {
			return true; 
		} else {
			return false;
		}
	}
}
