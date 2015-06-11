package data.hot;

import java.util.ArrayList;

import common.statics.Field;

import beans.GamePlayer;

public class PlayerHotData_driver {
	private PlayerHotData playerHotData = new PlayerHotData();

	public void testGetPlayerKingOfDaily() {
		ArrayList<GamePlayer> gamePlayerList = this.playerHotData.getPlayerKingOfDaily("2015-03-22", Field.point);
		for (int i = 0; i < gamePlayerList.size(); i++) {
			System.out.println(gamePlayerList.get(i));
		}
	}

	public static void main(String args[]) {
		PlayerHotData_driver driver = new PlayerHotData_driver();
		driver.testGetPlayerKingOfDaily();
	}
}
