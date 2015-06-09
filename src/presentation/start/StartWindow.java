package presentation.start;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import presentation.MainFrame;

public class StartWindow extends JWindow implements Runnable
{
 Thread splashThread=null;
 public StartWindow()
 {
  JPanel splash=new JPanel(new BorderLayout());
   splash.add(new JLabel(new ImageIcon("images/StartIcon.png")),BorderLayout.CENTER);
  setContentPane(splash);
  Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();//获得屏幕的大小
  pack();
  setLocation((screen.width-getSize().width)/2,(screen.height-getSize().height)/2);//使启动窗口居中显示
  start();
 }
 public void start()
 {
//  setAlwaysOnTop(true);//window类的toFront()方法可以让启动界面显示的时候暂时在最前面，用window类的setAlwayOnTop(boolean)方法可以让窗口总保持在最前面。
  splashThread=new Thread(this);
  splashThread.start();
 }
 public void run()
 {
  try
  {
   setVisible(true);
  }
  catch(Exception e)
  {
   e.printStackTrace();
  }
  MainFrame mainFrame = new MainFrame();
//  new Refresh().start();
  dispose();
 }
}