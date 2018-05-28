package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class FoodStationCollisionCommand extends Command {
	private GameWorld gw;
	
	public FoodStationCollisionCommand(GameWorld temp) {
		super("FoodStationCollision");
		gw = temp;
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		gw.collectFoodStation();
	}

}
