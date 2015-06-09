package presentation.players;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Method;
import java.nio.file.DirectoryStream.Filter;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import presentation.SonFrame;
import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyComboBox;
import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.mycomponent.MyScrollPanel;
import presentation.mycomponent.MyTable;
import presentation.mycomponent.MyTableModel;
import presentation.start.Main;
import presentation.statics.MyColor;
import presentation.statics.MyFont;
import presentation.statics.NUMBER;
import presentation.statics.PathOfFile;
import beans.SeasonPlayer;
import businesslogic.player.PlayerInfoBl;
import businesslogicservice.player.PlayerInfoBlService;
import common.datastructure.CombineSelectionCell;
import common.statics.AgeRange;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.League;
import common.statics.Position;
import common.statics.Season;

public class PlayerPanel extends MyPanel {
	private SelectionPanel selectionPanel;
	private JScrollPane playerShowPane;
	private MyTable playerShowTable, rangeAndNameTable;
	private MyTableModel playerTableModel, rangeAndNameTableModel;
	private static final long serialVersionUID = 1L;
	private String commonPerformanceList[] = { "球队", "场数","首发", "时间", "效率", "得分",  "助攻", "抢断", "盖帽", "失误", "犯规","前篮板","后篮板", "命中率", "三分命中率", "罚球命中率" };
	private String advancedPerformanceList[] = { "球队","球员效率", "真实命中率","三分效率", "罚球效率", "进攻篮板效率", "防守篮板效率", "助攻效率", "抢断效率","盖帽效率", "失误率", "使用率", "进攻贡献值", "防守贡献值", "替换价值" };
	private String rangeAndNamePerformance[] = { "排名", "头像", "姓名" };
	private PlayerInfoBlService playerInfoBl = new PlayerInfoBl();

