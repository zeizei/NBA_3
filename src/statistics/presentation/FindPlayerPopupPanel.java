//缺模糊查找
package statistics.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.statics.MyFont;
import presentation.statics.PathOfFile;
import businesslogic.hot.PlayerHotBl;
import businesslogicservice.hot.PlayerHotBlSrevice;
import common.datastructure.PlayerKingInfo;
import common.statics.Field;
import common.statics.Season;

public class FindPlayerPopupPanel extends JPopupMenu {
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
				this.add(playerPortrait[i]);
				this.add(playerName[i]);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource().equals(search)) {
				for (int i = 0; i < 5; i++) {
					// playerName[i].setText("");
					// playerPortrait[i].setMyIcon(new
					// ImageIcon(PathOfFile.PLAYER_PORTRAIT_IMAGE+initArray.get(i).getName()+".png"));
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

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