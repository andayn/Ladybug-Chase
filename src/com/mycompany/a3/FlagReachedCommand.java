package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class FlagReachedCommand extends Command {
	private GameWorld gw;
	
	public FlagReachedCommand(GameWorld temp) {
		super("Flag Reached");
		gw = temp;
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		int flag;
		
		TextField textField = new TextField();
		
		Command ok = new Command("OK");
		Command cancel = new Command("Cancel");
		Command[] cmds = new Command[] {ok, cancel};
		Command prompt = Dialog.show("Enter Flag Number: ", textField, cmds);
		
		if (prompt == ok) {
			String temp = textField.getText().toString();
			try {
				flag = Integer.parseInt(temp);
				if (flag < 9) {
				//	gw.flagsReached(flag);
				}
			} catch (NumberFormatException e) {
				Dialog.show("Error Message", "You have made an invalid input!", "OK", null);
			}
		}
	}

}
