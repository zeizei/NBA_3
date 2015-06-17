package presentation;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.game.GamePanel;
import presentation.hotsport.HotSportPanel;
import presentation.players.PlayerPanel;
import presentation.start.Main;
import presentation.statics.NUMBER;
import presentation.statics.PathOfFile;
import presentation.teams.TeamPanel;
import statistics.presentation.StatisticsPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private NavigationPanel navigationPanel;// 导航栏
	private ContentPanel contentPanel;// 内容栏
	private JLabel background;
	public int isPlayOff = 0;

	public MainFrame() {
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setBounds((NUMBER.SCREEN_WIDTH - NUMBER.FRAME_WIDTH) / 2, (NUMBER.SCREEN_HEIGHT - NUMBER.FRAME_HEIGHT) / 2 - 20, NUMBER.FRAME_WIDTH, NUMBER.FRAME_HEIGHT);
		background = new JLabel(new ImageIcon("images/players/background_player.png"));
		background.setBounds(0, 0, NUMBER.FRAME_WIDTH, NUMBER.FRAME_HEIGHT);
		this.getLayeredPane().add(background, new Integer(1));
		JPanel jp = (JPanel) this.getContentPane();
		jp.setOpaque(false);

		navigationPanel = new NavigationPanel();
		navigationPanel.setBounds(0, 0, NUMBER.NAVIGATION_PANEL_WIDTH, NUMBER.NAVIGATION_PANEL_HEIGHT);
		this.getLayeredPane().add(navigationPanel, new Integer(2));
		contentPanel = new ContentPanel();
		contentPanel.setBounds(0, NUMBER.NAVIGATION_PANEL_HEIGHT, NUMBER.FRAME_WIDTH, (NUMBER.FRAME_HEIGHT - NUMBER.NAVIGATION_PANEL_HEIGHT));
		this.getLayeredPane().add(contentPanel, new Integer(2));
		this.setVisible(true);
	}

	class ContentPanel extends JPanel {
		/**
		 * 展示类
		 */
		private static final long serialVersionUID = 1L;
		private CardLayout card;// 卡片布局管理器
		private PlayerPanel playerPanel;// 球员界面
		private TeamPanel teamPanel;// 球队界面
		private GamePanel gamePanel;// 比赛界面
		private HotSportPanel hotSportPanel;// 热点界面
		private StatisticsPanel statisticsPanel;// 统计分析界面

		ContentPanel() {
			card = new CardLayout();
			this.setOpaque(false);
			this.setLayout(card);
			//
			playerPanel = new PlayerPanel();
			teamPanel = new TeamPanel();
			gamePanel = new GamePanel();
			hotSportPanel = new HotSportPanel();
			statisticsPanel = new StatisticsPanel();

			this.add(playerPanel, "playerPanel");
			this.add(teamPanel, "teamPanel");
			this.add(gamePanel, "gamePanel");
			this.add(hotSportPanel, "hotSportPanel");
			this.add(statisticsPanel, "statisticsPanel");
			this.setVisible(true);
		}

		public void showPlayerPanel() {
			this.card.show(contentPanel, "playerPanel");
		}

		public void showTeamPanel() {
			this.card.show(contentPanel, "teamPanel");
		}

		public void showMatchPanel() {
			this.card.show(contentPanel, "gamePanel");
		}

		public void showHotPanel() {
			this.card.show(contentPanel, "hotSportPanel");
		}

		public void showStatisticsPanel() {
			this.card.show(contentPanel, "statisticsPanel");
		}
	}

	class NavigationPanel extends JPanel implements MouseListener {
		/**
		 * 导航栏
		 */
		private static final long serialVersionUID = 1L;
		private NavigationButton quitSystem, playerPanelButton, teamPanelButton, matchPanelButton, hotSportPanelButton, statisticsPanelButton,dataLivePanelButton;
		private int button_w = 150;
		private int button_h = 50;
		private int button_y = 10;
		private int gap = 130;
		private ImageIcon player_normal = new ImageIcon(
				PathOfFile.NAVIGATION_IMAGE + "players_normal.png");
		private ImageIcon player_press = new ImageIcon(
				"images/navigation/players_press.png");
		private ImageIcon player_enter = new ImageIcon(
				"images/navigation/players_enter.png");
		private ImageIcon teams_normal = new ImageIcon(
				"images/navigation/teams_normal.png");
		private ImageIcon teams_press = new ImageIcon(
				"images/navigation/teams_press.png");
		private ImageIcon teams_enter = new ImageIcon(
				"images/navigation/teams_enter.png");
		private ImageIcon match_normal = new ImageIcon(
				"images/navigation/match_normal.png");
		private ImageIcon match_press = new ImageIcon(
				"images/navigation/match_press.png");
		private ImageIcon match_enter = new ImageIcon(
				"images/navigation/match_enter.png");
		private ImageIcon hot_normal = new ImageIcon(
				"images/navigation/hot_normal.png");
		private ImageIcon hot_press = new ImageIcon(
				"images/navigation/hot_press.png");
		private ImageIcon hot_enter = new ImageIcon(
				"images/navigation/hot_enter.png");
		private ImageIcon exit_normal = new ImageIcon(
				"images/navigation/exit_normal.png");
		private ImageIcon exit_enter = new ImageIcon(
				"images/navigation/exit_enter.png");
		private ImageIcon datalive_normal=new ImageIcon("images/navigation/datalive_normal.png");
		private ImageIcon datalive_enter=new ImageIcon("images/navigation/datalive_enter.png");
		private ImageIcon statistics_normal=new ImageIcon("images/navigation/statistic_normal.png");
		private ImageIcon statistics_press=new ImageIcon("images/navigation/statistic_press.png");
		private ImageIcon statistics_enter=new ImageIcon("images/navigation/statistic_enter.png");

		public NavigationPanel() {
			this.setLayout(null);
			this.setOpaque(false);
			this.createObjects();
			this.setComponentsLocation();
			this.addListener();
			this.setVisible(true);
		}

		private void createObjects() {
			quitSystem = new NavigationButton(exit_normal);
			playerPanelButton = new NavigationButton(player_press);
			playerPanelButton.isPress = true;
			teamPanelButton = new NavigationButton(teams_normal);
			matchPanelButton = new NavigationButton(match_normal);
			hotSportPanelButton = new NavigationButton(hot_normal);
			dataLivePanelButton=new NavigationButton(datalive_normal);
			statisticsPanelButton = new NavigationButton(statistics_normal);
		}

		private void setComponentsLocation() {
			playerPanelButton.setBounds(390, button_y, button_w, button_h);
			teamPanelButton.setBounds(390 + gap, button_y, button_w, button_h);
			matchPanelButton.setBounds(390 + gap * 2, button_y, button_w,
					button_h);
			hotSportPanelButton.setBounds(390 + gap * 3, button_y, button_w,
					button_h);
			quitSystem.setBounds(390 + gap * 6, button_y, button_w, button_h);
			dataLivePanelButton.setBounds(390 + gap * 5, button_y, button_w, button_h);
			statisticsPanelButton.setBounds(390 + gap * 4, button_y, button_w, button_h);
			this.add(teamPanelButton);
			this.add(playerPanelButton);
			this.add(matchPanelButton);
			this.add(hotSportPanelButton);
			this.add(dataLivePanelButton);
			this.add(statisticsPanelButton);
			this.add(quitSystem);
		}

		private void addListener() {
			this.playerPanelButton.addMouseListener(this);
			this.teamPanelButton.addMouseListener(this);
			this.matchPanelButton.addMouseListener(this);
			this.hotSportPanelButton.addMouseListener(this);
			this.statisticsPanelButton.addMouseListener(this);
			this.dataLivePanelButton.addMouseListener(this);
			this.quitSystem.addMouseListener(this);
		}

		public void mouseClicked(MouseEvent e) {
			if (e.getSource().equals(quitSystem)) {
				Main.mainFrame.dispose();
				System.exit(0);
			}
			else if (e.getSource().equals(playerPanelButton)) {
				changeAllBackground();
				playerPanelButton.setIcon(player_press);
				changeAllIsPress();
				playerPanelButton.setIsPress(true);
				Main.mainFrame.changeBackGround(new ImageIcon("images/players/background_player.png"));
				contentPanel.showPlayerPanel();
			}
			else if (e.getSource().equals(teamPanelButton)) {
				changeAllBackground();
				teamPanelButton.setIcon(teams_press);
				changeAllIsPress();
				teamPanelButton.setIsPress(true);
				if (isPlayOff == 0) {
					Main.mainFrame.changeBackGround(new ImageIcon("images/teams/background_team.png"));
				}
				else if (isPlayOff == 1) {
					Main.mainFrame.changeBackGround(new ImageIcon(PathOfFile.HOTSPOT + "background_hotspot.png"));
				}
				contentPanel.showTeamPanel();
			}
			else if (e.getSource().equals(matchPanelButton)) {
				changeAllBackground();
				matchPanelButton.setIcon(match_press);
				changeAllIsPress();
				matchPanelButton.setIsPress(true);
				Main.mainFrame.changeBackGround(new ImageIcon("images/matchs/background_match.png"));
				contentPanel.showMatchPanel();
			}
			else if (e.getSource().equals(hotSportPanelButton)) {
				changeAllBackground();
				hotSportPanelButton.setIcon(hot_press);
				changeAllIsPress();
				hotSportPanelButton.setIsPress(true);
				Main.mainFrame.changeBackGround(new ImageIcon(PathOfFile.HOTSPOT + "background_hotspot.png"));
				contentPanel.showHotPanel();
			}
			else if (e.getSource().equals(statisticsPanelButton)) {
				changeAllBackground();
				statisticsPanelButton.setIcon(statistics_press);
				changeAllIsPress();
				hotSportPanelButton.setIsPress(true);
				Main.mainFrame.changeBackGround(new ImageIcon("images/statistics/background_season.png"));
				contentPanel.showStatisticsPanel();
			}
		}

		public void mouseEntered(MouseEvent e) {
			if (e.getSource().equals(quitSystem)) {
				if (quitSystem.isPress) {
				}
				else {
					quitSystem.setIcon(exit_enter);
				}
			}
			else if (e.getSource().equals(playerPanelButton)) {
				if (playerPanelButton.isPress) {
				}
				else
					playerPanelButton.setIcon(player_enter);
			}
			else if (e.getSource().equals(teamPanelButton)) {
				if (teamPanelButton.isPress) {
				}
				else
					teamPanelButton.setIcon(teams_enter);
			}
			else if (e.getSource().equals(matchPanelButton)) {
				if (matchPanelButton.isPress) {
				}
				else
					matchPanelButton.setIcon(match_enter);
			}
			else if (e.getSource().equals(hotSportPanelButton)) {
				if (hotSportPanelButton.isPress) {
				}
				else
					hotSportPanelButton.setIcon(hot_enter);
			}
			else if (e.getSource().equals(statisticsPanelButton)) {
				if (statisticsPanelButton.isPress) {
				}
				else
					statisticsPanelButton.setIcon(statistics_enter);
			}
			else if(e.getSource().equals(dataLivePanelButton)){
				if(dataLivePanelButton.isPress){
				}
				else
					dataLivePanelButton.setIcon(datalive_enter);
			}
		}

		public void mouseExited(MouseEvent e) {
			if (e.getSource().equals(quitSystem)) {
				quitSystem.setIcon(exit_normal);
			}
			else if (e.getSource().equals(playerPanelButton)) {
				if (playerPanelButton.isPress) {
				}
				else
					playerPanelButton.setIcon(player_normal);
			}
			else if (e.getSource().equals(teamPanelButton)) {
				if (teamPanelButton.isPress) {
				}
				else
					teamPanelButton.setIcon(teams_normal);
			}
			else if (e.getSource().equals(matchPanelButton)) {
				if (matchPanelButton.isPress) {
				}
				else
					matchPanelButton.setIcon(match_normal);
			}
			else if (e.getSource().equals(hotSportPanelButton)) {
				if (hotSportPanelButton.isPress) {
				}
				else
					hotSportPanelButton.setIcon(hot_normal);
			}
			else if (e.getSource().equals(statisticsPanelButton)) {
				if (statisticsPanelButton.isPress) {
				}
				else
					statisticsPanelButton.setIcon(statistics_normal);
			}
			else if(e.getSource().equals(dataLivePanelButton)){
				if(dataLivePanelButton.isPress){
				}
				else
					dataLivePanelButton.setIcon(datalive_normal);
			}
		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {

		}

		private void changeAllBackground() {
			quitSystem.setIcon(exit_normal);
			playerPanelButton.setIcon(player_normal);
			teamPanelButton.setIcon(teams_normal);
			matchPanelButton.setIcon(match_normal);
			hotSportPanelButton.setIcon(hot_normal);
			statisticsPanelButton.setIcon(statistics_normal);
		}

		private void changeAllIsPress() {
			quitSystem.setIsPress(false);
			playerPanelButton.setIsPress(false);
			teamPanelButton.setIsPress(false);
			matchPanelButton.setIsPress(false);
			hotSportPanelButton.setIsPress(false);
			statisticsPanelButton.setIsPress(false);
		}
	}

	public class NavigationButton extends JButton {
		/**
		 * 导航按钮设置
		 */
		private static final long serialVersionUID = 1L;
		boolean isPress = false;

		public NavigationButton(ImageIcon background) {
			this.setFocusable(false);
			this.setBorderPainted(false);
			this.setContentAreaFilled(false);
			this.setIcon(background);
		}

		public void setIsPress(boolean isP) {
			isPress = isP;
		}
	}

	public void changeBackGround(ImageIcon icon) {
		this.getLayeredPane().remove(background);
		background = new JLabel(icon);
		background.setBounds(0, 0, NUMBER.FRAME_WIDTH, NUMBER.FRAME_HEIGHT);
		this.getLayeredPane().add(background, new Integer(1));
	}
}
