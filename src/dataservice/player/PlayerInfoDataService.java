package dataservice.player;

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

public interface PlayerInfoDataService {
	// 得到球员数据
	public ArrayList<SeasonPlayer> getSeasonPlayer(Season season, GameKind gameKind, DataKind dataKind, League league, Position position, AgeRange ageRange, Field sortField);

	// 模糊查找
	public ArrayList<SeasonPlayer> vagueSearch(String str);

	// 组合筛选
	public ArrayList<SeasonPlayer> getSeasonPlayer(Season season, GameKind gameKind, DataKind dataKind, League league, Position position, AgeRange ageRange, ArrayList<CombineSelectionCell> cellList);

	//
	public ArrayList<String> getTargetPlayerIdsOfSeason(Season season);
}
