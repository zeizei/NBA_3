package statistics.presentation;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.mycomponent.MyTable;
import presentation.mycomponent.MyTableModel;
import presentation.mycomponent.MyTextArea;
import presentation.statics.MyFont;
import presentation.statics.PathOfFile;
import statistics.analysisbeans.PlayerShootDistance;
import statistics.analysisbeans.PlayerShootOpponent;
import statistics.analysisbeans.PlayerShootType;
import statistics.bl.StatisticsBl;
import statistics.blservice.StatisticsBlService;


public class OffenseAnalysisPanel extends MyPanel {
	
	private static final long serialVersionUID = 1L;
	private OffenseAnalysisPanel offenseAnalysisPanel=this;
	private NavigationPanel navigationPanel;
	private ContainerPanel containerPanel;
	private MyLabel changeplayer;
	private MyLabel portrait;
	private MyTextArea playerInfo;
	private MyLabel faceToOpponent,shotDistance,onOff;
	private FaceToTeamsPanel faceToTeamsPanel;
	private ShootDistanceTypePanel shootDistanceTypePanel;
	private OnOFFPanel onOFFPanel;
	private String playerID;
	private StatisticsBlService statisticsBl=new StatisticsBl();
	public OffenseAnalysisPanel(String playerID){
		this.playerID=playerID;
		this.createObjects();
		this.setComponentsLocation();
	}

	private void createObjects() {
		navigationPanel=new NavigationPanel();
		containerPanel=new ContainerPanel();
	}
	
	private void setComponentsLocation() {
		navigationPanel.setBounds(0,0,1150,100 );
		containerPanel.setBounds(0,100,1150,530);
		this.add(navigationPanel);
		this.add(containerPanel);
	}

	class NavigationPanel extends MyPanel implements MouseListener{
		
