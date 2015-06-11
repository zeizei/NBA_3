package presentation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyPanel;
import presentation.players.OnePlayerPanel;
import presentation.statics.MyColor;
import presentation.statics.MyFont;
import presentation.statics.NUMBER;

public class SonFrame {
	public static JFrame cardFrame;
	public static String playerCard = "player";
	public static String teamCard = "team";
	public static String matchCard = "match";
	private static JLabel BG;

	public SonFrame(Object o, String str) {
		cardFrame = new JFrame();
		cardFrame.setUndecorated(true);
		cardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cardFrame.setLayout(null);
		cardFrame.setBackground(MyColor.BACKGROUNDCOLOR);
		cardFrame.setBounds((NUMBER.SCREEN_WIDTH - NUMBER.FRAME_WIDTH) / 2, (NUMBER.SCREEN_HEIGHT - NUMBER.FRAME_HEIGHT) / 2 - 20, NUMBER.FRAME_WIDTH, NUMBER.FRAME_HEIGHT);
		MyPanel contentPanel = null;
		if (str.equals(playerCard)) {
			contentPanel = new OnePlayerPanel((String) o);
			BG = new JLabel(new ImageIcon("images/players/background_playercard.png"));
		}
		else if (str.endsWith(teamCard)) {
			// contentPanel = new OneTeamPanel((String) o);
			BG = new JLabel(new ImageIcon("images/teams/background_teamcard.png"));
		}
		else if (str.endsWith(matchCard)) {
			// contentPanel = new OneMatchPanel((GeneralInfoOfOneMatch) o);
			BG = new JLabel(new ImageIcon("images/matchs/background_matchcard.png"));
		}
		BG.setBounds(0, 0, NUMBER.FRAME_WIDTH, NUMBER.FRAME_HEIGHT);
		cardFrame.getLayeredPane().add(BG, new Integer(Integer.MIN_VALUE));
		JPanel jp = (JPanel) cardFrame.getContentPane();
		jp.setOpaque(false);

		headPanel headPanel = new headPanel();
		headPanel.setBounds(0, 0, NUMBER.FRAME_WIDTH, NUMBER.NAVIGATION_PANEL_HEIGHT);
		contentPanel.setBounds(0, NUMBER.NAVIGATION_PANEL_HEIGHT, NUMBER.FRAME_WIDTH, NUMBER.FRAME_HEIGHT - NUMBER.NAVIGATION_PANEL_HEIGHT);
		cardFrame.getContentPane().add(contentPanel);
		cardFrame.getContentPane().add(headPanel);
		cardFrame.setVisible(true);
	}

	public static void changePanel(MyPanel before, MyPanel after, String afterStr) {
		if (afterStr.equals(playerCard)) {
			SonFrame.changeBackGround(new ImageIcon("images/players/background_playercard.png"));
		}
		else if (afterStr.endsWith(teamCard)) {
			SonFrame.changeBackGround(new ImageIcon("images/teams/background_teamcard.png"));
		}
		else if (afterStr.endsWith(matchCard)) {
			SonFrame.changeBackGround(new ImageIcon("images/matchs/background_matchcard.png"));
		}
		SonFrame.cardFrame.getContentPane().remove(before);
		SonFrame.cardFrame.getContentPane().add(after);
		after.setBounds(0, NUMBER.NAVIGATION_PANEL_HEIGHT, NUMBER.FRAME_WIDTH, NUMBER.FRAME_HEIGHT - NUMBER.NAVIGATION_PANEL_HEIGHT);
		SonFrame.cardFrame.getContentPane().validate();
		SonFrame.cardFrame.getContentPane().repaint();
	}

	private static void changeBackGround(ImageIcon icon) {
		cardFrame.getLayeredPane().remove(BG);
		BG = new JLabel(icon);
		BG.setBounds(0, 0, NUMBER.FRAME_WIDTH, NUMBER.FRAME_HEIGHT);
		cardFrame.getLayeredPane().add(BG, new Integer(Integer.MIN_VALUE));
	}

	class headPanel extends MyPanel {

		private static final long serialVersionUID = 1L;
		private MyButton quitButton = new MyButton(new ImageIcon("images/quitButton_normal.png"));

		public headPanel() {
			quitButton.setBackground(MyColor.MIDDLE_COLOR);
			quitButton.setForeground(MyColor.MY_WHITE);
			quitButton.setFont(MyFont.LARGE_PLAIN);
			quitButton.setBounds(1200, 10, 50, 50);
			quitButton.addMouseListener(new MouseListener() {

				public void mouseReleased(MouseEvent arg0) {

				}

				public void mousePressed(MouseEvent arg0) {

				}

				public void mouseExited(MouseEvent arg0) {
					quitButton.setIcon(new ImageIcon("images/quitButton_normal.png"));
				}

				public void mouseEntered(MouseEvent arg0) {
					quitButton.setIcon(new ImageIcon("images/quitButton_enter.png"));
				}

				public void mouseClicked(MouseEvent arg0) {
					cardFrame.dispose();
				}
			});
			this.add(quitButton);
			this.setVisible(true);
		}
	}
}
