package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command {
	private Game g;
	
	public AboutCommand(Game temp) {
		super("About");
		g = temp;
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		g.displayAbout();
	}
}
