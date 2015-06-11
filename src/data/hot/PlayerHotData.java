package data.hot;

import java.sql.ResultSet;
import java.util.ArrayList;

import mysqldatabase.DB;
import beans.Bean;
import beans.GamePlayer;

import common.statics.Field;

import dataservice.hot.PlayerHotDataService;

public class PlayerHotData implements PlayerHotDataService {
	private DB db = DB.getInstance();

	public ArrayList<GamePlayer> getPlayerKingOfDaily(String date, Field sortField) {
		if (date != null && sortField != null) {
			String sql = "select * from gameplayer where date = '" + date + "' order by " + sortField + " desc";
			ResultSet rs = this.db.find(sql);
			ArrayList<GamePlayer> gamePlayerList = Bean.resultSetToList(rs, new GamePlayer());
			if (gamePlayerList != null && gamePlayerList.size() != 0) {
				return gamePlayerList;
			}
		}
		return null;
	}
}
