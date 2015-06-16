package statistics.presentation;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
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
	public SeasonAnalysisPanel(String playerID){
		playerInfoPanel=new PlayerInfoPanel();
		navigationPanel=new NavigationPanel();
		contentPanel=new ContentPanel();
		playerInfoPanel.setBounds(0, 0, 300, 100);
		navigationPanel.setBounds(300, 0, NUMBER.FRAME_WIDTH-450,100);
		contentPanel.setBounds(0,100,NUMBER.FRAME_WIDTH-150,NUMBER.FRAME_HEIGHT-NUMBER.NAVIGATION_PANEL_HEIGHT-100);
		this.add(navigationPanel);
		this.add(playerInfoPanel);
		this.add(contentPanel);	
	}
	class SeasonFindPlayerPopupPanel extends FindPlayerPopupPanel implements MouseListener{
		private static final long serialVersionUID = 1L;
		public SeasonFindPlayerPopupPanel(){
			for(int i=0;i<playerPanel.playerName.length;i++){
				this.playerPanel.playerName[i].addMouseListener(this);
			}
		}
		public void mouseClicked(MouseEvent e) {
			for(int i=0;i<5;i++){
				if(e.getSource().equals(playerPanel.playerName[i])){
//					portrait.setMyIcon(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE+playerPanel.playerName[i].getplayerID()+".png"));
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
	class NavigationPanel extends MyPanel implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
//		
//		private static final long serialVersionUID = 1L;
//		public NavigationPanel(){
//		forecast=new MyLabel("实力预测");
//		playOffRegularCompare=new MyLabel("季后/常规对比");
//		changeplayer=new MyLabel("更换球员");
//		portrait=new MyLabel();
//		playerInfo=new MyTextArea();
//		changeplayer.setBounds(150,60,120,40);
//		portrait.setBounds(30,10,100,80);
//		playerInfo.setBounds(150,10,120,50);
//		forecast.setBounds(410, 35, 100, 30);
//		playOffRegularCompare.setBounds(510, 35, 100, 30);
//		this.add(portrait);
//		this.add(playerInfo);
//		this.add(changeplayer);
//		this.add(forecast);
//		this.add(playOffRegularCompare);
//		forecast.addMouseListener(this);
//		playOffRegularCompare.addMouseListener(this);
//		playerInfo.setFont(MyFont.SMALL_PLAIN);
//		changeplayer.setFont(MyFont.SMALLEST_PLAIN);
//		changeplayer.setHorizontalAlignment(SwingConstants.LEFT);
//		playerInfo.setForeground(Color.black);
//		portrait.setBorder(BorderFactory.createLineBorder(Color.black));
//		changeplayer.addMouseListener(this);
//		playerInfo.setText("Kobe Bryant\nSG/24");
//		portrait.setMyIcon(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE+"Kobe Bryant.png"));//初始化换成ID
//		}
//		
//		public void mouseClicked(MouseEvent e) {
//			if(e.getSource().equals(changeplayer)){
//				SeasonFindPlayerPopupPanel seasonFindPlayerPopupPanel=new SeasonFindPlayerPopupPanel();
//				Point p=changeplayer.getLocation();
//				seasonFindPlayerPopupPanel.show(seasonAnalysisPanel,p.x, p.y+40);
//			}
//			else if(e.getSource().equals(forecast)){
//				contentPanel.showMyPanel("forcastPanel");
//			}
//			else if(e.getSource().equals(playOffRegularCompare)){
//				contentPanel.showMyPanel("playOffRegularCompare");
//			}
//		}
//		public void mouseEntered(MouseEvent e) {
//			if(e.getSource().equals(changeplayer)){
//				changeplayer.setForeground(Color.pink);
//			}
//		}
//		public void mouseExited(MouseEvent e) {
//			if(e.getSource().equals(changeplayer)){
//				changeplayer.setForeground(Color.white);
//			}
//		}
//		public void mousePressed(MouseEvent arg0) {
//			
//		}
//		public void mouseReleased(MouseEvent arg0) {
//		}
	}
	class ContentPanel extends JPanel{
		CardLayout card=new CardLayout();
		PlayOffRegularComparePanel playOffRegularComparePanel;
		ForcastPanel forcastPanel;
		private static final long serialVersionUID = 1L;
		public ContentPanel(){
			this.setOpaque(false);
			this.setLayout(card);
			playOffRegularComparePanel=new PlayOffRegularComparePanel();
			forcastPanel=new ForcastPanel();
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
		public PlayOffRegularComparePanel(){
			
			playOffRegularCompareLabel=new MyLabel();
			playOffRegularCompareLabel.setBounds(20,0,700,500);
			nickName.setBounds(800, 300, 200, 150);
			analysis.setBounds(750, 50, 400, 250);
			analysis.setFont(MyFont.SMALL_BOLD);
			this.add(playOffRegularCompareLabel);
			this.add(analysis);
			this.add(nickName);
			this.setContent();
			nickName.addMouseListener(this);
		}
		private void setContent() {
			CategoryDataset dataset = getDataSet1();
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
		        try {  
		            File f = new File("images/fruit.png");  
		            ChartUtilities.saveChartAsPNG(f, chart, 700,500);  
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }
		        
		        playOffRegularCompareLabel.setIcon(new ImageIcon("images/fruit.png"));
		        analysis.setText("根据Kobe Bryant的常规赛季后赛数据\n运用假设检验分析对比得知该球员:\n季后赛得分表现优于常规赛\n季后赛助攻表现优于常规赛\n季后赛助攻表现优于常规赛\n季后赛助攻表现优于常规赛\n季后赛助攻表现优于常规赛\n");
		        nickName.setIcon(new ImageIcon("images/statistics/bigMatchPlayer.png"));
		}
		private CategoryDataset getDataSet1() {
			 DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		        dataset.addValue(25, "常规赛", "得分");  
		        dataset.addValue(29.7, "季后赛", "得分");  
		        dataset.addValue(10.9,"常规赛", "助攻");  
		        dataset.addValue(8.9, "季后赛", "助攻");  
		        dataset.addValue(4.0, "常规赛", "篮板");  
		        dataset.addValue(4.5,"季后赛", "篮板");  
		        dataset.addValue(50.8, "常规赛", "命中率%");  
		        dataset.addValue(52.3,"季后赛", "命中率%");  
		        dataset.addValue(78, "常规赛", "罚球%");  
		        dataset.addValue(89,"季后赛","罚球%"); 
		        dataset.addValue(21,"常规赛","WS");
		        dataset.addValue(25,"季后赛","WS");
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
		public ForcastPanel(){
			forcastDataLabel=new MyLabel();
			forcastDataLabel.setBounds(20,0,700,500);
			analysis.setBounds(750, 50, 400, 250);
			analysis.setFont(MyFont.SMALL_BOLD);
			this.add(forcastDataLabel);
			this.add(analysis);
			this.setContent();
		}
		private void setContent() {
			CategoryDataset dataset = getDataSet();
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
		            File f = new File("images/happy.png");  
		            ChartUtilities.saveChartAsPNG(f, chart, 700,500);  
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }
		        
		        forcastDataLabel.setIcon(new ImageIcon("images/happy.png"));
		}
		private CategoryDataset getDataSet() {
			 DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		        dataset.addValue(25, "1", "球员效率");  
		        dataset.addValue(29.7, "1", "真实命中率");  
		        dataset.addValue(10.9,"1", "进攻贡献");  
		        dataset.addValue(8.9, "1", "防守贡献");  
		        dataset.addValue(4.0, "1", "胜利贡献");  
		        dataset.addValue(4.5,"1", "BPM");  
		        dataset.addValue(50.8, "1", "VORP");  
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
//		       chart.getLegend().setItemFont(MyFont.SMALLEST_PLAIN); 
		       // renderer.setItemLabelGenerator(new LabelGenerator(0.0));  
		       // renderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 12));  
		       // renderer.setItemLabelsVisible(true);  
		   }
	}
}
