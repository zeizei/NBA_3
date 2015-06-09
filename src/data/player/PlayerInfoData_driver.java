package data.player;

import java.util.ArrayList;

import beans.SeasonPlayer;

public class PlayerInfoData_driver {
	private PlayerInfoData playerInfoData = new PlayerInfoData();

	public void testVagueSearch() {
		ArrayList<SeasonPlayer> seasonPlayerList = this.playerInfoData.vagueSearch("lebron");
		for (int i = 0; i < seasonPlayerList.size(); i++) {
			System.out.println(seasonPlayerList.get(i));
		}
	}

	public static void main(String args[]) {
		PlayerInfoData_driver driver = new PlayerInfoData_driver();
		driver.testVagueSearch();
	}

}
