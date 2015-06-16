package presentation.teams;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import presentation.SonFrame;
import presentation.game.OneGamePanel;
import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyComboBox;
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
import beans.GameTeam;
import beans.GeneralGame;
import beans.SeasonPlayer;
import beans.SeasonTeam;
import beans.generalTeam;
import businesslogic.game.GameInfoBl;
import businesslogic.team.OneTeamBl;
import businesslogicservice.team.OneTeamBlService;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;

public class OneTeamPanel extends MyPanel implements MouseListener {
	private MyPanel thisPanel = this;
	private static final long serialVersionUID = 1L;
	private String teamName;
	private int buttonHeight = 40;
	private int buttonWidth = 240;
	private MyButton[] button = new MyButton[] { new MyButton("球队成员"), new MyButton("常规赛普通数据"), new MyButton("常规赛高级数据"), new MyButton("常规赛详细比赛信息"), new MyButton("季后赛系列赛"), };
	private MyPanel[] panel;
	private generalTeam teamGeneralInfo;
	private GeneralInfoPanel generalInfoPanel;
	private ContentPanel contentPanel;
	private int flag = 0;
	private String[] allSeasonArray;
	private OneTeamBlService oneTeamInfoBl = new OneTeamBl();

	// 球队卡只展示普通数据，高级数据要点击球员卡看
	public OneTeamPanel(generalTeam generalTeam) {
		if (generalTeam != null) {
			this.teamName = generalTeam.getTeamName();
			this.teamGeneralInfo = generalTeam;
			this.fillAllSeasonArray(generalTeam);
			panel = new MyPanel[] { new PlayerNormalInfoPanel(), new TeamRegularNormalPanel(), new TeamRegularHighPanel(), new AllGameOfSeasonPanel(), new TeamPlayOffSeriesPanel(), };
			this.createObjects();
			this.setComponentsLocation();
			this.setVisible(true);
		}
	}

	private void fillAllSeasonArray(generalTeam generalTeam) {
		String startSeason = generalTeam.getStartSeason();
		String finishSeason = generalTeam.getFinishSeason();
		int startYear = Integer.parseInt(startSeason.substring(0, 4));
		int finishYear = Integer.parseInt(finishSeason.substring(0, 4));
		this.allSeasonArray = new String[finishYear - startYear + 1];
		for (int i = startYear; i <= finishYear; i++) {
			String tempSeason = String.valueOf(i) + "-" + String.valueOf(i + 1).substring(2);
			this.allSeasonArray[finishYear - i] = tempSeason;
		}
	}

	private void createObjects() {
		generalInfoPanel = new GeneralInfoPanel();
		contentPanel = new ContentPanel();
	}

