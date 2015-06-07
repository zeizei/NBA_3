package data.player;

import java.util.ArrayList;

import beans.GamePlayer;
import beans.GeneralPlayer;
import beans.SeasonPlayer;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;

import dataservice.player.OnePlayerDataService;

public class OnePlayerData implements OnePlayerDataService {

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
		// TODO Auto-generated method stub
		return null;
	}

}
