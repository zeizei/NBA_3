package data.player;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.GamePlayer;
import beans.GeneralPlayer;
import beans.SeasonPlayer;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;

public class OnePlayerData_driver {
	private OnePlayerData onePlayerData = new OnePlayerData();

	public void testGetGeneralPlayer() {
		GeneralPlayer generalPlayer = onePlayerData.getGeneralPlayer("phillan01");
		System.out.println(generalPlayer);
	}

	public void testGetSeasonPlayer() {
		ArrayList<SeasonPlayer> seasonPlayerList = onePlayerData.getSeasonPlayer("jamesle01", GameKind.regular_game, DataKind.average, Field.point);
		if (seasonPlayerList != null) {
			for (int i = 0; i < seasonPlayerList.size(); i++) {
				System.out.println(seasonPlayerList.get(i));
			}
		}
	}

	public void testGetGamePlayer() {
		ArrayList<GamePlayer> gamePlayerList = onePlayerData.getGamePlayer("jamesle01", Season.seasons_with_Career[0], GameKind.regular_game, Field.date);
		for (int i = 0; i < gamePlayerList.size(); i++) {
			System.out.println(gamePlayerList.get(i).toString());
			System.out.println(i);
		}
	}

	public static void main(String args[]) throws SQLException {
		// OnePlayerData_driver driver = new OnePlayerData_driver();
		// driver.testGetGeneralPlayer();
		// driver.testGetSeasonPlayer();
		// driver.testGetGamePlayer();
		// String shortSql = "select distinct short from generalteam";
		// ResultSet rs = DB.getInstance().find(shortSql);
		// ArrayList<String> shortNameList = new ArrayList<String>();
		// while (rs.next()) {
		// String shortName = rs.getString("short");
		// shortNameList.add(shortName);
		// }
		// for (int i = 0; i < shortNameList.size(); i++) {
		// System.out.println(shortNameList.get(i));
		// String sql =
		// "update seasonplayer set teamName = (select distinct teamName from generalteam where short = seasonplayer.teamName) where seasonplayer.teamName='"
		// + shortNameList.get(i)
		// + "'";
		// DB.getInstance().update(sql);
		// }
	}
}
