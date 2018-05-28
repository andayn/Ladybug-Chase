package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SpiderCollisionCommand extends Command {
	private GameWorld gw;
	
	public SpiderCollisionCommand(GameWorld temp) {
		super("SpiderCollision");
		gw = temp;
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		gw.spiderCollision();
	}
} 
