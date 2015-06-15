package businesslogic.hot;

import java.util.ArrayList;

import businesslogic.game.GameInfoBl;
import businesslogicservice.game.GameInfoBlService;
import businesslogicservice.hot.PlayerHotBlSrevice;
import common.datastructure.PlayerHotInfo;
import common.datastructure.PlayerKingInfo;
import common.statics.Field;
import common.statics.Season;
import data.hot.PlayerHotData;
import dataservice.hot.PlayerHotDataService;

public class PlayerHotBl implements PlayerHotBlSrevice {
	private PlayerHotDataService playerHotData = new PlayerHotData();
	private GameInfoBlService gameInfoBl = new GameInfoBl();

	public ArrayList<PlayerHotInfo> getPlayerHot(Field field) {
		return playerHotData.getPlayerHot(field);
	}

	public ArrayList<PlayerKingInfo> getPlayerKingOfSeason(Season season, Field sortField) {
		return playerHotData.getPlayerKingOfSeason(season, sortField);
	}

	public ArrayList<PlayerKingInfo> getPlayerKingOfDaily(String date, Field sortField) {
		return playerHotData.getPlayerKingOfDaily(date, sortField);
	}

	public String getLatestDate() {
		return gameInfoBl.getLatestDate();
	}
}
