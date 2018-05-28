package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PositionCommand extends Command{
	private MapView mv;
	
	public PositionCommand(MapView temp){
		super("New Position");
		mv = temp;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		mv.setPosition();	
	}
	
}