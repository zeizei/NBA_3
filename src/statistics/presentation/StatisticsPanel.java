package statistics.presentation;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import presentation.MainFrame;
import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyPanel;
import presentation.start.Main;
import presentation.statics.NUMBER;

public class StatisticsPanel extends MyPanel {

	private static final long serialVersionUID = 1L;
	protected static String player = "jamesle01";
	protected static String comparePlayer = "";
	private FunctionPanel functionPanel;
	private ContentPanel contentPanel;
	private MyPanel panel[] = new MyPanel[] { new SeasonAnalysisPanel(player),
			new OffenseAnalysisPanel(player), new DefenseAnalysisPanel(player),
			new CompareAnalysisPanel() };
	private JButton button[] = new JButton[] { new MyButton("赛季分析"),
			new MyButton("进攻分析"), new MyButton("防守分析"), new MyButton("球员对比") };

	public StatisticsPanel() {
		functionPanel = new FunctionPanel();
		contentPanel = new ContentPanel();
		functionPanel.setBounds(0, 0, 150, NUMBER.FRAME_HEIGHT
				- NUMBER.NAVIGATION_PANEL_HEIGHT);
		contentPanel.setBounds(150, 0, NUMBER.FRAME_WIDTH - 150,
				NUMBER.FRAME_HEIGHT - NUMBER.NAVIGATION_PANEL_HEIGHT);
		this.add(functionPanel);
		this.add(contentPanel);
	}

	class FunctionPanel extends MyPanel implements MouseListener {
		private static final long serialVersionUID = 1L;

		public FunctionPanel() {

			for (int i = 0; i < 4; i++) {
				this.add(button[i]);
				button[i].addMouseListener(this);
				button[i].setBounds(0, i * 50, 150, 50);
			}
		}

		public void mouseClicked(MouseEvent e) {
			for (int i = 0; i < 4; i++) {
				if (e.getSource().equals(button[i])) {
					contentPanel.showMyPanel(i);
					break;
				}
			}

		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {

		}
	}

	class ContentPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private CardLayout card;

		public ContentPanel() {
			this.setOpaque(false);
			card = new CardLayout();
			this.setLayout(card);
			for (int i = 0; i < 4; i++) {
				this.add(panel[i], String.valueOf(i));
			}
//			Main.mainFrame.changeBackGround(new ImageIcon(
//					"images/statistics/background_season.png"));
		}

		public void showMyPanel(int i) {

			this.card.show(contentPanel, String.valueOf(i));
		}
	}
}
