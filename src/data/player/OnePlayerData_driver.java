package data.player;

import java.util.ArrayList;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;
import beans.GamePlayer;
import beans.GeneralPlayer;
import beans.SeasonPlayer;

public class OnePlayerData_driver {
	private OnePlayerData onePlayerData = new OnePlayerData();

	public void testGetGeneralPlayer() {
		GeneralPlayer generalPlayer = onePlayerData.getGeneralPlayer("phillan01");
		System.out.println(generalPlayer);
	}

	public void testGetSeasonPlayer() {
		ArrayList<SeasonPlayer> seasonPlayerList = onePlayerData.getSeasonPlayer("jamesle01", GameKind.regular_game, DataKind.AVERAGE, Field.point);
		if (seasonPlayerList != null) {
			for (int i = 0; i < seasonPlayerList.size(); i++) {
				System.out.println(seasonPlayerList.get(i));
			}
		}
	}

	public void testGetGamePlayer() {
		ArrayList<GamePlayer> gamePlayerList = onePlayerData.getGamePlayer("jamesle01", Season.season_array[0], Field.date);
		for (int i = 0; i < gamePlayerList.size(); i++) {
			System.out.println(gamePlayerList.get(i).toString());
		}
	}

	public static void main(String args[]) {
		OnePlayerData_driver driver = new OnePlayerData_driver();
		// driver.testGetGeneralPlayer();
		// driver.testGetSeasonPlayer();
		driver.testGetGamePlayer();
	}
}
