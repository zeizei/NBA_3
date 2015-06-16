package statistics.presentation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import common.datastructure.PlayerKingInfo;
import common.statics.Field;
import common.statics.Season;
import beans.GamePlayer;
import beans.SeasonPlayer;
import businesslogic.hot.PlayerHotBl;
import businesslogicservice.hot.PlayerHotBlSrevice;
import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.mycomponent.MyTextArea;
import presentation.statics.MyFont;
import presentation.statics.NUMBER;
import presentation.statics.PathOfFile;
import statistics.bl.StatisticsBl;
import statistics.blservice.StatisticsBlService;


public class SeasonAnalysisPanel extends MyPanel {
	private static final long serialVersionUID = 1L;
	private SeasonAnalysisPanel seasonAnalysisPanel=this;
	private NavigationPanel navigationPanel;
	private ContentPanel contentPanel;
	private PlayerInfoPanel playerInfoPanel;
	private StatisticsBlService statisticsBl=new StatisticsBl();
	private MyLabel playOffRegularCompareLabel,forcastDataLabel;
	private MyLabel forecast,playOffRegularCompare;
	private PlayOffRegularComparePanel playOffRegularComparePanel;
	private ForcastPanel forcastPanel;
	public SeasonAnalysisPanel(String playerID){
		playerInfoPanel=new PlayerInfoPanel(seasonAnalysisPanel);
		navigationPanel=new NavigationPanel();
		contentPanel=new ContentPanel();
		playerInfoPanel.setBounds(0, 0, 300, 100);
		navigationPanel.setBounds(300, 0, NUMBER.FRAME_WIDTH-450,100);
		contentPanel.setBounds(0,100,NUMBER.FRAME_WIDTH-150,NUMBER.FRAME_HEIGHT-NUMBER.NAVIGATION_PANEL_HEIGHT-100);
		this.add(navigationPanel);
		this.add(playerInfoPanel);
		this.add(contentPanel);	
	}
	class NavigationPanel extends MyPanel implements MouseListener{
		
