package data.hot;

import java.util.ArrayList;

import beans.GamePlayer;
import beans.SeasonPlayer;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;

import dataservice.hot.PlayerHotDataService;

public class PlayerHotData implements PlayerHotDataService {

	@Override
	public ArrayList<SeasonPlayer> getPlayerKingOfSeason(Season season, GameKind gameKind, DataKind dataKind, Field sortField) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GamePlayer> getPlayerKingOfDaily(String date, Field sortField) {
		// TODO Auto-generated method stub
		return null;
	}

}
