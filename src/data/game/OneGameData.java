package data.game;

import java.sql.ResultSet;
import java.util.ArrayList;

import mysqldatabase.DB;
import beans.Bean;
import beans.GamePlayer;
import beans.GameTeam;
import dataservice.game.OneGameDataService;

public class OneGameData implements OneGameDataService {
	private DB db = DB.getInstance();

	public ArrayList<GamePlayer> getPlayersPerformOfOneGame(String teamName, String date) {
		if (teamName != null && date != null) {
			String sql = "select * from gameplayer where date ='" + date + "' and teamName ='" + teamName + "'";
			ResultSet rs = this.db.find(sql);
			ArrayList<GamePlayer> gamePlayerList = Bean.resultSetToList(rs, new GamePlayer());
			if (gamePlayerList != null && gamePlayerList.size() != 0) {
				return gamePlayerList;
			}
		}
		return null;
	}// 得到一个球队某一天的比赛中所有球员比赛的数据

	public GameTeam getTeamPerformOfOneGame(String teamName, String date) {
		if (teamName != null && date != null) {
			String sql = "select * from gameteam where date ='" + date + "' and teamName ='" + teamName + "'";
			ResultSet rs = this.db.find(sql);
			ArrayList<GameTeam> gameTeamList = Bean.resultSetToList(rs, new GameTeam());
			if (gameTeamList != null && gameTeamList.size() == 1) {
				return gameTeamList.get(0);
			}
		}
		return null;
	}// 得到某一球队某一天中比赛的数据
}
