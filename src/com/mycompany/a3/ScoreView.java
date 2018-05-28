package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;

import java.util.Observable;
import java.util.Observer;

public class ScoreView extends Container implements Observer {
	private GameWorld scoreUpdate;
	private Label timeLabel = new Label("Time Elapsed: 0");
	private Label flagReachedLabel = new Label("Flag Reached: 0");
	private Label livesLabel = new Label("Lives Left: 5");
	private Label healthLabel = new Label("Health Level: 100");
	private Label foodLabel = new Label("Food Level: 100");
	private Label soundLabel = new Label ("Sound: ON");
	
	public ScoreView() {
		setLayout(new FlowLayout(Component.CENTER));
		getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.LTGRAY));
		
		timeLabel.getStyle().setFgColor(ColorUtil.BLACK);
		timeLabel.getStyle().setPadding(Component.LEFT, 3);
		timeLabel.getStyle().setPadding(Component.RIGHT, 15);
		
		flagReachedLabel.getStyle().setFgColor(ColorUtil.BLACK);
		flagReachedLabel.getStyle().setPadding(Component.LEFT, 3);
		flagReachedLabel.getStyle().setPadding(Component.RIGHT, 7);

		livesLabel.getStyle().setFgColor(ColorUtil.BLACK);
		livesLabel.getStyle().setPadding(Component.LEFT, 3);
		livesLabel.getStyle().setPadding(Component.RIGHT, 7);
		
		healthLabel.getStyle().setFgColor(ColorUtil.BLACK);
		healthLabel.getStyle().setPadding(Component.LEFT, 3);
		healthLabel.getStyle().setPadding(Component.RIGHT, 7);
		
		foodLabel.getStyle().setFgColor(ColorUtil.BLACK);
		foodLabel.getStyle().setPadding(Component.LEFT, 3);
		foodLabel.getStyle().setPadding(Component.RIGHT, 12);
		
		soundLabel.getStyle().setFgColor(ColorUtil.BLACK);
		soundLabel.getStyle().setPadding(Component.LEFT, 3);
		soundLabel.getStyle().setPadding(Component.RIGHT, 12);
		
		add(timeLabel);
		add(flagReachedLabel);
		add(livesLabel);
		add(healthLabel);
		add(foodLabel);
		add(soundLabel);	
	}
	
	public void setSound(String sound) {
		soundLabel.setText("Sound: " + sound);
	}
	
	public void update(Observable o, Object arg) {
		scoreUpdate = (GameWorld) o;
		
		timeLabel.setText("Time Elapsed: " + scoreUpdate.getClock());
		timeLabel.getStyle().setPadding(Component.LEFT, 3);
		timeLabel.getStyle().setPadding(Component.RIGHT, 15);
		
		flagReachedLabel.setText("Flag Reached: " + scoreUpdate.getFlagReached());
		flagReachedLabel.getStyle().setPadding(Component.LEFT, 3);
		flagReachedLabel.getStyle().setPadding(Component.RIGHT, 7);
		
		livesLabel.setText("Lives: " + scoreUpdate.getLivesLeft());
		livesLabel.getStyle().setPadding(Component.LEFT, 3);
		livesLabel.getStyle().setPadding(Component.RIGHT, 7);
		
		healthLabel.setText("Health Level: " + scoreUpdate.getHealth());
		healthLabel.getStyle().setPadding(Component.LEFT, 3);
		healthLabel.getStyle().setPadding(Component.RIGHT, 7);
		
		foodLabel.setText("Food Level: " + scoreUpdate.getFoodLevel());
		foodLabel.getStyle().setPadding(Component.LEFT, 3);
		foodLabel.getStyle().setPadding(Component.RIGHT, 12);
		
	}
}
