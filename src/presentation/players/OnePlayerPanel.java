package presentation.players;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
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
import beans.GamePlayer;
import beans.GeneralGame;
import beans.GeneralPlayer;
import beans.SeasonPlayer;
import businesslogic.game.GameInfoBl;
import businesslogic.player.OnePlayerBl;
import businesslogicservice.player.OnePlayerBlService;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;

public class OnePlayerPanel extends MyPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private MyPanel thisPanel = this;
	private MyButton[] button = new MyButton[] { new MyButton("普通数据"), new MyButton("高级数据"), new MyButton("近期比赛") };
	private MyPanel[] panel;
	private GeneralInfoPanel generalInfoPanel;
	private ContentPanel contentPanel;
	private int buttonHeight = 40;
	private int buttonWidth = 400;

	private OnePlayerBlService onePlayerInfoBl = new OnePlayerBl();
	//
	private String playerId;// 球员Id
	private GeneralPlayer PlayerGeneralInfo;// 球员基本信息
	private String[] regularSeasonArray;// 常规赛赛季
	private String[] playOffSeasonArray;// 季后赛赛季
	private int flag = 0;

	public OnePlayerPanel(String playerId) {
		this.playerId = playerId;
		this.PlayerGeneralInfo = onePlayerInfoBl.getGeneralPlayer(playerId);
		this.regularSeasonArray = onePlayerInfoBl.getSeasonsOfPlayer(playerId, GameKind.regular_game);
		this.playOffSeasonArray = onePlayerInfoBl.getSeasonsOfPlayer(playerId, GameKind.playOff_game);
		if (playerId != null && PlayerGeneralInfo != null && regularSeasonArray != null && playOffSeasonArray != null) {
			this.panel = new MyPanel[] { new PlayerNormalInfoPanel(), new PlayerHighInfoPanel(), new GameOfSeasonPanel() };
			this.createObjects();
			this.setComponentsLocation();
			this.setCompStyle();
			this.setVisible(true);
		}
	}

	private void createObjects() {
		generalInfoPanel = new GeneralInfoPanel();
		contentPanel = new ContentPanel();
	}

	private void setComponentsLocation() {
		for (int i = 0; i < 3; i++) {
			button[i].setBounds(50 + buttonWidth * i, 200, buttonWidth, buttonHeight);
			button[i].setBackground(MyColor.MIDDLE_COLOR);
			button[i].setForeground(MyColor.MY_WHITE);
			button[i].setFont(MyFont.SMALL_BOLD);
			button[i].setContentAreaFilled(true);
			button[i].addMouseListener(this);
			this.add(button[i]);
		}
		generalInfoPanel.setLocation(0, 0);
		contentPanel.setBounds(0, 240, NUMBER.FRAME_WIDTH, NUMBER.FRAME_HEIGHT - 280);
		this.add(generalInfoPanel);
		this.add(contentPanel);
	}

	private void setCompStyle() {
		button[flag].setBackground(MyColor.SELECTED);
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
	}

	public void mouseEntered(MouseEvent e) {
		for (int i = 0; i < 3; i++) {
			if (e.getSource().equals(button[i])) {
				button[i].setBackground(MyColor.DEEP_COLOR);
			}
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
			for (int i = 0; i < 3; i++) {
				this.add(panel[i], String.valueOf(i));
			}
			this.setVisible(true);
		}

		public void showMyPanel(int i) {
			this.card.show(contentPanel, String.valueOf(i));
		}
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
			portrait.setBounds(50, 10, 230, 200);
			playerNameText.setBounds(430, 50, 250, 50);
			normalInfoText.setBounds(780, 5, 300, 195);
			for (int i = 0; i < 3; i++) {
				mainMatchInfoText[i].setBounds(430 + i * 65, 150, 65, 50);
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
			portrait.setMyIcon(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE + playerId + ".png"));
			playerNameText.setText(PlayerGeneralInfo.getPlayerName());
			ArrayList<SeasonPlayer> seasonPlayerList = onePlayerInfoBl.getSeasonPlayer(playerId, GameKind.regular_game, DataKind.average, Field.season);
			SeasonPlayer career = seasonPlayerList.get(0);
			mainMatchInfoText[0].setText("得分\n" + career.getPoint());
			mainMatchInfoText[1].setText("篮板\n" + career.getTotRebound());
			mainMatchInfoText[2].setText("助攻\n" + career.getAssist());
			normalInfoText.setText("编号：" + PlayerGeneralInfo.getPlayerId() + "\n位置：" + PlayerGeneralInfo.getPosition() + "\n身高：" + PlayerGeneralInfo.getHeight() + "\n体重："
					+ PlayerGeneralInfo.getWeight() + "\n生日：" + PlayerGeneralInfo.getBirthday() + "\n进入联盟时间：" + PlayerGeneralInfo.getStartYear() + "\n退役或现役时间：" + PlayerGeneralInfo.getFinishYear()
					+ "\n毕业院校：" + PlayerGeneralInfo.getCollage());
		}
	}

	class PlayerNormalInfoPanel extends MyPanel {
		private static final long serialVersionUID = 1L;
		private String[] title = { "赛季", "球队", "场数", "首发", "时间", "得分", "命中率", "篮板", "助攻", "抢断", "盖帽", "失误", "犯规", "前板", "后板", "出手", "命中", "三分出", "三分中", "三分率", "罚球出", "罚球中", "罚中率", };
		private Field[] fields = { Field.season, Field.teamName, Field.numOfGame, Field.numOfStart, Field.minute, Field.point, Field.shot, Field.totRebound, Field.assist, Field.steal, Field.block,
				Field.fault, Field.foul, Field.offRebound, Field.defRebound, Field.totalShot, Field.totalHit, Field.threeShot, Field.threeHit, Field.three, Field.freeShot, Field.freeHit, Field.free };
		private String[] fieldStr = new String[fields.length];
		private MyTableModel model = new MyTableModel(title);
		private MyTable table = new MyTable(model);
		private MyScrollPanel scrollPanel = new MyScrollPanel(table);
		private MyComboBox<Object> dataKindChoose, gameKindChoose, sortFieldChoose;
		private MyButton search;

		public PlayerNormalInfoPanel() {
			for (int i = 0; i < fields.length; i++) {
				fieldStr[i] = fields[i].toString();
			}
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
					int dataKindInt = dataKindChoose.getSelectedIndex();
					int gameKindInt = gameKindChoose.getSelectedIndex();
					int sortFieldInt = sortFieldChoose.getSelectedIndex();
					ArrayList<SeasonPlayer> seasonPlayerList = onePlayerInfoBl.getSeasonPlayer(playerId, GameKind.gameKinds[gameKindInt], DataKind.dataKinds[dataKindInt], fields[sortFieldInt]);
					model.removeAllRows();
					table.updateUI();
					if (seasonPlayerList != null) {
						for (int i = 0; i < seasonPlayerList.size(); i++) {
							Object[] contents = seasonPlayerList.get(i).getSpeContent(fieldStr);
							model.addRow(contents);
							table.updateUI();
						}
					}
				}
			});
		}

		private void setComponentBounds() {
			gameKindChoose.setBounds(360, 10, 150, 30);
			dataKindChoose.setBounds(530, 10, 150, 30);
			sortFieldChoose.setBounds(700, 10, 150, 30);
			search.setBounds(1000, 10, 150, 30);
			scrollPanel.setBounds(30, 50, 1245, 300);
			table.setRowHeight(30);
			table.setTableColumnWidth(0, 65);
			table.setTableColumnWidth(1, 160);
			for (int i = 2; i <= 5; i++) {
				table.setTableColumnWidth(i, 44);
			}
			table.setTableColumnWidth(6, 55);
			for (int i = 7; i <= 16; i++) {
				table.setTableColumnWidth(i, 44);
			}
			for (int i = 17; i <= 22; i++) {
				table.setTableColumnWidth(i, 54);
			}
			this.add(gameKindChoose);
			this.add(dataKindChoose);
			this.add(sortFieldChoose);
			this.add(search);
			this.add(scrollPanel);
		}

		private void initTable() {
			ArrayList<SeasonPlayer> seasonPlayerList = onePlayerInfoBl.getSeasonPlayer(playerId, GameKind.regular_game, DataKind.average, Field.season);
			if (seasonPlayerList != null) {
				for (int i = 0; i < seasonPlayerList.size(); i++) {
					Object[] contents = seasonPlayerList.get(i).getSpeContent(fieldStr);
					model.addRow(contents);
					table.updateUI();
				}
			}
		}
	}

	class PlayerHighInfoPanel extends MyPanel {

		private static final long serialVersionUID = 1L;

		private String[] title = { "赛季", "球队", "效率值", "使用率%", "投篮效率", "真实命中率", "贡献值", "篮板率%", "助攻率%", "抢断率%", "盖帽率%", "失误率%", "前板率%", "后板率%", "替换价值" };

		private Field[] highField = { Field.season, Field.teamName, Field.playerEFF, Field.useEFF, Field.shotEFF, Field.realShot, Field.BoxPM, Field.totReboundEFF, Field.assistEFF, Field.stealEFF,
				Field.blockEFF, Field.faultEFF, Field.offReboundEFF, Field.defReboundEFF, Field.replaceValue };
		private String[] highFieldStr = new String[highField.length];
		private MyTableModel model = new MyTableModel(title);
		private MyTable table = new MyTable(model);
		private MyScrollPanel scrollPanel = new MyScrollPanel(table);
		private MyComboBox<Object> gameKindChoose, sortFieldChoose;
		private MyButton search;

		public PlayerHighInfoPanel() {
			for (int i = 0; i < highField.length; i++) {
				highFieldStr[i] = highField[i].toString();
			}
			gameKindChoose = new MyComboBox<Object>(GameKind.gameKinds);
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
					int gameKindInt = gameKindChoose.getSelectedIndex();
					int sortFieldInt = sortFieldChoose.getSelectedIndex();
					ArrayList<SeasonPlayer> seasonPlayerList = onePlayerInfoBl.getSeasonPlayer(playerId, GameKind.gameKinds[gameKindInt], DataKind.total, highField[sortFieldInt]);
					model.removeAllRows();
					table.updateUI();
					if (seasonPlayerList != null) {
						for (int i = 0; i < seasonPlayerList.size(); i++) {
							Object[] contents = seasonPlayerList.get(i).getSpeContent(highFieldStr);
							model.addRow(contents);
							table.updateUI();
						}
					}
				}
			});
		}

		private void setComponentBounds() {
			gameKindChoose.setBounds(360, 10, 150, 30);
			sortFieldChoose.setBounds(700, 10, 150, 30);
			search.setBounds(1000, 10, 150, 30);
			scrollPanel.setBounds(30, 50, 1245, 300);
			table.setRowHeight(30);
			table.setTableColumnWidth(1, 160);
			table.setTableColumnWidth(5, 85);
			this.add(gameKindChoose);
			this.add(sortFieldChoose);
			this.add(search);
			this.add(scrollPanel);
		}

		private void initTable() {
			ArrayList<SeasonPlayer> seasonPlayerList = onePlayerInfoBl.getSeasonPlayer(playerId, GameKind.regular_game, DataKind.average, Field.season);
			if (seasonPlayerList != null) {
				for (int i = 0; i < seasonPlayerList.size(); i++) {
					Object[] contents = seasonPlayerList.get(i).getSpeContent(highFieldStr);
					model.addRow(contents);
					table.updateUI();
				}
			}
		}
	}

	class GameOfSeasonPanel extends MyPanel {
		private static final long serialVersionUID = 1L;
		private String[] title = { "球队", "日期", "时间", "得分", "篮板", "助攻", "抢断", "盖帽", "失误", "犯规", "命中", "出手", "三分中", "三分出", "罚球中", "罚球出", "前板", "后板" };
		private Field[] gameField = { Field.teamName, Field.date, Field.minute, Field.point, Field.totRebound, Field.assist, Field.steal, Field.block, Field.fault, Field.foul, Field.totHit,
				Field.totShot, Field.threeHit, Field.threeShot, Field.freeHit, Field.freeShot, Field.offRebound, Field.defRebound };
		private String[] gameFieldStr = new String[gameField.length];
		private MyTableModel model = new MyTableModel(title);
		private MyTable table = new MyTable(model);
		private MyScrollPanel scrollPanel = new MyScrollPanel(table);
		private MyComboBox<Object> gameKindChoose, seasonChoose, sortFieldChoose;
		private MyButton search;

		public GameOfSeasonPanel() {
			for (int i = 0; i < gameField.length; i++) {
				gameFieldStr[i] = gameField[i].toString();
			}
			gameKindChoose = new MyComboBox<Object>(GameKind.gameKinds);
			seasonChoose = new MyComboBox<Object>(regularSeasonArray);
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
			gameKindChoose.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED || e.getSource().equals(gameKindChoose)) {
						if (gameKindChoose.getSelectedIndex() == 0) {
							seasonChoose.removeAllItems();
							if (regularSeasonArray != null) {
								for (int i = 0; i < regularSeasonArray.length; i++) {
									seasonChoose.addItem(regularSeasonArray[i]);
								}
							}
						}
						else if (gameKindChoose.getSelectedIndex() == 1) {
							seasonChoose.removeAllItems();
							if (playOffSeasonArray != null) {
								for (int i = 0; i < playOffSeasonArray.length; i++) {
									seasonChoose.addItem(playOffSeasonArray[i]);
								}
							}
						}
					}
				}
			});
			this.setVisible(true);
		}

		private void setComponentBounds() {
			table.setRowHeight(30);
			table.setTableColumnWidth(0, 175);
			table.setTableColumnWidth(1, 90);
			for (int i = 2; i <= 17; i++) {
				table.setTableColumnWidth(i, 60);
			}
			gameKindChoose.setBounds(360, 10, 150, 30);
			seasonChoose.setBounds(530, 10, 150, 30);
			sortFieldChoose.setBounds(700, 10, 150, 30);
			search.setBounds(1000, 10, 150, 30);
			scrollPanel.setBounds(30, 50, 1245, 300);
			this.add(gameKindChoose);
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
					int gameKindInt = gameKindChoose.getSelectedIndex();
					String seasonStr = (String) seasonChoose.getSelectedItem();
					int sortFieldInt = sortFieldChoose.getSelectedIndex();
					ArrayList<GamePlayer> gamePlayerList = onePlayerInfoBl.getGamePlayer(playerId, Season.getSeason(seasonStr), GameKind.gameKinds[gameKindInt], gameField[sortFieldInt]);
					model.removeAllRows();
					table.updateUI();
					if (gamePlayerList != null) {
						for (int i = 0; i < gamePlayerList.size(); i++) {
							Object[] contents = gamePlayerList.get(i).getSpeContent(gameFieldStr);
							model.addRow(contents);
							table.updateUI();
						}
					}
				}
			});
		}

		private void initTable() {
			ArrayList<GamePlayer> gamePlayerList = onePlayerInfoBl.getGamePlayer(playerId, Season.getSeason(regularSeasonArray[0]), GameKind.regular_game, Field.date);
			if (gamePlayerList != null) {
				for (int i = 0; i < gamePlayerList.size(); i++) {
					Object[] contents = gamePlayerList.get(i).getSpeContent(gameFieldStr);
					model.addRow(contents);
					table.updateUI();
				}
			}
		}
	}
}
