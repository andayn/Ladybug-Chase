package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SteerRightCommand extends Command {
	private GameWorld gw;
	
	public SteerRightCommand(GameWorld temp) {
		super("SteerRight");
		gw = temp;
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		gw.steerRight();
	}
} 
