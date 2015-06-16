package statistics.presentation;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.mycomponent.MyTextArea;
import presentation.statics.MyFont;
import presentation.statics.PathOfFile;


public class OffenseAnalysisPanel extends MyPanel {
	
	private static final long serialVersionUID = 1L;
	private OffenseAnalysisPanel offenseAnalysisPanel=this;
	private NavigationPanel navigationPanel;
	private ContainerPanel containerPanel;
	private MyLabel changeplayer;
	private MyLabel portrait;
	private MyTextArea playerInfo;
	private MyLabel faceToOpponent,shotDistance,onOff;
	public OffenseAnalysisPanel(){
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
		private MyPanel faceToTeamsPanel,shootDistanceTypePanel,onOFFPanel;
		
		private CardLayout card;
		public ContainerPanel(){
			this.setOpaque(false);
			this.createObjects();
			this.setContainerPanel();
		}
		private void createObjects() {
			faceToTeamsPanel=new FaceToTeamsPanel();
			shootDistanceTypePanel=new ShootDistanceTypePanel();
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

		public FaceToTeamsPanel(){
			
		}
	}
	class ShootDistanceTypePanel extends MyPanel{
	
		private static final long serialVersionUID = 1L;

		public ShootDistanceTypePanel(){
			
		}
	}
	class OnOFFPanel extends MyPanel{
		
		private static final long serialVersionUID = 1L;

		public OnOFFPanel(){
			
		}
	}
}
