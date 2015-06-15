package data.game;

import java.util.ArrayList;

import beans.GamePlayer;
import beans.GameTeam;

public class OneGameData_driver {
	OneGameData oneGameInfo = new OneGameData();

	public void testGetPlayersPerformOfOneGame() {
		ArrayList<GamePlayer> gamePlayerList = oneGameInfo.getPlayersPerformOfOneGame("Miami Heat", "2004-01-23");
		if (gamePlayerList != null) {
			for (int i = 0; i < gamePlayerList.size(); i++) {
				System.out.println(gamePlayerList.get(i));
			}
		}
	}

	public void testGetTeamPerformOfOneGame() {
		GameTeam gameTeam = oneGameInfo.getTeamPerformOfOneGame("Chicago Bulls", "1985-10-26");
		System.out.println(gameTeam);
	}

	public static void main(String args[]) {
		OneGameData_driver driver = new OneGameData_driver();
		driver.testGetPlayersPerformOfOneGame();
		driver.testGetTeamPerformOfOneGame();
	}
}
