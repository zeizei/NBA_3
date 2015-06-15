package presentation.players;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;

import beans.GeneralPlayer;
import businesslogic.player.OnePlayerBl;
import businesslogicservice.player.OnePlayerBlService;
import presentation.SonFrame;
import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.mycomponent.MyScrollPanel;
import presentation.mycomponent.MyTable;
import presentation.mycomponent.MyTableModel;
import presentation.mycomponent.MyTextArea;
import presentation.statics.MyColor;
import presentation.statics.MyFont;
import presentation.statics.NUMBER;
import presentation.statics.PathOfFile;
import presentation.teams.OneTeamPanel;

public class OnePlayerPanel extends MyPanel implements MouseListener {
	private MyLabel playerActImage;
	private MyPanel thisPanel = this;
	private String playerName;
	private MyButton[] button = new MyButton[] { new MyButton("普通数据"), new MyButton("高级数据"), new MyButton("近期比赛") };
	private MyPanel[] panel;
	private GeneralInfoPanel generalInfoPanel;
	private MyButton teamLogo;
	private ContentPanel contentPanel;
	private int buttonHeight = 40;
	private int buttonWidth = 300;
	private static final long serialVersionUID = 1L;
	private OnePlayerBlService onePlayerInfoBl = new OnePlayerBl();
	private GeneralPlayer PlayerGeneralInfo;
	private int flag = 0;

	public OnePlayerPanel(String playerName) {
		this.playerName = playerName;
//		PlayerGeneralInfo = onePlayerInfoBl.getPlayerGeneralInfo(playerName);
//		playerNormal_avg = onePlayerInfoBl.getPlayerNormalInfo_avg(playerName);
//		playerHigh = onePlayerInfoBl.getPlayerHighInfo(playerName);
//		playerOneMatchInfoList = onePlayerInfoBl.getPlayerPerform(playerName);
		panel = new MyPanel[] { new PlayerNormalInfoPanel(), new PlayerHighInfoPanel(), new AllMatchInfoPanel() };
		this.createObjects();
		this.setComponentsLocation();
		this.setCompStyle();
		this.addListener();
		this.setVisible(true);
	}

	private void createObjects() {
		playerActImage = new MyLabel();
		generalInfoPanel = new GeneralInfoPanel();
		contentPanel = new ContentPanel();
//		teamLogo = new MyButton(new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + playerNormal_avg.getTeamName() + ".png"), 100, 100);
	}

	private void setComponentsLocation() {
		playerActImage.setBounds(-20, 20, 350, 500);
//		playerActImage.setMyIcon(new ImageIcon(PathOfFile.PLAYER_ACTION_IMAGE + playerName + ".png"));
		for (int i = 0; i < 3; i++) {
			button[i].setBounds(350 + buttonWidth * i, 200, buttonWidth, buttonHeight);
			button[i].setBackground(MyColor.MIDDLE_COLOR);
			button[i].setForeground(MyColor.MY_WHITE);
			button[i].setFont(MyFont.SMALL_BOLD);
			button[i].setContentAreaFilled(true);
			button[i].addMouseListener(this);
			this.add(button[i]);
		}
		teamLogo.setBounds(1130, 30, 100, 100);
		generalInfoPanel.setLocation(0, 0);
		contentPanel.setBounds(0, 240, NUMBER.FRAME_WIDTH, NUMBER.FRAME_HEIGHT - -(280));
		this.add(generalInfoPanel);
		this.add(contentPanel);
		this.add(teamLogo);
		this.add(playerActImage);
	}

	private void setCompStyle() {
		button[flag].setBackground(MyColor.SELECTED);
	}

	private void addListener() {
		teamLogo.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
//		for (int i = 0; i < 3; i++) {
//			if (e.getSource().equals(button[i])) {
//				contentPanel.showMyPanel(i);
//				button[flag].setBackground(MyColor.MIDDLE_COLOR);
//				button[i].setBackground(MyColor.SELECTED);
//				flag = i;
//			}
//		}
//		if (e.getSource().equals(teamLogo)) {
//			OneTeamPanel teamPanel = new OneTeamPanel(playerNormal_avg.getTeamName());
//			SonFrame.changePanel(this, teamPanel, new String("team"));
//		}
	}

