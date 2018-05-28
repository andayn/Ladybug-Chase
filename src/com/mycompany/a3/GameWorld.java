package com.mycompany.a3;

import java.util.Observable;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class GameWorld extends Observable {
	private static int height, width;
	private int gameClock;
	private int livesLeft = 5;
	private int health;
	private double foodLevel;
	private int flagsReached;
	
	private double xLoc;
	private double yLoc;
	
	private GameObjectCollection objList;
	
	Random rand = new Random();
	
	public GameWorld() {
		objList = new GameObjectCollection();
	}
	
	public void init() {
		objList.add(new Ladybug(80, width / 2, height / 2, ColorUtil.rgb(125, 200, 134), 0, 5, 10));
		for (int i = 1; i < 5; i++) {
			randomGen();
			objList.add(new Flags(60, xLoc, yLoc, ColorUtil.rgb(100, 100, 100), i));
		}
		for (int s = 0; s < 2; s++) {
			randomGen();
			objList.add(new Spider(80, xLoc, yLoc, ColorUtil.rgb(130, 210, 30), rand.nextInt(359), (rand.nextInt(5) + 5)));
		}
		for (int f = 0; f < 2; f++) {
			randomGen();
			objList.add(new FoodStations(60, xLoc, yLoc, ColorUtil.rgb(255, 255, 255)));
		}
		//setChanged();
		//notifyObservers();
	}
	// additional methods here to manipulate world objects and related game data
	public void tick() {
		((Ladybug)getObject(0)).foodConsumption();
		starving();
		gameClock++;
		for (int i = 0; i < objList.size(); i++) {
			if (getObject(i) instanceof Moveable) {
				((Moveable)objList.get(i)).move();
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public Object getObject(int obj) {
		return objList.get(obj);
	}
	
	public void createStation() {
		objList.add(new FoodStations((rand.nextInt(10) + 10), Math.round(rand.nextInt(1024)), Math.round(rand.nextInt(768)), ColorUtil.rgb(255, 255, 255)));
	}
	
	public int getLivesLeft() {
		return livesLeft;
	}
	
	public void setLivesLeft(int lives) {
		livesLeft = lives;
	}
	
	public void addLife() {
		livesLeft++;
	}
	
	public int getClock() {
		return gameClock;
	}
	
	public void damage() {
		health = ((Ladybug)objList.get(0)).getHealth();
		if (livesLeft > 0) {
			if (health <= 0) {
				livesLeft--;
				System.out.println("You have lost a life. You have " + livesLeft + " lives left.");
				((Ladybug)objList.get(0)).setHealth(10);
				((Ladybug)objList.get(0)).setFood(100);
				((Ladybug)objList.get(0)).resetSpeed();
			}
		} else {
			System.out.println("Game Over!");
			System.exit(0);
		}
		setChanged();
		notifyObservers();
	}
	
	public void flagsReached(int flag, Object obj) {
		if (obj instanceof Ladybug) {
			Ladybug flagLadybug = (Ladybug)obj;
			int numFlag = flagLadybug.getFlag();
			if (numFlag == flag - 1) {
				flagLadybug.setFlag(flag);
			}
			for (int i = 0; i < objList.size() - 1; i++) {
				if (objList.get(i) instanceof Flags) {
					Flags flg = (Flags)objList.get(i);
					Ladybug playerLb = (Ladybug)getObject(0);
					int flagReached = playerLb.getFlag();
					if (flagReached == flag - 1) {
						flagLadybug.setFlag(flag);
					}
					setChanged();
					notifyObservers();
				}
			}
		}
	}
	
	public int getFlagReached() {
		return ((Ladybug)objList.get(0)).getFlag();
	}
	
	public void starving() {
		foodLevel = ((Ladybug)objList.get(0)).getFood();
		if (livesLeft > 0) {
			if (foodLevel <= 0) {
				livesLeft--;
				System.out.println("You have lost a life. You have " + livesLeft + " lives left.");
				((Ladybug)objList.get(0)).setHealth(10);
				((Ladybug)objList.get(0)).setFood(100);
				((Ladybug)objList.get(0)).resetSpeed();
			} 
		} else {
			System.out.println("Game Over!");
			System.exit(0);
		}
		setChanged();
		notifyObservers();
	}
	
	public int getHealth() {
		return ((Ladybug)objList.get(0)).getHealth();
	}
	
	public double getFoodLevel() {
		return ((Ladybug)objList.get(0)).getFood();
	}
	
	public String printView() {
		StringBuilder output = new StringBuilder();
		IIterator iterator = objList.getIterator();
		output.append("===============================================================");
		output.append("\n");
		while (iterator.hasNext()) {
			output.append(iterator.getNext());
			output.append("\n");
		}
		output.append("===============================================================");
		return output.toString();
	}
	
	public void accelerate() {
		((Ladybug)getObject(0)).accelerate();
		setChanged();
		notifyObservers();
	}
	
	public void decelerate() {
		((Ladybug)getObject(0)).decelerate();
		setChanged();
		notifyObservers();
	}
	
	public void spiderCollision() {
		System.out.println("A spider has collided with you. You have taken 1 damage.");
		((Ladybug)getObject(0)).collision();
		damage();
		setChanged();
		notifyObservers();
	}
	
	public void steerLeft() {
		((Ladybug)getObject(0)).steerLeft();
		setChanged();
		notifyObservers();
	}
	
	public void steerRight() {
		((Ladybug)getObject(0)).steerRight();
		setChanged();
		notifyObservers();
	}
	
	public void collectFoodStation() {
		double temp = ((Ladybug)getObject(0)).getFood();
		if (temp < 99) {
			System.out.println("You have collected a food station!");
			for (int i = objList.size() - 1; i >= 1; i--) {
				if (objList.get(i) instanceof FoodStations) {
					FoodStations tempStation = (FoodStations)getObject(i);
					int stationCapacity = tempStation.getCapacity();
					double food = temp + stationCapacity;
					if (food < 99 && stationCapacity > 0) {
						((Ladybug)getObject(0)).setFood(food);
						tempStation.setCapacity(0);
						createStation();
					} else if (food >= 99 && stationCapacity > 0) {
						((Ladybug)getObject(0)).setFood(100);
						tempStation.setCapacity(0);
						createStation();
					}
				}
			}
		} else {
			System.out.println("You are too full right now!");
		}
		setChanged();
		notifyObservers();
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public static int getHeight() {
		return height;
	}
	
	public static int getWidth() {
		return width;
	}
	
	public GameObjectCollection getObjList() {
		return objList;
	}
	
	public void randomGen() {
		xLoc = rand.nextInt(width) * rand.nextDouble() * 10.0 / 10.0;
		yLoc = rand.nextInt(height) * rand.nextDouble() * 10.0 / 10.0;
		while (yLoc < 200 || xLoc < 200 || xLoc > width - 200 || yLoc > height - 200) {
			xLoc = rand.nextInt(width) * rand.nextDouble() * 10.0 / 10.0;
			yLoc = rand.nextInt(height) * rand.nextDouble() * 10.0 / 10.0;
		}
	}
}
