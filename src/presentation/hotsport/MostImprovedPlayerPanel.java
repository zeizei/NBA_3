package presentation.hotsport;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import presentation.SonFrame;
import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.mycomponent.MyTextArea;
import presentation.statics.MyColor;
import presentation.statics.MyFont;
import presentation.statics.PathOfFile;
import beans.generalTeam;
import businesslogic.game.GameInfoBl;
import businesslogic.hot.PlayerHotBl;
import businesslogic.team.TeamInfoBl;
import businesslogicservice.hot.PlayerHotBlSrevice;
import common.datastructure.PlayerHotInfo;
import common.statics.Field;
import common.statics.Season;

public class MostImprovedPlayerPanel extends MyPanel implements MouseListener {

	private String[] fieldString = new String[] { Field.point.toString(), Field.totRebound.toString(), Field.assist.toString(), Field.steal.toString(), Field.block.toString() };
	private Field[] mostImproveField = new Field[] { Field.point, Field.totRebound, Field.assist, Field.steal, Field.block };
	private final int labelHeight = 100;
	private final int labelWidth = 180;
	private final int buttonWidth = 180;
	private final int buttonHeight = 50;
	private final int buttonY = 113;
	private final int labelY = 80;
	private MyButton[] fieldButton = new MyButton[5];// 属性按钮
	private MyLabel[] number = new MyLabel[5];// 排名
	private MyLabel[] Portrait = new MyLabel[5];// 头像
	private MyTextArea[] nameAndPosition = new MyTextArea[5];// 姓名和位置
	private MyLabel[] upgrade = new MyLabel[5];// 近五场提升率
	private MyLabel[] value = new MyLabel[5];// 属性值
	private MyLabel[] team = new MyLabel[5];// 球队
	private MyLabel[] type = new MyLabel[5];// 标识
	private PlayerHotBlSrevice playerHotBl = new PlayerHotBl();
	private ArrayList<PlayerHotInfo> playerHotList = new ArrayList<PlayerHotInfo>(5);
	private String typeString[] = { "头像", "姓名/位置", "数据", "球队", "提升率" };
	private int flag = 0;

	private static final long serialVersionUID = 1L;

	public MostImprovedPlayerPanel() {
		this.setLayout(null);
		this.createObjects();
		this.setComponentsLocation();
		this.setComponentsStyle();
		this.addListener();
		this.setVisible(true);
//		this.init();
	}

	private void createObjects() {
		for (int i = 0; i < 5; i++) {
			fieldButton[i] = new MyButton(fieldString[i]);
			number[i] = new MyLabel(String.valueOf(i + 1));
			upgrade[i] = new MyLabel();
			Portrait[i] = new MyLabel();
			nameAndPosition[i] = new MyTextArea();
			value[i] = new MyLabel();
			team[i] = new MyLabel();
			type[i] = new MyLabel(typeString[i]);
		}
	}

	private void setComponentsLocation() {
		for (int i = 0; i < 5; i++) {
			type[i].setBounds(100 + labelWidth * i, 10, labelWidth, 50);
			type[i].setFont(MyFont.SMALL_BOLD);
			fieldButton[i].setBounds(940, buttonY + i * buttonHeight, buttonWidth, buttonHeight);
			this.add(fieldButton[i]);
		}
		for (int i = 0; i < 5; i++) {
			number[i].setBounds(20, labelHeight * i + labelY, 50, 60);
			Portrait[i].setBounds(100, labelHeight * i + labelY, 100, 75);
			nameAndPosition[i].setBounds(260, labelHeight * i + labelY + 18, 200, (60));
			value[i].setBounds(460, labelHeight * i + labelY, (80), (80));
			team[i].setBounds(640, labelHeight * i + labelY, 70, 70);
			upgrade[i].setBounds(820, labelHeight * i + labelY, 100, (80));
		}
		for (int i = 0; i < 5; i++) {
			this.add(number[i]);
			this.add(Portrait[i]);
			this.add(nameAndPosition[i]);
			this.add(value[i]);
			this.add(team[i]);
			this.add(upgrade[i]);
			this.add(type[i]);
		}
	}

