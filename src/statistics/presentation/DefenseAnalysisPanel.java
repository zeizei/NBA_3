package statistics.presentation;

import java.awt.Color;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

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

import beans.GamePlayer;
import beans.SeasonPlayer;
import presentation.mycomponent.MyComboBox;
import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.mycomponent.MyTextArea;
import presentation.statics.MyFont;
import presentation.statics.NUMBER;
import presentation.statics.PathOfFile;
import statistics.analysisbeans.PlayerOnOrOff;
import statistics.bl.StatisticsBl;
import statistics.blservice.StatisticsBlService;

public class DefenseAnalysisPanel extends MyPanel {
	private static final long serialVersionUID = 1L;
	private DefenseAnalysisPanel defenseAnalysisPanel=this;
	private NavigationPanel navigationPanel;
	private ContentPanel contentPanel;
	private MyLabel portrait;
	private MyTextArea playerInfo;
	private MyLabel changeplayer;
	private MyLabel defenseLabel;
	private String playerID;
	private StatisticsBlService statisticsBl=new StatisticsBl();
	public DefenseAnalysisPanel(String playerID) {
		this.playerID=playerID;
		navigationPanel=new NavigationPanel();
		contentPanel=new ContentPanel(playerID);
		navigationPanel.setBounds(0, 0, NUMBER.FRAME_WIDTH-150,100);
		contentPanel.setBounds(0,100,NUMBER.FRAME_WIDTH-150,NUMBER.FRAME_HEIGHT-NUMBER.NAVIGATION_PANEL_HEIGHT-100);
		this.add(navigationPanel);
		this.add(contentPanel);	
	}
	class NavigationPanel extends MyPanel implements MouseListener{
		
