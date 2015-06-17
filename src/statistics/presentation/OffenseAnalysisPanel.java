package statistics.presentation;

import java.awt.CardLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import beans.SeasonPlayer;
import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.mycomponent.MyTable;
import presentation.mycomponent.MyTableModel;
import presentation.mycomponent.MyTextArea;
import presentation.statics.MyFont;
import presentation.statics.PathOfFile;
import statistics.analysisbeans.PlayerOnOrOff;
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
	private MyLabel OnOFFPanelDataLabel;
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
		onOff=new MyLabel("季后赛/常规赛进攻对比");
		changeplayer=new MyLabel("更换球员");
		portrait=new MyLabel();
		playerInfo=new MyTextArea();
		changeplayer.setBounds(150,60,120,40);
		portrait.setBounds(30,10,100,80);
		playerInfo.setBounds(150,10,120,50);
		faceToOpponent.setBounds(410, 35, 100, 30);
		shotDistance.setBounds(510, 35, 100, 30);
		onOff.setBounds(610, 35, 180, 30);
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
		playerInfo.setText(playerID);
		portrait.setMyIcon(new ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE+playerID+".png"));//初始化换成ID
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
					onOFFPanel.setContent(playerPanel.playerName[i].getplayerID());
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
			onOFFPanel=new OnOFFPanel(playerID);
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
		String[] head={"对手","命中数","出手数","命中率","有效命中率","受助功数","受助攻率"};
		String[] field={"opponent","fg","fga","fgr","efg","astd","astdr"};
		private MyLabel opponentShotLabel;
		public FaceToTeamsPanel(String playerID){
			opponentShotLabel=new MyLabel();
			opponentShotLabel.setBounds(20,0,1100,550);
			this.add(opponentShotLabel);
			setContent(playerID);
		}
		private void setContent(String playerID) {

			CategoryDataset dataset = getDataSet1(playerID);
			 JFreeChart chart = ChartFactory.createLineChart(  
					 "面对不同对手命中率", // 图表标题  
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
			   File f=null;
		        try {  
		           f = new File("images/"+playerID+"_oppoentShot.png");  
		            ChartUtilities.saveChartAsPNG(f, chart, 1100,550);  
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }
				opponentShotLabel.setIcon(new ImageIcon("images/"+playerID+"_oppoentShot.png"));
				f.delete();
		}
		private CategoryDataset getDataSet1(String playerID) {
			 ArrayList<PlayerShootOpponent> faceToOppontShootPerform=statisticsBl.getRegularSeasonOpponentShoot(playerID);
			 DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
			 String[] opponent = new String[faceToOppontShootPerform.size()];
			 double[] value = new double[faceToOppontShootPerform.size()];
			 double[] value_Shot =new double[faceToOppontShootPerform.size()];
			 for(int i=0;i<faceToOppontShootPerform.size();i++){
				 opponent[i]=faceToOppontShootPerform.get(i).getOpponent();
				 value[i]=faceToOppontShootPerform.get(i).getFgr();
				 value_Shot[i]=faceToOppontShootPerform.get(i).getEfg();
			 }
			 String line_fgr="命中率%";
			 String line_efgr="有效命中率";
		     
			 for(int i=0;i<opponent.length;i++){
				 dataset.setValue(value[i], line_fgr, opponent[i]);
				 dataset.setValue(value_Shot[i], line_efgr, opponent[i]);
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
			shotDistanceJScrollPane.setBounds(500, 20, 500, 300);
			shotDistancetable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			this.add(shotDistanceJScrollPane);
			
			shotTypePieIcon=new MyLabel();
			shotTypePieIcon.setBounds(20, 20, 400, 400);
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
			            ChartUtilities.saveChartAsPNG(f, chart, 400,400);  
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
		private MyTextArea analysis=new MyTextArea();
		public OnOFFPanel(String playerID){
			OnOFFPanelDataLabel=new MyLabel();
			OnOFFPanelDataLabel.setBounds(20,0,700,500);
			analysis.setBounds(750, 50, 400, 250);
			analysis.setFont(MyFont.SMALL_BOLD);
			this.add(OnOFFPanelDataLabel);
			this.add(analysis);
			this.setContent(playerID);
		}
		private void setContent(String playerID) {
			CategoryDataset dataset = getDataSet(playerID);
			 JFreeChart chart = ChartFactory.createBarChart3D(  
		                "季后赛/常规赛进攻对比", // 图表标题  
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
		     File f =null;
		        try {  
		            f= new File("images/"+playerID+"offenseforcast.png");  
		            ChartUtilities.saveChartAsPNG(f, chart, 700,500);  
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }
		        
		        boolean isshotEFFBetter=statisticsBl.isBetterThanRegular(playerID, "shotEFF");
				boolean isassistEFFBetter=statisticsBl.isBetterThanRegular(playerID, "assistEFF");
				boolean isoffEFFBetter=statisticsBl.isBetterThanRegular(playerID, "offEFF");
		        
		        OnOFFPanelDataLabel.setIcon(new ImageIcon("images/"+playerID+"offenseforcast.png"));
		        f.delete();
		        statisticsBl=new StatisticsBl();

				analysis.setText("根据"+playerID+"的常规赛季后赛数据\n运用假设检验分析对比得知该球员:\n季后赛投篮效率表现"+isBetter(isshotEFFBetter)+"常规赛"
		        		+ "\n季后赛助攻效率表现"+isBetter(isassistEFFBetter)+"常规赛\n进攻效率表现"+isBetter(isoffEFFBetter)+"常规赛");
		      }
		private String isBetter(boolean isBetter){
			if(!isBetter){
				return "显著优于";
			}
			else{
				return "不显著优于";
			}
		}
		private CategoryDataset getDataSet(String playerID) { 
		 SeasonPlayer playerRegularPerform=statisticsBl.getRegularSeasonPlayer(playerID);
		 SeasonPlayer playerPlayOffPerform=statisticsBl.getPlayOffSeasonPlayer(playerID);
		 String xLine[]={"投篮效率%","助攻效率","进攻贡献值","贡献值"};
		 String valueField[]={"shotEFF","assistEFF","offWinShare","winShare"};
		 Object[] regular_value=playerRegularPerform.getSpeContent(valueField);
		 Object[] playOff_value=playerPlayOffPerform.getSpeContent(valueField);
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
	       for(int i=0;i<xLine.length;i++){
	    	   if(i==0){
	    		   dataset.addValue((double)regular_value[i]*100,"常规赛",xLine[i]);
		    	   dataset.addValue((double)playOff_value[i]*100,"季后赛",xLine[i]);
	    	   }
	    	   else{
	    	   dataset.addValue((double)regular_value[i],"常规赛",xLine[i]);
	    	   dataset.addValue((double)playOff_value[i],"季后赛",xLine[i]);
	       }
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
}
