package data.game;

import java.util.ArrayList;

import beans.GeneralGame;

public class GameInfoData_driver {
	private GameInfoData gameInfoData = new GameInfoData();

	public void testGetLatestDate() {
		String latestDate = gameInfoData.getLatestDate();
		System.out.println(latestDate);
	}

	public void testGetTodayMatches() {
		ArrayList<GeneralGame> generalGameList = gameInfoData.getTodayMatches("2015-03-04");
		if (generalGameList != null) {
			for (int i = 0; i < generalGameList.size(); i++) {
				System.out.println(generalGameList.get(i));
			}
		}
	}

	public void testGetGeneralMatch() {
		GeneralGame generalGame = gameInfoData.getGeneralMatch("Atlanta Hawks", "2015-05-11");
		System.out.println(generalGame);
	}

	public static void main(String args[]) {
		GameInfoData_driver driver = new GameInfoData_driver();
		driver.testGetLatestDate();
		driver.testGetTodayMatches();
		driver.testGetGeneralMatch();
	}
}
