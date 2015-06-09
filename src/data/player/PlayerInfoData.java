package data.player;

import java.sql.ResultSet;
import java.util.ArrayList;

import mysqldatabase.DB;
import beans.Bean;
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
	private DB db = DB.getInstance();

	public ArrayList<SeasonPlayer> getSeasonPlayer(Season season, GameKind gameKind, DataKind dataKind, League league, Position position, AgeRange ageRange, Field sortField) {

		return null;
	}

	public ArrayList<SeasonPlayer> vagueSearch(String str) {
		if (str != null && str.length() != 0) {
			str = str.toLowerCase();
			String sql = "select * from seasonplayer where lower(playerName) like '%" + str + "%' and season = 'Career' and isPlayOff = 0";
			ResultSet rs = this.db.find(sql);
			ArrayList<SeasonPlayer> seasonPlayerList = Bean.resultSetToList(rs, new SeasonPlayer());
			if (seasonPlayerList != null && seasonPlayerList.size() != 0) {
				return seasonPlayerList;
			}
		}
		return null;
	}// 模糊查找

	public ArrayList<SeasonPlayer> getSeasonPlayer(Season season, GameKind gameKind, DataKind dataKind, League league, Position position, AgeRange ageRange,
			CombineSelectionCell[] combineSelectionCells) {
		return null;
	}
}
