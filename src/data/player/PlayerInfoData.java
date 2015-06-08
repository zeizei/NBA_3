package data.player;

import java.util.ArrayList;

import beans.SeasonPlayer;
import common.datastructure.CombineSelectionCell;
import common.statics.AgeRange;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.League;
import common.statics.Position;
import common.statics.Season;
import dataservice.player.PlayerInfoDataService;

public class PlayerInfoData implements PlayerInfoDataService {

	@Override
	public ArrayList<SeasonPlayer> getSeasonPlayer(Season season, GameKind gameKind, DataKind dataKind, League league, Position position, AgeRange ageRange, Field sortField) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SeasonPlayer> vagueSearch(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SeasonPlayer> getSeasonPlayer(CombineSelectionCell[] combineSelectionCells) {
		// TODO Auto-generated method stub
		return null;
	}

}
