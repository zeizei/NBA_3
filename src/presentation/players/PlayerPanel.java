package presentation.players;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
import presentation.statics.Method;
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
import common.statics.MathOperation;
import common.statics.Position;
import common.statics.Season;

public class PlayerPanel extends MyPanel {
	private SelectionPanel selectionPanel;
	private JScrollPane playerShowPane;
	private MyTable playerShowTable, rangeAndNameTable;
	private MyTableModel playerTableModel, rangeAndNameTableModel;
	private static final long serialVersionUID = 1L;
	private String fieldList[] = { "teamName", "age", "position", "numOfGame", "numOfStart", "minute", "point", "shot", "totRebound", "assist", "steal", "block", "fault", "foul", "totalHit",
			"totalShot", "threeHit", "threeShot", "three", "twoShot", "twoHit", "two", "shotEFF", "freeHit", "freeShot", "free", "offRebound", "defRebound", "playerEFF", "realShot", "threeEFF",
			"freeEFF", "offReboundEFF", "defReboundEFF", "totReboundEFF", "assistEFF", "stealEFF", "blockEFF", "faultEFF", "useEFF", "offWinShare", "defWinShare", "winShare", "winSharePer48",
			"offBoxPM", "defBoxPM", "BoxPM", "replaceValue" };
	private String identity[] = { "rank", "portrait", "playerId", "name" };
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
		ArrayList<SeasonPlayer> playerNormalList = this.playerInfoBl.getSeasonPlayer(Season.seasons[1], GameKind.regular_game, DataKind.average, League.all_league, Position.All, AgeRange.All,
				Field.point);
		this.fillTable(playerNormalList);
	}

	private void fillTable(ArrayList<SeasonPlayer> seasonPlayerList) {
		if (seasonPlayerList != null) {
			for (int i = 0; i < seasonPlayerList.size(); i++) {
				Object performRow[] = seasonPlayerList.get(i).getSpeContent(fieldList);
				Object infoRow[] = { String.valueOf(i + 1), Method.changeSize(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE + seasonPlayerList.get(i).getPlayerId() + ".png"), 60, 50),
						seasonPlayerList.get(i).getPlayerId(), seasonPlayerList.get(i).getPlayerName() };
				playerTableModel.addRow(performRow);
				rangeAndNameTableModel.addRow(infoRow);
			}
			playerShowTable.updateUI();
			rangeAndNameTable.updateUI();
		}
	}

	private void clearTable() {
		playerTableModel.removeAllRows();
		rangeAndNameTableModel.removeAllRows();
		playerShowTable.updateUI();
		rangeAndNameTable.updateUI();
	}

	private void createObjects() {
		playerTableModel = new MyTableModel(fieldList);
		rangeAndNameTableModel = new MyTableModel(identity);
		playerShowTable = new MyTable(playerTableModel);
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
		rangeAndNameTable.setTableColumnWidth(0, 50);
		rangeAndNameTable.setTableColumnWidth(1, 50);
		rangeAndNameTable.setTableColumnWidth(2, 80);
		rangeAndNameTable.setTableColumnWidth(3, 120);
		playerShowPane.getViewport().setOpaque(false);
		playerShowPane.setOpaque(false);
		playerShowPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		playerShowTable.setAllTableColumnWidth(90);
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
			}
			else {
				rangeAndNameTable.setRowSelectionInterval(selectedIndex, selectedIndex);
			}
		}
	}

	class SelectionPanel extends MyPanel implements MouseListener {
		private int Jcombobox_x = 20;
		private int Jcombobox_y = 60;
		private int Jcombobox_gap = 70;
		private int width = 130;
		private int height = 40;
		private static final long serialVersionUID = 1L;
		private JButton advancedSelect, searchButton, findButton;
		private JTextField playerInput;
		private MyComboBox<Object> seasonChoose, gameKindChoose, dataKindChoose, sortFieldChoose, leagueChoose, positionChoose, ageRangeChoose;

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
			//
			ageRangeChoose = new MyComboBox<Object>(AgeRange.ageRanges);
			positionChoose = new MyComboBox<Object>(Position.positions);
			leagueChoose = new MyComboBox<Object>(League.leagues);
			sortFieldChoose = new MyComboBox<Object>(Field.player_sort_fields);
			dataKindChoose = new MyComboBox<Object>(DataKind.dataKinds);
			seasonChoose = new MyComboBox<Object>(Season.seasons);
			gameKindChoose = new MyComboBox<Object>(GameKind.gameKinds);
		}

		private void setComponentsLocation() {
			seasonChoose.setBounds(Jcombobox_x, Jcombobox_y, width, height);
			gameKindChoose.setBounds(Jcombobox_x + 150, Jcombobox_y, width, height);
			dataKindChoose.setBounds(Jcombobox_x, Jcombobox_y + Jcombobox_gap, width, height);
			leagueChoose.setBounds(Jcombobox_x + 150, Jcombobox_y + Jcombobox_gap, width, height);
			ageRangeChoose.setBounds(Jcombobox_x + 10, Jcombobox_y + Jcombobox_gap * 2, width * 2, height);
			positionChoose.setBounds(Jcombobox_x, Jcombobox_y + Jcombobox_gap * 3, width, height);
			sortFieldChoose.setBounds(Jcombobox_x + 150, Jcombobox_y + Jcombobox_gap * 3, width, height);
			searchButton.setBounds(Jcombobox_x, Jcombobox_y + Jcombobox_gap * 4, 120, 40);
			advancedSelect.setBounds(Jcombobox_x + 140, Jcombobox_y + Jcombobox_gap * 4, 120, 40);
			findButton.setBounds(Jcombobox_x + 190, 500, 35, 35);
			playerInput.setBounds(Jcombobox_x, 500, 180, 35);
			this.add(seasonChoose);
			this.add(gameKindChoose);
			this.add(positionChoose);
			this.add(leagueChoose);
			this.add(ageRangeChoose);
			this.add(advancedSelect);
			this.add(sortFieldChoose);
			this.add(searchButton);
			this.add(findButton);
			this.add(playerInput);
			this.add(dataKindChoose);
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
				int gameKindInt = gameKindChoose.getSelectedIndex();
				int ageRangeInt = ageRangeChoose.getSelectedIndex();
				int dataKindInt = dataKindChoose.getSelectedIndex();
				int leagueInt = leagueChoose.getSelectedIndex();
				int positionInt = positionChoose.getSelectedIndex();
				int sortFieldInt = sortFieldChoose.getSelectedIndex();
				//
				ArrayList<SeasonPlayer> seasonPlayerList = playerInfoBl.getSeasonPlayer(Season.seasons[seasonInt], GameKind.gameKinds[gameKindInt], DataKind.dataKinds[dataKindInt],
						League.leagues[leagueInt], Position.positions[positionInt], AgeRange.ageRanges[ageRangeInt], Field.player_sort_fields[sortFieldInt]);
				clearTable();
				fillTable(seasonPlayerList);
			}
			else if (e.getSource().equals(advancedSelect)) {
				new AdvancedSelectionJdialog();
			}
			else if (e.getSource().equals(findButton)) {
				findButton.setIcon(new ImageIcon("images/players/find_press.png"));
				String playerName = playerInput.getText();
				if (playerName.equals("") || playerName == null) {
					JOptionPane.showMessageDialog(Main.mainFrame, "请输入要查找的球员");// 弹出提示
				}
				else {
					ArrayList<SeasonPlayer> seasonPlayerList = playerInfoBl.vagueSearch(playerName);
					if (seasonPlayerList == null || seasonPlayerList.size() == 0) {
						JOptionPane.showMessageDialog(Main.mainFrame, "没有找到任何相关球员信息");// 弹出提示
					}
					else {
						clearTable();
						fillTable(seasonPlayerList);
					}
				}
			}
		}

		public void mouseEntered(MouseEvent e) {
			if (e.getSource().equals(searchButton)) {
				searchButton.setBackground(MyColor.DEEP_ORANGE);
			}
			else if (e.getSource().equals(advancedSelect)) {
				advancedSelect.setBackground(MyColor.DEEP_ORANGE);
			}
			else if (e.getSource().equals(findButton)) {
				findButton.setIcon(new ImageIcon("images/players/find_enter.png"));
			}
		}

		public void mouseExited(MouseEvent e) {
			if (e.getSource().equals(searchButton)) {
				searchButton.setBackground(MyColor.MIDDLE_ORANGE);
			}
			else if (e.getSource().equals(advancedSelect)) {
				advancedSelect.setBackground(MyColor.MIDDLE_ORANGE);
			}
			else if (e.getSource().equals(findButton)) {
				findButton.setIcon(new ImageIcon("images/players/find_normal.png"));
			}
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {

		}
	}

	class AdvancedSelectionJdialog extends JDialog implements MouseListener {
		private static final long serialVersionUID = 1L;
		private int Jcombobox_x = 50;
		private int Jcombobox_y = 60;
		private int Jcombobox_gap = 70;
		private int width = 180;
		private int height = 40;
		private MyScrollPanel hasChooseJscrollPane;
		private MyLabel advancedSelectionLabel;
		private MyTable hasChooseTable;
		private MyTableModel hasChooseTableModel;
		private MyButton addItemButton, deletButton;
		private MyButton sureButton, cancelButton;
		private JTextField valueInput;
		private MyComboBox<Object> fieldChoose, operationChoose, seasonChoose, gameKindChoose, dataKindChoose, leagueChoose, positionChoose, ageRangeChoose;
		private String header[] = { "field", "operation", "number" };
		private AdvancedSelectionJdialog advancedSelectionJdialog = this;
		private HashMap<String, CombineSelectionCell> selectedFields = new HashMap<String, CombineSelectionCell>();

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
			addItemButton = new MyButton("addItem");
			deletButton = new MyButton("deleteItem");
			sureButton = new MyButton("sure");
			cancelButton = new MyButton("cancle");
			advancedSelectionLabel = new MyLabel("Advanced selection");
			valueInput = new JTextField("input a value");
			fieldChoose = new MyComboBox<Object>(Field.player_advanced_selection_fields);
			operationChoose = new MyComboBox<>(MathOperation.operatons);
			//
			ageRangeChoose = new MyComboBox<Object>(AgeRange.ageRanges);
			positionChoose = new MyComboBox<Object>(Position.positions);
			leagueChoose = new MyComboBox<Object>(League.leagues);
			dataKindChoose = new MyComboBox<Object>(DataKind.dataKinds);
			seasonChoose = new MyComboBox<Object>(Season.seasons);
			gameKindChoose = new MyComboBox<Object>(GameKind.gameKinds);
		}

		private void setComponentsLocation() {
			seasonChoose.setBounds(Jcombobox_x, Jcombobox_y, width, height);
			gameKindChoose.setBounds(Jcombobox_x + 250, Jcombobox_y, width, height);
			dataKindChoose.setBounds(Jcombobox_x + 500, Jcombobox_y, width, height);
			ageRangeChoose.setBounds(Jcombobox_x, Jcombobox_y + Jcombobox_gap, width, height);
			leagueChoose.setBounds(Jcombobox_x + 250, Jcombobox_y + Jcombobox_gap, width, height);
			positionChoose.setBounds(Jcombobox_x + 500, Jcombobox_y + Jcombobox_gap, width, height);
			//
			advancedSelectionLabel.setBounds((0), (0), this.getWidth(), (50));
			fieldChoose.setBounds(20, 200, 120, 35);
			operationChoose.setBounds(170, 200, 120, 35);
			valueInput.setBounds((320), (200), (120), (35));
			cancelButton.setBounds((530), (540), (120), (35));
			sureButton.setBounds((150), (540), (130), (35));
			deletButton.setBounds((630), (200), (130), (35));
			addItemButton.setBounds((490), (200), (120), (35));
			hasChooseJscrollPane.setBounds((20), (250), (760), (280));
			this.add(seasonChoose);
			this.add(gameKindChoose);
			this.add(positionChoose);
			this.add(leagueChoose);
			this.add(ageRangeChoose);
			this.add(dataKindChoose);
			//
			this.add(advancedSelectionLabel);
			this.add(fieldChoose);
			this.add(operationChoose);
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
			}
			else if (e.getSource().equals(sureButton)) {
				if (hasChooseTableModel.getRowCount() == 0) {
					JOptionPane.showMessageDialog(advancedSelectionJdialog, "请添加筛选项");// 弹出提示，请添加筛选项
				}
				else {
					ArrayList<CombineSelectionCell> cellList = new ArrayList<CombineSelectionCell>();
					for (Entry<String, CombineSelectionCell> temp : selectedFields.entrySet()) {
						cellList.add(temp.getValue());
					}
					int seasonInt = seasonChoose.getSelectedIndex();
					int gameKindInt = gameKindChoose.getSelectedIndex();
					int ageRangeInt = ageRangeChoose.getSelectedIndex();
					int dataKindInt = dataKindChoose.getSelectedIndex();
					int leagueInt = leagueChoose.getSelectedIndex();
					int positionInt = positionChoose.getSelectedIndex();
					//
					ArrayList<SeasonPlayer> seasonPlayerList = playerInfoBl.getSeasonPlayer(Season.seasons[seasonInt], GameKind.gameKinds[gameKindInt], DataKind.dataKinds[dataKindInt],
							League.leagues[leagueInt], Position.positions[positionInt], AgeRange.ageRanges[ageRangeInt], cellList);
					clearTable();
					fillTable(seasonPlayerList);
					advancedSelectionJdialog.dispose();
				}
			}// 确认搜索
			else if (e.getSource().equals(addItemButton)) {
				boolean isNum = false;
				double number = 0;
				try {
					number = Double.parseDouble(valueInput.getText());
					isNum = true;

				} catch (NumberFormatException a) {
					isNum = false;
					JOptionPane.showMessageDialog(advancedSelectionJdialog, "输入必须为数");
				}
				if (isNum) {
					int fieldInt = fieldChoose.getSelectedIndex();
					if (selectedFields.containsKey(Field.player_advanced_selection_fields[fieldInt].toString())) {
						JOptionPane.showMessageDialog(advancedSelectionJdialog, "该筛选项已存在");
					}
					else {
						int operationInt = operationChoose.getSelectedIndex();
						String str[] = { fieldChoose.getSelectedItem().toString(), operationChoose.getSelectedItem().toString(), valueInput.getText() };
						CombineSelectionCell cell = new CombineSelectionCell();
						cell.setField(Field.player_advanced_selection_fields[fieldInt]);
						cell.setOperation(MathOperation.operatons[operationInt]);
						cell.setNumber(number);
						hasChooseTableModel.addRow(str);
						hasChooseTable.updateUI();
						selectedFields.put(Field.player_advanced_selection_fields[fieldInt].toString(), cell);
					}
				}
			}// 添加一行
			else if (e.getSource().equals(deletButton)) {
				int index = hasChooseTable.getSelectedRow();
				if (index >= 0) {
					selectedFields.remove(hasChooseTable.getValueAt(index, 0));
					hasChooseTableModel.removeRow(index);
					hasChooseTable.updateUI();
				}
				else {
					JOptionPane.showMessageDialog(advancedSelectionJdialog, "请选中移除行");
				}
			}// 删除一行
			else if (e.getSource().equals(valueInput)) {
				valueInput.setText("");
				valueInput.setForeground(MyColor.DEEP_COLOR);
			}
		}

		public void mouseEntered(MouseEvent e) {
			if (e.getSource().equals(cancelButton)) {
				cancelButton.setBackground(MyColor.DEEP_ORANGE);
			}
			else if (e.getSource().equals(sureButton)) {
				sureButton.setBackground(MyColor.DEEP_ORANGE);
			}
			else if (e.getSource().equals(addItemButton)) {
				addItemButton.setBackground(MyColor.DEEP_ORANGE);
			}
			else if (e.getSource().equals(deletButton)) {
				deletButton.setBackground(MyColor.DEEP_ORANGE);
			}
		}

		public void mouseExited(MouseEvent e) {
			if (e.getSource().equals(cancelButton)) {
				cancelButton.setBackground(MyColor.MIDDLE_ORANGE);
			}
			else if (e.getSource().equals(sureButton)) {
				sureButton.setBackground(MyColor.MIDDLE_ORANGE);
			}
			else if (e.getSource().equals(addItemButton)) {
				addItemButton.setBackground(MyColor.MIDDLE_ORANGE);
			}
			else if (e.getSource().equals(deletButton)) {
				deletButton.setBackground(MyColor.MIDDLE_ORANGE);
			}
		}

		public void mousePressed(MouseEvent arg0) {

		}

		public void mouseReleased(MouseEvent arg0) {

		}
	}
}
