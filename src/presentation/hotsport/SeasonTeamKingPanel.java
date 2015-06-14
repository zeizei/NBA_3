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
import businesslogic.game.GameInfoBl;
import businesslogic.hot.TeamHotBl;
import businesslogicservice.hot.TeamHotBlService;
import common.datastructure.TeamHotInfo;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;

public class SeasonTeamKingPanel extends MyPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyButton[] fieldButton = new MyButton[8];// 属性按钮
	private String[] fieldString = new String[] { Field.point.toString(), Field.totRebound.toString(), Field.assist.toString(), Field.steal.toString(), Field.block.toString(), Field.shot.toString(),
			Field.three.toString(), Field.free.toString() };
	private final int labelHeight = 100;
	private final int labelWidth = 220;
	private final int buttonWidth = 180;
	private final int buttonHeight = 50;
	private final int buttonY = 113;
	private final int labelY = 80;
	private MyLabel[] number = new MyLabel[5];// 序号
	private MyLabel[] logo = new MyLabel[5];// 队标
	private MyLabel[] team = new MyLabel[5];// 球队名称
	private MyLabel[] value = new MyLabel[5];// 属性值
	private MyLabel[] league = new MyLabel[5];// 联盟
	private TeamHotBlService teamHotBl = new TeamHotBl();
	private ArrayList<TeamHotInfo> teamHotInfo = new ArrayList<TeamHotInfo>(5);
	private MyLabel[] type = new MyLabel[5];// 标识
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
		for (int i = 0; i < 5; i++) {
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
		for (int i = 0; i < 5; i++) {
			number[i].setBounds(20, labelHeight * i + labelY, 50, 60);
			logo[i].setBounds(120, labelHeight * i + labelY, 80, 80);
			team[i].setBounds(350, labelHeight * i + labelY + 10, 200, (60));
			league[i].setBounds(560, labelHeight * i + labelY, (80), (80));
			value[i].setBounds(770, labelHeight * i + labelY, 100, (80));
		}
		for (int i = 0; i < 5; i++) {
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

		for (int i = 0; i < 5; i++) {
			number[i].setHorizontalAlignment(SwingConstants.CENTER);
			number[i].setFont(MyFont.LARGE_BOLD);
			value[i].setFont(MyFont.MIDDLE_BOLD);
			team[i].setFont(MyFont.MIDDLE_BOLD);
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
		for (int i = 0; i < 5; i++) {
			logo[i].addMouseListener(this);
		}

	}

	private void init() {
		this.teamHotInfo = this.teamHotBl.getTeamHot(Season.season_team[0], GameKind.regular_game, DataKind.average, Field.point);
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
				this.teamHotInfo = this.teamHotBl.getTeamHot(Season.season_team[0], GameKind.regular_game, DataKind.average, Field.point);
				this.setContent();
				HotSportPanel.showRefreshed();
				String date = new GameInfoBl().getLatestDate();
				HotSportPanel.refreshDate(date);
				break;
			}
		}
		for (int i = 0; i < 5; i++) {
			if (e.getSource().equals(logo[i])) {
				String teamName = this.teamHotInfo.get(i).getTeamName();
				new SonFrame(teamName, SonFrame.teamCard);
				break;
			}
		}

	}

	private void setContent() {
		if (teamHotInfo != null) {
			for (int i = 0; i < teamHotInfo.size(); i++) {
				TeamHotInfo temp = this.teamHotInfo.get(i);
				logo[i].setMyIcon(new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + temp.getTeamName() + ".png"));
				team[i].setText(temp.getTeamName());
				value[i].setText(String.valueOf(temp.getValue()));
				league[i].setText(temp.getLeague());
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
		for (int i = 0; i < 5; i++) {
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
		for (int i = 0; i < 5; i++) {
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
