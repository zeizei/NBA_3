package presentation.statics;

import java.awt.Dimension;
import java.awt.Toolkit;

public class NUMBER {

	public static final int SCREEN_WIDTH = getScreenWidth();// 屏幕的宽度
	public static final int SCREEN_HEIGHT = getScreenHeight();// 屏幕的长度
	public static final int FRAME_WIDTH = 1300;// 窗口的宽度
	public static final int FRAME_HEIGHT = 700;// 窗口的高度
	public static final int NAVIGATION_PANEL_WIDTH = NUMBER.FRAME_WIDTH;// 导航栏宽度
	public static final int NAVIGATION_PANEL_HEIGHT = NUMBER.FRAME_HEIGHT / 10;// 导航栏高度
	public static final int DATA_PANEL_WIDTH = FRAME_WIDTH;// 数据面板宽度
	//
	public static final int NUMBER_OF_TEAM = 30;// 球队个数
	public static final int NUMBER_OF_FIRST = 5;// 首发人数
	public static final int DEFAULT_NUMBER = 50;// 筛选出的球员人数
	public static final int UNKNOWN_AGE = -1;// 年龄未知
	public static final int defualt_hot_num = 5;

	private static int getScreenWidth() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize.width;
	}

	private static int getScreenHeight() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize.height;
	}
}
