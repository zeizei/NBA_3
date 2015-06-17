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
import presentation.statics.MyColor;
import presentation.statics.MyFont;
import presentation.statics.PathOfFile;
import beans.generalTeam;
import businesslogic.hot.TeamHotBl;
import businesslogic.team.TeamInfoBl;
import businesslogicservice.hot.TeamHotBlService;

import common.datastructure.TeamHotInfo;
import common.statics.Field;
import common.statics.Number;
import common.statics.Season;

public class SeasonTeamKingPanel extends MyPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyButton[] fieldButton = new MyButton[8];// 属性按钮
	private String[] fieldString = new String[] { "得分", "篮板", "助攻", "抢断", "盖帽", "总命中率", "三分命中率", "罚球命中率" };
	private Field[] seasonTeamKingField = { Field.point, Field.totRebound, Field.assist, Field.steal, Field.block, Field.shot, Field.three, Field.free };
	private final int labelHeight = 100;
	private final int labelWidth = 220;
	private final int buttonWidth = 180;
	private final int buttonHeight = 50;
	private final int buttonY = 113;
	private final int labelY = 80;
	private MyLabel[] number = new MyLabel[Number.defaut_hot_num];// 序号
	private MyLabel[] logo = new MyLabel[Number.defaut_hot_num];// 队标
	private MyLabel[] team = new MyLabel[Number.defaut_hot_num];// 球队名称
	private MyLabel[] value = new MyLabel[Number.defaut_hot_num];// 属性值
	private MyLabel[] league = new MyLabel[Number.defaut_hot_num];// 联盟
	private TeamHotBlService teamHotBl = new TeamHotBl();
	private ArrayList<TeamHotInfo> teamHotInfo = new ArrayList<TeamHotInfo>(Number.defaut_hot_num);
	private MyLabel[] type = new MyLabel[Number.defaut_hot_num];// 标识
	private String typeString[] = { "队标", "队名", "联盟", "数据" };
	private int flag = 0;

	public SeasonTeamKingPanel() {
		this.setLayout(null);
		this.createObjects();
		this.setComponentsLocation();
		this.setComponentsStyle();
		this.addListener();
		this.setVisible(true);
		this.init();
	}

	private void createObjects() {
		for (int i = 0; i < Number.defaut_hot_num; i++) {
			number[i] = new MyLabel(String.valueOf(i + 1));
			logo[i] = new MyLabel();
			team[i] = new MyLabel();
			value[i] = new MyLabel();
			league[i] = new MyLabel();
		}
		for (int i = 0; i < 8; i++) {
			fieldButton[i] = new MyButton(fieldString[i]);
		}
		for (int i = 0; i < 4; i++) {
			type[i] = new MyLabel(typeString[i]);
		}
	}

	private void setComponentsLocation() {
		for (int i = 0; i < 8; i++) {
			fieldButton[i].setBounds(940, buttonY + i * buttonHeight, buttonWidth, buttonHeight);
			this.add(fieldButton[i]);
		}
		for (int i = 0; i < Number.defaut_hot_num; i++) {
			number[i].setBounds(20, labelHeight * i + labelY, 50, 60);
			logo[i].setBounds(120, labelHeight * i + labelY, 80, 80);
			team[i].setBounds(350, labelHeight * i + labelY + 10, 200, (60));
			league[i].setBounds(560, labelHeight * i + labelY, (80), (80));
			value[i].setBounds(770, labelHeight * i + labelY, 100, (80));
		}
		for (int i = 0; i < Number.defaut_hot_num; i++) {
			this.add(number[i]);
			this.add(logo[i]);
			this.add(team[i]);
			this.add(league[i]);
			this.add(value[i]);
		}
		for (int i = 0; i < 4; i++) {
			type[i].setBounds(130 + labelWidth * i, 10, labelWidth, 50);
			type[i].setFont(MyFont.SMALL_BOLD);
			this.add(type[i]);
		}
	}

	private void setComponentsStyle() {
		for (int i = 0; i < Number.defaut_hot_num; i++) {
			number[i].setHorizontalAlignment(SwingConstants.CENTER);
			number[i].setFont(MyFont.LARGE_BOLD);
			value[i].setFont(MyFont.MIDDLE_BOLD);
			team[i].setFont(MyFont.SMALLEST_BOLD);
			league[i].setFont(MyFont.MIDDLE_BOLD);
		}

		for (int i = 0; i < 8; i++) {
			fieldButton[i].setContentAreaFilled(true);
			fieldButton[i].setBackground(MyColor.MIDDLE_COLOR);
		}
		fieldButton[flag].setBackground(MyColor.SELECTED);
	}

	private void addListener() {
		for (int i = 0; i < 8; i++) {
			fieldButton[i].addMouseListener(this);

		}
		for (int i = 0; i < Number.defaut_hot_num; i++) {
			logo[i].addMouseListener(this);
		}

	}

	private void init() {
		this.teamHotInfo = this.teamHotBl.getTeamHot(Season.this_season, Field.point);
		this.setContent();
	}

	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < 8; i++) {
			if (e.getSource().equals(fieldButton[i])) {
				flag = i;
				for (int j = 0; j < 8; j++) {
					fieldButton[j].setBackground(MyColor.MIDDLE_COLOR);
				}
				fieldButton[i].setBackground(MyColor.SELECTED);
				this.teamHotInfo = this.teamHotBl.getTeamHot(Season.this_season, seasonTeamKingField[i]);
				this.setContent();
				break;
			}
		}
		for (int i = 0; i < Number.defaut_hot_num; i++) {
			if (e.getSource().equals(logo[i])) {
				String teamName = this.teamHotInfo.get(i).getTeamName();
				generalTeam generalTeam = new TeamInfoBl().getGeneralTeam(teamName, Season.this_season);
				new SonFrame(generalTeam, SonFrame.teamCard);
				break;
			}
		}

	}

	private void setContent() {
		if (teamHotInfo != null) {
			for (int i = 0; i < teamHotInfo.size(); i++) {
				TeamHotInfo temp = this.teamHotInfo.get(i);
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
					logo[i].setMyIcon(teamIcon);
				}
				team[i].setText(temp.getTeamName());
				team[i].setForeground(MyColor.MY_WHITE);
				value[i].setText(String.valueOf(temp.getValue()));
				value[i].setForeground(MyColor.MY_WHITE);
				league[i].setText(temp.getLeague());
				league[i].setForeground(MyColor.MY_WHITE);
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
		for (int i = 0; i < 8; i++) {
			if (e.getSource().equals(fieldButton[i])) {
				fieldButton[i].setBackground(MyColor.DEEP_COLOR);
				;
				break;
			}

		}
		for (int i = 0; i < Number.defaut_hot_num; i++) {
			if (e.getSource().equals(logo[i])) {
				logo[i].setLocation(logo[i].getX() - (3), logo[i].getY() - (3));
				break;
			}
		}
	}

	public void mouseExited(MouseEvent e) {
		for (int i = 0; i < 8; i++) {
			if (e.getSource().equals(fieldButton[i])) {
				if (flag == i) {
					fieldButton[i].setBackground(MyColor.SELECTED);
				}
				else {
					fieldButton[i].setBackground(MyColor.MIDDLE_COLOR);
				}
				break;
			}
		}
		for (int i = 0; i < Number.defaut_hot_num; i++) {
			if (e.getSource().equals(logo[i])) {
				logo[i].setLocation(logo[i].getX() + (3), logo[i].getY() + (3));
				break;
			}
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

}
