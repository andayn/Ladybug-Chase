package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

import com.codename1.ui.Button;
import com.codename1.ui.Component;

public class ButtonStyle extends Button {
	public ButtonStyle(String button) {
		super.setName(button);
		setStyle();
	}
	
	public void setStyle() {
		super.getUnselectedStyle().setBgTransparency(255);
		super.getUnselectedStyle().setBgColor(ColorUtil.rgb(25, 50, 200));
		super.getUnselectedStyle().setFgColor(ColorUtil.GRAY);
		super.getAllStyles().setPadding(Component.LEFT, 5);
		super.getAllStyles().setPadding(Component.RIGHT, 5);
		super.getAllStyles().setPadding(Component.TOP, 5);
		super.getAllStyles().setPadding(Component.BOTTOM, 5);
	}
} 
