package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.CheckBox;

public class SideMenuSoundCheck extends Command {
	private Game g;
	
	public SideMenuSoundCheck(Game temp){
		super("Sound: ON/OFF");
		g = temp;
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		if(((CheckBox)action.getComponent()).isSelected()) {
			g.setCheckboxSound(true);
		} else {
			g.setCheckboxSound(false);
		}	
		SideMenuBar.closeCurrentMenu();
	}
	
}