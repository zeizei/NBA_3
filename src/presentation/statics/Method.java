package presentation.statics;

import java.awt.Image;
import java.math.BigDecimal;

import javax.swing.ImageIcon;

public class Method {
	public static ImageIcon changeSize(ImageIcon icon, int width, int height) {
		if (icon != null) {
			ImageIcon Icon = icon;
			Image image = Icon.getImage();
			Image temp = image.getScaledInstance(width, height, Image.SCALE_REPLICATE);
			Icon.setImage(temp);
			return Icon;
		}
		return null;
	}
	
	public static double cutTail(double number) {
		BigDecimal bigDecimal = new BigDecimal(number);
		double result = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return result;
	}// 保留2位小数
}
