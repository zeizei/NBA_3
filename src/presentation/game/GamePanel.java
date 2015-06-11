package presentation.game;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import presentation.SonFrame;
import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.mycomponent.MyTextField;
import presentation.start.Main;
import presentation.statics.MyColor;
import presentation.statics.MyFont;
import presentation.statics.PathOfFile;
import beans.GeneralGame;
import businesslogic.game.GameInfoBl;
import businesslogicservice.game.GameInfoBlService;

public class GamePanel extends MyPanel implements MouseListener {
	/**
	 * 
	 */
	private String showDay;
	private static final long serialVersionUID = 1L;
	private final int gamePanelHeight =74;
	private final int gamePanelWidth =400;
	private GameInfoBlService gameInfoBl = new GameInfoBl();
	private ArrayList<GeneralGame> oneDayGame = new ArrayList<GeneralGame>();
	private GameGeneralPanel[] GameGeneral;
	
	private CalendarPanel calendarPanel;
	private MyLabel nowDate,nowYear;
	private MyTextField dateInput;
	private MyButton findButton;
	private JButton refresh, preDayButton, nextDayButton;
    
	public GamePanel() {
		createObjects();
		this.init();
		setComponentPosition();
		addListener();
		setStyle();
		this.setVisible(true);
	}

	private void setStyle() {
		preDayButton.setFont(MyFont.SMALL_BOLD);
		preDayButton.setBackground(MyColor.MIDDLE_ORANGE);
		nextDayButton.setFont(MyFont.SMALL_BOLD);
		nextDayButton.setBackground(MyColor.MIDDLE_ORANGE);
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
		button.setBackground(MyColor.MIDDLE_COLOR);
	}

	private void createObjects() {
		calendarPanel=new CalendarPanel();
		nowDate = new MyLabel();
		nowYear=new MyLabel();
		preDayButton = new JButton("<html>前<br>一<br>天</html>");
		nextDayButton = new JButton("<html>后<br>一<br>天</html>");
		refresh = new JButton("刷新");
		dateInput=new MyTextField();
		findButton=new MyButton(new ImageIcon("images/players/find_normal.png"));
	}

	private void setComponentPosition() {
		calendarPanel.setBounds(0, 113, 280, 550);
		nowYear.setBounds(0, 70, 280, 30);
		nowDate.setBounds(0, 10, 280, 50);
		refresh.setBounds(20,540,240,40);
		preDayButton.setBounds(310,220, 40, 120);
		nextDayButton.setBounds(1220,220,40,120);
		dateInput.setBounds(30, 480, 180, 35);
		findButton.setBounds(220, 480, 35, 35);
		this.setButton(refresh);
		this.setButton(preDayButton);
		this.setButton(nextDayButton);
		this.add(nowDate);
		this.add(nowYear);
		this.add(refresh);
		this.add(calendarPanel);
		this.add(preDayButton);
		this.add(nextDayButton);
		this.add(dateInput);
		this.add(findButton);
	}

	private void init() {
		showDay = this.gameInfoBl.getLatestDate();
		oneDayGame = this.gameInfoBl.getTodayMatches(showDay);
		this.setContent();
	}

