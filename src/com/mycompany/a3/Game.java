package com.mycompany.a3;

import java.util.Random;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.charts.util.ColorUtil;

public class Game extends Form implements Runnable, ActionListener {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private Random rand = new Random();
	private boolean exit = false;
	private boolean pause = false;
	private boolean mute = false;
	private Button pauseButton;
	private BGSound bgMusic;
	private UITimer timer = new UITimer(this);
	
	private AboutCommand aboutCommand;
	private AccelerateCommand accelerateCommand;
	private DecelerateCommand decelerateCommand;
	private DefaultCommand defaultCommand;
	private ExitCommand exitCommand;
	private FlagReachedCommand flagReachedCommand;
	private FoodStationCollisionCommand foodStationCommand;
	private PositionCommand positionCommand;
	private SpiderCollisionCommand spiderCommand;
	private SteerLeftCommand steerLeftCommand;
	private SteerRightCommand steerRightCommand;
	private TickCommand tickCommand;

	private ButtonStyle aboutButton;
	private ButtonStyle accelerateButton;
	private ButtonStyle decelerateButton;
	private ButtonStyle defaultButton;
	private ButtonStyle exitButton;
	private ButtonStyle flagReachedButton;
	private ButtonStyle foodStationButton;
	private ButtonStyle positionButton;
	private ButtonStyle spiderButton;
	private ButtonStyle steerLeftButton;
	private ButtonStyle steerRightButton;
	private ButtonStyle tickButton;
	
	Sound foodSound;
	Sound collisionSound;
	Sound spiderSound;
	
