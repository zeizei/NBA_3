package businesslogic.hot;

import java.util.ArrayList;

import common.datastructure.PlayerHotInfo;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;
import data.hot.PlayerHotData;
import dataservice.hot.PlayerHotDataService;
import beans.GamePlayer;
import beans.SeasonPlayer;
import businesslogicservice.hot.PlayerHotBlSrevice;

public class PlayerHotBl implements PlayerHotBlSrevice {
	private PlayerHotDataService playerHotData = new PlayerHotData();

	public ArrayList<PlayerHotInfo> getPlayerHot(String nowDate, String field) {
		return null;
	}

	public ArrayList<SeasonPlayer> getPlayerKingOfSeason(Season season, GameKind gameKind, DataKind dataKind, Field sortField) {
		return null;
	}

	public ArrayList<GamePlayer> getPlayerKingOfDaily(String date, Field sortField) {
		return playerHotData.getPlayerKingOfDaily(date, sortField);
	}
}
