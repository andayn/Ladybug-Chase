package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class DecelerateCommand extends Command {
	private GameWorld gw;
	
	public DecelerateCommand(GameWorld temp) {
		super("Decelerate");
		gw = temp;
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		gw.decelerate();
	}
}
