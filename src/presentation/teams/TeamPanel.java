package presentation.teams;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import presentation.SonFrame;
import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyComboBox;
import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.mycomponent.MyTable;
import presentation.mycomponent.MyTableModel;
import presentation.start.Main;
import presentation.statics.Method;
import presentation.statics.MyColor;
import presentation.statics.MyFont;
import presentation.statics.PathOfFile;
import beans.PlayOffSeries;
import beans.SeasonTeam;
import beans.generalTeam;
import businesslogic.team.TeamInfoBl;
import businesslogicservice.team.TeamInfoBlService;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.League;
import common.statics.Season;

public class TeamPanel extends MyPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContentPanel contentPanel;// 内容栏
	private MyButton regularButton, playOffButton;

	public TeamPanel() {
		contentPanel = new ContentPanel();
		regularButton = new MyButton("常规赛");
		playOffButton = new MyButton("季后赛");
		regularButton.setBounds(600, 20, 100, 30);
		playOffButton.setBounds(900, 20, 100, 30);
		regularButton.setBackground(MyColor.MIDDLE_ORANGE);
		playOffButton.setBackground(MyColor.MIDDLE_ORANGE);
		contentPanel.setBounds(0, 20, 1250, 600);
		this.add(regularButton);
		regularButton.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
				regularButton.setBackground(MyColor.MIDDLE_ORANGE);
			}

			public void mouseEntered(MouseEvent arg0) {
				regularButton.setBackground(MyColor.DEEP_ORANGE);
			}

			public void mouseClicked(MouseEvent arg0) {
				contentPanel.showRegularPanel();
				Main.mainFrame.changeBackGround(new ImageIcon("images/teams/background_team.png"));
				Main.mainFrame.isPlayOff = 0;
			}
		});
		playOffButton.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
				playOffButton.setBackground(MyColor.MIDDLE_ORANGE);
			}

			public void mouseEntered(MouseEvent arg0) {
				playOffButton.setBackground(MyColor.DEEP_ORANGE);
			}

			public void mouseClicked(MouseEvent arg0) {
				contentPanel.showPlayOffPanel();
				Main.mainFrame.changeBackGround(new ImageIcon(PathOfFile.TEAM_IMAGE + "background_playOff_team.png"));
				Main.mainFrame.isPlayOff = 1;
			}
		});
		this.add(playOffButton);
		this.add(contentPanel);
		this.setVisible(true);
	}

	class ContentPanel extends MyPanel {
		private static final long serialVersionUID = 1L;
		private CardLayout card;// 卡片布局管理器
		private RegularGamePanel regularPanel;
		private PlayOffGamePanel playOffPanel;

		ContentPanel() {
			card = new CardLayout();
			this.setOpaque(false);
			this.setLayout(card);
			regularPanel = new RegularGamePanel();
			playOffPanel = new PlayOffGamePanel();
			this.add(regularPanel, "regularPanel");
			this.add(playOffPanel, "playOffPanel");
			this.setVisible(true);
		}

		public void showRegularPanel() {
			this.card.show(contentPanel, "regularPanel");
		}

		public void showPlayOffPanel() {
			this.card.show(contentPanel, "playOffPanel");
		}
	}

	class RegularGamePanel extends MyPanel {
		private static final long serialVersionUID = 1L;
		private SelectionPanel selectionPanel;// 筛选界面
		private JScrollPane teamShowPane;// 展示界面
		private MyTable teamShowTable, rangeAndNameTable;
		private MyTableModel teamShowTableModel, rangeAndNameTableModel;
		private String fieldList[] = { "numOfWin", "numOfLose", "avgAge", "point", "shot", "three", "two", "free", "totRebound", "assist", "steal", "block", "fault", "foul", "minute", "totalHit",
				"totalShot", "threeHit", "threeShot", "twoShot", "twoHit", "freeHit", "freeShot", "offRebound", "defRebound", "offEFF", "defEFF", "pace", "freeEFF", "threeEFF", "realShot", "shotEFF",
				"faultEFF", "offReboundEFF", "defReboundEFF" };// 数据展示
		private String field[] = { "赢场数", "输场数", "球队平均年龄", "得分", "命中率", "三分命中率", "两分命中率", "罚球命中率", "篮板", "助攻", "抢断", "盖帽", "失误", "犯规", "分钟", "命中", "出手", "三分命中", "三分出手", " 两分出手", " 两分命中", "罚球命中",
				"罚球出手", "前篮板", "后篮板", "进攻效率", "防守效率", "回合数", "罚球效率", "三分效率", "真实命中率", "投篮效率", "失误率", "进攻篮板效率", "防守篮板效率" };
		private String identity[] = { "排名", "队标", "队名", "赛季" };// 球队标识
		private TeamInfoBlService teamInfoBl = new TeamInfoBl();

		RegularGamePanel() {
			this.createObjects();
			this.setComponentsLocation();
			this.setComponentsStyle();
			this.initTable();
			rangeAndNameTable.addMouseListener(new MouseListener() {

				public void mouseReleased(MouseEvent e) {

				}

				public void mousePressed(MouseEvent e) {

				}

				public void mouseExited(MouseEvent e) {

				}

				public void mouseEntered(MouseEvent e) {

				}

				public void mouseClicked(MouseEvent arg0) {
					if (rangeAndNameTable.getSelectedRow() >= 0 && rangeAndNameTable.getSelectedRow() < rangeAndNameTable.getRowCount()) {
						int row = rangeAndNameTable.getSelectedRow();
						String teamName = (String) rangeAndNameTable.getValueAt(row, 2);
						String season = (String) rangeAndNameTable.getValueAt(row, 3);
						generalTeam generalTeam = teamInfoBl.getGeneralTeam(teamName, Season.getSeason(season));
						new SonFrame(generalTeam, SonFrame.teamCard);
					}
				}
			});
		}

		private void initTable() {
			ArrayList<SeasonTeam> seasonTeamList = this.teamInfoBl.getSeasonTeam(Season.this_season, DataKind.average, League.all_league, Field.numOfWin);
			this.fillTable(seasonTeamList);
		}

		private void fillTable(ArrayList<SeasonTeam> voList) {
			teamShowTableModel.removeAllRows();
			rangeAndNameTableModel.removeAllRows();
			if (voList != null) {
				for (int i = 0; i < voList.size(); i++) {
					Object performRow[] = voList.get(i).getSpeContent(fieldList);
					String seasonStr = voList.get(i).getSeason();
					Season season = Season.getSeason(seasonStr);
					String teamName = voList.get(i).getTeamName();
					generalTeam generalTeam = teamInfoBl.getGeneralTeam(teamName, season);
					ImageIcon logo = new ImageIcon();
					if (generalTeam != null) {
						logo = Method.changeSize(new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + generalTeam.getImgName() + ".png"), 60, 50);
					}
					Object infoRow[] = { String.valueOf(i + 1), logo, voList.get(i).getTeamName(), voList.get(i).getSeason() };
					teamShowTableModel.addRow(performRow);
					rangeAndNameTableModel.addRow(infoRow);
				}
			}
			teamShowTable.updateUI();
			rangeAndNameTable.updateUI();
			setTableStyle();
		}

		private void setTableStyle() {
			teamShowPane.getViewport().setOpaque(false);
			teamShowPane.setOpaque(false);
			teamShowPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			teamShowTable.setAllTableColumnWidth(120);
			rangeAndNameTable.setTableColumnWidth(0, 40);
			rangeAndNameTable.setTableColumnWidth(1, 60);
			rangeAndNameTable.setTableColumnWidth(2, 180);
			rangeAndNameTable.setTableColumnWidth(3, 70);
			teamShowTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					checkSelection(false);
				}
			});
			rangeAndNameTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					checkSelection(true);
				}
			});
			JViewport viewport = new JViewport();
			viewport.setOpaque(false);
			viewport.setView(rangeAndNameTable);
			viewport.setPreferredSize(rangeAndNameTable.getPreferredSize());
			teamShowPane.setRowHeaderView(viewport);
			teamShowPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rangeAndNameTable.getTableHeader());

		}

		private void checkSelection(boolean isFixedTable) {
			int fixedSelectedIndex = rangeAndNameTable.getSelectedRow();
			int selectedIndex = teamShowTable.getSelectedRow();
			if (fixedSelectedIndex != selectedIndex) {
				if (isFixedTable) {
					teamShowTable.setRowSelectionInterval(fixedSelectedIndex, fixedSelectedIndex);
				}
				else {
					rangeAndNameTable.setRowSelectionInterval(selectedIndex, selectedIndex);
				}
			}
		}

		private void setComponentsLocation() {
			selectionPanel.setLocation(27, 20);
			teamShowPane.setBounds(350, 40, 900, 530);
			this.add(teamShowPane);
			this.add(selectionPanel);
		}

		private void createObjects() {
			selectionPanel = new SelectionPanel();
			teamShowTableModel = new MyTableModel(field);
			rangeAndNameTableModel = new MyTableModel(identity);
			teamShowTable = new MyTable(teamShowTableModel);
			rangeAndNameTable = new MyTable(rangeAndNameTableModel) {
				private static final long serialVersionUID = 1L;

				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int column) {
					if (column == 1) {// 要这样定义table，要重写这个方法0，0的意思就是别的格子的类型都跟0,0的一样。
						return ImageIcon.class;
					}
					else {
						return getValueAt(0, 0).getClass();
					}
				}
			};
			teamShowPane = new JScrollPane();
			teamShowPane.getViewport().add(teamShowTable);
		}

		private void setComponentsStyle() {

		}

		class SelectionPanel extends MyPanel implements MouseListener {
			private int Jcombobox_x = 25;
			private int Jcombobox_y = 50;
			private int Jcombobox_gap = 75;
			private static final long serialVersionUID = 1L;
			private JButton searchButton, findTeamButton;
			private JTextField teamInput;
			private MyComboBox<Object> seasonChoose, dataKindChoose, leagueChoose, sortFieldChoose;

			public SelectionPanel() {
				this.setSize(300, 600);
				this.setBackground(MyColor.LIGHT_COLOR);
				this.createObjects();
				this.setComponentsLocation();
				this.setComponentsStyle();
				this.setVisible(true);
			}

			private void createObjects() {
				teamInput = new JTextField();
				searchButton = new JButton("搜索");
				findTeamButton = new JButton(new ImageIcon("images/players/find_normal.png"));

				String[] league = { "全联盟", "西部", "东部" };
				String[] dataKind = { "场均数据", "总数据" };

				seasonChoose = new MyComboBox<>(Season.all_seasons);
				dataKindChoose = new MyComboBox<Object>(dataKind);
				leagueChoose = new MyComboBox<Object>(league);
				sortFieldChoose = new MyComboBox<Object>(field);
			}

			private void setComponentsLocation() {
				findTeamButton.setBounds(Jcombobox_x + 160, 500, 40, 40);
				teamInput.setBounds(Jcombobox_x, 500, 150, 40);
				seasonChoose.setLocation(Jcombobox_x, Jcombobox_y);
				dataKindChoose.setLocation(Jcombobox_x, Jcombobox_y + Jcombobox_gap);
				leagueChoose.setLocation(Jcombobox_x, Jcombobox_y + Jcombobox_gap * 2);
				sortFieldChoose.setLocation(Jcombobox_x, Jcombobox_y + Jcombobox_gap * 3);

				searchButton.setBounds(Jcombobox_x, 350, 220, 40);
				this.add(teamInput);
				this.add(findTeamButton);
				this.add(sortFieldChoose);
				this.add(searchButton);
				this.add(dataKindChoose);
				this.add(leagueChoose);
				this.add(seasonChoose);
			}

			private void setComponentsStyle() {
				this.setButton(searchButton);
				this.setButton(findTeamButton);
				teamInput.setOpaque(false);
				teamInput.setForeground(MyColor.MY_BLACK);
				teamInput.setFont(MyFont.SMALL_PLAIN);
			}

			private void setButton(JButton button) {
				button.setFocusable(false);
				button.setBorderPainted(false);
				button.setFont(MyFont.SMALLEST_BOLD);
				button.setForeground(MyColor.MY_WHITE);
				button.setBackground(MyColor.MIDDLE_ORANGE);
				button.addMouseListener(this);
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getSource().equals(searchButton)) {
					int seasonInt = seasonChoose.getSelectedIndex();
					int dataKindInt = dataKindChoose.getSelectedIndex();
					int leagueInt = leagueChoose.getSelectedIndex();
					int sortCellInt = sortFieldChoose.getSelectedIndex();
					ArrayList<SeasonTeam> seasonTeamList = teamInfoBl.getSeasonTeam(Season.all_seasons[seasonInt], DataKind.dataKinds[dataKindInt], League.leagues[leagueInt],
							Field.team_sort_field[sortCellInt]);
					fillTable(seasonTeamList);
				}
				else if (e.getSource().equals(findTeamButton)) {
					String str = teamInput.getText();
					if (str.equals("") || str == null) {
						JOptionPane.showMessageDialog(Main.mainFrame, "请输入要查找的球队");// 弹出提示
					}
					else {
						ArrayList<SeasonTeam> seasonTeamList = teamInfoBl.vagueSearch(str);
						fillTable(seasonTeamList);
					}
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (e.getSource().equals(searchButton)) {
					searchButton.setBackground(MyColor.DEEP_ORANGE);
				}
				else if (e.getSource().equals(findTeamButton)) {
					findTeamButton.setBackground(MyColor.DEEP_ORANGE);
				}
			}

			public void mouseExited(MouseEvent e) {
				if (e.getSource().equals(searchButton)) {
					searchButton.setBackground(MyColor.MIDDLE_ORANGE);
				}
				else if (e.getSource().equals(findTeamButton)) {
					findTeamButton.setBackground(MyColor.MIDDLE_ORANGE);
				}
			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseReleased(MouseEvent e) {

			}
		}
	}

	class PlayOffGamePanel extends MyPanel {
		private static final long serialVersionUID = 1L;
		private PlayOffGamePanel thisPanel = this;
		private final String westFirstRound = "Western Conf First Round";
		private final String eastFirstRound = "Eastern Conf First Round";
		private final String westSecondRound = "Western Conf Semifinals";
		private final String eastSecondRound = "Eastern Conf Semifinals";
		private final String westFinal = "Western Conf Finals";
		private final String eastFinal = "Eastern Conf Finals";
		private final String finals = "Finals";
		private TeamInfoBlService teamInfoBl = new TeamInfoBl();
		private ArrayList<PlayOffSeries> oneSeasonPlayOffSeries;
		private ArrayList<SeriesPanel> seriesPanelList = new ArrayList<SeriesPanel>();
		private MyComboBox<Object> seasonChoose;
		private MyButton search;
		private String[] allSeasonArray = new String[45];

		PlayOffGamePanel() {
			for (int i = 44; i >= 0; i--) {
				allSeasonArray[i] = Season.all_seasons[i].toString();
			}
			seasonChoose = new MyComboBox<Object>(allSeasonArray);
			seasonChoose.setBounds(50, 0, 100, 30);
			this.add(seasonChoose);
			search = new MyButton("搜索");
			search.setBounds(200, 0, 100, 30);
			search.setBackground(MyColor.MIDDLE_ORANGE);
			search.setForeground(MyColor.MY_WHITE);
			this.add(search);
			search.addMouseListener(new MouseListener() {

				public void mouseReleased(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {
				}

				public void mouseExited(MouseEvent e) {
					search.setBackground(MyColor.MIDDLE_ORANGE);
				}

				public void mouseEntered(MouseEvent e) {
					search.setBackground(MyColor.DEEP_ORANGE);
				}

				public void mouseClicked(MouseEvent e) {
					String seasonStr = (String) seasonChoose.getSelectedItem();
					oneSeasonPlayOffSeries = teamInfoBl.getOneSeasonPlayerOffSeries(Season.getSeason(seasonStr));
					seriesPanelList.clear();
					if (oneSeasonPlayOffSeries != null) {
						for (int i = 0; i < oneSeasonPlayOffSeries.size(); i++) {
							seriesPanelList.add(new SeriesPanel(oneSeasonPlayOffSeries.get(i)));
						}
					}
					thisPanel.removeAll();
					thisPanel.add(search);
					thisPanel.add(seasonChoose);
					setContent();
				}
			});
			this.initPanel();
		}

		private void initPanel() {
			oneSeasonPlayOffSeries = teamInfoBl.getOneSeasonPlayerOffSeries(Season.this_season);
			if (oneSeasonPlayOffSeries != null) {
				for (int i = 0; i < oneSeasonPlayOffSeries.size(); i++) {
					seriesPanelList.add(new SeriesPanel(oneSeasonPlayOffSeries.get(i)));
				}
			}
			this.setContent();
			this.setVisible(true);
		}

		private void setContent() {
			ArrayList<SeriesPanel> westFirst = new ArrayList<SeriesPanel>();
			for (int i = 0; i < oneSeasonPlayOffSeries.size(); i++) {
				if (oneSeasonPlayOffSeries.get(i).getSeries().equals(westFirstRound)) {
					westFirst.add(seriesPanelList.get(i));
					// oneSeasonPlayOffSeries.remove(i);
				}
			}
			for (int i = 0; i < westFirst.size(); i++) {
				westFirst.get(i).setLocation(100 + i * 300, 50);
				this.add(westFirst.get(i));
			}

			ArrayList<SeriesPanel> eastFirst = new ArrayList<SeriesPanel>();
			for (int i = 0; i < oneSeasonPlayOffSeries.size(); i++) {
				if (oneSeasonPlayOffSeries.get(i).getSeries().equals(eastFirstRound)) {
					eastFirst.add(seriesPanelList.get(i));
				}
			}
			for (int i = 0; i < eastFirst.size(); i++) {
				eastFirst.get(i).setLocation(100 + i * 300, 520);
				this.add(eastFirst.get(i));
			}

			ArrayList<SeriesPanel> westSecond = new ArrayList<SeriesPanel>();
			for (int i = 0; i < oneSeasonPlayOffSeries.size(); i++) {
				if (oneSeasonPlayOffSeries.get(i).getSeries().equals(westSecondRound)) {
					westSecond.add(seriesPanelList.get(i));
				}
			}
			for (int i = 0; i < westSecond.size(); i++) {
				westSecond.get(i).setLocation(250 + i * 600, 130);
				this.add(westSecond.get(i));
			}

			ArrayList<SeriesPanel> eastSecond = new ArrayList<SeriesPanel>();
			for (int i = 0; i < oneSeasonPlayOffSeries.size(); i++) {
				if (oneSeasonPlayOffSeries.get(i).getSeries().equals(eastSecondRound)) {
					eastSecond.add(seriesPanelList.get(i));
				}
			}
			for (int i = 0; i < eastSecond.size(); i++) {
				eastSecond.get(i).setLocation(250 + i * 600, 440);
				this.add(eastSecond.get(i));
			}

			ArrayList<SeriesPanel> westFinalPanel = new ArrayList<SeriesPanel>();
			for (int i = 0; i < oneSeasonPlayOffSeries.size(); i++) {
				if (oneSeasonPlayOffSeries.get(i).getSeries().equals(westFinal)) {
					westFinalPanel.add(seriesPanelList.get(i));
				}
			}
			for (int i = 0; i < westFinalPanel.size(); i++) {
				westFinalPanel.get(i).setLocation(530, 180);
				this.add(westFinalPanel.get(i));
			}

			ArrayList<SeriesPanel> eastFinalPanel = new ArrayList<SeriesPanel>();
			for (int i = 0; i < oneSeasonPlayOffSeries.size(); i++) {
				if (oneSeasonPlayOffSeries.get(i).getSeries().equals(eastFinal)) {
					eastFinalPanel.add(seriesPanelList.get(i));
				}
			}
			for (int i = 0; i < eastFinalPanel.size(); i++) {
				eastFinalPanel.get(i).setLocation(530, 390);
				this.add(eastFinalPanel.get(i));
			}

			SeriesPanel finalPanel = null;
			for (int i = 0; i < oneSeasonPlayOffSeries.size(); i++) {
				if (oneSeasonPlayOffSeries.get(i).getSeries().equals(finals)) {
					finalPanel = seriesPanelList.get(i);
					break;
				}
			}
			if (finalPanel != null) {
				finalPanel.setLocation(530, 285);
				this.add(finalPanel);
			}
			this.updateUI();
		}

		class SeriesPanel extends MyPanel {
			private static final long serialVersionUID = 1L;
			private Season season;// 所处赛季
			private PlayOffSeries playOffSerise;// 系列赛信息
			private generalTeam winTeamGeneral;// 赢球球队信息
			private generalTeam loseTeamGeneral;// 输球球队信息
			private int winTeamWinNum;// 赢球球队胜利场数
			private int loseTeamWinNum;// 输球球队胜利场数
			private final int labelHeight = 60;

			SeriesPanel(PlayOffSeries playOffSeries) {
				this.playOffSerise = playOffSeries;
				this.season = Season.dateToSeason(playOffSeries.getStartDate());
				this.winTeamGeneral = teamInfoBl.getGeneralTeam(playOffSeries.getWinTeam(), season);
				this.loseTeamGeneral = teamInfoBl.getGeneralTeam(playOffSeries.getLoseTeam(), season);
				this.winTeamWinNum = playOffSeries.getWinTeamWin();
				this.loseTeamWinNum = playOffSeries.getLoseTeamWin();
				this.setPanel();
			}

			private void setPanel() {
				MyLabel winTeamImg = new MyLabel();
				MyLabel loseTeamImg = new MyLabel();

				MyLabel winNumLabel = new MyLabel();
				winTeamImg.setBounds(0, 0, 70, labelHeight);
				winNumLabel.setBounds(70, 0, 40, labelHeight);
				loseTeamImg.setBounds(110, 0, 70, labelHeight);
				winTeamImg.setMyIcon(new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + winTeamGeneral.getImgName() + ".png"));
				loseTeamImg.setMyIcon(new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + loseTeamGeneral.getImgName() + ".png"));
				String str = " " + String.valueOf(winTeamWinNum) + " " + String.valueOf(loseTeamWinNum);
				winNumLabel.setTextAndStyle(str);
				winNumLabel.setForeground(MyColor.MY_WHITE);
				this.setSize(180, labelHeight);
				this.add(winTeamImg);
				this.add(winNumLabel);
				this.add(loseTeamImg);
				this.setVisible(true);
			}

			public PlayOffSeries getSeriesInfo() {
				return this.playOffSerise;
			}
		}
	}
}