	private void setComponentsLocation() {
		generalInfoPanel.setLocation(0, 0);
		contentPanel.setBounds(0, 240, NUMBER.FRAME_WIDTH, NUMBER.FRAME_HEIGHT - NUMBER.NAVIGATION_PANEL_HEIGHT - 240);
		this.add(generalInfoPanel);
		this.add(contentPanel);
		for (int i = 0; i < 5; i++) {
			button[i].setBounds((50) + buttonWidth * i, 200, buttonWidth, buttonHeight);
			button[i].setContentAreaFilled(true);
			button[i].setBackground(MyColor.MIDDLE_COLOR);
			button[i].setForeground(MyColor.MY_WHITE);
			button[i].setFont(MyFont.SMALL_BOLD);
			button[i].addMouseListener(this);
			this.add(button[i]);
		}
		button[flag].setBackground(MyColor.SELECTED);
	}

	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < 5; i++) {
			if (e.getSource().equals(button[i])) {
				contentPanel.showMyPanel(i);
				button[flag].setBackground(MyColor.MIDDLE_COLOR);
				button[i].setBackground(MyColor.SELECTED);
				flag = i;
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
		for (int i = 0; i < 5; i++) {
			if (e.getSource().equals(button[i])) {
				button[i].setBackground(MyColor.DEEP_COLOR);
			}
		}
	}

	public void mouseExited(MouseEvent e) {
		for (int i = 0; i < 5; i++) {
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

	class ContentPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private CardLayout card;

		ContentPanel() {
			card = new CardLayout();
			this.setOpaque(false);
			this.setLayout(card);
			for (int i = 0; i < 5; i++) {
				this.add(panel[i], String.valueOf(i));
			}
		}

		public void showMyPanel(int i) {
			this.card.show(contentPanel, String.valueOf(i));
		}
	}

	class GeneralInfoPanel extends MyPanel {
		private static final long serialVersionUID = 1L;
		private MyLabel logo;
		private JTextArea teamNameText;
		private JTextArea normalInfoText;
		private JTextArea mainMatchInfoText;

		public GeneralInfoPanel() {
			this.setSize(NUMBER.FRAME_WIDTH, 200);
			this.createObjects();
			this.setComponentsLocation();
			this.setContent();
		}

		private void createObjects() {
			logo = new MyLabel();
			teamNameText = new MyTextArea();
			normalInfoText = new MyTextArea();
			mainMatchInfoText = new MyTextArea();
		}

		private void setComponentsLocation() {
			logo.setBounds(100, 20, 150, 150);
			teamNameText.setBounds(400, 40, 250, 50);
			normalInfoText.setBounds(800, 50, 400, 150);
			mainMatchInfoText.setBounds(400, 100, 200, 50);
			this.add(logo);
			this.add(teamNameText);
			this.add(normalInfoText);
			this.add(mainMatchInfoText);
		}

		private void setContent() {
			logo.setMyIcon(new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + teamGeneralInfo.getImgName() + ".png"));
			teamNameText.setText(teamName);
			teamNameText.setFont(MyFont.MIDDLE_BOLD);
			ArrayList<SeasonTeam> seasonTeamList = oneTeamInfoBl.getRegularSeasonTeam(teamName, DataKind.average, Field.season);
			SeasonTeam latestSeasonTeam = seasonTeamList.get(0);
			mainMatchInfoText.setText("得分    篮板    助攻\n" + latestSeasonTeam.getPoint() + "  " + latestSeasonTeam.getTotRebound() + "  " + latestSeasonTeam.getAssist());
			mainMatchInfoText.setFont(MyFont.SMALL_PLAIN);
			normalInfoText.setText("所属联盟：" + teamGeneralInfo.getLeague() + "\n主场：" + latestSeasonTeam.getArean() + "\n建队时间：" + teamGeneralInfo.getStartSeason());
			normalInfoText.setFont(MyFont.SMALL_PLAIN);
		}
	}

	class TeamRegularNormalPanel extends MyPanel {
		private static final long serialVersionUID = 1L;
		private String[] title = { "赛季", "场数", "胜场", "得分", "命中率", "篮板", "助攻", "抢断", "盖帽", "失误", "犯规", "前板", "后板", "出手", "命中", "三分出", "三分中", "三分命中率", "罚球出", "罚球中", "罚中率" };
		private Field[] fields = { Field.season, Field.numOfGame, Field.numOfWin, Field.point, Field.shot, Field.totRebound, Field.assist, Field.steal, Field.block, Field.fault, Field.foul,
				Field.offRebound, Field.defRebound, Field.totalShot, Field.totalHit, Field.threeShot, Field.threeHit, Field.three, Field.freeShot, Field.freeHit, Field.free };
		private String[] fieldStr = new String[fields.length];
		private MyTableModel model = new MyTableModel(title);
		private MyTable table = new MyTable(model);
		private MyScrollPanel scrollPanel = new MyScrollPanel(table);
		private MyComboBox<Object> dataKindChoose, sortFieldChoose;
		private MyButton search;

		public TeamRegularNormalPanel() {
			for (int i = 0; i < fields.length; i++) {
				fieldStr[i] = fields[i].toString();
			}
			dataKindChoose = new MyComboBox<Object>(DataKind.dataKinds);
			sortFieldChoose = new MyComboBox<>(title);
			search = new MyButton("搜索");
			search.setBackground(MyColor.MIDDLE_ORANGE);
			search.setForeground(MyColor.MY_BLACK);
			this.setComponentBounds();
			this.initTable();
			this.setVisible(true);
			search.addMouseListener(new MouseListener() {

				public void mouseReleased(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {
				}

				public void mouseExited(MouseEvent e) {
				}

				public void mouseEntered(MouseEvent e) {
				}

				public void mouseClicked(MouseEvent e) {
					int dataKindInt = dataKindChoose.getSelectedIndex();
					int sortFieldInt = sortFieldChoose.getSelectedIndex();
					ArrayList<SeasonTeam> seasonTeamList = oneTeamInfoBl.getRegularSeasonTeam(teamName, DataKind.dataKinds[dataKindInt], fields[sortFieldInt]);
					model.removeAllRows();
					table.updateUI();
					if (seasonTeamList != null) {
						for (int i = 0; i < seasonTeamList.size(); i++) {
							Object[] contents = seasonTeamList.get(i).getSpeContent(fieldStr);
							model.addRow(contents);
							table.updateUI();
						}
					}
				}
			});
		}

		private void setComponentBounds() {
			dataKindChoose.setBounds(350, 10, 150, 30);
			sortFieldChoose.setBounds(550, 10, 150, 30);
			search.setBounds(750, 10, 150, 30);
			scrollPanel.setBounds(30, 50, 1245, 300);
			table.setRowHeight(30);
			for (int i = 0; i <= 14; i++) {
				table.setTableColumnWidth(i, 55);
			}
			table.setTableColumnWidth(0, 60);
			table.setTableColumnWidth(4, 55);
			for (int i = 15; i <= 20; i++) {
				table.setTableColumnWidth(i, 60);
			}
			table.setTableColumnWidth(17, 90);
			this.add(dataKindChoose);
			this.add(sortFieldChoose);
			this.add(search);
			this.add(scrollPanel);
		}

		private void initTable() {
			ArrayList<SeasonTeam> seasonTeamList = oneTeamInfoBl.getRegularSeasonTeam(teamName, DataKind.average, Field.season);
			if (seasonTeamList != null) {
				for (int i = 0; i < seasonTeamList.size(); i++) {
					Object[] contents = seasonTeamList.get(i).getSpeContent(fieldStr);
					model.addRow(contents);
					table.updateUI();
				}
			}
		}
	}// 球队常规赛普通数据界面

	class TeamRegularHighPanel extends MyPanel {

		private static final long serialVersionUID = 1L;
		private String[] title = { "赛季", "球队平均年龄", "场均净胜分", "场均回合数", "进攻效率", "防守效率", "真实命中率", "投篮效率", "罚球效率", "三分效率", "前板率", "后板率", "失误率", "赛程密度", "观众数" };
		private Field[] fields = { Field.season, Field.avgAge, Field.pointOfWin, Field.pace, Field.offEFF, Field.defEFF, Field.realShot, Field.shotEFF, Field.freeEFF, Field.threeEFF,
				Field.offReboundEFF, Field.defReboundEFF, Field.faultEFF, Field.strengthOfSchedule, Field.attendance };
		private String[] fieldStr = new String[fields.length];
		private MyTableModel model = new MyTableModel(title);
		private MyTable table = new MyTable(model);
		private MyScrollPanel scrollPanel = new MyScrollPanel(table);
		private MyComboBox<Object> dataKindChoose, sortFieldChoose;
		private MyButton search;

		public TeamRegularHighPanel() {
			for (int i = 0; i < fields.length; i++) {
				fieldStr[i] = fields[i].toString();
			}
			dataKindChoose = new MyComboBox<Object>(DataKind.dataKinds);
			sortFieldChoose = new MyComboBox<>(title);
			search = new MyButton("搜索");
			search.setBackground(MyColor.MIDDLE_ORANGE);
			search.setForeground(MyColor.MY_BLACK);
			this.setComponentBounds();
			this.initTable();
			this.setVisible(true);
			search.addMouseListener(new MouseListener() {

				public void mouseReleased(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {
				}

				public void mouseExited(MouseEvent e) {
				}

				public void mouseEntered(MouseEvent e) {
				}

				public void mouseClicked(MouseEvent e) {
					int dataKindInt = dataKindChoose.getSelectedIndex();
					int sortFieldInt = sortFieldChoose.getSelectedIndex();
					ArrayList<SeasonTeam> seasonTeamList = oneTeamInfoBl.getRegularSeasonTeam(teamName, DataKind.dataKinds[dataKindInt], fields[sortFieldInt]);
					model.removeAllRows();
					table.updateUI();
					if (seasonTeamList != null) {
						for (int i = 0; i < seasonTeamList.size(); i++) {
							Object[] contents = seasonTeamList.get(i).getSpeContent(fieldStr);
							model.addRow(contents);
							table.updateUI();
						}
					}
				}
			});
		}

		private void setComponentBounds() {
			dataKindChoose.setBounds(350, 10, 150, 30);
			sortFieldChoose.setBounds(550, 10, 150, 30);
			search.setBounds(750, 10, 150, 30);
			scrollPanel.setBounds(30, 50, 1245, 300);
			table.setRowHeight(30);
			for (int i = 2; i <= 3; i++) {
				table.setTableColumnWidth(i, 95);
			}
			table.setTableColumnWidth(1, 120);
			table.setTableColumnWidth(6, 90);
			this.add(dataKindChoose);
			this.add(sortFieldChoose);
			this.add(search);
			this.add(scrollPanel);
		}

		private void initTable() {
			ArrayList<SeasonTeam> seasonTeamList = oneTeamInfoBl.getRegularSeasonTeam(teamName, DataKind.average, Field.season);
			if (seasonTeamList != null) {
				for (int i = 0; i < seasonTeamList.size(); i++) {
					Object[] contents = seasonTeamList.get(i).getSpeContent(fieldStr);
					model.addRow(contents);
					table.updateUI();
				}
			}
		}
	}// 球队常规赛高级数据界面

	class TeamPlayOffSeriesPanel extends MyPanel {

	}// 球队季后赛系列赛界面

	class PlayerNormalInfoPanel extends MyPanel {
		private static final long serialVersionUID = 1L;
		private String[] title = { "姓名", "场数", "首发", "时间", "得分", "命中率", "篮板", "助攻", "抢断", "盖帽", "失误", "犯规", "前板", "后板", "出手", "命中", "三分出", "三分中", "三分命中率", "罚球出", "罚球中", "罚中率", };
		private Field[] fields = { Field.playerName, Field.numOfGame, Field.numOfStart, Field.minute, Field.point, Field.shot, Field.totRebound, Field.assist, Field.steal, Field.block, Field.fault,
				Field.foul, Field.offRebound, Field.defRebound, Field.totalShot, Field.totalHit, Field.threeShot, Field.threeHit, Field.three, Field.freeShot, Field.freeHit, Field.free };
		private String[] fieldStr = new String[fields.length];
		private MyTableModel model = new MyTableModel(title);
		private MyTable table = new MyTable(model);
		private MyScrollPanel scrollPanel = new MyScrollPanel(table);
		private MyComboBox<Object> seasonChoose, dataKindChoose, gameKindChoose, sortFieldChoose;
		private MyButton search;

		public PlayerNormalInfoPanel() {
			for (int i = 0; i < fields.length; i++) {
				fieldStr[i] = fields[i].toString();
			}
			seasonChoose = new MyComboBox<Object>(allSeasonArray);
			gameKindChoose = new MyComboBox<Object>(GameKind.gameKinds);
			dataKindChoose = new MyComboBox<Object>(DataKind.dataKinds);
			sortFieldChoose = new MyComboBox<>(title);
			search = new MyButton("搜索");
			search.setBackground(MyColor.MIDDLE_ORANGE);
			search.setForeground(MyColor.MY_BLACK);
			this.setComponentBounds();
			this.initTable();
			this.setVisible(true);
			search.addMouseListener(new MouseListener() {

				public void mouseReleased(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {
				}

				public void mouseExited(MouseEvent e) {
				}

				public void mouseEntered(MouseEvent e) {
				}

				public void mouseClicked(MouseEvent e) {
					String seasonStr = (String) seasonChoose.getSelectedItem();
					int dataKindInt = dataKindChoose.getSelectedIndex();
					int gameKindInt = gameKindChoose.getSelectedIndex();
					int sortFieldInt = sortFieldChoose.getSelectedIndex();
					ArrayList<SeasonPlayer> teamPlayerList = oneTeamInfoBl.getOneSeasonPlayers(teamName, Season.getSeason(seasonStr), GameKind.gameKinds[gameKindInt], DataKind.dataKinds[dataKindInt],
							fields[sortFieldInt]);
					model.removeAllRows();
					table.updateUI();
					if (teamPlayerList != null) {
						for (int i = 0; i < teamPlayerList.size(); i++) {
							Object[] contents = teamPlayerList.get(i).getSpeContent(fieldStr);
							model.addRow(contents);
							table.updateUI();
						}
					}
				}
			});
		}

		private void setComponentBounds() {
			seasonChoose.setBounds(190, 10, 150, 30);
			gameKindChoose.setBounds(360, 10, 150, 30);
			dataKindChoose.setBounds(530, 10, 150, 30);
			sortFieldChoose.setBounds(700, 10, 150, 30);
			search.setBounds(1000, 10, 150, 30);
			scrollPanel.setBounds(30, 50, 1245, 300);
			table.setRowHeight(30);
			table.setTableColumnWidth(0, 140);
			for (int i = 1; i <= 15; i++) {
				table.setTableColumnWidth(i, 45);
			}
			for (int i = 16; i <= 21; i++) {
				table.setTableColumnWidth(i, 55);
			}
			for (int i = 3; i <= 5; i++) {
				table.setTableColumnWidth(i, 55);
			}
			table.setTableColumnWidth(14, 55);
			table.setTableColumnWidth(15, 55);
			table.setTableColumnWidth(18, 85);
			table.setTableColumnWidth(21, 55);
			this.add(seasonChoose);
			this.add(gameKindChoose);
			this.add(dataKindChoose);
			this.add(sortFieldChoose);
			this.add(search);
			this.add(scrollPanel);
		}

		private void initTable() {
			ArrayList<SeasonPlayer> teamPlayerList = oneTeamInfoBl.getOneSeasonPlayers(teamName, Season.getSeason(allSeasonArray[0]), GameKind.regular_game, DataKind.average, Field.point);
			if (teamPlayerList != null) {
				for (int i = 0; i < teamPlayerList.size(); i++) {
					Object[] contents = teamPlayerList.get(i).getSpeContent(fieldStr);
					model.addRow(contents);
					table.updateUI();
				}
			}
		}
	}// 球队球员普通数据界面

	class AllGameOfSeasonPanel extends MyPanel {
		private static final long serialVersionUID = 1L;
		private String[] title = { "球队", "日期", "时间", "得分", "篮板", "助攻", "抢断", "盖帽", "失误", "犯规", "命中", "出手", "三分中", "三分出", "三分命中率", "罚球中", "罚球出", "罚球命中率", "前板", "后板" };
		private Field[] fields = { Field.teamName, Field.date, Field.minute, Field.point, Field.totRebound, Field.assist, Field.steal, Field.block, Field.fault, Field.foul, Field.totHit,
				Field.totShot, Field.threeHit, Field.threeShot, Field.three, Field.freeHit, Field.freeShot, Field.freeShot, Field.offRebound, Field.defRebound };
		private String[] fieldStr = new String[fields.length];
		private MyTableModel model = new MyTableModel(title);
		private MyTable table = new MyTable(model);
		private MyScrollPanel scrollPanel = new MyScrollPanel(table);
		private MyComboBox<Object> seasonChoose, sortFieldChoose;
		private MyButton search;

		public AllGameOfSeasonPanel() {
			for (int i = 0; i < fields.length; i++) {
				fieldStr[i] = fields[i].toString();
			}
			seasonChoose = new MyComboBox<Object>(allSeasonArray);
			sortFieldChoose = new MyComboBox<Object>(title);
			search = new MyButton("搜索");
			search.setBackground(MyColor.MIDDLE_ORANGE);
			search.setForeground(MyColor.MY_BLACK);
			this.setComponentBounds();
			this.initTable();
			table.addMouseListener(new MouseListener() {

				public void mouseReleased(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {
				}

				public void mouseExited(MouseEvent e) {
				}

				public void mouseEntered(MouseEvent e) {
				}

				public void mouseClicked(MouseEvent e) {
					if (table.getSelectedRow() >= 0 && table.getSelectedRow() < table.getRowCount()) {
						int row = table.getSelectedRow();
						String teamName = (String) table.getValueAt(row, 0);
						String date = (String) table.getValueAt(row, 1);
						GeneralGame generalMatch = new GameInfoBl().getGeneralMatch(teamName, date);
						OneGamePanel matchPanel = new OneGamePanel(generalMatch);
						SonFrame.changePanel(thisPanel, matchPanel, SonFrame.gameCard);
					}
				}
			});
			this.setVisible(true);
		}

		private void setComponentBounds() {
			table.setRowHeight(30);
			table.setTableColumnWidth(0, 180);
			table.setTableColumnWidth(1, 90);
			for (int i = 2; i <= 11; i++) {
				table.setTableColumnWidth(i, 45);
			}
			for (int i = 12; i <= 17; i++) {
				table.setTableColumnWidth(i, 56);
			}
			table.setTableColumnWidth(14, 90);
			table.setTableColumnWidth(17, 90);
			table.setTableColumnWidth(18, 50);
			table.setTableColumnWidth(19, 50);
			seasonChoose.setBounds(350, 10, 150, 30);
			sortFieldChoose.setBounds(550, 10, 150, 30);
			search.setBounds(750, 10, 150, 30);
			scrollPanel.setBounds(30, 50, 1245, 300);
			this.add(seasonChoose);
			this.add(sortFieldChoose);
			this.add(scrollPanel);
			this.add(search);
			search.addMouseListener(new MouseListener() {

				public void mouseReleased(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {
				}

				public void mouseExited(MouseEvent e) {
				}

				public void mouseEntered(MouseEvent e) {
				}

				public void mouseClicked(MouseEvent e) {
					String seasonStr = (String) seasonChoose.getSelectedItem();
					int sortFieldInt = sortFieldChoose.getSelectedIndex();
					ArrayList<GameTeam> gamePlayerList = oneTeamInfoBl.getRegularGameTeam(teamName, Season.getSeason(seasonStr), fields[sortFieldInt]);
					model.removeAllRows();
					table.updateUI();
					if (gamePlayerList != null) {
						for (int i = 0; i < gamePlayerList.size(); i++) {
							Object[] contents = gamePlayerList.get(i).getSpeContent(fieldStr);
							model.addRow(contents);
							table.updateUI();
						}
					}
				}
			});
		}

		private void initTable() {
			ArrayList<GameTeam> gamePlayerList = oneTeamInfoBl.getRegularGameTeam(teamName, Season.getSeason(allSeasonArray[0]), Field.date);
			if (gamePlayerList != null) {
				for (int i = 0; i < gamePlayerList.size(); i++) {
					Object[] contents = gamePlayerList.get(i).getSpeContent(fieldStr);
					model.addRow(contents);
					table.updateUI();
				}
			}
		}
	}// 球队每场比赛信息界面
}
