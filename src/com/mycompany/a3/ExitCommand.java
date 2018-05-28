package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command {
	private Game g;
	
	public ExitCommand(Game temp) {
		super("Exit");
		g = temp;
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		g.exit();
	}
}
