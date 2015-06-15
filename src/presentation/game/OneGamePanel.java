package presentation.game;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import presentation.SonFrame;
import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.mycomponent.MyScrollPanel;
import presentation.mycomponent.MyTable;
import presentation.mycomponent.MyTableModel;
import presentation.players.OnePlayerPanel;
import presentation.statics.MyColor;
import presentation.statics.MyFont;
import presentation.statics.NUMBER;
import presentation.statics.PathOfFile;
import beans.GamePlayer;
import businesslogic.game.OneGameBl;
import businesslogicservice.game.OneGameBlService;
import businesslogicservice.team.OneTeamBlService;

public class OneGamePanel extends MyPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private MyPanel thisPanel = this;
	private MyLabel point;
	private MyLabel firstTeamLogo;
	private MyLabel secondTeamLogo;
	private ContentPanel contentPanel;
	private JScrollPane gameInfo;
	private MyTable gameInfoTable;
	private MyTableModel gameInfoTableModel;
	private int Label_height =  ( 40);
	private int Label_width =380;
	private String title[];

	private OneGameBlService oneMatchInfoBl = new OneGameBl();
//	private GeneralInfoOfOneMatch generalOneMatch;
	private ArrayList<GamePlayer> firstTeamPlayerPerform;
	private ArrayList<GamePlayer> secondTeamPlayerPerform;

	private MyButton[] button = new MyButton[] { new MyButton("主队球员数据"), new MyButton("客队球员数据"), new MyButton("球队对比") };
	private MyPanel[] panel;
	private int flag = 0;

