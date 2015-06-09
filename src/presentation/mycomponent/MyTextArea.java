package presentation.mycomponent;

import java.awt.Color;

import javax.swing.JTextArea;

import presentation.statics.MyColor;

public class MyTextArea extends JTextArea{
	
	private static final long serialVersionUID = 1L;

	public MyTextArea(){
		this.setEditable(false);
		this.setOpaque(false);
		this.setForeground(MyColor.LIGHT_COLOR);
	}
}
