package presentation.hotsport;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.statics.MyFont;
import presentation.statics.NUMBER;
import presentation.statics.PathOfFile;
import beans.generalTeam;
import businesslogic.hot.PlayerHotBl;
import businesslogic.team.TeamInfoBl;
import businesslogicservice.hot.PlayerHotBlSrevice;

import common.datastructure.PlayerKingInfo;
import common.statics.Season;

public class PlayerKingPanel extends MyPanel {
	private static final long serialVersionUID = 1L;
	protected MyLabel[] contentLable = new MyLabel[6];
	private final int labelHeight = 100;
	protected final int labelWidth = 180;
	protected final int buttonHeight = 50;
	private final int labelY = 80;
	protected MyLabel[] number = new MyLabel[NUMBER.defualt_hot_num];// 序号
	protected MyLabel[] portrait = new MyLabel[NUMBER.defualt_hot_num];// 头像
	protected MyLabel[] name = new MyLabel[NUMBER.defualt_hot_num];// 姓名
	protected MyLabel[] position = new MyLabel[NUMBER.defualt_hot_num];// 位置
	protected MyLabel[] value = new MyLabel[NUMBER.defualt_hot_num];// 属性值
	protected MyLabel[] team = new MyLabel[NUMBER.defualt_hot_num];// 球队
	protected MyLabel[][] allLabel = new MyLabel[][] { number, portrait, name, position, value, team };
	protected PlayerHotBlSrevice playerHotBl = new PlayerHotBl();
	protected ArrayList<PlayerKingInfo> playerKing = new ArrayList<PlayerKingInfo>(NUMBER.defualt_hot_num);

	public PlayerKingPanel() {
		this.setLayout(null);
		this.createObjects();
		this.setComponentsLocation();
		this.setComponentsStyle();
		this.setVisible(true);
	}

	private void createObjects() {
		for (int k = 0; k < 5; k++) {
			allLabel[0][k] = new MyLabel(String.valueOf(k + 1));
		}
		for (int i = 1; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				allLabel[i][j] = new MyLabel();
			}
		}
	}

	private void setComponentsLocation() {
		for (int i = 0; i < 5; i++) {
			number[i].setBounds(20, labelHeight * i + labelY, 50, 60);
			portrait[i].setBounds(100, labelHeight * i + labelY, 100, 75);
			name[i].setBounds(260, labelHeight * i + labelY + 10, 200, (60));
			position[i].setBounds(460, labelHeight * i + labelY, (80), (80));
			value[i].setBounds(820, labelHeight * i + labelY, 100, (80));
			team[i].setBounds(640, labelHeight * i + labelY, 70, 70);
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				this.add(allLabel[i][j]);
			}
		}
	}

	protected void setContent() {
		if (playerKing != null) {
			for (int i = 0; i < playerKing.size(); i++) {
				PlayerKingInfo temp = this.playerKing.get(i);
				portrait[i].setMyIcon(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE + temp.getPlayerId() + ".png"));
				name[i].setText(temp.getPlayerName());
				position[i].setText(temp.getPosition());
				value[i].setText(String.valueOf(temp.getValue()));
				String teamName = temp.getTeamName();
				ImageIcon teamIcon = null;
				if (teamName.equals("TOT")) {
					System.out.println("效力于多支球队");
					teamIcon = new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + "NBA.png");
				}
				else {
					generalTeam generalTeam = new TeamInfoBl().getGeneralTeam(teamName, Season.season_team[0]);
					if (generalTeam != null) {
						teamIcon = new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + generalTeam.getImgName() + ".png");
					}
					else {
						teamIcon = new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + "NBA.png");
					}
				}
				if (teamIcon != null) {
					team[i].setMyIcon(teamIcon);
				}
			}
		}
	}

	private void setComponentsStyle() {
		for (int i = 0; i < 5; i++) {
			number[i].setFont(MyFont.LARGE_BOLD);
			number[i].setHorizontalAlignment(SwingConstants.CENTER);
			value[i].setFont(MyFont.MIDDLE_BOLD);
			name[i].setFont(MyFont.SMALLEST_BOLD);
			position[i].setFont(MyFont.MIDDLE_BOLD);
		}
	}

}
