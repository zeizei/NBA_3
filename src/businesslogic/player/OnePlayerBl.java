package businesslogic.player;

import java.util.ArrayList;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;
import data.player.OnePlayerData;
import dataservice.player.OnePlayerDataService;
import beans.GamePlayer;
import beans.GeneralPlayer;
import beans.SeasonPlayer;
import businesslogicservice.player.OnePlayerBlService;

public class OnePlayerBl implements OnePlayerBlService {
	private OnePlayerDataService onePlayerData = new OnePlayerData();

	public GeneralPlayer getGeneralPlayer(String playerId) {
		return onePlayerData.getGeneralPlayer(playerId);
	}

	public ArrayList<SeasonPlayer> getSeasonPlayer(String playerId, GameKind gameKind, DataKind dataKind, Field sortField) {
		return onePlayerData.getSeasonPlayer(playerId, gameKind, dataKind, sortField);
	}

	public ArrayList<GamePlayer> getGamePlayer(String playerId, Season season, GameKind gameKind, Field sortField) {
		return onePlayerData.getGamePlayer(playerId, season, gameKind, sortField);
	}

	public String[] getSeasonsOfPlayer(String playerId, GameKind gameKind) {
		ArrayList<String> seasonStrs = onePlayerData.getSeasonsOfPlayer(playerId, gameKind);
		if (seasonStrs != null && seasonStrs.size() != 0) {
			String[] seasons = new String[seasonStrs.size()];
			for (int i = 0; i < seasonStrs.size(); i++) {
				seasons[i] = seasonStrs.get(i);
			}
			return seasons;
		}
		return null;
	}
}
