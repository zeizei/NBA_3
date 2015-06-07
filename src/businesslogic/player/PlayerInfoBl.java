package businesslogic.player;

import java.util.ArrayList;

import common.datastructure.CombineSelectionCell;
import common.statics.AgeRange;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Position;
import common.statics.Season;
import data.player.PlayerInfoData;
import dataservice.player.PlayerInfoDataService;
import beans.SeasonPlayer;
import businesslogicservice.player.PlayerInfoBlService;

public class PlayerInfoBl implements PlayerInfoBlService {
	private PlayerInfoDataService playerInfoData = new PlayerInfoData();

	public ArrayList<SeasonPlayer> getSeasonPlayer(Season season, GameKind gameKind, DataKind dataKind, Position position, AgeRange ageRange, Field sortField) {
		return playerInfoData.getSeasonPlayer(season, gameKind, dataKind, position, ageRange, sortField);
	}

	public ArrayList<SeasonPlayer> vagueSearch(String str) {
		return playerInfoData.vagueSearch(str);
	}

	public ArrayList<SeasonPlayer> getSeasonPlayer(CombineSelectionCell[] combineSelectionCells) {
		return playerInfoData.getSeasonPlayer(combineSelectionCells);
	}
}
