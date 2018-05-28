package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AccelerateCommand extends Command {
	private GameWorld gw;
	
	public AccelerateCommand(GameWorld temp) {
		super("Accelerate");
		gw = temp;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.accelerate();
	}
} 