		private static final long serialVersionUID = 1L;
		public NavigationPanel(){
		faceToOpponent=new MyLabel("面向对手进攻");
		shotDistance=new MyLabel("个人进攻方式");
		onOff=new MyLabel("在场/不在场对手表现");
		changeplayer=new MyLabel("更换球员");
		portrait=new MyLabel();
		playerInfo=new MyTextArea();
		changeplayer.setBounds(150,60,120,40);
		portrait.setBounds(30,10,100,80);
		playerInfo.setBounds(150,10,120,50);
		faceToOpponent.setBounds(410, 35, 100, 30);
		shotDistance.setBounds(510, 35, 100, 30);
		onOff.setBounds(610, 35, 150, 30);
		this.add(portrait);
		this.add(playerInfo);
		this.add(changeplayer);
		this.add(faceToOpponent);
		this.add(shotDistance);
		this.add(onOff);
		onOff.addMouseListener(this);
		faceToOpponent.addMouseListener(this);
		shotDistance.addMouseListener(this);
		playerInfo.setFont(MyFont.SMALL_PLAIN);
		changeplayer.setFont(MyFont.SMALLEST_PLAIN);
		changeplayer.setHorizontalAlignment(SwingConstants.LEFT);
		playerInfo.setForeground(Color.black);
		portrait.setBorder(BorderFactory.createLineBorder(Color.black));
		changeplayer.addMouseListener(this);
		playerInfo.setText("Kobe Bryant\nSG/24");
		portrait.setMyIcon(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE+"Kobe Bryant.png"));//初始化换成ID
		}
		
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(changeplayer)){
				OffenseFindPlayerPopupPanel seasonFindPlayerPopupPanel=new OffenseFindPlayerPopupPanel();
				Point p=changeplayer.getLocation();
				seasonFindPlayerPopupPanel.show(offenseAnalysisPanel,p.x, p.y+40);
			}
			else if(e.getSource().equals(faceToOpponent)){
				containerPanel.showMyPanel("faceToOpponent");
			}
			else if(e.getSource().equals(shotDistance)){
				containerPanel.showMyPanel("shotDistance");
			}
			else if(e.getSource().equals(onOff)){
				containerPanel.showMyPanel("onOff");
			}
		}
		public void mouseEntered(MouseEvent arg0) {
		
		}
		public void mouseExited(MouseEvent arg0) {
			
		}
		public void mousePressed(MouseEvent arg0) {
			
		}
		public void mouseReleased(MouseEvent arg0) {
		}
	}
	class OffenseFindPlayerPopupPanel extends FindPlayerPopupPanel implements MouseListener{
		private static final long serialVersionUID = 1L;
		public OffenseFindPlayerPopupPanel(){
			for(int i=0;i<playerPanel.playerName.length;i++){
				this.playerPanel.playerName[i].addMouseListener(this);
			}
		}
		public void mouseClicked(MouseEvent e) {
			for(int i=0;i<5;i++){
				if(e.getSource().equals(playerPanel.playerName[i])){
					portrait.setMyIcon(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE+playerPanel.playerName[i].getplayerID()+".png"));
					faceToTeamsPanel.setContent(playerPanel.playerName[i].getplayerID());
					shootDistanceTypePanel.setContent(playerPanel.playerName[i].getplayerID());
					break;
				}
			}
		}
		public void mouseEntered(MouseEvent e) {
			for(int i=0;i<5;i++){
				if(e.getSource().equals(playerPanel.playerName[i])){
					Point p=playerPanel.playerName[i].getLocation();
					playerPanel.playerName[i].setLocation(p.x+3, p.y+3);
					break;
				}
			}
					
		}
		public void mouseExited(MouseEvent e) {
			for(int i=0;i<5;i++){
				if(e.getSource().equals(playerPanel.playerName[i])){
					Point p=playerPanel.playerName[i].getLocation();
					playerPanel.playerName[i].setLocation(p.x-3, p.y-3);
					break;
				}
			}
					
		
		}
		public void mousePressed(MouseEvent arg0) {
			
		}
		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}


	class ContainerPanel extends JPanel{
		
		private static final long serialVersionUID = 1L;
		
		private CardLayout card;

		public ContainerPanel(){
			this.setOpaque(false);
			this.createObjects();
			this.setContainerPanel();
		}
		private void createObjects() {
			faceToTeamsPanel=new FaceToTeamsPanel(playerID);
			shootDistanceTypePanel=new ShootDistanceTypePanel(playerID);
			onOFFPanel=new OnOFFPanel();
		}
		private void setContainerPanel() {
			card=new CardLayout();
			this.setLayout(card);
			this.add(faceToTeamsPanel,"faceToOpponent");
			this.add(shootDistanceTypePanel,"shotDistance");
			this.add(onOFFPanel,"onOff");
			
		}
		public void showMyPanel(String str){
			this.card.show(containerPanel,str);
		}
	}
	class ButtonPressed extends MyButton {
		/**
		 * 控制按钮显示状态
		 */
		private static final long serialVersionUID = 1L;
		boolean isPressed = false;

		public ButtonPressed(ImageIcon background) {
			super(background);
		}

		public void setIsPressed(boolean b) {
			isPressed = b;
		}

	}
	class FaceToTeamsPanel extends MyPanel{
		
		private static final long serialVersionUID = 1L;
		MyTableModel tableModel;
		MyTable table;
		String[] head={"对手","命中数","出手数","命中率","有效命中率","受助功数","受助攻率"};
		String[] field={"opponent","fg","fga","fgr","efg","astd","astdr"};
		public FaceToTeamsPanel(String playerID){
			tableModel=new MyTableModel(head);
			table=new MyTable(tableModel);
			JScrollPane j=new JScrollPane();
			j.getViewport().add(table);
			j.setBounds(100, 100, 900, 400);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			this.add(j);
			setContent(playerID);
		}
		private void setContent(String playerID) {
			tableModel.removeAllRows();
			ArrayList<PlayerShootOpponent> playerShootOpponentList= statisticsBl.getRegularSeasonOpponentShoot(playerID);
			for(int i=0;i<playerShootOpponentList.size();i++){
				tableModel.addRow(playerShootOpponentList.get(i).getSpeContent(field));
			}
			table.updateUI();
		}
		
	}
	class ShootDistanceTypePanel extends MyPanel{
	
		private static final long serialVersionUID = 1L;
		private MyLabel shotTypePieIcon;
		MyTableModel shotDistanceTableModel;
		MyTable shotDistancetable;
		String[] head={"距离","命中数","出手数","命中率","有效命中率","受助功数","受助攻率"};
		String[] field={"distance","fg","fga","fgr","efg","astd","astdr"};
		public ShootDistanceTypePanel(String playerID){
			shotDistanceTableModel=new MyTableModel(head);
			shotDistancetable=new MyTable(shotDistanceTableModel);
			JScrollPane shotDistanceJScrollPane=new JScrollPane();
			shotDistanceJScrollPane.getViewport().add(shotDistancetable);
			shotDistanceJScrollPane.setBounds(400, 50, 600, 250);
			shotDistancetable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			this.add(shotDistanceJScrollPane);
			
			shotTypePieIcon=new MyLabel();
			shotTypePieIcon.setBounds(100, 50, 250, 250);
			this.add(shotTypePieIcon);
			setContent(playerID);
		}
			private void setContent(String playerID) {
				
				shotDistanceTableModel.removeAllRows();
				ArrayList<PlayerShootDistance> playerShootDistanceList= statisticsBl.getRegularSeasonDistanceShoot(playerID);
				for(int i=0;i<playerShootDistanceList.size();i++){
					shotDistanceTableModel.addRow(playerShootDistanceList.get(i).getSpeContent(field));
				}
				shotDistancetable.updateUI();
				
				
				DefaultPieDataset dataset = getDataSet1(playerID);
				JFreeChart chart = ChartFactory.createPieChart3D("出手类型占比", dataset, true, false, false);  
				//加个副标题  
				PiePlot pieplot = (PiePlot) chart.getPlot();  
				 chart.setBackgroundPaint(null);
				 chart.getPlot().setBackgroundAlpha(0.0f);
				pieplot.setLabelFont(MyFont.SMALL_PLAIN);  
				//设置饼图是圆的（true），还是椭圆的（false）；默认为true  
				pieplot.setCircular(true);  
//				StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}:({1},{2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());  
//				pieplot.setLabelGenerator(standarPieIG);  
//				  
				//没有数据的时候显示的内容  
				pieplot.setNoDataMessage("无数据显示");  
				pieplot.setLabelGap(0.02D);  
				  
				 File f = null;
			        try {  
			             f= new File("images/"+playerID+"_shotType.png");  
			            ChartUtilities.saveChartAsPNG(f, chart, 250,250);  
			        } catch (Exception e) {  
			            e.printStackTrace();  
			        }
			        shotTypePieIcon.setIcon(new ImageIcon("images/"+playerID+"_shotType.png"));
			        f.delete();
		}

		private DefaultPieDataset getDataSet1(String playerID) {
			DefaultPieDataset data = new DefaultPieDataset();
			ArrayList<PlayerShootType> playerShootTypeList=statisticsBl.getRegularSeasonShootType(playerID);
			for(int i=0;i<playerShootTypeList.size();i++){
				data.setValue(playerShootTypeList.get(i).getType(),playerShootTypeList.get(i).getFga());
			}
			return data;
		}
	}
	class OnOFFPanel extends MyPanel{
		
		private static final long serialVersionUID = 1L;

		public OnOFFPanel(){
			
		}
	}
}
