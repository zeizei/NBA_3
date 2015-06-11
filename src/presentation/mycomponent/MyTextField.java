package presentation.mycomponent;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presentation.statics.MyColor;
import presentation.statics.MyFont;

public class MyTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	public MyTextField() {
		this.setOpaque(false);
		this.setForeground(MyColor.MY_WHITE);
		this.setFont(MyFont.SMALL_PLAIN);
	}
}