package presentation.hotsport;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;

import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.statics.MyColor;
import presentation.statics.MyFont;
import presentation.statics.NUMBER;
import businesslogic.game.GameInfoBl;

public class HotSportPanel extends MyPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	public static MyLabel showNew = new MyLabel("没有更新");// 提示更新
	private static MyLabel nowDate;
	private static MyLabel nowYear;
	private final int buttonWidth = 180;
	private final int buttonHeight = 50;
	private final int buttonX = 113;
	private MyButton[] button = new MyButton[] { new MyButton("今日数据王"), new MyButton("赛季数据王"), new MyButton("进步最快球员"), new MyButton("赛季球队数据王") };
	private MyPanel panel[] = new MyPanel[] { new DailyPlayerKingPanel(), new SeasonPlayerKingPanel(), new MostImprovedPlayerPanel(), new SeasonTeamKingPanel() };
	private ContentPanel contentPanel = new ContentPanel();
	private int flag = 0;

	public HotSportPanel() {
		this.createObjects();
		this.setComponentStyle();
		this.setComponentsLocation();
		this.addListener();
		String date = new GameInfoBl().getLatestDate();
		HotSportPanel.refreshDate(date);
		this.setVisible(true);
	}

	private void createObjects() {
		nowDate = new MyLabel();
		nowYear = new MyLabel();
	}

	public static void showNew() {
		showNew.setText("有更新");
	}

	public static void showRefreshed() {
		showNew.setText("没有更新");
	}

	public static void refreshDate(String dateString) {
		String[] splitStr = dateString.split("-");
		nowDate.setText(splitStr[1] + "月" + splitStr[2] + "日");
		nowYear.setText(splitStr[0] + "年");
	}

	private void addListener() {
		for (int i = 0; i < 4; i++) {
			button[i].addMouseListener(this);
		}
	}

	private void setComponentsLocation() {
		for (int i = 0; i < 4; i++) {
			button[i].setBounds(0, i * buttonHeight + buttonX, buttonWidth, buttonHeight);
			this.add(button[i]);
		}
		contentPanel.setBounds(180, 0, NUMBER.FRAME_WIDTH - 180, NUMBER.FRAME_HEIGHT - NUMBER.NAVIGATION_PANEL_HEIGHT);
		showNew.setBounds(0, 7 * buttonHeight + buttonX, buttonWidth, buttonHeight);
		nowDate.setBounds(0, 10, 180, 50);
		nowYear.setBounds(0, 70, 180, 30);
		this.add(contentPanel);
		this.add(showNew);
		this.add(nowDate);
		this.add(nowYear);
	}

	private void setComponentStyle() {
		for (int i = 0; i < 4; i++) {
			button[i].setContentAreaFilled(true);
			button[i].setBackground(MyColor.MIDDLE_COLOR);
		}
		button[0].setBackground(MyColor.SELECTED);
		showNew.setFont(MyFont.SMALL_BOLD);
		showNew.setForeground(Color.white);
		showNew.setHorizontalAlignment(SwingConstants.CENTER);

		nowDate.setFont(MyFont.LARGEST_BOLD);
		nowDate.setForeground(Color.white);
		nowDate.setHorizontalAlignment(SwingConstants.CENTER);
		nowYear.setFont(MyFont.MIDDLE_BOLD);
		nowYear.setForeground(MyColor.LIGHT_COLOR);
		nowYear.setHorizontalAlignment(SwingConstants.CENTER);

	}

	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < 4; i++) {
			if (e.getSource().equals(button[i])) {
				contentPanel.showMyPanel(i);
				button[flag].setBackground(MyColor.MIDDLE_COLOR);
				button[i].setBackground(MyColor.SELECTED);
				flag = i;
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
		for (int i = 0; i < 4; i++) {
			if (e.getSource().equals(button[i])) {
				button[i].setBackground(MyColor.DEEP_COLOR);
			}
		}
	}

	public void mouseExited(MouseEvent e) {
		for (int i = 0; i < 4; i++) {
			if (e.getSource().equals(button[i])) {
				if (flag == i) {
					button[i].setBackground(MyColor.SELECTED);
				}
				else {
					button[i].setBackground(MyColor.MIDDLE_COLOR);
				}

			}
		}

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	class ContentPanel extends MyPanel {
		private static final long serialVersionUID = 1L;
		private CardLayout card;

		ContentPanel() {
			card = new CardLayout();
			this.setLayout(card);
			for (int i = 0; i < 4; i++) {
				this.add(panel[i], String.valueOf(i));
			}
		}

		public void showMyPanel(int i) {
			this.card.show(contentPanel, String.valueOf(i));
		}
	}
}
