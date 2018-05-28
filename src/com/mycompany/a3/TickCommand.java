package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TickCommand extends Command {
	private GameWorld gw;
	
	public TickCommand(GameWorld temp) {
		super("Tick");
		gw = temp;
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		//gw.tick();
	}
}
