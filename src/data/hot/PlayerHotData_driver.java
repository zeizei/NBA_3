package data.hot;

import java.util.ArrayList;

import common.datastructure.PlayerKingInfo;
import common.statics.Field;

public class PlayerHotData_driver {
	private PlayerHotData playerHotData = new PlayerHotData();

	public void testGetPlayerKingOfDaily() {
		ArrayList<PlayerKingInfo> gamePlayerList = this.playerHotData.getPlayerKingOfDaily("2015-06-04", Field.point);
		for (int i = 0; i < gamePlayerList.size(); i++) {
			System.out.println(gamePlayerList.get(i));
		}
	}

	public static void main(String args[]) {
		PlayerHotData_driver driver = new PlayerHotData_driver();
		driver.testGetPlayerKingOfDaily();
	}
}