	public PlayerPanel() {
		this.createObjects();
		this.setComponentsLocation();
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

			public void mouseClicked(MouseEvent e) {
				if (rangeAndNameTable.getSelectedRow() >= 0 && rangeAndNameTable.getSelectedRow() < rangeAndNameTable.getRowCount()) {
					int row = rangeAndNameTable.getSelectedRow();
					String teamName = (String) rangeAndNameTable.getValueAt(row, 2);
					new SonFrame(teamName, SonFrame.playerCard);
				}
			}
		});
	}

	private void initTable() {
		/*
		 * Filter filter = new Filter(); filter.setAge(Age.All);
		 * filter.setLeague(League.All); filter.setPosition(Position.All);
		 * SortCell[] sortcells = new SortCell[1]; sortcells[0] = new
		 * SortCell(Field.point + Command.dot + Command.descend);
		 * ArrayList<PlayerNormalInfo_Expand> playerNormalList =
		 * this.playerInfoBl.getPlayerNormal_avg(NUMBER.DEFAULT_NUMBER, filter,
		 * sortcells); this.fillTable(playerNormalList);
		 */}

	/*
	 * private void fillTable(ArrayList<PlayerNormalInfo_Expand> voList) { for
	 * (int i = 0; i < voList.size(); i++) { String performRow[] =
	 * voList.get(i).toStringArray(); Object infoRow[] = { String.valueOf(i +
	 * 1), Method.changeSize(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE +
	 * voList.get(i).getName() + ".png"), (40* 1), (40 * 1)),
	 * voList.get(i).getName() }; playerTableModel.addRow(performRow);
	 * rangeAndNameTableModel.addRow(infoRow); } playerShowTable.updateUI();
	 * rangeAndNameTable.updateUI(); }
	 */

	private void clearTable() {
		playerTableModel.removeAllRows();
		rangeAndNameTableModel.removeAllRows();
	}

	private void createObjects() {
		playerTableModel = new MyTableModel(commonPerformanceList);
		rangeAndNameTableModel = new MyTableModel(rangeAndNamePerformance);
		playerShowTable = new MyTable(playerTableModel);
		rangeAndNameTable = new MyTable(rangeAndNameTableModel) {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int column) {
				if (column == 1) {// 要这样定义table，要重写这个方法0，0的意思就是别的格子的类型都跟0,0的一样。
					return ImageIcon.class;
				} else {
					return getValueAt(0, 0).getClass();
				}
			}

		};
		selectionPanel = new SelectionPanel();
		playerShowPane = new JScrollPane();
		playerShowPane.getViewport().add(playerShowTable);
	}

	private void setComponentsLocation() {
		selectionPanel.setLocation(27, 20);
		playerShowPane.setBounds(390, 55, 860, 530);
		this.add(selectionPanel);
		this.add(playerShowPane);
	}

	private void setTableStyle() {
		rangeAndNameTable.setTableColumnWidth(1, 60);
		rangeAndNameTable.setTableColumnWidth(2, 120);
		playerShowPane.getViewport().setOpaque(false);
		playerShowPane.setOpaque(false);
		playerShowPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		playerShowTable.setAllTableColumnWidth(50);
		playerShowTable.setTableColumnWidth(5, 70);
		for (int i = 11; i < 14; i++) {
			playerShowTable.setTableColumnWidth(i, (100));
		}
		for (int i = 14; i < 16; i++) {
			playerShowTable.setTableColumnWidth(i, (120));
		}
		playerShowTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
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
		playerShowPane.setRowHeaderView(viewport);
		playerShowPane.getRowHeader().setOpaque(false);
		playerShowPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rangeAndNameTable.getTableHeader());
	}

	private void checkSelection(boolean isFixedTable) {
		int fixedSelectedIndex = rangeAndNameTable.getSelectedRow();
		int selectedIndex = playerShowTable.getSelectedRow();
		if (fixedSelectedIndex != selectedIndex) {
			if (isFixedTable) {
				playerShowTable.setRowSelectionInterval(fixedSelectedIndex, fixedSelectedIndex);
			} else {
				rangeAndNameTable.setRowSelectionInterval(selectedIndex, selectedIndex);
			}
		}
	}
	
	class SelectionPanel extends MyPanel implements MouseListener {
		private int Jcombobox_x = 30;
		private int Jcombobox_y = 60;
		private int Jcombobox_gap = 70;
		private int width=120;
		private int height=40;
		private static final long serialVersionUID = 1L;
		private JButton advancedSelect, searchButton, findButton;
		private JTextField playerInput;
		private MyComboBox<String> seasonChoose, gameKindChoose,totOrAvgChoose, commonOrAdvanced, selectCellChoose, leagueChoose, positionChoose, ageChoose;
		private AgeRange[] ageArray = { AgeRange.All, AgeRange.less_equal_22, AgeRange.more_22_less_equal_25, AgeRange.more_25_less_equal_30, AgeRange.more_30_less_equal_35, AgeRange.more_35 };
		private League[] leagueArray = { League.all_league, League.east, League.west };
		private Position[] positionArray = { Position.All, Position.G, Position.F, Position.C, Position.PG, Position.SG, Position.PF, Position.SF };
		private DataKind[] dataKindArray = { DataKind.AVERAGE, DataKind.TOTAL };
		private Field[] commonField = { Field.point,Field.assist, Field.steal, Field.block, Field.fault, Field.foul , Field.minute, Field.numOfGame, Field.numOfStart, Field.shotEFF, Field.offRebound, Field.defRebound,   Field.shot, Field.three, Field.two, Field.free};
		private Field[] advancedField = { Field.playerEFF,Field.faultEFF, Field.useEFF, Field.BoxPM,Field.replaceValue, Field.threeEFF, Field.freeEFF, Field.assistEFF, Field.stealEFF, Field.blockEFF, Field.realShot,  Field.offBoxPM, Field.defBoxPM , Field.totReboundEFF,Field.offReboundEFF, Field.defReboundEFF};
		private GameKind[] gameKindArray={GameKind.regular_game,GameKind.playOff_game};
		String[] commonSelectCellList = { "得分","助攻", "抢断", "盖帽", "失误", "犯规", "分钟", "比赛场数","首发次数","投篮效率", "前场篮板","后场篮板", "投篮命中率", "三分命中率", "两分命中率","罚球命中率" };
		String[] advancedSelectCellList = { "效率值","使用率","失误率", "贡献值","替换价值", "三分效率", "罚球效率", "助攻效率", "抢断效率", "盖帽效率", "真实命中率","进攻贡献值", "防守贡献值", " 总篮板效率", "真实命中率","进攻篮板效率" ,"防守篮板效率"};
		
		public SelectionPanel() {
			this.setSize(350, 600);
			this.createObjects();
			this.setComponentsLocation();
			this.setComponentsStyle();
		}

		private void createObjects() {
			findButton = new JButton(new ImageIcon("images/players/find_normal.png"));
			advancedSelect = new JButton("高级搜索");
			searchButton = new JButton("搜索");
			playerInput = new JTextField();
			String[] season = new String[Season.season_array.length];
			for (int i = 0; i < season.length; i++) {
				season[i] = Season.season_array[i].toString();
			}
			String[] gameKindList={"常规赛","季后赛"};
			String[] commonOrAdvancedArray = { "普通数据", "高级数据" };
			String[] totOrAvg = { "场均数据", "总数据" };
			String[] ageList = { "全部年龄", "<=22岁", ">22岁,<=25岁", ">25岁,<=30岁",">30,<=35岁", ">=35岁" };
			String[] conferenceList = { "全联盟", "东部", "西部" };
			String[] positionList = { "全部位置", "后卫", "前锋", "中锋" ,"控球后卫","得分后卫","大前锋","小前锋"};
			
			ageChoose = new MyComboBox<>(ageList);
			positionChoose = new MyComboBox<String>(positionList);
			leagueChoose = new MyComboBox<String>(conferenceList);
			selectCellChoose = new MyComboBox<String>(commonSelectCellList);
			totOrAvgChoose = new MyComboBox<>(totOrAvg);
			seasonChoose=new MyComboBox<>(season);
			gameKindChoose=new MyComboBox<String>(gameKindList);
			commonOrAdvanced=new MyComboBox<String>(commonOrAdvancedArray);
		}

		private void setComponentsLocation() {
			seasonChoose.setBounds(Jcombobox_x,Jcombobox_y,width,height);
			gameKindChoose.setBounds(Jcombobox_x+150,Jcombobox_y,width,height);
			totOrAvgChoose.setBounds(Jcombobox_x, Jcombobox_y + Jcombobox_gap, width,height);
			ageChoose.setBounds(Jcombobox_x+150, Jcombobox_y + Jcombobox_gap, width,height);
			commonOrAdvanced.setBounds(Jcombobox_x, Jcombobox_y + Jcombobox_gap * 2, width,height);
			selectCellChoose.setBounds(Jcombobox_x+150, Jcombobox_y + Jcombobox_gap * 2, width,height);
			leagueChoose.setBounds(Jcombobox_x, Jcombobox_y + Jcombobox_gap*3, width,height);
			positionChoose.setBounds(Jcombobox_x+150,Jcombobox_y+ Jcombobox_gap*3, width,height);
			searchButton.setBounds(Jcombobox_x, Jcombobox_y + Jcombobox_gap * 4, 120, 40);
			advancedSelect.setBounds(Jcombobox_x + 140, Jcombobox_y + Jcombobox_gap * 4, 120, 40);
			findButton.setBounds(Jcombobox_x + 190, 500, 35, 35);
			playerInput.setBounds(Jcombobox_x, 500, 180, 35);
			this.add(seasonChoose);
			this.add(gameKindChoose);
			this.add(commonOrAdvanced);
			commonOrAdvanced.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){
						int comOrAdv = commonOrAdvanced.getSelectedIndex();
						if(comOrAdv==1){
							selectCellChoose.removeAllItems();
							DefaultComboBoxModel model=new DefaultComboBoxModel(advancedSelectCellList);
							selectCellChoose.setModel(model);
						}
						else{
							System.out.println(comOrAdv);
							selectCellChoose.removeAllItems();
							DefaultComboBoxModel model=new DefaultComboBoxModel(commonSelectCellList);
							selectCellChoose.setModel(model);
						}
					}
					 				}
			});
			this.add(positionChoose);
			this.add(leagueChoose);
			this.add(ageChoose);
			this.add(advancedSelect);
			this.add(selectCellChoose);
			this.add(searchButton);
			this.add(findButton);
			this.add(playerInput);
			this.add(totOrAvgChoose);
		}

		private void setComponentsStyle() {
			this.setButton(advancedSelect);
			this.setButton(searchButton);
			this.setButton(findButton);
			findButton.setContentAreaFilled(false);
			playerInput.setOpaque(false);
			playerInput.setForeground(Color.black);
			playerInput.setFont(MyFont.SMALL_PLAIN);

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
				int gameKindInt=gameKindChoose.getSelectedIndex();
				int comOrAdv = commonOrAdvanced.getSelectedIndex();
				int ageInt = ageChoose.getSelectedIndex();
				int totOrAvgInt = totOrAvgChoose.getSelectedIndex();
				int leagueInt = leagueChoose.getSelectedIndex();
				int positionInt = positionChoose.getSelectedIndex();
				int sortCellInt = selectCellChoose.getSelectedIndex();
				Season season = Season.season_array[seasonInt];
				GameKind gameKind=gameKindArray[gameKindInt];
				AgeRange ageRange=ageArray[ageInt];
				DataKind dataKind = dataKindArray[totOrAvgInt];
				Position position=positionArray[positionInt];
				League league =leagueArray[leagueInt];
				Field field;
				if(comOrAdv==0){
					field=commonField[sortCellInt];
				}
				else{
					field=advancedField[sortCellInt];
				}
					
					ArrayList<SeasonPlayer> playerNormalList = playerInfoBl.getSeasonPlayer(season, gameKind,dataKind, league,position,ageRange, field);
					clearTable();
					// fillTable(playerNormalList);
				
			} else if (e.getSource().equals(advancedSelect)) {
				new AdvancedSelectionJdialog();
			} else if (e.getSource().equals(findButton)) {
				findButton.setIcon(new ImageIcon("images/players/find_press.png"));
				String playerName = playerInput.getText();
				if (playerName.equals("") || playerName == null) {
					JOptionPane.showMessageDialog(Main.mainFrame, "请输入要查找的球员");// 弹出提示
				} else {
//					PlayerNormalInfo_Expand playerNormal = new OnePlayerInfoBl().getPlayerNormalInfo_avg(playerName);
				/*	if (playerNormal == null) {
						JOptionPane.showMessageDialog(Main.mainFrame, "不存在该球员");// 弹出提示
					} else {
						new SonFrame(playerName, SonFrame.playerCard);
					}*/
				}
			}
		}

		public void mouseEntered(MouseEvent e) {
			if (e.getSource().equals(searchButton)) {
				searchButton.setBackground(MyColor.DEEP_ORANGE);
			} else if (e.getSource().equals(advancedSelect)) {
				advancedSelect.setBackground(MyColor.DEEP_ORANGE);
			} else if (e.getSource().equals(findButton)) {
				findButton.setIcon(new ImageIcon("images/players/find_enter.png"));
			}

		}

		public void mouseExited(MouseEvent e) {
			if (e.getSource().equals(searchButton)) {
				searchButton.setBackground(MyColor.MIDDLE_ORANGE);
			} else if (e.getSource().equals(advancedSelect)) {
				advancedSelect.setBackground(MyColor.MIDDLE_ORANGE);
			} else if (e.getSource().equals(findButton)) {
				findButton.setIcon(new ImageIcon("images/players/find_normal.png"));
			}
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {

		}
	}

	protected class AdvancedSelectionJdialog extends JDialog implements MouseListener {
		private MyScrollPanel hasChooseJscrollPane;
		private MyLabel advancedSelectionLabel;
		private MyTable hasChooseTable;
		private MyTableModel hasChooseTableModel;
		private MyButton addItemButton, deletButton;
		private MyButton sureButton, cancelButton;
		private JTextField valueInput;
		private MyComboBox<String> selectionChoose;
		private String selectionItemList[] = { "得分", "篮板", "助攻", "抢断", "盖帽", "总命中率", "三分命中率", "罚球命中率" };
		private String header[] = { "筛选依据", "大于数值" };
		private static final long serialVersionUID = 1L;
		private AdvancedSelectionJdialog advancedSelectionJdialog = this;
		private PlayerInfoBlService playerInfoBl;

		public AdvancedSelectionJdialog() {
			super(Main.mainFrame, true);
			this.setLayout(null);
			this.setUndecorated(true);
			this.setSize((800), (600));
			this.setLocation((NUMBER.SCREEN_WIDTH - this.getWidth()) / 2, (NUMBER.SCREEN_HEIGHT - this.getHeight()) / 2);
			this.createObjects();
			this.setComponentsLocation();
			this.addListener();
			this.setComponentsStyle();
			this.setVisible(true);
		}

		private void createObjects() {
			hasChooseTableModel = new MyTableModel(header);
			hasChooseTable = new MyTable(hasChooseTableModel);
			hasChooseJscrollPane = new MyScrollPanel(hasChooseTable);
			addItemButton = new MyButton("添加筛选项");
			deletButton = new MyButton("移除筛选项");
			sureButton = new MyButton("确认");
			cancelButton = new MyButton("取消");
			advancedSelectionLabel = new MyLabel("高级筛选");
			valueInput = new JTextField("在此输入数值");
			selectionChoose = new MyComboBox<>(selectionItemList);
			playerInfoBl = new PlayerInfoBl();
		}

		private void setComponentsLocation() {
			advancedSelectionLabel.setBounds((0), (0), this.getWidth(), (50));
			selectionChoose.setBounds((20), (70), (200), (35));
			valueInput.setBounds((240), (70), (200), (35));
			cancelButton.setBounds((530), (540), (120), (35));
			sureButton.setBounds((150), (540), (120), (35));
			deletButton.setBounds((630), (70), (120), (35));
			addItemButton.setBounds((490), (70), (120), (35));
			hasChooseJscrollPane.setBounds((20), (130), (760), (400));

			this.add(advancedSelectionLabel);
			this.add(selectionChoose);
			this.add(valueInput);
			this.add(cancelButton);
			this.add(sureButton);
			this.add(deletButton);
			this.add(addItemButton);
			this.add(hasChooseJscrollPane);
		}

		private void addListener() {
			addItemButton.addMouseListener(this);
			deletButton.addMouseListener(this);
			sureButton.addMouseListener(this);
			cancelButton.addMouseListener(this);
			valueInput.addMouseListener(this);
		}

		private void setComponentsStyle() {
			advancedSelectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
			advancedSelectionLabel.setOpaque(true);
			advancedSelectionLabel.setBackground(MyColor.MIDDLE_ORANGE);
			advancedSelectionLabel.setForeground(MyColor.MY_WHITE);
			advancedSelectionLabel.setFont(MyFont.MIDDLE_BOLD);
			valueInput.setOpaque(false);
			valueInput.setFont(MyFont.SMALLEST_BOLD);
			addItemButton.setBackground(MyColor.MIDDLE_ORANGE);
			deletButton.setBackground(MyColor.MIDDLE_ORANGE);
			sureButton.setBackground(MyColor.MIDDLE_ORANGE);
			cancelButton.setBackground(MyColor.MIDDLE_ORANGE);
			addItemButton.setContentAreaFilled(true);
			deletButton.setContentAreaFilled(true);
			sureButton.setContentAreaFilled(true);
			cancelButton.setContentAreaFilled(true);
			hasChooseTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		}

		public void mouseClicked(MouseEvent e) {
			if (e.getSource().equals(cancelButton)) {
				advancedSelectionJdialog.dispose();
			} else if (e.getSource().equals(sureButton)) {

				if (hasChooseTableModel.getRowCount() == 0) {
					JOptionPane.showMessageDialog(advancedSelectionJdialog, "请添加筛选项");// 弹出提示，请添加筛选项
				} else {
					CombineSelectionCell combineSelectionCells[] = new CombineSelectionCell[hasChooseTable.getRowCount()];
					for (int i = 0; i < hasChooseTable.getRowCount(); i++) {
						combineSelectionCells[i] = new CombineSelectionCell();
//						combineSelectionCells[i].setField(changeToSortField((String) hasChooseTableModel.getValueAt(i, 0)));
						combineSelectionCells[i].setNumber(Double.parseDouble((String) hasChooseTableModel.getValueAt(i, 1)));
					}
					clearTable();
//					fillTable(playerInfoBl.getPlayerNormal_avg(combineSelectionCells));
					advancedSelectionJdialog.dispose();
				}
			} else if (e.getSource().equals(addItemButton)) {
				try {
					Double.parseDouble(valueInput.getText());
					String str[] = { (String) selectionChoose.getSelectedItem(), valueInput.getText() };
					hasChooseTableModel.addRow(str);
					hasChooseTable.updateUI();
				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(advancedSelectionJdialog, "输入必须为数");
				}

			} else if (e.getSource().equals(deletButton)) {
				int index = hasChooseTable.getSelectedRow();
				if (index >= 0) {
					hasChooseTableModel.removeRow(index);
					hasChooseTable.updateUI();
				} else {
					JOptionPane.showMessageDialog(advancedSelectionJdialog, "请选中移除行");
				}

			} else if (e.getSource().equals(valueInput)) {
				valueInput.setText("");
				valueInput.setForeground(MyColor.DEEP_COLOR);
			}
		}

/*		private String changeToSortField(String valueAt) {
			String[] sortField = { Field.point, Field.rebound, Field.assist, Field.steal, Field.blockShot, Field.shot, Field.three, Field.penalty };
			for (int i = 0; i < selectionItemList.length; i++) {
				if (valueAt.equals(selectionItemList[i])) {
					return sortField[i];
				}
			}
			return null;
		}*/

		public void mouseEntered(MouseEvent e) {
			if (e.getSource().equals(cancelButton)) {
				cancelButton.setBackground(MyColor.DEEP_ORANGE);
			} else if (e.getSource().equals(sureButton)) {
				sureButton.setBackground(MyColor.DEEP_ORANGE);
			} else if (e.getSource().equals(addItemButton)) {
				addItemButton.setBackground(MyColor.DEEP_ORANGE);
			} else if (e.getSource().equals(deletButton)) {
				deletButton.setBackground(MyColor.DEEP_ORANGE);
			}
		}

		public void mouseExited(MouseEvent e) {
			if (e.getSource().equals(cancelButton)) {
				cancelButton.setBackground(MyColor.MIDDLE_ORANGE);
			} else if (e.getSource().equals(sureButton)) {
				sureButton.setBackground(MyColor.MIDDLE_ORANGE);
			} else if (e.getSource().equals(addItemButton)) {
				addItemButton.setBackground(MyColor.MIDDLE_ORANGE);
			} else if (e.getSource().equals(deletButton)) {
				deletButton.setBackground(MyColor.MIDDLE_ORANGE);
			}
		}

		public void mousePressed(MouseEvent arg0) {

		}

		public void mouseReleased(MouseEvent arg0) {

		}

	}
}