	public void mouseEntered(MouseEvent e) {
		for (int i = 0; i < 3; i++) {
			if (e.getSource().equals(button[i])) {
				button[i].setBackground(MyColor.DEEP_COLOR);
			}
		}
		if (e.getSource().equals(teamLogo)) {
			teamLogo.setLocation(teamLogo.getX() - 3, teamLogo.getY() - 3);
		}
	}

	public void mouseExited(MouseEvent e) {
		for (int i = 0; i < 3; i++) {
			if (e.getSource().equals(button[i])) {
				if (flag == i) {
					button[i].setBackground(MyColor.SELECTED);
				}
				else {
					button[i].setBackground(MyColor.MIDDLE_COLOR);
				}
			}
		}
		if (e.getSource().equals(teamLogo)) {
			teamLogo.setLocation(teamLogo.getX() + (3), teamLogo.getY() + (3));
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	class GeneralInfoPanel extends MyPanel {
		private static final long serialVersionUID = 1L;
		private MyLabel portrait;
		private JTextArea playerNameText;
		private JTextArea normalInfoText;
		private JTextArea mainMatchInfoText[] = new MyTextArea[3];;

		public GeneralInfoPanel() {
			this.setSize(NUMBER.FRAME_WIDTH, 200);
			this.createObjects();
			this.setComponentsLocation();
			this.setComponentsStyle();
			this.setContents();
		}

		private void createObjects() {
			portrait = new MyLabel();
			playerNameText = new MyTextArea();
			normalInfoText = new MyTextArea();
			for (int i = 0; i < 3; i++) {
				mainMatchInfoText[i] = new MyTextArea();
			}
		}

		private void setComponentsLocation() {
			portrait.setBounds(350, 10, 230, 200);
			playerNameText.setBounds(630, 50, 250, 50);
			normalInfoText.setBounds(880, 5, 300, 195);
			for (int i = 0; i < 3; i++) {
				mainMatchInfoText[i].setBounds(630 + i * 65, 150, 65, 50);
				this.add(mainMatchInfoText[i]);
			}
			this.add(portrait);
			this.add(playerNameText);
			this.add(normalInfoText);
		}

		private void setComponentsStyle() {
			playerNameText.setFont(MyFont.MIDDLE_BOLD);
			mainMatchInfoText[1].setFont(MyFont.SMALL_PLAIN);
			mainMatchInfoText[2].setFont(MyFont.SMALL_PLAIN);
			mainMatchInfoText[0].setFont(MyFont.SMALL_PLAIN);
			normalInfoText.setFont(MyFont.SMALL_PLAIN);
		}

		private void setContents() {
			portrait.setMyIcon(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE + playerName + ".png"));
//			playerNameText.setText(playerName);
//			mainMatchInfoText[0].setText("得分\n" + playerNormal_avg.getPoint());
//			mainMatchInfoText[1].setText("篮板\n" + playerNormal_avg.getRebound());
//			mainMatchInfoText[2].setText("助攻\n" + playerNormal_avg.getAssist());
//			normalInfoText.setText("号码：" + PlayerGeneralInfo.getPlayerNumber() + "\n位置：" + PlayerGeneralInfo.getPosition() + "\n身高：" + PlayerGeneralInfo.getHeight() + "\n体重："
//					+ PlayerGeneralInfo.getWeight() + "\n生日：" + PlayerGeneralInfo.getBirthday() + "\n年龄：" + PlayerGeneralInfo.getAge() + "\n球龄：" + PlayerGeneralInfo.getTrainingYear() + "\n毕业院校："
//					+ PlayerGeneralInfo.getSchool());
		}
	}

	class ContentPanel extends MyPanel {
		private static final long serialVersionUID = 1L;
		private CardLayout card;

		ContentPanel() {
			card = new CardLayout();
			this.setLayout(card);
			for (int i = 0; i < 3; i++) {
				this.add(panel[i], String.valueOf(i));
			}
			this.setVisible(true);
		}

		public void showMyPanel(int i) {
			this.card.show(contentPanel, String.valueOf(i));
		}
	}

	class PlayerNormalInfoPanel extends MyPanel {
		private static final long serialVersionUID = 1L;
		private final int labelHeight = 50;
		private final int labelWidth = 120;
		private final int valueLabelWidth = 50;
		private final int gap = 8;
		private MyLabel fieldLabel[] = new MyLabel[25];
		private MyLabel value[] = new MyLabel[25];
		private String[] fieldString = { "所属球队:", "参赛场数:", "首发次数:", "在场时间:", "效率值:", "得分:", "投篮命中率:", "篮板:", "助攻:", "抢断:", "盖帽:", "两双次数:", "三双次数:", "失误:", "犯规:", "三分命中率:", "罚球命中率:", "进攻篮板数:",
				"防守篮板:", "场均出手数:", "场均命中数:", "场均三分出手数:", "场均三分命中数:", "场均罚球出手数:", "场均罚球命中数:" };

////		private String[] valueString = { playerNormal_avg.getTeamName(), String.valueOf(playerNormal_avg.getNumOfGame()), String.valueOf(playerNormal_avg.getStart()),
////				String.valueOf(playerNormal_avg.getMinute()), String.valueOf(playerNormal_avg.getEfficiency()), String.valueOf(playerNormal_avg.getPoint()),
////				String.valueOf(playerNormal_avg.getShot()), String.valueOf(playerNormal_avg.getRebound()), String.valueOf(playerNormal_avg.getAssist()), String.valueOf(playerNormal_avg.getSteal()),
////				String.valueOf(playerNormal_avg.getBlockShot()), String.valueOf(playerNormal_avg.getDoubleTwo()), String.valueOf(playerNormal_avg.getTripleTwo()),
////				String.valueOf(playerNormal_avg.getFault()), String.valueOf(playerNormal_avg.getFoul()), String.valueOf(playerNormal_avg.getThree()), String.valueOf(playerNormal_avg.getPenalty()),
////				String.valueOf(playerNormal_avg.getOffend()), String.valueOf(playerNormal_avg.getDefend()), String.valueOf(playerNormal_avg.getTotalShot()),
////				String.valueOf(playerNormal_avg.getTotalHit()), String.valueOf(playerNormal_avg.getThreeShot()), String.valueOf(playerNormal_avg.getThreeHit()),
////				String.valueOf(playerNormal_avg.getFreehot()), String.valueOf(playerNormal_avg.getFreeHit()) };
//
//		public PlayerNormalInfoPanel() {
//			for (int i = 0; i < 25; i++) {
//				fieldLabel[i] = new MyLabel(fieldString[i]);
//				value[i] = new MyLabel(valueString[i]);
//			}
//			for (int i = 0; i < 25; i++) {
//				fieldLabel[i].setBounds((labelWidth + gap + valueLabelWidth) * (i % 5) + 350, (i / 5) * labelHeight + 30, labelWidth, labelHeight);
//				this.add(fieldLabel[i]);
//				value[i].setBounds(fieldLabel[i].getX() + labelWidth, fieldLabel[i].getY(), valueLabelWidth, labelHeight);
//				this.add(value[i]);
//			}
//			this.setVisible(true);
//		}
	}

	class PlayerHighInfoPanel extends MyPanel {

		private static final long serialVersionUID = 1L;
		private final int labelHeight = 50;
		private final int labelWidth = 120;
		private final int gap = 50;
		private MyLabel fieldLabel[] = new MyLabel[15];
		private MyLabel value[] = new MyLabel[15];
		private String[] fieldString = { "所属球队:", "所属联盟", "使用率", "GmSc效率值", "投篮效率:", "真实命中率:", "篮板率:", "助攻率:", "抢断率:", "盖帽率:", "两双次数:", "三双次数:", "失误率:", "进攻篮板率:", "防守篮板率:", };

//		private String[] valueString = { playerHigh.getTeamName(), String.valueOf(playerHigh.getLeague()), String.valueOf(playerHigh.getFrequency()), String.valueOf(playerHigh.getGmSc()),
//				String.valueOf(playerHigh.getShotEfficient()), String.valueOf(playerHigh.getRealShot()), String.valueOf(playerHigh.getReboundEfficient()),
//				String.valueOf(playerHigh.getAssistEfficient()), String.valueOf(playerHigh.getStealEfficient()), String.valueOf(playerHigh.getBlockShotEfficient()),
//				String.valueOf(playerNormal_avg.getDoubleTwo()), String.valueOf(playerNormal_avg.getTripleTwo()), String.valueOf(playerHigh.getFaultEfficient()),
//				String.valueOf(playerHigh.getOffendReboundEfficient()), String.valueOf(playerHigh.getDefendReboundEfficient()) };
//
//		public PlayerHighInfoPanel() {
//			for (int i = 0; i < 15; i++) {
//				fieldLabel[i] = new MyLabel(fieldString[i]);
//				value[i] = new MyLabel(valueString[i]);
//			}
//			for (int i = 0; i < 15; i++) {
//				fieldLabel[i].setBounds((labelWidth + gap) * (i % 3) * 2 + 350, (i / 3) * labelHeight + 30, labelWidth, labelHeight);
//				this.add(fieldLabel[i]);
//				value[i].setBounds(fieldLabel[i].getX() + labelWidth, fieldLabel[i].getY(), labelWidth, labelHeight);
//				this.add(value[i]);
//			}
//			this.setVisible(true);
//		}
	}

	class AllMatchInfoPanel extends MyPanel {
		private static final long serialVersionUID = 1L;
		private String[] title = { "球队", "日期", "时间", "得分", "篮板", "助攻", "抢断", "盖帽", "失误", "犯规", "命中", "出手", "三分命中", "三分出手", "罚球命中", "罚球出手", "前板", "后板" };
		private MyTableModel model = new MyTableModel(title);
		private MyTable table = new MyTable(model);
		private MyScrollPanel scrollPanel = new MyScrollPanel(table);

//		public AllMatchInfoPanel() {
//			table.setTableColumnWidth(1, (90));
//			table.setTableColumnWidth(12, (80));
//			table.setTableColumnWidth(13, (80));
//			table.setTableColumnWidth(14, (80));
//			table.setTableColumnWidth(15, (80));
//			if (playerOneMatchInfoList != null) {
//				for (int i = playerOneMatchInfoList.size() - 1; i >= 0; i--) {
//					model.addRow(playerOneMatchInfoList.get(i).toStringArray());
//				}
//			}
//			scrollPanel.setBounds(350, 0, 900, 350);
//			this.add(scrollPanel);
//			table.addMouseListener(new MouseListener() {
//
//				public void mouseReleased(MouseEvent e) {
//				}
//
//				public void mousePressed(MouseEvent e) {
//				}
//
//				public void mouseExited(MouseEvent e) {
//				}
//
//				public void mouseEntered(MouseEvent e) {
//				}
//
//				public void mouseClicked(MouseEvent e) {
//					if (table.getSelectedRow() >= 0 && table.getSelectedRow() < table.getRowCount()) {
//						int row = table.getSelectedRow();
//						String teamName = (String) table.getValueAt(row, 0);
//						String dateString = (String) table.getValueAt(row, 1);
//						MyDate date = new MyDate(dateString);
//						GeneralInfoOfOneMatch generalMatch = new MatchInfoBl().getGeneralMatch(teamName, date);
//						OneMatchPanel matchPanel = new OneMatchPanel(generalMatch);
//						SonFrame.changePanel(thisPanel, matchPanel, "match");
//					}
//				}
//			});
//			this.setVisible(true);
//		}
	}
}
