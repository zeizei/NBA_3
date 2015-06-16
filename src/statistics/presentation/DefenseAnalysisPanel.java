package statistics.presentation;

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
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import presentation.mycomponent.MyComboBox;
import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.mycomponent.MyTextArea;
import presentation.statics.MyFont;
import presentation.statics.NUMBER;
import presentation.statics.PathOfFile;

public class DefenseAnalysisPanel extends MyPanel {
	private static final long serialVersionUID = 1L;
	private DefenseAnalysisPanel defenseAnalysisPanel=this;
	private NavigationPanel navigationPanel;
	private ContentPanel contentPanel;
	private MyLabel portrait;
	private MyTextArea playerInfo;
	private MyLabel changeplayer;
	private String seasonStr[]={"13-14","14-15"};
	private MyLabel defenseLabel;
	private MyComboBox<String> season;
	public DefenseAnalysisPanel(String playerID) {
		navigationPanel=new NavigationPanel();
		contentPanel=new ContentPanel();
		navigationPanel.setBounds(0, 0, NUMBER.FRAME_WIDTH-150,100);
		contentPanel.setBounds(0,100,NUMBER.FRAME_WIDTH-150,NUMBER.FRAME_HEIGHT-NUMBER.NAVIGATION_PANEL_HEIGHT-100);
		this.add(navigationPanel);
		this.add(contentPanel);	
	}
	class NavigationPanel extends MyPanel implements MouseListener{
		
		private static final long serialVersionUID = 1L;
		public NavigationPanel(){
		changeplayer=new MyLabel("更换球员");
		season=new MyComboBox<String>(seasonStr);
		portrait=new MyLabel();
		playerInfo=new MyTextArea();
		changeplayer.setBounds(130,60,120,40);
		season.setBounds(260, 35, 100, 30);
		portrait.setBounds(10,10,100,80);
		playerInfo.setBounds(130,10,120,50);
		this.add(portrait);
		this.add(season);
		this.add(playerInfo);
		this.add(changeplayer);
		playerInfo.setFont(MyFont.SMALL_PLAIN);
		changeplayer.setFont(MyFont.SMALLEST_PLAIN);
		changeplayer.setHorizontalAlignment(SwingConstants.LEFT);
		playerInfo.setForeground(Color.black);
		portrait.setBorder(BorderFactory.createLineBorder(Color.black));
		changeplayer.addMouseListener(this);
		playerInfo.setText("Kobe Bryant\nSG/24");
		portrait.setMyIcon(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE+"Kobe Bryant.png"));
		}
		
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(changeplayer)){
				SeasonFindPlayerPopupPanel seasonFindPlayerPopupPanel=new SeasonFindPlayerPopupPanel();
				Point p=changeplayer.getLocation();
				seasonFindPlayerPopupPanel.show(defenseAnalysisPanel,p.x, p.y+40);
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
	class ContentPanel extends JPanel{
		
		private static final long serialVersionUID = 1L;
		private MyTextArea analysis=new MyTextArea();
		private MyLabel nickName=new MyLabel();
		public ContentPanel(){
			this.setOpaque(false);
			defenseLabel=new MyLabel();
			defenseLabel.setBounds(20,0,700,500);
			nickName.setBounds(800, 300, 200, 150);
			analysis.setBounds(750, 50, 400, 250);
			analysis.setFont(MyFont.SMALL_BOLD);
			this.add(defenseLabel);
			this.add(analysis);
			this.add(nickName);
			this.setContent();
		}
		private void setContent() {
			CategoryDataset dataset = getDataSet1();
			 JFreeChart chart = ChartFactory.createLineChart(  
					 "在场/下场对比", // 图表标题  
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
			 
//			 CategoryPlot plot = (CategoryPlot) chart.getCategoryPlot(); 
//		     BarRenderer3D customBarRenderer = (BarRenderer3D) plot.getRenderer(); 
//		     customBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
//		     customBarRenderer.setBaseItemLabelsVisible(true);
//		     customBarR/enderer.setSeriesItemLabelsVisible(0,true);
//		     customBarRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition( 
//		    		 ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER)); 
//		    		 customBarRenderer.setItemLabelAnchorOffset(10D);// 设置柱形图上的文字偏离值 
//		    		 customBarRenderer.setItemLabelsVisible(true); 
//			 chart.getPlot().setBackgroundPaint(null);
//			 chart.getPlot().setBackgroundAlpha(0.0f);
		        try {  
		            File f = new File("images/hi.png");  
		            ChartUtilities.saveChartAsPNG(f, chart, 700,500);  
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }
		        
		        defenseLabel.setIcon(new ImageIcon("images/hi.png"));
		        analysis.setText("根据Kobe Bryant的常规赛季后赛数据\n运用假设检验分析对比得知该球员:\n季后赛得分表现优于常规赛\n季后赛助攻表现优于常规赛\n季后赛助攻表现优于常规赛\n季后赛助攻表现优于常规赛\n季后赛助攻表现优于常规赛\n");
		        nickName.setIcon(new ImageIcon("images/statistics/bigMatchPlayer.png"));
		}
		private CategoryDataset getDataSet1() {
			 DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
			 String[] x={
					 "有效命中%","进攻篮板率","防守篮板率","总篮板率","助攻率","抢断率","盖帽率","失误率"
			 };//x轴
			 double[] onCourt_Value={
					 50,27,75,51,5,8,7,15
			 };
			 double[] offCourt_Value={
					 53,11,21,4,3,1,1,15
			 };
			 String onCourtLine="在场";
			 String offCourtLine="不在场";
		        for(int i=0;i<x.length;i++){
		        	dataset.addValue(onCourt_Value[i], onCourtLine, x[i]);
		        	dataset.addValue(offCourt_Value[i], offCourtLine, x[i]);
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
	}
	class SeasonFindPlayerPopupPanel extends FindPlayerPopupPanel implements MouseListener{
		private static final long serialVersionUID = 1L;
		public SeasonFindPlayerPopupPanel(){
			for(int i=0;i<playerPanel.playerName.length;i++){
				this.playerPanel.playerName[i].addMouseListener(this);
			}
		}
		public void mouseClicked(MouseEvent e) {
			for(int i=0;i<10;i++){
				if(e.getSource().equals(playerPanel.playerName[i])){
					portrait.setMyIcon(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE+playerPanel.playerName[i].getText()+".png"));
					break;
				}
			}
		}
		public void mouseEntered(MouseEvent e) {
			for(int i=0;i<10;i++){
				if(e.getSource().equals(playerPanel.playerName[i])){
					Point p=playerPanel.playerName[i].getLocation();
					playerPanel.playerName[i].setLocation(p.x+3, p.y+3);
					break;
				}
			}
					
		}
		public void mouseExited(MouseEvent e) {
			for(int i=0;i<10;i++){
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
}