	public Game() {
		gw = new GameWorld();
		mv = new MapView(gw, this);
		sv = new ScoreView();
		
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		aboutCommand = new AboutCommand(this);
		accelerateCommand = new AccelerateCommand(gw);
		decelerateCommand = new DecelerateCommand(gw);
		defaultCommand = new DefaultCommand(this);
		exitCommand = new ExitCommand(this);
		flagReachedCommand = new FlagReachedCommand(gw);
		foodStationCommand = new FoodStationCollisionCommand(gw);
		positionCommand = new PositionCommand(mv);
		spiderCommand = new SpiderCollisionCommand(gw);
		steerLeftCommand = new SteerLeftCommand(gw);
		steerRightCommand = new SteerRightCommand(gw);
		tickCommand = new TickCommand(gw);
		
		// Key bindings for accelerate, decelerate, steering, spider collision, food station collision, tick and exit.
		addKeyListener('a', accelerateCommand);
		addKeyListener('b', decelerateCommand);
		addKeyListener('l', steerLeftCommand);
		addKeyListener('r', steerRightCommand);
		addKeyListener('g', spiderCommand);
		addKeyListener('f', foodStationCommand);
		addKeyListener('t', tickCommand);
		addKeyListener('x', exitCommand);
		
		this.setLayout(new BorderLayout());
		this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.GRAY));
		
		//creates a left container
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
				
		//creates a right container
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		accelerateButton = new ButtonStyle("Accelerate");
		accelerateButton.setCommand(accelerateCommand);
		decelerateButton = new ButtonStyle("Decelerate");
		decelerateButton.setCommand(decelerateCommand);
		steerLeftButton = new ButtonStyle("SteerLeft");
		steerLeftButton.setCommand(steerLeftCommand);
		steerRightButton = new ButtonStyle("SteerRight");
		steerRightButton.setCommand(steerRightCommand);
		spiderButton = new ButtonStyle("Spider Collision");
		spiderButton.setCommand(spiderCommand);
		flagReachedButton = new ButtonStyle("Flag Reached");
		flagReachedButton.setCommand(flagReachedCommand);
		foodStationButton = new ButtonStyle("Collect Food Station");
		foodStationButton.setCommand(foodStationCommand);
		exitButton = new ButtonStyle("Exit");
		exitButton.setCommand(exitCommand);
		positionButton = new ButtonStyle("Position");
		positionButton.getAllStyles().setMargin(Component.LEFT,800);
		positionButton.setCommand(positionCommand);
		positionButton.setEnabled(false);
		pauseButton = new Button("Pause");
		pauseButton.getUnselectedStyle().setBgTransparency(255);
		pauseButton.getUnselectedStyle().setBgColor(ColorUtil.rgb(50,100,100));
		pauseButton.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		pauseButton.getAllStyles().setPadding(Component.TOP, 5);
		pauseButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		pauseButton.getAllStyles().setPadding(Component.LEFT, 5);
		pauseButton.getAllStyles().setPadding(Component.RIGHT, 5);
		pauseButton.addActionListener(this);
				
		//Create bottom container for buttons
		Container bottomContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
				
		//create toolbar
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
				
		//add commands to side menu 
		myToolbar.addCommandToSideMenu(defaultCommand);
		myToolbar.addCommandToSideMenu(aboutCommand);
		myToolbar.addCommandToSideMenu(exitCommand);
				
		//add help to title
		myToolbar.addCommandToRightBar(defaultCommand);
			
		//add sound checkbox to side menu
		Command sideMenuSoundCheck = new Command("Sound");
		CheckBox checkSound = new CheckBox("Sound  ON/OFF");
		Command mySideMenuSoundCheck = new SideMenuSoundCheck(this);
		checkSound.setSelected(true);
		checkSound.setCommand(mySideMenuSoundCheck);
		checkSound.getAllStyles().setBgTransparency(255);
		checkSound.getAllStyles().setBgColor(ColorUtil.rgb(50, 100, 100));
		sideMenuSoundCheck.putClientProperty("SideComponent", checkSound);
		myToolbar.addCommandToSideMenu(sideMenuSoundCheck);
				
		this.setTitle("Ladybug Game");

		//add top buttons into left container
		leftContainer.add(accelerateButton);
		leftContainer.add(steerLeftButton);

		//add top buttons into right container
		rightContainer.add(decelerateButton);
		rightContainer.add(steerRightButton);
				
		//add top buttons into left container
		bottomContainer.add(positionButton);
		bottomContainer.add(pauseButton);

		this.add(BorderLayout.SOUTH, bottomContainer);
		this.add(BorderLayout.WEST, leftContainer);
		this.add(BorderLayout.EAST, rightContainer);

		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.CENTER,mv);
		this.show();
				
		gw.setHeight(mv.getHeight());
		gw.setWidth(mv.getWidth());
		gw.init();
				
		//set sounds
		bgMusic = new BGSound("Main.mp3");
		bgMusic.play();
				
		foodSound = new Sound("food.wav");
		collisionSound = new Sound("collision.wav");
		spiderSound = new Sound("spider.wav");
		timer.schedule(20, true, this);
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public void defaultCommand() {
		Dialog.show("Help Commands", " \n a || Accelerate the ladybug's speed. \n b || Brake the ladybug's speed. "
				+ " \n l || Steer the ladybug left. \n r || Steer the ladybug right. \n 1-9 || Check to see if the ladybug has reached flag number 1-9."
				+ " \n f || Pretends that ladybug has collided with a food station. \n g || Pretends that a spider has collided with your ladybug."
				+ " \n t || Tells the game world that the clock has ticked. \n d || Generate a display of game information."
				+ " \n m || Tell the game world to output a map showing the current world. \n x || Exit the game.", "OK", null);
	}
	
	public void displayAbout() {
		Dialog.show("About", "Ladybug Game \n Andrew Nguyen \n CSC133", "OK", null);
	}
	
	public void displayExit() {
		this.setTitle("Exiting game.");
		Boolean exitOk = Dialog.show("Confirming exit", "Do you want to exit?", "OK", "Cancel");
		if (exitOk) {
			Display.getInstance().exitApplication();
		}
	}
	
	public void displayEndGame() {
		boolean endGame = Dialog.show("You have reached the last flag!", "The game will end now.", "Exit", null);
		if (endGame) {
			Display.getInstance().exitApplication();
		}
		
	}
	
	public boolean isPaused() {
		return pause;
	}
	
	public void setCheckboxSound(boolean check) {
		if (check) {
			sv.setSound("ON");
			mute = false;
			if (!mute && !pause) {
				bgMusic.play();
			}
		} else {
			sv.setSound("OFF");
			mute = true;
			bgMusic.pause();
		}
	}
	
	public void run() {
		gw.tick();
		GameObjectCollection listRef = gw.getObjList();		
		for (int i = 0; i < listRef.size(); i++) {
			if (listRef.get(i) instanceof ICollider) {
				Ladybug currentObj = (Ladybug)listRef.get(i);
				for (int j = 0; j < listRef.size(); j++) {
					if (listRef.get(j) instanceof GameObject) {
						GameObject otherObj = (GameObject)listRef.get(j);
						if (currentObj != otherObj) {
							if (!currentObj.getColSet().contains(otherObj)) {
								if (currentObj.collidesWith(otherObj)) {
									currentObj.addToColSet(listRef.get(j));
									currentObj.handleCollision(otherObj, currentObj, gw, this);
									if (listRef.get(j) instanceof ICollider) {
										Ladybug obj = (Ladybug)listRef.get(j);
										obj.addToColSet(listRef.get(i));
									}
								}
							}
						}
						if (currentObj.getColSet().contains(otherObj) && !currentObj.collidesWith(otherObj)) {
							currentObj.removeFromColSet(otherObj);
							if (otherObj instanceof ICollider) {
								Ladybug obj = (Ladybug)otherObj;
								obj.removeFromColSet(currentObj);
							}
						}		
					}						
				}
			}				
		}
	}
	
	public void playFoodSound() {
		if (!mute) {
			foodSound.play();
		}
	}
	
	public void playCollisionSound() {
		if (!mute) {
			collisionSound.play();
		}
	}
	
	public void playSpiderSound() {
		if (!mute) {
			spiderSound.play();
		}
	}
	
	public void actionPerformed(ActionEvent evt) {
		pause = !pause;
		if (pause) {
			pauseButton.setText("Play");
			//stop timer which disable tick
			timer.cancel();
			accelerateButton.setEnabled(false);
			decelerateButton.setEnabled(false);
			steerLeftButton.setEnabled(false);
			steerRightButton.setEnabled(false);
			positionButton.setEnabled(true);
			removeKeyListener('a', accelerateCommand);
			removeKeyListener('b', decelerateCommand);
			removeKeyListener('l', steerLeftCommand);
			removeKeyListener('r', steerRightCommand);
			
			bgMusic.pause();
		}
		else {
			//enable buttons
			accelerateButton.setEnabled(true);
			decelerateButton.setEnabled(true);
			steerLeftButton.setEnabled(true);
			steerRightButton.setEnabled(true);
			positionButton.setEnabled(false);
			//enable keys
			addKeyListener('a', accelerateCommand);
			addKeyListener('b', decelerateCommand);
			addKeyListener('l', steerLeftCommand);
			addKeyListener('r', steerRightCommand);
			if (!mute) {
				bgMusic.play();
			}
			GameObjectCollection listRef = gw.getObjList();
			
			for(int i = 0; i < listRef.size();i++){
				if (listRef.get(i) instanceof Flags) {
					Flags fObj = (Flags)listRef.get(i);
					fObj.setSelected(false);
				}else if (listRef.get(i) instanceof FoodStations) {
					FoodStations fsObj = (FoodStations)listRef.get(i);
					fsObj.setSelected(false);
				}
			}	
			timer.schedule(20, true, this);
		}
	}
}