		private static final long serialVersionUID = 1L;
		public NavigationPanel(){
		forecast=new MyLabel("实力预测");
		playOffRegularCompare=new MyLabel("季后/常规对比");
		
		forecast.setBounds(410, 35, 100, 30);
		playOffRegularCompare.setBounds(510, 35, 100, 30);
		
		this.add(forecast);
		this.add(playOffRegularCompare);
		forecast.addMouseListener(this);
		playOffRegularCompare.addMouseListener(this);
		
		}
		
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(forecast)){
				contentPanel.showMyPanel("forcastPanel");
			}
			else if(e.getSource().equals(playOffRegularCompare)){
				contentPanel.showMyPanel("playOffRegularCompare");
			}
		}
		public void mouseEntered(MouseEvent e) {
			
		}
		public void mouseExited(MouseEvent e) {
			
		}
		public void mousePressed(MouseEvent arg0) {
			
		}
		public void mouseReleased(MouseEvent arg0) {
		}
	}
	class ContentPanel extends JPanel{
		CardLayout card=new CardLayout();
		
		private static final long serialVersionUID = 1L;
		public ContentPanel(){
			this.setOpaque(false);
			this.setLayout(card);
			playOffRegularComparePanel=new PlayOffRegularComparePanel(playerInfoPanel.playerID);
			forcastPanel=new ForcastPanel(playerInfoPanel.playerID);
			this.add(playOffRegularComparePanel,"playOffRegularCompare");
			this.add(forcastPanel,"forcastPanel");
		}
		public void showMyPanel(String panelStr) {	
			this.card.show(contentPanel, panelStr);
		}
	}
	
	class PlayOffRegularComparePanel extends MyPanel implements MouseListener {
		
		private static final long serialVersionUID = 1L;
		private MyTextArea analysis=new MyTextArea();
		private MyLabel nickName=new MyLabel();
		public PlayOffRegularComparePanel(String PlayerID){
			playOffRegularCompareLabel=new MyLabel();
			playOffRegularCompareLabel.setBounds(20,0,700,500);
			nickName.setBounds(800, 300, 200, 150);
			analysis.setBounds(750, 50, 400, 250);
			analysis.setFont(MyFont.SMALL_BOLD);
			this.add(playOffRegularCompareLabel);
			this.add(analysis);
			this.add(nickName);
			this.setContent(PlayerID);
			nickName.addMouseListener(this);
		}
		private void setContent(String playerID) {
			CategoryDataset dataset = getDataSet1(playerID);
			 JFreeChart chart = ChartFactory.createBarChart3D(  
		                "常规/季后赛对比", // 图表标题  
		                "数据类型", // 目录轴的显示标签--横轴  
		                "", // 数值轴的显示标签--纵轴  
		                dataset, // 数据集  
		                PlotOrientation.VERTICAL, // 图表方向：水平、  
		                true, // 是否显示图例(对于简单的柱状图必须  
		                false, // 是否生成工具  
		                false // 是否生成URL链接  
		                );
			 processChart(chart);
			 chart.setBackgroundPaint(null);
			 chart.getPlot().setBackgroundAlpha(0.0f);
			 
			 CategoryPlot plot = (CategoryPlot) chart.getCategoryPlot(); 
		     BarRenderer3D customBarRenderer = (BarRenderer3D) plot.getRenderer(); 
		     customBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		     customBarRenderer.setBaseItemLabelsVisible(true);
//		     customBarR/enderer.setSeriesItemLabelsVisible(0,true);
//		     customBarRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition( 
//		    		 ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER)); 
//		    		 customBarRenderer.setItemLabelAnchorOffset(10D);// 设置柱形图上的文字偏离值 
//		    		 customBarRenderer.setItemLabelsVisible(true); 
//			 chart.getPlot().setBackgroundPaint(null);
//			 chart.getPlot().setBackgroundAlpha(0.0f);
		     File f = null;
		        try {  
		             f= new File("images/"+playerID+".png");  
		            ChartUtilities.saveChartAsPNG(f, chart, 700,500);  
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }
		        
		        playOffRegularCompareLabel.setIcon(new ImageIcon("images/"+playerID+".png"));
		        f.delete();
		        statisticsBl=new StatisticsBl();
		        boolean isShotEFFBetter=statisticsBl.isBetterThanRegular(playerID,"shotEFF");
		        boolean isrealShotBetter=statisticsBl.isBetterThanRegular(playerID,"realShot");
		        boolean isUseEFFBetter=statisticsBl.isBetterThanRegular(playerID,"useEFF");
		        boolean isfaultBetter=statisticsBl.isBetterThanRegular(playerID,"faultEFF");
		        boolean isstealBetter=statisticsBl.isBetterThanRegular(playerID,"stealEFF");

		        analysis.setText("根据"+playerID+"的常规赛季后赛数据\n运用假设检验分析对比得知该球员:\n季后赛投篮效率表现"+isBetter(isShotEFFBetter)+"常规赛"
		        		+ "\n季后赛真实命中率表现"+isBetter(isrealShotBetter)+"常规赛\n季后赛使用率表现"+isBetter(isUseEFFBetter)+"常规赛\n季后赛失误率"+isBetter(isfaultBetter)+"常规赛"
		        				+ "\n季后赛抢断表现"+isBetter(isstealBetter)+"常规赛\n");
		        nickName.setIcon(new ImageIcon("images/statistics/bigMatchPlayer.png"));
		}
		private String isBetter(boolean isBetter){
			if(!isBetter){
				return "优于";
			}
			else{
				return "差于";
			}
		}
		private CategoryDataset getDataSet1(String playerID) {
			 SeasonPlayer playerPlayOffPerform=statisticsBl.getPlayOffSeasonPlayer(playerID);
			 SeasonPlayer playerRegularPerform=statisticsBl.getRegularSeasonPlayer(playerID);
			 String xLine[]={"投篮效率","真实命中率%","使用率","失误率","抢断率"};
			 String valueField[]={"shotEFF","realShot","useEFF","faultEFF","stealEFF"};
			 Object[] playoff_value=playerPlayOffPerform.getSpeContent(valueField);
			 Object[] regular_value=playerRegularPerform.getSpeContent(valueField);
			 DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		       for(int i=0;i<xLine.length;i++){
		    	   if(i==1||i==0){
		    		   dataset.addValue((double)regular_value[i]*100,"常规赛",xLine[i]);
			    	   dataset.addValue((double)playoff_value[i]*100,"季后赛",xLine[i]);
		    	   }else{
		    	   dataset.addValue((double)regular_value[i],"常规赛",xLine[i]);
		    	   dataset.addValue((double)playoff_value[i],"季后赛",xLine[i]);
		       }
		       }
		        return dataset;  
		}
		private  void processChart(JFreeChart chart) {  
		       CategoryPlot plot = chart.getCategoryPlot();  
		       CategoryAxis domainAxis = plot.getDomainAxis();  
		       ValueAxis rAxis = plot.getRangeAxis();  
		       chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,  
		               RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);  
		       TextTitle textTitle = chart.getTitle();  
		       textTitle.setFont(MyFont.SMALL_PLAIN);  
		       domainAxis.setTickLabelFont(MyFont.SMALLEST_PLAIN);  
		       domainAxis.setLabelFont(MyFont.SMALL_PLAIN);  
		       rAxis.setTickLabelFont(MyFont.SMALL_PLAIN);  
		       rAxis.setLabelFont(MyFont.SMALL_PLAIN);  
		       chart.getLegend().setItemFont(MyFont.SMALLEST_PLAIN); 
		       // renderer.setItemLabelGenerator(new LabelGenerator(0.0));  
		       // renderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 12));  
		       // renderer.setItemLabelsVisible(true);  
		   }
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(nickName)){
				
			}
		}
		public void mouseEntered(MouseEvent e) {
			if(e.getSource().equals(nickName)){
				Point p=nickName.getLocation();
				nickName.setLocation(p.x+3, p.y+3);
			}
		}
		public void mouseExited(MouseEvent e) {
			if(e.getSource().equals(nickName)){
				Point p=nickName.getLocation();
				nickName.setLocation(p.x-3, p.y-3);
			}
		}
		public void mousePressed(MouseEvent arg0) {
			
		}
		public void mouseReleased(MouseEvent arg0) {
		}
	}
	class ForcastPanel extends MyPanel{
		private static final long serialVersionUID = 1L;
		private MyTextArea analysis=new MyTextArea();
		public ForcastPanel(String playerID){
			forcastDataLabel=new MyLabel();
			forcastDataLabel.setBounds(20,0,700,500);
			analysis.setBounds(750, 50, 400, 250);
			analysis.setFont(MyFont.SMALL_BOLD);
			this.add(forcastDataLabel);
			this.add(analysis);
			this.setContent(playerID);
		}
		private void setContent(String playerID) {
			CategoryDataset dataset = getDataSet(playerID);
			 JFreeChart chart = ChartFactory.createBarChart3D(  
		                "赛季高阶数据", // 图表标题  
		                "数据类型", // 目录轴的显示标签--横轴  
		                "", // 数值轴的显示标签--纵轴  
		                dataset, // 数据集  
		                PlotOrientation.VERTICAL, // 图表方向：水平、  
		                false, // 是否显示图例(对于简单的柱状图必须  
		                false, // 是否生成工具  
		                false // 是否生成URL链接  
		                );
			 processChart(chart);
			 chart.setBackgroundPaint(null);
			 chart.getPlot().setBackgroundAlpha(0.0f);
			 
			 CategoryPlot plot = (CategoryPlot) chart.getCategoryPlot(); 
		     BarRenderer3D customBarRenderer = (BarRenderer3D) plot.getRenderer(); 
		     customBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		     customBarRenderer.setBaseItemLabelsVisible(true);
//		     customBarR/enderer.setSeriesItemLabelsVisible(0,true);
//		     customBarRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition( 
//		    		 ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER)); 
//		    		 customBarRenderer.setItemLabelAnchorOffset(10D);// 设置柱形图上的文字偏离值 
//		    		 customBarRenderer.setItemLabelsVisible(true); 
//			 chart.getPlot().setBackgroundPaint(null);
//			 chart.getPlot().setBackgroundAlpha(0.0f);
		        try {  
		            File f = new File("images/"+playerID+"forcast.png");  
		            ChartUtilities.saveChartAsPNG(f, chart, 700,500);  
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }
		        
		        forcastDataLabel.setIcon(new ImageIcon("images/"+playerID+"forcast.png"));
		        statisticsBl=new StatisticsBl();
		        double resultPoint[]=statisticsBl.forcast(playerID, "point");
		        double resultRebound[]=statisticsBl.forcast(playerID, "totRebound");
		        double resultAst[]=statisticsBl.forcast(playerID, "assist");
		        double resultStl[]=statisticsBl.forcast(playerID, "steal");
		        double resultFau[]=statisticsBl.forcast(playerID, "fault");

				analysis.setText("根据"+playerID+"的常规赛数据\n运用参数估计得知该球员95%几率:\n得分在："+resultPoint[0]
		        		+ "\n篮板在："+resultRebound[0]+"\n助攻在："+resultAst[0]+"\n抢断在："+resultStl[0]
		        				+ "\n失误在："+resultFau[0]);
		}
		private CategoryDataset getDataSet(String playerID) { 
		 SeasonPlayer playerRegularPerform=statisticsBl.getRegularSeasonPlayer(playerID);
		 String xLine[]={"得分","篮板","助攻","抢断","失误"};
		 String valueField[]={"point","totRebound","assist","steal","fault"};
		 Object[] regular_value=playerRegularPerform.getSpeContent(valueField);
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
	       for(int i=0;i<xLine.length;i++){
	    	   dataset.addValue((double)regular_value[i],"1",xLine[i]);
	       }
	        return dataset;  }
		private  void processChart(JFreeChart chart) {  
		       CategoryPlot plot = chart.getCategoryPlot();  
		       CategoryAxis domainAxis = plot.getDomainAxis();  
		       ValueAxis rAxis = plot.getRangeAxis();  
		       chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,  
		               RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);  
		       TextTitle textTitle = chart.getTitle();  
		       textTitle.setFont(MyFont.SMALL_PLAIN);  
		       domainAxis.setTickLabelFont(MyFont.SMALLEST_PLAIN);  
		       domainAxis.setLabelFont(MyFont.SMALL_PLAIN);  
		       rAxis.setTickLabelFont(MyFont.SMALL_PLAIN);  
		       rAxis.setLabelFont(MyFont.SMALL_PLAIN);  
//		       chart.getLegend().setItemFont(MyFont.SMALLEST_PLAIN); 
		       // renderer.setItemLabelGenerator(new LabelGenerator(0.0));  
		       // renderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 12));  
		       // renderer.setItemLabelsVisible(true);  
		   }
	}
	class PlayerInfoPanel extends MyPanel implements MouseListener{
		protected  String playerID="jamesle01";
		private MyLabel portrait;
		private MyLabel playerNameLabel;
		private MyLabel changeplayer;
		private JPanel panel;
		private static final long serialVersionUID = 1L;
		public PlayerInfoPanel(JPanel parentPanel){
			this.panel=parentPanel;
			changeplayer=new MyLabel("更换球员");
			portrait=new MyLabel();
			playerNameLabel=new MyLabel();
			changeplayer.setBounds(150,60,120,40);
			portrait.setBounds(30,10,100,80);
			playerNameLabel.setBounds(150,10,120,50);
			this.add(portrait);
			this.add(playerNameLabel);
			this.add(changeplayer);
			this.setStyle();
			this.init();
			this.addListener();
		}
		private void addListener() {
			changeplayer.addMouseListener(this);
		}
		private void init() {
			portrait.setMyIcon(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE+playerID+".png"));//初始化换成ID
			playerNameLabel.setText("lebron james");
		}
		private void setStyle() {
			changeplayer.setFont(MyFont.SMALLEST_PLAIN);
			changeplayer.setHorizontalAlignment(SwingConstants.LEFT);
			playerNameLabel.setForeground(Color.black);
			playerNameLabel.setFont(MyFont.SMALL_PLAIN);
			portrait.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(changeplayer)){
				FindPlayerPopupPanel seasonFindPlayerPopupPanel=new FindPlayerPopupPanel();
				Point p=changeplayer.getLocation();
				seasonFindPlayerPopupPanel.show(panel,p.x, p.y+40);
			}		
		}
		public void mouseEntered(MouseEvent e) {
			if(e.getSource().equals(changeplayer)){
				changeplayer.setForeground(Color.pink);
			}
		}
		public void mouseExited(MouseEvent e) {
			if(e.getSource().equals(changeplayer)){
				changeplayer.setForeground(Color.white);
			}
		}
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseReleased(MouseEvent e) {
			
		}
		class FindPlayerPopupPanel extends JPopupMenu {
			private static final long serialVersionUID = 1L;
			public FindPlayerPanel playerPanel;

			public FindPlayerPopupPanel() {
				playerPanel = new FindPlayerPanel();
				playerPanel.setPreferredSize(new Dimension(600, 400));
				// this.setOpaque(false);
				// this.setBorderPainted(false);
				this.setBackground(Color.white);
				this.setLayout(new BorderLayout());
				this.add(playerPanel, BorderLayout.CENTER);
			}

			class FindPlayerPanel extends MyPanel implements MouseListener {
				private JTextField nameInput;
				private JButton search;
				private MyLabel playerPortrait[] = new MyLabel[5];
				public SearchButton playerName[] = new SearchButton[5];
				private static final long serialVersionUID = 1L;

				public FindPlayerPanel() {
					PlayerHotBlSrevice pbs = new PlayerHotBl();
					ArrayList<PlayerKingInfo> initArray = pbs.getPlayerKingOfSeason(
							Season.this_season, Field.point);
					search = new JButton("查找");
					search.setBounds(415, 10, 100, 40);
					search.addMouseListener(this);
					nameInput = new JTextField();
					nameInput.setBounds(100, 10, 300, 40);
					this.add(nameInput);
					this.add(search);
					for (int i = 0; i < 5; i++) {
						playerPortrait[i] = new MyLabel();
						playerName[i] = new SearchButton(initArray.get(i).getPlayerName(),initArray.get(i).getPlayerId());
						playerName[i].setForeground(Color.black);
						playerName[i].setFont(MyFont.SMALLEST_PLAIN);
						playerPortrait[i].setBounds(25, i * 65 + 60, 65, 50);
						playerName[i].setBounds(100, i * 65 + 60, 200, 50);
						playerPortrait[i].setMyIcon(new ImageIcon(
								PathOfFile.PLAYER_PORTRAIT_IMAGE
										+ initArray.get(i).getPlayerId() + ".png"));
						playerName[i].addMouseListener(this);
						this.add(playerPortrait[i]);
						this.add(playerName[i]);
					}
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					for(int i=0;i<5;i++){
						if(e.getSource().equals(playerName[i])){
							portrait.setMyIcon(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE+playerName[i].getplayerID()+".png"));
							System.out.print(playerName[i].getplayerID());
							playerID=playerName[i].getplayerID();
							playerNameLabel.setText(playerName[i].getText());
							playOffRegularComparePanel.setContent(playerName[i].getplayerID());
							forcastPanel.setContent(playerName[i].getplayerID());
							break;
						}
					}
				
					if (e.getSource().equals(search)) {
						for (int i = 0; i < 5; i++) {
							// playerName[i].setText("");
							// playerPortrait[i].setMyIcon(new
							// ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE+initArray.get(i).getName()+".png"));
						}
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					for(int i=0;i<5;i++){
						if(e.getSource().equals(playerName[i])){
							Point p=playerName[i].getLocation();
							playerName[i].setLocation(p.x+3, p.y+3);
							break;
						}
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					for(int i=0;i<5;i++){
						if(e.getSource().equals(playerName[i])){
							Point p=playerName[i].getLocation();
							playerName[i].setLocation(p.x-3, p.y-3);
							break;
						}
					}
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

			}
			class SearchButton extends JButton{
				private static final long serialVersionUID = 1L;
				private String playerID;
				public SearchButton(String name,String ID){
					super(name);
					this.setBorderPainted(false);
					this.setFocusable(false);
					this.setContentAreaFilled(false);
					playerID=ID;
				}
				public String getplayerID(){
					return this.playerID;
				}
				
			}
		}
	}

}
