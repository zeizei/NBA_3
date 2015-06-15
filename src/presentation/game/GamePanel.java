package presentation.game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.statics.MyColor;
import presentation.statics.MyFont;
import presentation.statics.PathOfFile;
import beans.GeneralGame;
import businesslogic.game.GameInfoBl;
import businesslogic.team.TeamInfoBl;
import businesslogicservice.game.GameInfoBlService;
import businesslogicservice.team.TeamInfoBlService;

import common.statics.Season;

public class GamePanel extends MyPanel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameInfoBlService gameInfoBl = new GameInfoBl();
	private String showDay = gameInfoBl.getLatestDate();
	private ArrayList<GeneralGame> oneDayGame = gameInfoBl.getTodayMatches(showDay);
	private final int gamePanelHeight = 74;
	private final int gamePanelWidth = 400;
	private GameGeneralPanel[] generalGamePanelArray;// 一天里的所有比赛
	private CalendarPanel calendarPanel;// 日历
	private DateInputPanel dateInputPanel;// 日期输入
	private MyLabel nowDate, nowYear;
	private JButton refresh, quickJump;

	public GamePanel() {
		this.createObjects();
		this.setComponentPosition();
		this.addListener();
		this.setStyle();
		this.setContent();
		this.setVisible(true);
	}

	private void createObjects() {
		calendarPanel = new CalendarPanel();
		dateInputPanel = new DateInputPanel();
		nowDate = new MyLabel();
		nowYear = new MyLabel();
		refresh = new JButton("刷新");
		quickJump = new JButton("快速跳转");
		this.setVisible(true);
	}

	private void setComponentPosition() {
		nowYear.setBounds(0, 70, 150, 30);
		nowDate.setBounds(0, 10, 150, 50);
		refresh.setBounds(150, 30, 100, 40);
		calendarPanel.setBounds(0, 120, 280, 360);
		dateInputPanel.setBounds(40, 460, 300, 50);
		quickJump.setBounds(100, 530, 100, 40);
		this.setButton(refresh);
		this.setButton(quickJump);
		this.add(nowDate);
		this.add(nowYear);
		this.add(refresh);
		this.add(calendarPanel);
		this.add(dateInputPanel);
		this.add(quickJump);
	}

	private void setStyle() {
		nowDate.setFont(MyFont.LARGEST_BOLD);
		nowDate.setForeground(Color.white);
		nowDate.setHorizontalAlignment(SwingConstants.CENTER);
		nowYear.setFont(MyFont.MIDDLE_BOLD);
		nowYear.setForeground(MyColor.LIGHT_COLOR);
		nowYear.setHorizontalAlignment(SwingConstants.CENTER);
	}

	private void setButton(JButton button) {
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setFont(MyFont.SMALLEST_BOLD);
		button.setForeground(MyColor.MY_WHITE);
		button.setBackground(MyColor.MIDDLE_ORANGE);
	}

	private void refresh() {
		showDay = gameInfoBl.getLatestDate();
		oneDayGame = gameInfoBl.getTodayMatches(showDay);
		this.setContent();
	}

	private void setContent() {
		if (this.oneDayGame == null) {
			if (generalGamePanelArray != null) {
				for (int i = 0; i < generalGamePanelArray.length; i++) {
					this.remove(generalGamePanelArray[i]);
				}
				generalGamePanelArray = null;
			}
		}
		else {
			if (generalGamePanelArray != null) {
				for (int i = 0; i < generalGamePanelArray.length; i++) {
					this.remove(generalGamePanelArray[i]);
				}
			}
			int numOfMatch = oneDayGame.size();
			generalGamePanelArray = new GameGeneralPanel[numOfMatch];
			if (numOfMatch < 5) {
				for (int i = 0; i < numOfMatch; i++) {
					generalGamePanelArray[i] = new GameGeneralPanel(oneDayGame.get(i));
					generalGamePanelArray[i].setBounds(600, (gamePanelHeight + 30) * i + 30, gamePanelWidth, gamePanelHeight);
					this.add(generalGamePanelArray[i]);
				}
			}
			else if (numOfMatch <= 8) {
				for (int i = 0; i < numOfMatch; i++) {
					generalGamePanelArray[i] = new GameGeneralPanel(oneDayGame.get(i));
					generalGamePanelArray[i].setBounds(600, (gamePanelHeight) * i + 20, gamePanelWidth, gamePanelHeight);
					this.add(generalGamePanelArray[i]);
				}
			}
			else {
				for (int i = 0; i < 8; i++) {
					generalGamePanelArray[i] = new GameGeneralPanel(oneDayGame.get(i));
					generalGamePanelArray[i].setBounds(370, (gamePanelHeight) * i + 10, gamePanelWidth, gamePanelHeight);
					this.add(generalGamePanelArray[i]);
				}
				for (int i = 8; i < numOfMatch; i++) {
					generalGamePanelArray[i] = new GameGeneralPanel(oneDayGame.get(i));
					generalGamePanelArray[i].setBounds(820, (gamePanelHeight) * (i - 8) + 10, gamePanelWidth, gamePanelHeight);
					this.add(generalGamePanelArray[i]);
				}

			}
			if (generalGamePanelArray != null) {
				for (int i = 0; i < generalGamePanelArray.length; i++) {
					generalGamePanelArray[i].addMouseListener(this);
				}
			}
		}
		String[] splitStr = showDay.split("-");
		this.nowDate.setText(Integer.parseInt(splitStr[1]) + "-" + Integer.parseInt(splitStr[2]));
		this.nowYear.setText(splitStr[0]);
		this.updateUI();
	}

	private void addListener() {
		refresh.addMouseListener(this);
		quickJump.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(refresh)) {
			this.refresh();
		}
		else if (e.getSource().equals(quickJump)) {
			showDay = dateInputPanel.getDate();
			oneDayGame = gameInfoBl.getTodayMatches(showDay);
			setContent();
		}
		else {
			for (int i = 0; i < oneDayGame.size(); i++) {
				if (e.getSource().equals(generalGamePanelArray[i])) {
					// new SonFrame(GameGeneral[i].getGeneralInfoOfMatch(),
					// SonFrame.matchCard);
					System.out.println(generalGamePanelArray[i].getGeneralInfoOfMatch());
					break;
				}
			}
		}

	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource().equals(refresh)) {
			refresh.setBackground(MyColor.DEEP_ORANGE);
		}
		else if (e.getSource().equals(quickJump)) {
			quickJump.setBackground(MyColor.DEEP_ORANGE);
		}
		else {
			if (oneDayGame != null) {
				for (int i = 0; i < oneDayGame.size(); i++) {
					if (e.getSource().equals(generalGamePanelArray[i])) {
						generalGamePanelArray[i].setLocation(generalGamePanelArray[i].getX() - 3, generalGamePanelArray[i].getY() - 3);
						break;
					}
				}
			}
		}
	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource().equals(refresh)) {
			refresh.setBackground(MyColor.MIDDLE_ORANGE);
		}
		else if (e.getSource().equals(quickJump)) {
			quickJump.setBackground(MyColor.MIDDLE_ORANGE);
		}
		else {
			if (oneDayGame != null) {
				for (int i = 0; i < oneDayGame.size(); i++) {
					if (e.getSource().equals(generalGamePanelArray[i])) {
						generalGamePanelArray[i].setLocation(generalGamePanelArray[i].getX() + 3, generalGamePanelArray[i].getY() + 3);
						break;
					}
				}
			}
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	class GameGeneralPanel extends MyPanel {
		private static final long serialVersionUID = 1L;

		private GeneralGame oneGame;
		private TeamInfoBlService teamInfoBl = new TeamInfoBl();

		public GameGeneralPanel(GeneralGame oneGame) {
			this.oneGame = oneGame;
			this.setLayout(null);
			String homeTeamName = oneGame.getHomeTeam();
			String guestTeamName = oneGame.getGuestTeam();
			int homeTeamPoint = oneGame.getHomePoint();
			int guestTeamPoint = oneGame.getGuestPoint();
			Season season = Season.dateToSeason(oneGame.getDate());
			ImageIcon homeImage = new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + teamInfoBl.getGeneralTeam(homeTeamName, season).getImgName() + ".png");
			ImageIcon guestImage = new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + teamInfoBl.getGeneralTeam(guestTeamName, season).getImgName() + ".png");
			String score = "<html>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + String.valueOf(homeTeamPoint) + " VS " + String.valueOf(+guestTeamPoint) + "<br>" + homeTeamName + "<br>"
					+ guestTeamName + "</html>";
			MyLabel firstTeam = new MyLabel();
			MyLabel secondTeam = new MyLabel();
			MyLabel scoreLabel = new MyLabel(score);
			firstTeam.setBounds(0, 5, 64, 64);
			scoreLabel.setBounds(gamePanelWidth / 4, 0, gamePanelWidth / 2, gamePanelHeight);
			secondTeam.setBounds(gamePanelWidth / 4 * 3, 5, 64, 64);
			scoreLabel.setFont(MyFont.SMALLEST_PLAIN);
			firstTeam.setMyIcon(homeImage);
			secondTeam.setMyIcon(guestImage);
			this.add(firstTeam);
			this.add(scoreLabel);
			this.add(secondTeam);
			this.setVisible(true);
		}

		public GeneralGame getGeneralInfoOfMatch() {
			return this.oneGame;
		}
	}

	class CalendarPanel extends MyPanel implements MouseListener {
		private int nowYear, nowMonth, nowDay;
		private MyLabel calYearAndMonth;
		private MyLabel nextMonth, preMonth;
		private MyPanel dayPanel;// 显示日期
		private final String[] week = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		private MyLabel[] weekLabel;
		private MyLabel[] dayLabel;
		private static final long serialVersionUID = 1L;

		public CalendarPanel() {
			this.createObjects();
			this.setComLocation();
			this.initDayPanel();
			this.setStyle();
			this.addListener();
		}

		private void addListener() {
			nextMonth.addMouseListener(this);
			preMonth.addMouseListener(this);
		}

		private void createObjects() {
			calYearAndMonth = new MyLabel();
			nextMonth = new MyLabel(">>");
			preMonth = new MyLabel("<<");
			dayPanel = new MyPanel();

		}

		private void setComLocation() {
			calYearAndMonth.setBounds(30, 0, 220, 40);
			nextMonth.setBounds(250, 0, 40, 40);
			preMonth.setBounds(0, 0, 40, 40);
			dayPanel.setBounds(0, 40, 280, 280);

			this.add(calYearAndMonth);
			this.add(dayPanel);
			this.add(nextMonth);
			this.add(preMonth);
		}

		private void setStyle() {
			calYearAndMonth.setHorizontalAlignment(SwingConstants.CENTER);
			calYearAndMonth.setForeground(MyColor.DEEP_COLOR);
			calYearAndMonth.setFont(MyFont.LARGE_BOLD);
			nextMonth.setHorizontalAlignment(SwingConstants.CENTER);
			nextMonth.setForeground(MyColor.DEEP_COLOR);
			nextMonth.setFont(MyFont.SMALLEST_BOLD);
			preMonth.setHorizontalAlignment(SwingConstants.CENTER);
			preMonth.setForeground(MyColor.DEEP_COLOR);
			preMonth.setFont(MyFont.SMALLEST_BOLD);
		}

		private void initDayPanel() {
			int col = 0;
			int row = 1;
			weekLabel = new MyLabel[7];
			dayLabel = new MyLabel[42];
			for (int i = 0; i < 7; i++) {
				weekLabel[i] = new MyLabel(week[i]);
				weekLabel[i].setBounds(i * 40, 0, 40, 40);
				dayPanel.add(weekLabel[i]);
				weekLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
				weekLabel[i].setForeground(Color.white);
				weekLabel[i].setBackground(MyColor.DEEP_COLOR);
				weekLabel[i].setOpaque(true);
				weekLabel[i].setFont(MyFont.SMALL_PLAIN);
			}
			for (int i = 0; i < 42; i++) {
				dayLabel[i] = new MyLabel(" ");
				dayLabel[i].setBounds(col * 40, row * 40, 40, 40);
				if (i % 7 == 6) {
					col = 0;
					row++;
				}
				else {
					col++;
				}
				dayPanel.add(dayLabel[i]);
				dayLabel[i].addMouseListener(this);
				dayLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
				dayLabel[i].setForeground(MyColor.DEEP_COLOR);
				dayLabel[i].setFont(MyFont.SMALL_BOLD);
			}
			String[] splitStr = showDay.split("-");
			nowYear = Integer.parseInt(splitStr[0]);
			nowMonth = Integer.parseInt(splitStr[1]);
			nowDay = Integer.parseInt(splitStr[2]);
			setCalander();
		}

		@SuppressWarnings("deprecation")
		private void setCalander() {
			calYearAndMonth.setText(String.valueOf(nowYear) + "-" + String.valueOf(nowMonth));
			int year = nowYear - 1900;
			int month = nowMonth;
			int firstDayInWeek = 0;// 0代表星期天
			int daysCount = 0;
			Date date = new Date(year, month - 1, 1);
			GregorianCalendar cal = new GregorianCalendar();// 创建一个Calendar的实例
			cal.setTime(date);
			firstDayInWeek = date.getDay();
			if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				daysCount = 31;
			}
			else if (month == 4 || month == 6 || month == 9 || month == 11) {
				daysCount = 30;
			}
			else {
				if (cal.isLeapYear(year)) {
					daysCount = 29;
				}
				else {
					daysCount = 28;
				}
			}
			int n = 1;
			for (int i = firstDayInWeek; n <= daysCount; i++) {
				dayLabel[i].setText(String.valueOf(n));
				n++;
			}
		}

		public void mouseClicked(MouseEvent e) {
			if (e.getSource().equals(preMonth)) {
				if (nowMonth == 1) {
					nowYear--;
					nowMonth = 12;
				}
				else {
					nowMonth--;
				}
				this.cleanAll();
				this.setCalander();
			}
			else if (e.getSource().equals(nextMonth)) {
				if (nowMonth == 12) {
					nowYear++;
					nowMonth = 1;
				}
				else {
					nowMonth++;
				}
				this.cleanAll();
				this.setCalander();
			}
			else {
				for (int i = 0; i < dayLabel.length; i++) {
					if (e.getSource().equals(dayLabel[i])) {
						if (!dayLabel[i].getText().equals(" ")) {
							nowDay = Integer.parseInt(dayLabel[i].getText());
							String dayStr = String.valueOf(nowDay);
							String monthStr = String.valueOf(nowMonth);
							String yearStr = String.valueOf(nowYear);
							if (dayStr.length() == 1) {
								dayStr = "0" + dayStr;
							}
							if (monthStr.length() == 1) {
								monthStr = "0" + monthStr;
							}
							showDay = yearStr + "-" + monthStr + "-" + dayStr;
							oneDayGame = gameInfoBl.getTodayMatches(showDay);
							setContent();
						}
					}
				}
			}
		}

		public void mouseEntered(MouseEvent e) {
			if (e.getSource().equals(preMonth)) {
				preMonth.setForeground(MyColor.LIGHT_BLUE);
			}
			else if (e.getSource().equals(nextMonth)) {
				nextMonth.setForeground(MyColor.LIGHT_BLUE);
			}
			else {
				for (int i = 0; i < 42; i++) {
					if (e.getSource().equals(dayLabel[i])) {
						if (!(dayLabel[i].getText().equals(" "))) {
							dayLabel[i].setForeground(MyColor.LIGHT_BLUE);
						}
					}
				}
			}
		}

		public void mouseExited(MouseEvent e) {
			if (e.getSource().equals(preMonth)) {
				preMonth.setForeground(MyColor.DEEP_COLOR);
			}
			else if (e.getSource().equals(nextMonth)) {
				nextMonth.setForeground(MyColor.DEEP_COLOR);
			}
			else {
				for (int i = 0; i < 42; i++) {
					if (e.getSource().equals(dayLabel[i])) {
						if (!(dayLabel[i].getText().equals(" "))) {
							dayLabel[i].setForeground(MyColor.DEEP_COLOR);
						}
					}
				}
			}
		}

		public void mousePressed(MouseEvent arg0) {

		}

		public void mouseReleased(MouseEvent arg0) {

		}

		private void cleanAll() {
			for (int i = 0; i < 42; i++) {
				dayLabel[i].setText(" ");
			}
		}
	}

	class DateInputPanel extends MyPanel {
		private static final long serialVersionUID = 1L;
		private JComboBox<Integer> year;
		private JComboBox<Integer> month;
		private JComboBox<Integer> day;

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public DateInputPanel() {
			this.setOpaque(false);
			this.setFocusable(false);
			this.setLayout(new FlowLayout(FlowLayout.LEFT));
			year = new JComboBox<Integer>();
			this.setComBox(year);
			year.setModel(new DefaultComboBoxModel(getModel(1946, 2015)));
			this.add(year);

			month = new JComboBox();
			this.setComBox(month);
			month.setModel(new DefaultComboBoxModel(getModel(1, 12)));
			this.add(month);

			day = new JComboBox();
			this.setComBox(day);
			this.add(day);

			year.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					setDay(year, month, day);
				}

			});
			month.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					setDay(year, month, day);
				}
			});
			setDay(year, month, day);
			this.setVisible(true);
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		private void setDay(JComboBox year, JComboBox month, JComboBox day) {
			int y = Integer.parseInt((String) year.getSelectedItem());
			int m = Integer.parseInt((String) month.getSelectedItem());
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, y);
			c.set(Calendar.MONTH, m - 1);
			int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			day.setModel(new DefaultComboBoxModel(getModel(1, days)));
		}

		private String[] getModel(int start, int end) {
			String[] m = new String[end - start + 1];
			for (int i = 0; i < m.length; i++) {
				m[i] = String.valueOf(i + start);
			}
			return m;
		}

		private void setComBox(JComboBox<Integer> com) {
			com.setSize(100, 50);
			com.setBackground(MyColor.MIDDLE_ORANGE);
			com.setForeground(MyColor.MIDDLE_BLUE);
			com.setFont(MyFont.SMALL_PLAIN);
		}

		public String getDate() {
			String yearStr = (String) year.getSelectedItem();
			String monthStr = (String) month.getSelectedItem();
			if (monthStr.length() == 1) {
				monthStr = "0" + monthStr;
			}
			String dayStr = (String) day.getSelectedItem();
			if (dayStr.length() == 1) {
				dayStr = "0" + dayStr;
			}
			return yearStr + "-" + monthStr + "-" + dayStr;
		}
	}
}