	private void setContent() {
		
		if (this.oneDayGame == null) {
			if (GameGeneral != null) {
				for (int i = 0; i < GameGeneral.length; i++) {
					this.remove(GameGeneral[i]);
				}
			}
		/*	String[] splitStr=showDay.getFormatString().split("-");
			this.nowDate.setText(splitStr[1]+"月"+splitStr[2] + "日");
			this.nowYear.setText("20"+splitStr[0]+"年");
			*/this.repaint();
		}
		else {
			if (GameGeneral != null) {
				for (int i = 0; i < GameGeneral.length; i++) {
					this.remove(GameGeneral[i]);
				}
			}
			
			int numOfMatch = oneDayGame.size();
			
			GameGeneral = new GameGeneralPanel[numOfMatch];
			if(numOfMatch<5){
			for (int i = 0; i < numOfMatch; i++) {
				System.out.println(i);
//				GameGeneral[i] = new GameGeneralPanel(oneDayGame.get(i));
				GameGeneral[i].setBounds(600,(gamePanelHeight+30)*i+30,gamePanelWidth, gamePanelHeight);
				this.add(GameGeneral[i]);
				}
			}
			else if(numOfMatch<=8){
				for (int i = 0; i < numOfMatch; i++) {
					System.out.println(i);
//					GameGeneral[i] = new GameGeneralPanel(oneDayGame.get(i));
					GameGeneral[i].setBounds(600,(gamePanelHeight)*i+20,gamePanelWidth, gamePanelHeight);
					this.add(GameGeneral[i]);
					}
			}
			else{
				for (int i = 0; i < 8; i++) {
				
//				GameGeneral[i] = new GameGeneralPanel(oneDayGame.get(i));
				GameGeneral[i].setBounds(370,(gamePanelHeight)*i+10,gamePanelWidth, gamePanelHeight);
				this.add(GameGeneral[i]);
				}
				for (int i = 8; i < numOfMatch; i++) {
//					GameGeneral[i] = new GameGeneralPanel(oneDayGame.get(i));
					GameGeneral[i].setBounds(820,(gamePanelHeight)*(i-8)+10,gamePanelWidth, gamePanelHeight);
					this.add(GameGeneral[i]);
					}
				
			}
//			String[] splitStr=showDay.getFormatString().split("-");
//			this.nowDate.setText(splitStr[1]+"月"+splitStr[2]+"日");
//			this.nowYear.setText("20"+splitStr[0]+"年");
			this.updateUI();
			
		}
		if (GameGeneral != null) {
			for (int i = 0; i < GameGeneral.length; i++) {
				GameGeneral[i].addMouseListener(this);
			}
		}
	}

