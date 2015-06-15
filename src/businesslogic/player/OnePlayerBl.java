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

	@Override
	public GeneralPlayer getGeneralPlayer(String playerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SeasonPlayer> getSeasonPlayer(String playerId, GameKind gameKind, DataKind dataKind, Field sortField) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GamePlayer> getGamePlayer(String playerId, Season season, Field sortField) {
		return null;
	}

	@Override
	public ArrayList<GamePlayer> getLatestGamePlayer(String playerId) {
		return null;
	}

}
