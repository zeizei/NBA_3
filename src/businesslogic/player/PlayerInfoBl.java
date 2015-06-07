package businesslogic.player;

import java.util.ArrayList;

import common.datastructure.CombineSelectionCell;
import common.statics.AgeRange;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Position;
import common.statics.Season;

import beans.SeasonPlayer;
import businesslogicservice.player.PlayerInfoBlService;

public class PlayerInfoBl implements PlayerInfoBlService {

	@Override
	public ArrayList<SeasonPlayer> getSeasonPlayer(Season season, GameKind gameKind, DataKind dataKind, Position position, AgeRange ageRange, Field sortField) {
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
