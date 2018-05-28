package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class DefaultCommand extends Command {
	private Game g;
	
	public DefaultCommand(Game temp) {
		super("Help");
		g = temp;
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		g.defaultCommand();
	}
}
