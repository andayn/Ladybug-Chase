package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SteerLeftCommand extends Command {
	private GameWorld gw;
	
	public SteerLeftCommand(GameWorld temp) {
		super("SteerLeft");
		gw = temp;
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		gw.steerLeft();
	}
} 
