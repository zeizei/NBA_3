package data.player;

import java.sql.ResultSet;
import java.util.ArrayList;

import mysqldatabase.DB;
import beans.Bean;
import beans.GamePlayer;
import beans.GeneralPlayer;
import beans.SeasonPlayer;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;
import dataservice.player.OnePlayerDataService;

public class OnePlayerData implements OnePlayerDataService {
	private DB db = DB.getInstance();

	public GeneralPlayer getGeneralPlayer(String playerId) {
		if (playerId != null) {
			String sql = "select * from generalplayer where playerId = '" + playerId + "'";
			ResultSet rs = this.db.find(sql);
			ArrayList<GeneralPlayer> generalPlayerList = Bean.resultSetToList(rs, new GeneralPlayer());
			if (generalPlayerList != null && generalPlayerList.size() == 1) {
				return generalPlayerList.get(0);
			}
		}
		return null;
	}// 得到球员的基本信息

	public ArrayList<SeasonPlayer> getSeasonPlayer(String playerId, GameKind gameKind, DataKind dataKind, Field sortField) {
		if (playerId != null && gameKind != null && dataKind != null && sortField != null) {
			int isPlayOff = 0;
			if (GameKind.playOff_game.equals(gameKind)) {
				isPlayOff = 1;
			}
			String sql = "select " + SeasonPlayer.getAvgSqlString() + " from seasonplayer where playerId = '" + playerId + "' and isPlayOff=" + isPlayOff + " order by " + sortField.toString()
					+ " desc";
			if (DataKind.TOTAL.equals(dataKind)) {
				sql = "select * from seasonplayer where playerId = '" + playerId + "' and isPlayOff=" + isPlayOff + " order by " + sortField.toString() + " desc";
			}
			ResultSet rs = this.db.find(sql);
			ArrayList<SeasonPlayer> seasonPlayerList = Bean.resultSetToList(rs, new SeasonPlayer());
			if (seasonPlayerList != null && seasonPlayerList.size() != 0) {
				return seasonPlayerList;
			}
		}
		return null;
	}// 得到一个球员所有赛季的数据

	public ArrayList<GamePlayer> getGamePlayer(String playerId, Season season, Field sortField) {
		if (playerId != null && season != null && sortField != null) {
			String sql = "select * from gameplayer where playerId = '" + playerId + "' and (date between '" + season.getStartDate() + "' and '" + season.getFinishDate() + "') order by "
					+ sortField.toString() + " desc";
			ResultSet rs = this.db.find(sql);
			ArrayList<GamePlayer> gamePlayerList = Bean.resultSetToList(rs, new GamePlayer());
			if (gamePlayerList != null && gamePlayerList.size() != 0) {
				return gamePlayerList;
			}
		}
		return null;
	}// 得到一个球员一个赛季里的所有比赛
}