	private void setComponentsStyle() {
		for (int i = 0; i < 5; i++) {
			number[i].setHorizontalAlignment(SwingConstants.CENTER);
			number[i].setFont(MyFont.LARGE_BOLD);
			value[i].setFont(MyFont.MIDDLE_BOLD);
			nameAndPosition[i].setForeground(MyColor.DEEP_COLOR);
			upgrade[i].setFont(MyFont.MIDDLE_BOLD);
			value[i].setFont(MyFont.MIDDLE_BOLD);
			nameAndPosition[i].setFont(MyFont.SMALLEST_BOLD);
		}
		for (int i = 0; i < 5; i++) {
			fieldButton[i].setContentAreaFilled(true);
			fieldButton[i].setBackground(MyColor.MIDDLE_COLOR);
		}
		fieldButton[flag].setBackground(MyColor.SELECTED);
	}

	private void addListener() {
		for (int i = 0; i < 5; i++) {
			fieldButton[i].addMouseListener(this);
			Portrait[i].addMouseListener(this);
			team[i].addMouseListener(this);
		}
	}

	private void init() {
		playerHotList = this.playerHotBl.getPlayerHot(Season.this_season, Field.point);
		this.setContent();
	}

	private void setContent() {
		if (playerHotList != null) {
			for (int i = 0; i < playerHotList.size(); i++) {
				PlayerHotInfo temp = this.playerHotList.get(i);
				Portrait[i].setMyIcon(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE + temp.getPlayerId() + ".png"));
				upgrade[i].setText(String.valueOf(temp.getUpgradeRate()));
				value[i].setText(String.valueOf(temp.getValue()));
				nameAndPosition[i].setText(temp.getPlayerName() + "\n" + temp.getPosition());
				String teamName = temp.getTeamName();
				ImageIcon teamIcon = null;
				generalTeam generalTeam = new TeamInfoBl().getGeneralTeam(teamName, Season.this_season);
				if (generalTeam != null) {
					teamIcon = new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + generalTeam.getImgName() + ".png");
				}
				else {
					teamIcon = new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + "NBA.png");
				}
				if (teamIcon != null) {
					team[i].setMyIcon(teamIcon);
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < 5; i++) {
			if (e.getSource().equals(fieldButton[i])) {
				flag = i;
				for (int j = 0; j < 5; j++) {
					fieldButton[j].setBackground(MyColor.MIDDLE_COLOR);
				}
				fieldButton[i].setBackground(MyColor.SELECTED);
				this.playerHotList = this.playerHotBl.getPlayerHot(Season.this_season, mostImproveField[i]);
				this.setContent();
				HotSportPanel.showRefreshed();
				String date = new GameInfoBl().getLatestDate();
				HotSportPanel.refreshDate(date);
				break;
			}
			if (e.getSource().equals(Portrait[i])) {
				String playerName = playerHotList.get(i).getPlayerId();
				new SonFrame(playerName, SonFrame.playerCard);
				break;
			}
			if (e.getSource().equals(team[i])) {
				String teamName = playerHotList.get(i).getTeamName();
				new SonFrame(teamName, SonFrame.teamCard);
				break;
			}
		}

	}

	public void mouseEntered(MouseEvent e) {
		for (int i = 0; i < 5; i++) {
			if (e.getSource().equals(fieldButton[i])) {
				fieldButton[i].setBackground(MyColor.DEEP_COLOR);
				break;
			}
			if (e.getSource().equals(Portrait[i])) {
				Portrait[i].setLocation(Portrait[i].getX() - (3), Portrait[i].getY() - (3));
				break;
			}
			if (e.getSource().equals(team[i])) {
				team[i].setLocation(team[i].getX() - (3), team[i].getY() - (3));
				break;
			}
		}

	}

	public void mouseExited(MouseEvent e) {
		for (int i = 0; i < 5; i++) {
			if (e.getSource().equals(fieldButton[i])) {
				if (flag == i) {
					fieldButton[i].setBackground(MyColor.SELECTED);
				}
				else {
					fieldButton[i].setBackground(MyColor.MIDDLE_COLOR);
				}
				break;
			}
			if (e.getSource().equals(Portrait[i])) {
				Portrait[i].setLocation(Portrait[i].getX() + (3), Portrait[i].getY() + (3));
				break;
			}
			if (e.getSource().equals(team[i])) {
				team[i].setLocation(team[i].getX() + (3), team[i].getY() + (3));
				break;
			}
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

}
