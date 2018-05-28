package com.mycompany.a3;

import java.util.Observer;
import java.util.Observable;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class MapView extends Container implements Observer {
	private Game g;
	private GameWorld mapUpdate;
	private GameWorld game;
	private GameObjectCollection objList;
	private boolean newPos;
	
	public MapView(GameWorld gameTemp, Game gTemp) {
		getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(255, 0, 0)));
		this.game = gameTemp;
		objList = game.getObjList();
		this.g = gTemp;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(getX(), getY());
		((Ladybug)objList.get(0)).draw(g, pCmpRelPrnt);
		for (int i = 1; i <= objList.size() - 1; i++) {
			if (objList.get(i) instanceof Flags) {
				((Flags)objList.get(i)).draw(g, pCmpRelPrnt);
			} else if (objList.get(i) instanceof FoodStations) {
				((FoodStations)objList.get(i)).draw(g, pCmpRelPrnt);
			} else if (objList.get(i) instanceof Spider) {
				((Spider)objList.get(i)).draw(g, pCmpRelPrnt);
			}
		}
	}
	
	public void pointerPressed(int x, int y) {
		if (g.isPaused()) {
			x = x - getParent().getAbsoluteX();
			y = y - getParent().getAbsoluteY();
			Point pPtrRelPrnt = new Point(x, y);
			Point pCmpRelPrnt = new Point(getX(), getY());
			IIterator itr = objList.getIterator();
			while (itr.hasNext()) {
				Object obj = itr.getNext();
				if (obj instanceof ISelectable) {
					ISelectable tempObj = (ISelectable)obj;
					if (tempObj.contains(pPtrRelPrnt, pCmpRelPrnt)) {
						tempObj.setSelected(true);
					} else if (newPos && tempObj.isSelected()) {
						GameObject temp = (GameObject)tempObj;
						temp.setX(pPtrRelPrnt.getX() - pCmpRelPrnt.getX());
						temp.setY(pPtrRelPrnt.getY() - pCmpRelPrnt.getY());
						newPos = false;
					} else {
						tempObj.setSelected(false);
					}
				}
			}
			repaint();
		}
	}
	
	public void setPosition() {
		newPos = true;
	}
	public void update(Observable o, Object arg) {
		repaint();
		mapUpdate = (GameWorld) o;
		System.out.println(mapUpdate.printView());
	}
}
