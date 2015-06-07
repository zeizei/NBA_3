package businesslogic.hot;

import java.util.ArrayList;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;

import beans.GamePlayer;
import beans.SeasonPlayer;
import businesslogicservice.hot.PlayerHotBlSrevice;

public class PlayerHotBl implements PlayerHotBlSrevice {

	@Override
	public ArrayList<GamePlayer> getPlayerHot(String nowDate, String field) {
		// TODO Auto-generated method stub
		return null;
	}

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
