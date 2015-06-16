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
import presentation.teams.OneTeamPanel;
import beans.GamePlayer;
import beans.GameTeam;
import beans.GeneralGame;
import beans.generalTeam;
import businesslogic.game.OneGameBl;
import businesslogic.team.TeamInfoBl;
import businesslogicservice.game.OneGameBlService;
import common.statics.Season;

public class OneGamePanel extends MyPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private MyPanel thisPanel = this;
	private MyLabel point;
	private MyLabel homeTeamLogo;
	private MyLabel guestTeamLogo;
	private ContentPanel contentPanel;
	private JScrollPane gameInfo;
	private MyTable gameInfoTable;
	private MyTableModel gameInfoTableModel;
	private int Label_height = 40;
	private int Label_width = 380;
	private String title[];
	//
	private OneGameBlService oneGameBl = new OneGameBl();
	private GeneralGame generalGame;
	private String date;
	private Season season;
	private ArrayList<GamePlayer> homeTeamPlayerPerform;
	private ArrayList<GamePlayer> guestTeamPlayerPerform;
	private GameTeam homeTeamPerform;
	private GameTeam guestTeamPerform;

	private MyButton[] button = new MyButton[] { new MyButton("主队球员数据"), new MyButton("客队球员数据"), new MyButton("球队对比") };
	private MyPanel[] panel;
	private int flag = 0;
	private int numOfQuarter;

	public OneGamePanel(GeneralGame generalGame) {
		this.generalGame = generalGame;
		this.date = generalGame.getDate();
		this.season = Season.dateToSeason(date);
		this.homeTeamPlayerPerform = oneGameBl.getPlayersPerformOfOneGame(generalGame.getHomeTeam(), date);
		this.guestTeamPlayerPerform = oneGameBl.getPlayersPerformOfOneGame(generalGame.getGuestTeam(), date);
		this.homeTeamPerform = oneGameBl.getTeamPerformOfOneGame(generalGame.getHomeTeam(), date);
		this.guestTeamPerform = oneGameBl.getTeamPerformOfOneGame(generalGame.getGuestTeam(), date);
		if (generalGame != null && date != null && homeTeamPlayerPerform != null && guestTeamPlayerPerform != null && homeTeamPerform != null && guestTeamPerform != null) {
			this.createObjects();
			this.setComponentsLocation();
			this.setComponentsStyle();
			this.initTable();
			this.setTableStyle();
			this.addListener();
		}
	}

	private void addListener() {
		homeTeamLogo.addMouseListener(this);
		guestTeamLogo.addMouseListener(this);
	}

	private void createObjects() {
		panel = new MyPanel[] { new TeamMemberInfoPanel(homeTeamPlayerPerform), new TeamMemberInfoPanel(guestTeamPlayerPerform), new TeamInfoComparePanel() };
		point = new MyLabel();
		homeTeamLogo = new MyLabel();
		guestTeamLogo = new MyLabel();
		String quarterPoint = homeTeamPerform.getQuarterPoint();
		String part[] = quarterPoint.split("---");
		this.title = new String[part.length + 2];
		title[0] = "球队";
		title[title.length - 1] = "总分";
		this.numOfQuarter = part.length;
		for (int i = 1; i <= part.length; i++) {
			title[i] = String.valueOf(i);
		}
		gameInfoTableModel = new MyTableModel(title);
		gameInfoTable = new MyTable(gameInfoTableModel);
		gameInfo = new MyScrollPanel(gameInfoTable);
		contentPanel = new ContentPanel();
	}

	private void setComponentsLocation() {
		for (int i = 0; i < 3; i++) {
			button[i].setBounds((80) + Label_width * i, 200, Label_width, Label_height);
			button[i].setBackground(MyColor.MIDDLE_COLOR);
			button[i].setForeground(MyColor.MY_WHITE);
			button[i].setFont(MyFont.SMALL_BOLD);
			button[i].setContentAreaFilled(true);
			button[i].addMouseListener(this);
			this.add(button[i]);
		}
		homeTeamLogo.setBounds((NUMBER.FRAME_WIDTH - 300) / 2 - 100, (10), (100), (80));
		guestTeamLogo.setBounds((NUMBER.FRAME_WIDTH - 300) / 2 + 300, (10), (100), (80));
		point.setBounds((NUMBER.FRAME_WIDTH - 300) / 2, (10), 300, (100));
		gameInfoTable.setRowHeight(33);
		gameInfo.setBounds(80, 100, 1140, 100);
		contentPanel.setBounds(0, 240, 1300, 370);
		this.add(homeTeamLogo);
		this.add(guestTeamLogo);
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
		Season season = Season.dateToSeason(date);
		generalTeam homeTeamGeneral = new TeamInfoBl().getGeneralTeam(generalGame.getHomeTeam(), season);
		generalTeam guestTeamGeneral = new TeamInfoBl().getGeneralTeam(generalGame.getGuestTeam(), season);
		homeTeamLogo.setMyIcon(new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + homeTeamGeneral.getImgName() + ".png"));
		guestTeamLogo.setMyIcon(new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + guestTeamGeneral.getImgName() + ".png"));
		point.setText(generalGame.getHomePoint() + "  vs  " + generalGame.getGuestPoint());
		String[] firstContent = new String[title.length];
		firstContent[0] = generalGame.getHomeTeam();
		firstContent[title.length - 1] = String.valueOf(generalGame.getHomePoint());
		String homePart[] = homeTeamPerform.getQuarterPoint().split("---");
		for (int i = 1; i <= numOfQuarter; i++) {
			firstContent[i] = homePart[i - 1];
		}
		gameInfoTableModel.addRow(firstContent);
		//
		String[] secondContent = new String[title.length];
		secondContent[0] = generalGame.getGuestTeam();
		secondContent[title.length - 1] = String.valueOf(generalGame.getGuestPoint());
		String guestPart[] = guestTeamPerform.getQuarterPoint().split("---");
		for (int i = 1; i <= numOfQuarter; i++) {
			secondContent[i] = guestPart[i - 1];
		}
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
		private String[] field = { "playerName", "date", "minute", "point", "totRebound", "assist", "steal", "block", "fault", "foul", "totHit", "totShot", "threeHit", "threeShot", "freeHit",
				"freeShot", "offRebound", "defRebound" };
		private static final long serialVersionUID = 1L;

		public TeamMemberInfoPanel(ArrayList<GamePlayer> playerPerformList) {
			teamInfoTableModel = new MyTableModel(title);
			teamInfoTable = new MyTable(teamInfoTableModel);
			teamPlayerInfo = new MyScrollPanel(teamInfoTable);
			teamInfoTable.setTableColumnWidth(0, 180);
			teamInfoTable.setTableColumnWidth(1, 100);
			for (int i = 2; i <= 11; i++) {
				teamInfoTable.setTableColumnWidth(i, 42);
			}
			for (int i = 12; i <= 15; i++) {
				teamInfoTable.setTableColumnWidth(i, 82);
			}
			for (int i = 16; i <= 17; i++) {
				teamInfoTable.setTableColumnWidth(i, 42);
			}
			if (playerPerformList != null) {
				for (int i = 0; i < playerPerformList.size(); i++) {
					Object[] content = playerPerformList.get(i).getSpeContent(field);
					teamInfoTableModel.addRow(content);
				}
			}
			teamPlayerInfo.setBounds(80, 0, 1140, 370);
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
						String playerId = playerPerformList.get(row).getPlayerId();
						OnePlayerPanel playerPanel = new OnePlayerPanel(playerId);
						SonFrame.changePanel(thisPanel, playerPanel, SonFrame.playerCard);
					}
				}
			});
			this.setVisible(true);
		}
	}

	class TeamInfoComparePanel extends MyPanel {
		private String performanceList[] = { "分数", "投篮", "三分球", "罚球", "助攻", "篮板", "抢断", "盖帽" };
		private String firstPerform[] = { String.valueOf(homeTeamPerform.getPoint()), String.valueOf(homeTeamPerform.getTotHit()) + "/" + String.valueOf(homeTeamPerform.getTotShot()),
				String.valueOf(homeTeamPerform.getThreeHit()) + "/" + String.valueOf(homeTeamPerform.getThreeShot()),
				String.valueOf(homeTeamPerform.getFreeHit()) + "/" + String.valueOf(homeTeamPerform.getFreeShot()), String.valueOf(homeTeamPerform.getAssist()),
				String.valueOf(homeTeamPerform.getTotRebound()), String.valueOf(homeTeamPerform.getSteal()), String.valueOf(homeTeamPerform.getBlock()) };

		private String secondPerform[] = { String.valueOf(guestTeamPerform.getPoint()), String.valueOf(guestTeamPerform.getTotHit()) + "/" + String.valueOf(guestTeamPerform.getTotShot()),
				String.valueOf(guestTeamPerform.getThreeHit()) + "/" + String.valueOf(guestTeamPerform.getThreeShot()),
				String.valueOf(guestTeamPerform.getFreeHit()) + "/" + String.valueOf(guestTeamPerform.getFreeShot()), String.valueOf(guestTeamPerform.getAssist()),
				String.valueOf(guestTeamPerform.getTotRebound()), String.valueOf(guestTeamPerform.getSteal()), String.valueOf(guestTeamPerform.getBlock()) };

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
				performLabel[i].setBounds(500, i * 47, 300, 47);

				firstTeamInfo[i] = new MyLabel(firstPerform[i] + "--------------------");
				firstTeamInfo[i].setBounds(100, i * 47, 300, 47);

				secondTeamInfo[i] = new MyLabel("--------------------" + secondPerform[i]);
				secondTeamInfo[i].setHorizontalAlignment(SwingConstants.RIGHT);
				secondTeamInfo[i].setBounds(900, i * 47, 300, 47);

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
		if (e.getSource().equals(homeTeamLogo)) {
			generalTeam homeGeneral = new TeamInfoBl().getGeneralTeam(generalGame.getHomeTeam(), season);
			OneTeamPanel teamPanel = new OneTeamPanel(homeGeneral);
			SonFrame.changePanel(thisPanel, teamPanel, SonFrame.teamCard);
		}
		else if (e.getSource().equals(guestTeamLogo)) {
			generalTeam guestGeneral = new TeamInfoBl().getGeneralTeam(generalGame.getGuestTeam(), season);
			OneTeamPanel teamPanel = new OneTeamPanel(guestGeneral);
			SonFrame.changePanel(thisPanel, teamPanel, SonFrame.teamCard);
		}
	}

	public void mouseEntered(MouseEvent e) {
		for (int i = 0; i < 3; i++) {
			if (e.getSource().equals(button[i])) {
				button[i].setBackground(MyColor.DEEP_COLOR);
			}
		}
		if (e.getSource().equals(homeTeamLogo)) {
			homeTeamLogo.setLocation(homeTeamLogo.getX() - 3, homeTeamLogo.getY() - 3);
		}
		else if (e.getSource().equals(guestTeamLogo)) {
			guestTeamLogo.setLocation(guestTeamLogo.getX() - 3, guestTeamLogo.getY() - 3);
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
		if (e.getSource().equals(homeTeamLogo)) {
			homeTeamLogo.setLocation(homeTeamLogo.getX() + (3), homeTeamLogo.getY() + (3));
		}
		else if (e.getSource().equals(guestTeamLogo)) {
			guestTeamLogo.setLocation(guestTeamLogo.getX() + (3), guestTeamLogo.getY() + (3));
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

}
