package presentation.hotsport;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import presentation.SonFrame;
import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyLabel;
import presentation.statics.MyColor;
import presentation.statics.MyFont;
import beans.generalTeam;
import businesslogic.team.TeamInfoBl;

import common.statics.Field;
import common.statics.Season;

public class SeasonPlayerKingPanel extends PlayerKingPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] fieldString = new String[] { Field.point.toString(), Field.totRebound.toString(), Field.assist.toString(), Field.steal.toString(), Field.block.toString(), Field.shot.toString(),
			Field.three.toString(), Field.free.toString() };
	private Field[] seasonPlayerKingFields = { Field.point, Field.totRebound, Field.assist, Field.steal, Field.block, Field.shot, Field.three, Field.free };
	private MyButton[] fieldButton = new MyButton[8];// 属性按钮
	private MyLabel[] type = new MyLabel[5];// 标识
	private String typeString[] = { "头像", "姓名", "位置", "球队", "数据" };
	protected final int buttonWidth = 180;
	private int flag = 0;
	private int buttonY = 113;

	public SeasonPlayerKingPanel() {
		this.setLayout(null);
		this.createObjects();
		this.setComponentsLocation();
		this.setComponentsStyle();
		this.addListener();
		this.setVisible(true);
		this.init();
	}

	private void init() {
		super.playerKing = this.playerHotBl.getPlayerKingOfSeason(Season.this_season, Field.point);
		super.setContent();
	}

	private void addListener() {
		for (int i = 0; i < 8; i++) {
			fieldButton[i].addMouseListener(this);
		}
		for (int i = 0; i < 5; i++) {
			portrait[i].addMouseListener(this);
			team[i].addMouseListener(this);
		}
	}

	private void setComponentsStyle() {
		for (int i = 0; i < 8; i++) {
			fieldButton[i].setContentAreaFilled(true);
			fieldButton[i].setBackground(MyColor.MIDDLE_COLOR);
		}
		fieldButton[flag].setBackground(MyColor.SELECTED);
	}

	private void setComponentsLocation() {
		for (int i = 0; i < 8; i++) {
			fieldButton[i].setBounds(940, buttonY + i * buttonHeight, buttonWidth, buttonHeight);
			this.add(fieldButton[i]);
		}
		for (int i = 0; i < 5; i++) {
			type[i].setBounds(100 + labelWidth * i, 10, labelWidth, 50);
			type[i].setFont(MyFont.SMALL_BOLD);
			this.add(type[i]);
		}

	}

	private void createObjects() {
		for (int i = 0; i < 8; i++) {
			fieldButton[i] = new MyButton(fieldString[i]);
		}
		for (int i = 0; i < 5; i++) {
			type[i] = new MyLabel(typeString[i]);
		}
	}

	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < 8; i++) {
			if (e.getSource().equals(fieldButton[i])) {
				flag = i;
				for (int j = 0; j < 8; j++) {
					fieldButton[j].setBackground(MyColor.MIDDLE_COLOR);
				}
				super.playerKing = super.playerHotBl.getPlayerKingOfSeason(Season.this_season, seasonPlayerKingFields[i]);
				fieldButton[i].setBackground(MyColor.SELECTED);
				this.setContent();
				break;
			}
		}
		for (int i = 0; i < 5; i++) {
			if (e.getSource().equals(portrait[i])) {
				String playerId = this.playerKing.get(i).getPlayerId();
				new SonFrame(playerId, SonFrame.playerCard);
				break;
			}
			if (e.getSource().equals(team[i])) {
				String teamName = this.playerKing.get(i).getTeamName();
				generalTeam generalTeam = new TeamInfoBl().getGeneralTeam(teamName, Season.this_season);
				new SonFrame(generalTeam, SonFrame.teamCard);
				break;
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
			if (e.getSource().equals(portrait[i])) {
				portrait[i].setLocation(portrait[i].getX() - (3), portrait[i].getY() - (3));
				break;
			}
			if (e.getSource().equals(team[i])) {
				team[i].setLocation(team[i].getX() - (3), team[i].getY() - (3));
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
			if (e.getSource().equals(portrait[i])) {
				portrait[i].setLocation(portrait[i].getX() + (3), portrait[i].getY() + (3));
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
