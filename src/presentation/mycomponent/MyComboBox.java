package presentation.mycomponent;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import presentation.statics.MyColor;
import presentation.statics.MyFont;

@SuppressWarnings("hiding")
public class MyComboBox<String> extends JComboBox<String> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public MyComboBox(String[] Items) {
		super(Items);
		this.setSize(220, 40);
		this.setForeground(Color.black);
		this.setFont(MyFont.SMALLEST_PLAIN);
		this.setRenderer(new ColorComboBoxRenderer());
		this.setFocusable(false);
	}

	public MyComboBox() {

	}

	public class ColorComboBoxRenderer extends BasicComboBoxRenderer {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("rawtypes")
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			c.setForeground(Color.black);
			setHorizontalAlignment(CENTER);
			if (isSelected) {
				c.setBackground(MyColor.SELECTED);
			}
			return c;
		}
	}

	// public void paint(Graphics g) {
	// Graphics2D g2d = (Graphics2D) g;
	// AlphaComposite newComposite =
	// AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f);// 在这里设置透明度
	// g2d.setComposite(newComposite);
	// super.paint(g2d);
	// }
	//
	// protected void paintComponent(Graphics g) {// 绘制组件界面的方法
	// Graphics2D g2d = (Graphics2D) g;// 获取2D绘图上下文
	// Composite composite = g2d.getComposite();// 备份合成模式 // 设置绘图使用透明合成规则
	// g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
	// 0.8f));
	// g2d.fillRect(0, 0, getWidth(), getHeight());// 使用当前颜色填充矩形空间
	// g2d.setComposite(composite);// // 恢复原有合成模式
	// super.paintComponent(g2d);// 执行超类的组件绘制方法
	// }
}
