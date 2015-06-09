package presentation.mycomponent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class MyScrollPanel extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyScrollPanel(JTable table) {
		super(table);
		
	}

	public MyScrollPanel() {
		super();
		this.setOpaque(false);
//		this.setLayout(null);
		this.getViewport().setOpaque(false);
		this.setBorder(new EmptyBorder(0,0,0,0));
	}
}
