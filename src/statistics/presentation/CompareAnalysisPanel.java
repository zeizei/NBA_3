package statistics.presentation;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import presentation.mycomponent.MyButton;
import presentation.mycomponent.MyComboBox;
import presentation.mycomponent.MyLabel;
import presentation.mycomponent.MyPanel;
import presentation.statics.MyColor;
import presentation.statics.MyFont;

import common.statics.GameKind;

public class CompareAnalysisPanel extends MyPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private ContentPanel contentPanel;
	private MyPanel[] panels;
	private DataComparisonPanel dataComparisonPanel;
	private OffensiveComparisonPanel offensiveComparisonPanel;
	private JTextField firstPlayerInput, secondPlayerInput;
	private JButton firstFindButton, secondFindButton;
	private DifensiveComparisonPanel difensiveComparisonPanel;
	private MyLabel firstPlayerLabel, secondPlayerLabel;
	private MyComboBox<String> firstGameKind, secondGameKind;
	private ButtonPressed dataAnalysisButton, offensiveAnaylisisButton,
			difensiveAnalysisButton;
	private String[] gameKindArray = { GameKind.regular_game.toString(),
			GameKind.playOff_game.toString() };
	private int mycombobox_x = 80;
	private int mycobobox_y = 30;
	private int playerLabel_xy = 100;
	private int mycombobox_width = 120;
	private int mycombobox_height = 40;
	private int button_y = 140;
	private int button_w = 150;
	private int button_h = 50;
	private int panel_w = mycombobox_width * 2 + mycombobox_height + 730;
	private int panel_h = 360;

	public CompareAnalysisPanel() {
		this.createObjects();
		this.init();
		this.setComponentPosition();
		this.setStyle();
		this.addListener();
		this.setVisible(true);
	}

	class ContentPanel extends MyPanel {
		private static final long serialVersionUID = 1L;
		private CardLayout card;

		ContentPanel() {
			card = new CardLayout();
			this.setLayout(card);
			for (int i = 0; i < 3; i++) {
				this.add(panels[i], String.valueOf(i));
			}
			this.setVisible(true);
		}

		public void showMyPanel(int i) {
			this.card.show(contentPanel, String.valueOf(i));
		}
	}

	class DataComparisonPanel extends MyPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DataComparisonPanel() {
			this.createObjects();
			this.init();
			this.setComponentPosition();
			this.setStyle();
			this.setVisible(true);
		}

		private void setStyle() {
			// TODO Auto-generated method stub

		}

		private void setComponentPosition() {
		}

		private void init() {
			// TODO Auto-generated method stub

		}

		private void createObjects() {
		}

	}

	class OffensiveComparisonPanel extends MyPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public OffensiveComparisonPanel() {
			this.createObjects();
			this.init();
			this.setComponentPosition();
			this.setStyle();
			this.setVisible(true);
		}

		private void setStyle() {
			// TODO Auto-generated method stub

		}

		private void setComponentPosition() {
			// TODO Auto-generated method stub

		}

		private void init() {
			// TODO Auto-generated method stub

		}

		private void createObjects() {
			// TODO Auto-generated method stub

		}
	}

	class DifensiveComparisonPanel extends MyPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DifensiveComparisonPanel() {
			this.createObjects();
			this.init();
			this.setComponentPosition();
			this.setStyle();
			this.setVisible(true);
		}

		private void setStyle() {
			// TODO Auto-generated method stub

		}

		private void setComponentPosition() {
			// TODO Auto-generated method stub

		}

		private void init() {
			// TODO Auto-generated method stub

		}

		private void createObjects() {
			// TODO Auto-generated method stub

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

	private void addListener() {
		this.firstFindButton.addMouseListener(this);
		this.secondFindButton.addMouseListener(this);
	}

	private void setStyle() {
		firstPlayerLabel.setLayout(null);
		firstPlayerLabel.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		firstPlayerInput.setOpaque(false);
		firstPlayerInput.setForeground(MyColor.MY_WHITE);
		firstPlayerInput.setFont(MyFont.SMALL_PLAIN);
		firstFindButton.setBorderPainted(false);

		secondPlayerLabel.setLayout(null);
		secondPlayerLabel.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		secondPlayerInput.setOpaque(false);
		secondPlayerInput.setForeground(MyColor.MY_WHITE);
		secondPlayerInput.setFont(MyFont.SMALL_PLAIN);
		secondFindButton.setBorderPainted(false);
	}

	private void setComponentPosition() {

		contentPanel.setBounds(mycombobox_x, button_y + button_h + 20, panel_w,
				panel_h);
		contentPanel.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		this.add(contentPanel);

		firstGameKind.setBounds(270, mycobobox_y + 60, mycombobox_width,
				mycombobox_height);
		firstPlayerLabel.setBounds(420, mycobobox_y, playerLabel_xy,
				playerLabel_xy);
		firstPlayerInput.setBounds(mycombobox_x, mycobobox_y + 30,
				mycombobox_width, mycombobox_height);
		firstFindButton.setBounds(mycombobox_x + mycombobox_width + 10,
				mycobobox_y + 30, mycombobox_height, mycombobox_height);

		secondGameKind.setBounds(mycombobox_x + 710, mycobobox_y + 60,
				mycombobox_width, mycombobox_height);
		secondPlayerLabel.setBounds(660, mycobobox_y, playerLabel_xy,
				playerLabel_xy);
		secondPlayerInput.setBounds(mycombobox_x + mycombobox_width + 720,
				mycobobox_y + 30, mycombobox_width, mycombobox_height);
		secondFindButton.setBounds(mycombobox_x + mycombobox_width * 2 + 730,
				mycobobox_y + 30, mycombobox_height, mycombobox_height);

		dataAnalysisButton.setBounds(mycombobox_x, button_y + 10, button_w,
				button_h);
		dataAnalysisButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				dataAnalysisButton.setIcon(new ImageIcon(
						"images/statistics/dataAnalysis_press.png"));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				dataAnalysisButton.setIcon(new ImageIcon(
						"images/statistics/dataAnalysis_press.png"));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (dataAnalysisButton.isPressed == false) {
					dataAnalysisButton.setIcon(new ImageIcon(
							"images/statistics/dataAnalysis_normal.png"));
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (dataAnalysisButton.isPressed == false) {
					dataAnalysisButton.setIcon(new ImageIcon(
							"images/statistics/dataAnalysis_enter.png"));
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				dataAnalysisButton.setIsPressed(true);
				offensiveAnaylisisButton.setIsPressed(false);
				difensiveAnalysisButton.setIsPressed(false);
				offensiveAnaylisisButton.setIcon(new ImageIcon(
						"images/statistics/offensiveAnalysis_normal.png"));
				difensiveAnalysisButton.setIcon(new ImageIcon(
						"images/statistics/difensiveAnalysis_normal.png"));

				contentPanel.showMyPanel(0);
			}
		});

		offensiveAnaylisisButton.setBounds(mycombobox_x + button_w + 10,
				button_y + 10, button_w, button_h);
		offensiveAnaylisisButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				offensiveAnaylisisButton.setIcon(new ImageIcon(
						"images/statistics/offensiveAnalysis_press.png"));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				offensiveAnaylisisButton.setIcon(new ImageIcon(
						"images/statistics/offensiveAnalysis_press.png"));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (offensiveAnaylisisButton.isPressed == false) {
					offensiveAnaylisisButton.setIcon(new ImageIcon(
							"images/statistics/offensiveAnalysis_normal.png"));
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (offensiveAnaylisisButton.isPressed == false) {
					offensiveAnaylisisButton.setIcon(new ImageIcon(
							"images/statistics/offensiveAnalysis_enter.png"));
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				offensiveAnaylisisButton.setIsPressed(true);
				dataAnalysisButton.setIsPressed(false);
				difensiveAnalysisButton.setIsPressed(false);
				dataAnalysisButton.setIcon(new ImageIcon(
						"images/statistics/dataAnalysis_normal.png"));
				difensiveAnalysisButton.setIcon(new ImageIcon(
						"images/statistics/difensiveAnalysis_normal.png"));

				contentPanel.showMyPanel(1);
			}
		});

		difensiveAnalysisButton.setBounds(mycombobox_x + button_w * 2 + 10 * 2,
				button_y + 10, button_w, button_h);
		difensiveAnalysisButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				difensiveAnalysisButton.setIcon(new ImageIcon(
						"images/statistics/difensiveAnalysis_press.png"));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				difensiveAnalysisButton.setIcon(new ImageIcon(
						"images/statistics/difensiveAnalysis_press.png"));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (difensiveAnalysisButton.isPressed == false) {
					difensiveAnalysisButton.setIcon(new ImageIcon(
							"images/statistics/difensiveAnalysis_normal.png"));
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (difensiveAnalysisButton.isPressed == false) {
					difensiveAnalysisButton.setIcon(new ImageIcon(
							"images/statistics/difensiveAnalysis_enter.png"));
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				difensiveAnalysisButton.setIsPressed(true);
				dataAnalysisButton.setIsPressed(false);
				offensiveAnaylisisButton.setIsPressed(false);
				dataAnalysisButton.setIcon(new ImageIcon(
						"images/statistics/dataAnalysis_normal.png"));
				offensiveAnaylisisButton.setIcon(new ImageIcon(
						"images/statistics/offensiveAnalysis_normal.png"));

				contentPanel.showMyPanel(2);
			}
		});

		this.add(firstGameKind);
		this.add(firstPlayerLabel);
		this.add(firstPlayerInput);
		this.add(firstFindButton);

		this.add(secondGameKind);
		this.add(secondPlayerLabel);
		this.add(secondPlayerInput);
		this.add(secondFindButton);

		this.add(dataAnalysisButton);
		this.add(offensiveAnaylisisButton);
		this.add(difensiveAnalysisButton);
	}

	private void init() {
		// TODO Auto-generated method stub

	}

	private void createObjects() {
		dataComparisonPanel = new DataComparisonPanel();
		offensiveComparisonPanel = new OffensiveComparisonPanel();
		difensiveComparisonPanel = new DifensiveComparisonPanel();
		panels = new MyPanel[] { dataComparisonPanel, offensiveComparisonPanel,
				difensiveComparisonPanel };
		contentPanel = new ContentPanel();

		firstGameKind = new MyComboBox<>(gameKindArray);
		firstPlayerLabel = new MyLabel();
		firstPlayerInput = new JTextField();
		firstFindButton = new JButton(new ImageIcon(
				"images/players/find_normal.png"));

		secondGameKind = new MyComboBox<>(gameKindArray);
		secondPlayerLabel = new MyLabel();
		secondPlayerInput = new JTextField();
		secondFindButton = new JButton(new ImageIcon(
				"images/players/find_normal.png"));

		dataAnalysisButton = new ButtonPressed(new ImageIcon(
				"images/statistics/dataAnalysis_press.png"));
		dataAnalysisButton.setIsPressed(true);
		offensiveAnaylisisButton = new ButtonPressed(new ImageIcon(
				"images/statistics/offensiveAnalysis_normal.png"));
		difensiveAnalysisButton = new ButtonPressed(new ImageIcon(
				"images/statistics/difensiveAnalysis_normal.png"));
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(firstFindButton)) {
			firstFindButton.setIcon(new ImageIcon(
					"images/players/find_press.png"));
		} else if (e.getSource().equals(secondFindButton)) {
			secondFindButton.setIcon(new ImageIcon(
					"images/players/find_press.png"));
		}

	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource().equals(firstFindButton)) {
			firstFindButton.setIcon(new ImageIcon(
					"images/players/find_enter.png"));
		} else if (e.getSource().equals(secondFindButton)) {
			secondFindButton.setIcon(new ImageIcon(
					"images/players/find_enter.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource().equals(firstFindButton)) {
			firstFindButton.setIcon(new ImageIcon(
					"images/players/find_normal.png"));
		} else if (e.getSource().equals(secondFindButton)) {
			secondFindButton.setIcon(new ImageIcon(
					"images/players/find_normal.png"));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
