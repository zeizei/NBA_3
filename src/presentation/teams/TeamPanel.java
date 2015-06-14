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

import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyComboBox;
import presentation.mycomponent.MyPanel;
import presentation.mycomponent.MyTable;
import presentation.mycomponent.MyTableModel;
import presentation.start.Main;
import presentation.statics.Method;
import presentation.statics.MyColor;
import presentation.statics.MyFont;
import presentation.statics.PathOfFile;
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
		regularButton = new MyButton("regularGame");
		playOffButton = new MyButton("playOffGame");
		regularButton.setBounds(300, 0, 200, 50);
		playOffButton.setBounds(700, 0, 200, 50);
		contentPanel.setBounds(0, 20, 1200, 600);
		this.add(regularButton);
		regularButton.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseClicked(MouseEvent arg0) {
				contentPanel.showRegularPanel();
			}
		});
		playOffButton.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseClicked(MouseEvent arg0) {
				contentPanel.showPlayOffPanel();
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
		private String identity[] = { "rank", "logo", "name", "season" };// 球队标识
		private TeamInfoBlService teamInfoBl = new TeamInfoBl();

		RegularGamePanel() {
			this.createObjects();
			this.setComponentsLocation();
			this.setComponentsStyle();
			this.initTable();
			this.setTableStyle();
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
						// int row = rangeAndNameTable.getSelectedRow();
						// String teamName = (String) *
						// rangeAndNameTable.getValueAt(row, 2);
						// new SonFrame(teamName, SonFrame.teamCard);
					}
				}
			});
		}

		private void initTable() {
			ArrayList<SeasonTeam> seasonTeamList = this.teamInfoBl.getSeasonTeam(Season.season_team[0], DataKind.average, League.all_league, Field.numOfWin);
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
		}

		private void setTableStyle() {
			teamShowPane.getViewport().setOpaque(false);
			teamShowPane.setOpaque(false);
			teamShowPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			teamShowTable.setAllTableColumnWidth(80);
			rangeAndNameTable.setAllTableColumnWidth(96);
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
			teamShowPane.setBounds(350, 50, 850, 530);
			this.add(teamShowPane);
			this.add(selectionPanel);
		}

		private void createObjects() {
			selectionPanel = new SelectionPanel();
			teamShowTableModel = new MyTableModel(fieldList);
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

				seasonChoose = new MyComboBox<>(Season.season_team);
				dataKindChoose = new MyComboBox<Object>(DataKind.dataKinds);
				leagueChoose = new MyComboBox<Object>(League.leagues);
				sortFieldChoose = new MyComboBox<Object>(Field.team_sort_field);
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
				teamInput.setForeground(MyColor.MY_WHITE);
				teamInput.setFont(MyFont.SMALL_BOLD);
			}

			private void setButton(JButton button) {
				button.setFocusable(false);
				button.setBorderPainted(false);
				button.setFont(MyFont.SMALLEST_BOLD);
				button.setForeground(MyColor.MY_WHITE);
				button.setBackground(MyColor.MIDDLE_COLOR);
				button.addMouseListener(this);
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getSource().equals(searchButton)) {
					int seasonInt = seasonChoose.getSelectedIndex();
					int dataKindInt = dataKindChoose.getSelectedIndex();
					int leagueInt = leagueChoose.getSelectedIndex();
					int sortCellInt = sortFieldChoose.getSelectedIndex();
					ArrayList<SeasonTeam> seasonTeamList = teamInfoBl.getSeasonTeam(Season.season_team[seasonInt], DataKind.dataKinds[dataKindInt], League.leagues[leagueInt],
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
					searchButton.setBackground(MyColor.DEEP_COLOR);
				}
				else if (e.getSource().equals(findTeamButton)) {
					findTeamButton.setBackground(MyColor.DEEP_COLOR);
				}
			}

			public void mouseExited(MouseEvent e) {
				if (e.getSource().equals(searchButton)) {
					searchButton.setBackground(MyColor.MIDDLE_COLOR);
				}
				else if (e.getSource().equals(findTeamButton)) {
					findTeamButton.setBackground(MyColor.MIDDLE_COLOR);
				}
			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseReleased(MouseEvent e) {

			}
		}
	}

	class PlayOffGamePanel extends MyPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		PlayOffGamePanel() {
		}

	}
}
