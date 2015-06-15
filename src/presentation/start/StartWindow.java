package presentation.start;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import presentation.MainFrame;

public class StartWindow extends JWindow implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread splashThread = null;

	public StartWindow() {
		JPanel splash = new JPanel(new BorderLayout());
		splash.add(new JLabel(new ImageIcon("images/StartIcon.png")), BorderLayout.CENTER);
		this.setContentPane(splash);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();// 获得屏幕的大小
		this.pack();
		this.setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2);// 使启动窗口居中显示
		this.start();
	}

	public void start() {
		splashThread = new Thread(this);
		splashThread.start();
	}

	public void run() {
		try {
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new MainFrame();
		this.dispose();
	}
}