/*	public OneGamePanel(GeneralInfoOfOneMatch generalOneMatch) {
		this.generalOneMatch = generalOneMatch;
		this.firstTeamPlayerPerform = oneMatchInfoBl.getPlayersPerformOfOneMatch(generalOneMatch.getFirstTeamName(), generalOneMatch.getDate());
		this.secondTeamPlayerPerform = oneMatchInfoBl.getPlayersPerformOfOneMatch(generalOneMatch.getSecondTeamName(), generalOneMatch.getDate());
		panel = new MyPanel[] { new TeamMemberInfoPanel(firstTeamPlayerPerform), new TeamMemberInfoPanel(secondTeamPlayerPerform),
				new TeamInfoComparePanel() };
		this.createObjects();
		this.setComponentsLocation();
		this.setComponentsStyle();
		this.initTable();
		this.setTableStyle();
		this.addListener();
	}*/
	private void addListener() {
		firstTeamLogo.addMouseListener(this);
		secondTeamLogo.addMouseListener(this);
	}

	private void createObjects() {
		point = new MyLabel();
		firstTeamLogo = new MyLabel();
		secondTeamLogo = new MyLabel();
//		this.title = new String[generalOneMatch.getFirstTeamQuarterScore().length + 2];
		title[0] = "球队";
		title[title.length - 1] = "总分";
//		for (int i = 1; i <= generalOneMatch.getFirstTeamQuarterScore().length; i++) {
//			title[i] = String.valueOf(i);
//		}
		gameInfoTableModel = new MyTableModel(title);
		gameInfoTable = new MyTable(gameInfoTableModel);
		gameInfo = new MyScrollPanel(gameInfoTable);
		contentPanel = new ContentPanel();
	}

	private void setComponentsLocation() {
		for (int i = 0; i < 3; i++) {
			button[i].setBounds( ( 80) + Label_width * i,200, Label_width, Label_height);
			button[i].setBackground(MyColor.MIDDLE_COLOR);
			button[i].setForeground(MyColor.MY_WHITE);
			button[i].setFont(MyFont.SMALL_BOLD);
			button[i].setContentAreaFilled(true);
			button[i].addMouseListener(this);
			this.add(button[i]);
		}
		firstTeamLogo.setBounds((NUMBER.FRAME_WIDTH-300)/2-100,  ( 10),  ( 100),  ( 80));
		secondTeamLogo.setBounds((NUMBER.FRAME_WIDTH-300)/2+300,  ( 10),  ( 100),  ( 80));
		point.setBounds((NUMBER.FRAME_WIDTH-300)/2,  ( 10), 300,  ( 100));
		gameInfo.setBounds(80,100, 1140,100);
		contentPanel.setBounds(0,240,1300,370);
		this.add(firstTeamLogo);
		this.add(secondTeamLogo);
		this.add(point);
		this.add(gameInfo);
		this.add(contentPanel);
	}

	private void setComponentsStyle() {
		point.setHorizontalAlignment(SwingConstants.CENTER);
		point.setFont(MyFont.LARGEST_BOLD);
		point.setForeground(MyColor.LIGHT_COLOR);
		button[flag].setBackground(MyColor.SELECTED);
		gameInfo.setOpaque(false);
		gameInfo.getViewport().setOpaque(false);
	}

	private void initTable() {
//		firstTeamLogo.setMyIcon(new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + this.generalOneMatch.getFirstTeamName() + ".png"));
//		secondTeamLogo.setMyIcon(new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + this.generalOneMatch.getSecondTeamName() + ".png"));
//		point.setText(generalOneMatch.getFirstTeamScore() + "  vs  " + generalOneMatch.getSecondTeamScore());
		String[] firstContent = new String[title.length];
//		firstContent[0] = generalOneMatch.getFirstTeamName();
//		firstContent[title.length - 1] = String.valueOf(generalOneMatch.getFirstTeamScore());
//		for (int i = 1; i <= generalOneMatch.getFirstTeamQuarterScore().length; i++) {
//			firstContent[i] = String.valueOf(generalOneMatch.getFirstTeamQuarterScore()[i - 1]);
//		}
		gameInfoTableModel.addRow(firstContent);
		//
		String[] secondContent = new String[title.length];
//		secondContent[0] = generalOneMatch.getSecondTeamName();
//		secondContent[title.length - 1] = String.valueOf(generalOneMatch.getSecondTeamScore());
//		for (int i = 1; i <= generalOneMatch.getFirstTeamQuarterScore().length; i++) {
//			secondContent[i] = String.valueOf(generalOneMatch.getSecondTeamQuarterScore()[i - 1]);
//		}
		gameInfoTableModel.addRow(secondContent);
	}

	private void setTableStyle() {
		gameInfoTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}

	class TeamMemberInfoPanel extends MyPanel {
		private MyTable teamInfoTable;
		private JScrollPane teamPlayerInfo;
		private MyTableModel teamInfoTableModel;
		private String[] title = { "姓名", "日期", "时间", "得分", "篮板", "助攻", "抢断", "盖帽", "失误", "犯规", "命中", "出手", "三分命中", "三分出手", "罚球命中", "罚球出手", "前板", "后板" };
		private static final long serialVersionUID = 1L;

		public TeamMemberInfoPanel(ArrayList<GamePlayer> playerPerformList) {
			teamInfoTableModel = new MyTableModel(title);
			teamInfoTable = new MyTable(teamInfoTableModel);
			teamPlayerInfo = new MyScrollPanel(teamInfoTable);
			teamInfoTable.setTableColumnWidth(0,180);
			for (int i = 1; i <= 2; i++) {
				teamInfoTable.setTableColumnWidth(i,100);
			}
			for (int i = 3; i <= 11; i++) {
				teamInfoTable.setTableColumnWidth(i, 80);
			}
			for (int i = 12; i <= 15; i++) {
				teamInfoTable.setTableColumnWidth(i, 150);
			}

			if (playerPerformList != null) {
				for (int i = 0; i < playerPerformList.size(); i++) {
//					String[] content = playerPerformList.get(i).toStringArray();
//					content[0] = playerPerformList.get(i).getName();
//					teamInfoTableModel.addRow(content);
				}
			}
			teamPlayerInfo.setBounds(80, 0,1140,370);
			this.add(teamPlayerInfo);
			teamInfoTable.addMouseListener(new MouseListener() {

				public void mouseReleased(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {
				}

				public void mouseExited(MouseEvent e) {
				}

				public void mouseEntered(MouseEvent e) {
				}

				public void mouseClicked(MouseEvent e) {
					if (teamInfoTable.getSelectedRow() >= 0 && teamInfoTable.getSelectedRow() < teamInfoTable.getRowCount()) {
						int row = teamInfoTable.getSelectedRow();
						String playerName = (String) teamInfoTable.getValueAt(row, 0);
//						OnePlayerPanel playerPanel = new OnePlayerPanel(playerName);
//						SonFrame.changePanel(thisPanel, playerPanel,new String("player"));
					}
				}
			});
			this.setVisible(true);
		}
	}

	class TeamInfoComparePanel extends MyPanel {
//		private OneTeamBlService OneTeamInfoBl = new businesslogic.teams.OneTeamInfoBl();
//		private TeamPerformOfOneMatch firstTeamPerform = OneTeamInfoBl.getOneMatchTeamPerform(generalOneMatch.getFirstTeamName(),
//				generalOneMatch.getDate());
//		private TeamPerformOfOneMatch secondTeamPerform = OneTeamInfoBl.getOneMatchTeamPerform(generalOneMatch.getSecondTeamName(),
//				generalOneMatch.getDate());
		private String performanceList[] = { "分数", "投篮", "三分球", "罚球", "助攻", "篮板", "抢断", "盖帽" };
//		private String firstPerform[] = { String.valueOf(firstTeamPerform.getPoint()),
//				String.valueOf(firstTeamPerform.getTotalHit()) + "/" + String.valueOf(firstTeamPerform.getTotalShot()),
//				String.valueOf(firstTeamPerform.getThreeHit()) + "/" + String.valueOf(firstTeamPerform.getThreeShot()),
//				String.valueOf(firstTeamPerform.getFreeHit()) + "/" + String.valueOf(firstTeamPerform.getFreeShot()),
//				String.valueOf(firstTeamPerform.getAssist()), String.valueOf(firstTeamPerform.getRebound()),
//				String.valueOf(firstTeamPerform.getSteal()), String.valueOf(firstTeamPerform.getBlock()) };

//		private String secondPerform[] = { String.valueOf(secondTeamPerform.getPoint()),
//				String.valueOf(secondTeamPerform.getTotalHit()) + "/" + String.valueOf(secondTeamPerform.getTotalShot()),
//				String.valueOf(secondTeamPerform.getThreeHit()) + "/" + String.valueOf(secondTeamPerform.getThreeShot()),
//				String.valueOf(secondTeamPerform.getFreeHit()) + "/" + String.valueOf(secondTeamPerform.getFreeShot()),
//				String.valueOf(secondTeamPerform.getAssist()), String.valueOf(secondTeamPerform.getRebound()),
//				String.valueOf(secondTeamPerform.getSteal()), String.valueOf(secondTeamPerform.getBlock()) };

		private MyLabel performLabel[] = new MyLabel[8];
		private MyLabel firstTeamInfo[] = new MyLabel[8];
		private MyLabel secondTeamInfo[] = new MyLabel[8];
		private static final long serialVersionUID = 1L;

		public TeamInfoComparePanel() {
			for (int i = 0; i < 8; i++) {
				performLabel[i] = new MyLabel(performanceList[i]);
				performLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
				performLabel[i].setOpaque(true);
				performLabel[i].setBackground(MyColor.DEEP_COLOR);
				performLabel[i].setBounds(500, i * 47,300,47);

//				firstTeamInfo[i] = new MyLabel(firstPerform[i] + "--------------------");
				firstTeamInfo[i].setBounds(100, i *  47,
						300,47);

//				secondTeamInfo[i] = new MyLabel("--------------------" + secondPerform[i]);
				secondTeamInfo[i].setHorizontalAlignment(SwingConstants.RIGHT);
				secondTeamInfo[i].setBounds(900, i *  47,
						300,47);

				this.add(performLabel[i]);
				this.add(firstTeamInfo[i]);
				this.add(secondTeamInfo[i]);
			}

		}

	}

	class ContentPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private CardLayout card;

		ContentPanel() {
			this.setOpaque(false);
			card = new CardLayout();
			this.setLayout(card);
			for (int i = 0; i < 3; i++) {
				this.add(panel[i], String.valueOf(i));
			}
		}

		public void showMyPanel(int i) {
			this.card.show(contentPanel, String.valueOf(i));
		}
	}

	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < 3; i++) {
			if (e.getSource().equals(button[i])) {
				contentPanel.showMyPanel(i);
				button[flag].setBackground(MyColor.MIDDLE_COLOR);
				button[i].setBackground(MyColor.SELECTED);
				flag = i;
			}
		}
		if (e.getSource().equals(firstTeamLogo)) {
//			OneTeamPanel teamPanel = new OneTeamPanel(generalOneMatch.getFirstTeamName());
//			SonFrame.changePanel(thisPanel, teamPanel,new String("team"));
		}
		else if (e.getSource().equals(secondTeamLogo)) {
//			OneTeamPanel teamPanel = new OneTeamPanel(generalOneMatch.getSecondTeamName());
//			SonFrame.changePanel(thisPanel, teamPanel,new String("team"));
		}
	}

	public void mouseEntered(MouseEvent e) {
		for (int i = 0; i < 3; i++) {
			if (e.getSource().equals(button[i])) {
				button[i].setBackground(MyColor.DEEP_COLOR);
			}
		}
		if (e.getSource().equals(firstTeamLogo)) {
			firstTeamLogo.setLocation(firstTeamLogo.getX() - 3, firstTeamLogo.getY() - 3);
		}
		else if (e.getSource().equals(secondTeamLogo)) {
			secondTeamLogo.setLocation(secondTeamLogo.getX() - 3, secondTeamLogo.getY() - 3);
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
		if (e.getSource().equals(firstTeamLogo)) {
			firstTeamLogo.setLocation(firstTeamLogo.getX() +  ( 3), firstTeamLogo.getY() +  ( 3));
		}
		else if (e.getSource().equals(secondTeamLogo)) {
			secondTeamLogo.setLocation(secondTeamLogo.getX() +  ( 3), secondTeamLogo.getY() +  ( 3));
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

}