		private static final long serialVersionUID = 1L;
		public NavigationPanel(){
		changeplayer=new MyLabel("更换球员");
		portrait=new MyLabel();
		playerInfo=new MyTextArea();
		changeplayer.setBounds(130,60,120,40);
		portrait.setBounds(10,10,100,80);
		playerInfo.setBounds(130,10,120,50);
		this.add(portrait);
		this.add(playerInfo);
		this.add(changeplayer);
		playerInfo.setFont(MyFont.SMALL_PLAIN);
		changeplayer.setFont(MyFont.SMALLEST_PLAIN);
		changeplayer.setHorizontalAlignment(SwingConstants.LEFT);
		playerInfo.setForeground(Color.black);
		portrait.setBorder(BorderFactory.createLineBorder(Color.black));
		changeplayer.addMouseListener(this);
		playerInfo.setText(playerID);
		portrait.setMyIcon(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE+playerID+".png"));//初始化换成ID
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
		public ContentPanel(String playerID){
			this.setOpaque(false);
			defenseLabel=new MyLabel();
			defenseLabel.setBounds(20,0,700,500);
			nickName.setBounds(800, 300, 200, 150);
			analysis.setBounds(750, 50, 400, 250);
			analysis.setFont(MyFont.SMALL_BOLD);
			this.add(defenseLabel);
			this.add(analysis);
			this.add(nickName);
			this.setContent(playerID);
		}
		private void setContent(String playerID) {
			CategoryDataset dataset = getDataSet1(playerID);
			 JFreeChart chart = ChartFactory.createLineChart(  
					 "常规赛季后赛对比", // 图表标题  
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
		            File f = new File("images/"+playerID+"_defense.png");  
		            ChartUtilities.saveChartAsPNG(f, chart, 700,500);  
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }
				boolean isoffReboundEFFBetter=statisticsBl.isBetterThanRegular(playerID, "offReboundEFF");
				boolean isdefReboundEFFBetter=statisticsBl.isBetterThanRegular(playerID, "defReboundEFF");
				boolean istotReboundEFFBetter=statisticsBl.isBetterThanRegular(playerID, "totReboundEFF");
				boolean isdefEFFReboundEFFBetter=statisticsBl.isBetterThanRegular(playerID, "defEFF");
		        defenseLabel.setIcon(new ImageIcon("images/"+playerID+"_defense.png"));
		        analysis.setText("根据"+playerID+"的常规赛季后赛数据\n运用假设检验分析对比得知该球员:\n季后赛进攻篮板表现"+isBetter(isoffReboundEFFBetter)+"常规赛"
		        		+ "\n季后赛防守篮板表现"+isBetter(isdefReboundEFFBetter)+"常规赛\n总篮板表现"+isBetter(istotReboundEFFBetter)+"常规赛\n季后赛防守效率"+isBetter(isdefEFFReboundEFFBetter)+"常规赛");
		        nickName.setIcon(new ImageIcon("images/statistics/bigMatchPlayer.png"));
		}
		private String isBetter(boolean isBetter){
			if(!isBetter){
				return "显著优于";
			}
			else{
				return "不显著优于";
			}
		}
		private CategoryDataset getDataSet1(String playerID) {
			 SeasonPlayer playOffSeasonPerform=statisticsBl.getPlayOffSeasonPlayer(playerID);
			 SeasonPlayer regularSeasonPerform=statisticsBl.getRegularSeasonPlayer(playerID);
			
			 DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
			 String[] x={
					"offReboundEFF","defReboundEFF","defWinShare","winShare"
			 };
			 boolean hasPlayOff=true;
			 String line_playOff="季后赛";
			 String line_regular="常规赛";
			 Object[]  playOffValue = null;
			 if(playOffSeasonPerform.getSpeContent(x)==null){
				 hasPlayOff=false;
			 }
			 else{
				 playOffValue= playOffSeasonPerform.getSpeContent(x);
			 }
			 Object[]  regularValue= regularSeasonPerform.getSpeContent(x);
			 for(int i=0;i<x.length;i++){
				 dataset.setValue((double)regularValue[i], line_regular, x[i]);
				 if(hasPlayOff){
				 dataset.setValue((double)playOffValue[i], line_playOff, x[i]);
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
	}
	class SeasonFindPlayerPopupPanel extends FindPlayerPopupPanel implements MouseListener{
		private static final long serialVersionUID = 1L;
		public SeasonFindPlayerPopupPanel(){
			for(int i=0;i<playerPanel.playerName.length;i++){
				this.playerPanel.playerName[i].addMouseListener(this);
			}
			playerPanel.search.addMouseListener(this);
		}
		public void mouseClicked(MouseEvent e) {
			for(int i=0;i<5;i++){
				if(e.getSource().equals(playerPanel.playerName[i])){
					portrait.setMyIcon(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE+playerPanel.playerName[i].getplayerID()+".png"));
					playerInfo.setText(playerPanel.playerName[i].getText());
					contentPanel.setContent(playerPanel.playerName[i].getplayerID());
					break;
				}
			}
			if(e.getSource().equals(playerPanel.search)){
				 ArrayList<SeasonPlayer> searchResult=statisticsBl.vagueSearchPlayer(playerPanel.nameInput.getText());
				 for(int i=0;i<5;i++){
					 playerPanel.playerName[i].setText("");
					 playerPanel.playerName[i].setID("");
					 playerPanel.playerPortrait[i].setMyIcon(new
							 ImageIcon(""));
				 }
				 if(searchResult.size()<5){
				for (int i = 0; i < searchResult.size(); i++) {
					playerPanel.playerName[i].setText(searchResult.get(i).getPlayerName());
					playerPanel.playerName[i].setID(searchResult.get(i).getPlayerId());
					playerPanel.playerPortrait[i].setMyIcon(new
					 ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE+searchResult.get(i).getPlayerId()+".png"));
				}
			}
				 else{
						for (int i = 0; i < 5; i++) {
							playerPanel.playerName[i].setText(searchResult.get(i).getPlayerName());
							playerPanel.playerName[i].setID(searchResult.get(i).getPlayerId());
							playerPanel.playerPortrait[i].setMyIcon(new
							 ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE+searchResult.get(i).getPlayerId()+".png"));
						}
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
}