	private void addListener() {
		refresh.addMouseListener(this);
		preDayButton.addMouseListener(this);
		nextDayButton.addMouseListener(this);
		findButton.addMouseListener(this);

	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(refresh)) {
			this.init();
		}
		else if (e.getSource().equals(findButton)) {
			findButton.setIcon(new ImageIcon("images/players/find_press.png"));
			String playerName = dateInput.getText();
			if (playerName.equals("") || playerName == null) {
				JOptionPane.showMessageDialog(Main.mainFrame, "请输入要查找的球员");// 弹出提示
			}
		}
		else if (e.getSource().equals(preDayButton)) {
//			this.showDay = showDay.toPreDate();
			this.oneDayGame = this.gameInfoBl.getTodayMatches(showDay);
			this.setContent();
		}
		else if (e.getSource().equals(nextDayButton)) {
//			this.showDay = showDay.toNextDate();
			this.oneDayGame = this.gameInfoBl.getTodayMatches(showDay);
			this.setContent();
		}
		else {
			for (int i = 0; i < oneDayGame.size(); i++) {
				if (e.getSource().equals(GameGeneral[i])) {
//					new SonFrame(GameGeneral[i].getGeneralInfoOfMatch(), SonFrame.matchCard);
					break;
				}
			}
		}

	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource().equals(refresh)) {
			refresh.setBackground(MyColor.DEEP_COLOR);
		}
		else if (e.getSource().equals(findButton)) {
			findButton.setIcon(new ImageIcon("images/players/find_enter.png"));
		}
		else if (e.getSource().equals(preDayButton)) {
			preDayButton.setBackground(MyColor.DEEP_ORANGE);
		}
		else if (e.getSource().equals(nextDayButton)) {
			nextDayButton.setBackground(MyColor.DEEP_ORANGE);
		}
		else {
			for (int i = 0; i < oneDayGame.size(); i++) {
				if (e.getSource().equals(GameGeneral[i])) {
					GameGeneral[i].setLocation(GameGeneral[i].getX() - 3, GameGeneral[i].getY() - 3);
					break;
				}
			}
		}

	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource().equals(refresh)) {
			refresh.setBackground(MyColor.MIDDLE_COLOR);
		}
		else if (e.getSource().equals(findButton)) {
			findButton.setIcon(new ImageIcon("images/players/find_normal.png"));
		}
		else if (e.getSource().equals(preDayButton)) {
			preDayButton.setBackground(MyColor.MIDDLE_ORANGE);
		}
		else if (e.getSource().equals(nextDayButton)) {
			nextDayButton.setBackground(MyColor.MIDDLE_ORANGE);
		}
		else {
			for (int i = 0; i < oneDayGame.size(); i++) {
				if (e.getSource().equals(GameGeneral[i])) {
					GameGeneral[i].setLocation(GameGeneral[i].getX() + 3, GameGeneral[i].getY() + 3);
					break;
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
//		private GeneralInfoOfOneMatch oneMatch;

/*	public GameGeneralPanel(GeneralInfoOfOneMatch oneMatch) {
			this.oneMatch = oneMatch;
			this.setLayout(null);
			String firstTeamName = oneMatch.getFirstTeamName();
			String secondTeamName = oneMatch.getSecondTeamName();
			int firstTeamScore = oneMatch.getFirstTeamScore();
			int secondTeamScore = oneMatch.getSecondTeamScore();
			ImageIcon firstImage = new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + firstTeamName + ".png");
			ImageIcon secondImage = new ImageIcon(PathOfFile.TEAM_LOGO_IMAGE + secondTeamName + ".png");
			String score = "<html>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + String.valueOf(firstTeamScore) + " VS "
					+ String.valueOf(+secondTeamScore) + "<br>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + firstTeamName
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + secondTeamName + "</html>";
			MyLabel firstTeam = new MyLabel();
			MyLabel secondTeam = new MyLabel();
			MyLabel scoreLabel = new MyLabel(score);
			firstTeam.setBounds(0,5, 64,64);
			scoreLabel.setBounds(gamePanelWidth / 4, 0, gamePanelWidth / 2, gamePanelHeight -  ( 5));
			secondTeam.setBounds(gamePanelWidth / 4 * 3,  ( 5),64,64);
			scoreLabel.setFont(MyFont.SMALL_BOLD);
			firstTeam.setMyIcon(firstImage);
			secondTeam.setMyIcon(secondImage);
			this.add(firstTeam);
			this.add(scoreLabel);
			this.add(secondTeam);
			this.setVisible(true);
		}

		public GeneralInfoOfOneMatch getGeneralInfoOfMatch() {
			return this.oneMatch;
		}*/
	}
	class CalendarPanel extends MyPanel implements MouseListener{
		private MyTextField hasChoosenDate;
		private int year_now,month_now;
		private int chooseY,chooseM,chooseD;
		private MyLabel calYearAndMonth;
		private MyLabel nextMonth,preMonth;
		private MyPanel dayPanel;
		private final String[] week={"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
		private MyLabel[] weekLabel;
		private MyLabel[] dayLabel;
		private static final long serialVersionUID = 1L;
		
		public CalendarPanel(){
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
			hasChoosenDate=new MyTextField();
			calYearAndMonth=new MyLabel();
			nextMonth=new MyLabel(">>");
			preMonth=new MyLabel("<<");
			dayPanel=new MyPanel();
		}
		private void setComLocation() {
			hasChoosenDate.setBounds(10, 310, 260, 40);
			calYearAndMonth.setBounds(30, 0, 220, 40);
			nextMonth.setBounds(250, 0, 40, 40);
			preMonth.setBounds(0,0,40, 40);
			dayPanel.setBounds(0, 40, 280, 400);
			
			this.add(calYearAndMonth);
			this.add(dayPanel);
			this.add(nextMonth);
			this.add(preMonth);
			this.add(hasChoosenDate);
		}
		private void setStyle() {
			hasChoosenDate.setEditable(false);
			hasChoosenDate.setFont(MyFont.SMALL_BOLD);
			hasChoosenDate.setHorizontalAlignment(SwingConstants.CENTER);
			hasChoosenDate.setBorder(new EmptyBorder(0, 0, 0, 0));
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
		private void initDayPanel(){
			int col=0;
			int row=1;
			weekLabel=new MyLabel[7];
			dayLabel=new MyLabel[42];
			for(int i=0;i<7;i++){
				weekLabel[i]=new MyLabel(week[i]);
				weekLabel[i].setBounds(i*40, 0, 40, 40);
				dayPanel.add(weekLabel[i]);
				weekLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
				weekLabel[i].setForeground(Color.white);
				weekLabel[i].setBackground(MyColor.DEEP_COLOR);
				weekLabel[i].setOpaque(true);
				weekLabel[i].setFont(MyFont.SMALL_PLAIN);
			}
			for(int i=0;i<42;i++){
				dayLabel[i]=new MyLabel(" ");
				dayLabel[i].setBounds(col*40, row*40, 40, 40);
				if(i%7==6){
					col=0;
					row++;
				}
				else{
					col++;
				}
				dayPanel.add(dayLabel[i]);
				
				dayLabel[i].addMouseListener(this);
				dayLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
				dayLabel[i].setForeground(MyColor.DEEP_COLOR);
				dayLabel[i].setFont(MyFont.SMALL_BOLD);
			}
			showDay = gameInfoBl.getLatestDate();
//			String[] splitStr=showDay.getFormatString().split("-");
//			chooseY=year_now=Integer.parseInt("20"+splitStr[0]);
//			chooseM=month_now=Integer.parseInt(splitStr[1]);
//			chooseD=Integer.parseInt(splitStr[2]);
			hasChoosenDate.setText("已选择："+chooseY+"年"+chooseM+"月"+chooseD+"日"); 
			setContent(year_now,month_now);
		}
		private void setContent(int year0,int month0) {
			
			calYearAndMonth.setText(year0+"年"+month0+"月");
			int year=year0-1900;
			int month=month0;
			int firstDayInWeek=0;//0代表星期天
			int daysCount=0;
			Date date=new Date(year, month-1, 1);
   		    GregorianCalendar cal=new GregorianCalendar();//创建一个Calendar的实例
   		    cal.setTime(date);
   		    firstDayInWeek=date.getDay();
   		    if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)  {   
   	               daysCount=31;   
   	           }    
   		    else if(month==4||month==6||month==9||month==11) {   
   	               daysCount=30;   
   	        } 
			else {
				if(cal.isLeapYear(year)) {
					daysCount = 29;
				} else {
					daysCount = 28;
				}
			}
   	        int n=1;
			for(int i=firstDayInWeek;n<=daysCount;i++){
				dayLabel[i].setText(String.valueOf(n));
				n++;
			}
		}

		public void mouseClicked(MouseEvent e) {
			if (e.getSource().equals(preMonth)) {
				if(month_now==1){
					year_now--;
					month_now=12;
				}else{
					month_now--;
				}
				this.cleanAll();
				this.setContent(year_now,month_now);
			}
			else if (e.getSource().equals(nextMonth)) {
				if(month_now==12){
					year_now++;
					month_now=1;
				}else{
					month_now++;
				}
				this.cleanAll();
				this.setContent(year_now,month_now);
			}
			else {
				for (int i = 0; i < dayLabel.length; i++) {
					if (e.getSource().equals(dayLabel[i])) {
						if(!dayLabel[i].getText().equals(" ")){
							chooseY=year_now;
							chooseM=month_now;
							chooseD=Integer.parseInt(dayLabel[i].getText());
							hasChoosenDate.setText("已选择："+chooseY+"年"+chooseM+"月"+chooseD+"日");
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
						if(!(dayLabel[i].getText().equals(" "))){
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
						if(!(dayLabel[i].getText().equals(" "))){
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
		private void cleanAll(){
			for(int i=0;i<42;i++){
				dayLabel[i].setText(" ");
			}
		}
	/*	public MyDate getDate(){
			int y =chooseY % 100;
			int m =chooseM;
			int d =chooseD;
			MyDate date = new MyDate(y, m, d);
			return date;
		}*/
	}
}
