package statistics.data;

import java.sql.ResultSet;
import java.util.ArrayList;

import mysqldatabase.DB;
import statistics.analysisbeans.PlayerOnOrOff;
import statistics.analysisbeans.PlayerShootDistance;
import statistics.analysisbeans.PlayerShootOpponent;
import statistics.analysisbeans.PlayerShootType;
import statistics.dataservice.StatisticsDataService;
import statistics.mysqldatabase.DB_statistics;
import beans.GamePlayer;
import beans.SeasonPlayer;

public class StatisticsData implements StatisticsDataService {
	private DB nba_db = DB.getInstance();
	private DB_statistics analysis_db = DB_statistics.getInstance();

	public ArrayList<GamePlayer> getPlayersGames(String playerID, int isPlayOff) {
		if (playerID != null) {
			String sql = "select * from gameplayer where  playerId = '" + playerID + "' and date in (select date from generalgame where date between '2014-10-25' and '2015-07-01' and isPlayOff = "
					+ isPlayOff + ")";
			ResultSet rs = this.nba_db.find(sql);
			ArrayList<GamePlayer> gamePlayerList = beans.Bean.resultSetToList(rs, new GamePlayer());
			if (gamePlayerList != null && gamePlayerList.size() != 0) {
				return gamePlayerList;
			}
		}
		return null;
	}// 得到一个球员14-15常规赛/季后赛的所有比赛数据

	public SeasonPlayer getSeasonPlayer(String playerID, int isPlayOff) {
		if (playerID != null) {
			String sql = "select " + SeasonPlayer.getAvgSqlString() + " from seasonplayer where playerId = '" + playerID + "' and season = '2014-15' and isPlayOff=" + isPlayOff;
			ResultSet rs = this.nba_db.find(sql);
			ArrayList<SeasonPlayer> seasonPlayerList = beans.Bean.resultSetToList(rs, new SeasonPlayer());
			if (seasonPlayerList != null && seasonPlayerList.size() == 1) {
				return seasonPlayerList.get(0);
			}
		}
		return null;
	}// 14-15赛季常规赛/季后赛一个球员的平均数据

	public ArrayList<PlayerOnOrOff> getCourtPerform(String playerID, int isPlayOff) {
		if (playerID != null) {
			String sql = "select * from playeronoroff where playerId = '" + playerID + "' and season = '2014-15' and isPlayOff=" + isPlayOff;
			ResultSet rs = this.analysis_db.find(sql);
			ArrayList<PlayerOnOrOff> playerOnOrOffList = statistics.analysisbeans.Bean.resultSetToList(rs, new PlayerOnOrOff());
			if (playerOnOrOffList != null && playerOnOrOffList.size() != 0) {
				return playerOnOrOffList;
			}
		}
		return null;
	}// 14-15赛季常规赛/季后赛球员在场、不在场数据

	public ArrayList<PlayerShootDistance> getDistanceShoot(String playerID, int isPlayOff) {
		if (playerID != null) {
			String sql = "select * from playershootdistance where playerId = '" + playerID + "' and season = '2014-15' and isPlayOff=" + isPlayOff;
			ResultSet rs = this.analysis_db.find(sql);
			ArrayList<PlayerShootDistance> playerShootDistanceList = statistics.analysisbeans.Bean.resultSetToList(rs, new PlayerShootDistance());
			if (playerShootDistanceList != null && playerShootDistanceList.size() != 0) {
				return playerShootDistanceList;
			}
		}
		return null;
	}// 14-15赛季常规赛/季后赛球员不同距离命中率

	public ArrayList<PlayerShootOpponent> getOpponentShoot(String playerID, int isPlayOff) {
		if (playerID != null) {
			String sql = "select * from playershootopponent where playerId = '" + playerID + "' and season = '2014-15' and isPlayOff=" + isPlayOff;
			ResultSet rs = this.analysis_db.find(sql);
			ArrayList<PlayerShootOpponent> playerShootOpponentList = statistics.analysisbeans.Bean.resultSetToList(rs, new PlayerShootOpponent());
			if (playerShootOpponentList != null && playerShootOpponentList.size() != 0) {
				return playerShootOpponentList;
			}
		}
		return null;
	}// 14-15赛季常规赛/季后赛球员不同对手命中率

	public ArrayList<PlayerShootType> getShootType(String playerID, int isPlayOff) {
		if (playerID != null) {
			String sql = "select * from playershoottype where playerId = '" + playerID + "' and season = '2014-15' and isPlayOff=" + isPlayOff;
			ResultSet rs = this.analysis_db.find(sql);
			ArrayList<PlayerShootType> playerShootTypeList = statistics.analysisbeans.Bean.resultSetToList(rs, new PlayerShootType());
			if (playerShootTypeList != null && playerShootTypeList.size() != 0) {
				return playerShootTypeList;
			}
		}
		return null;
	}// 14-15赛季常规赛/季后赛球员不同出手类型命中率

	public ArrayList<SeasonPlayer> vagueSearchPlayer(String str) {
		if (str != null) {
			String sql = "select * from seasonplayer where season = '2014-15' and isPlayOff = 0 and playerName like '%" + str
					+ "%' and playerName in (select playerName from seasonplayer where season = '2014-15' and isPlayOff = 1) order by  point/numOfGame desc limit 10";
			ResultSet rs = this.nba_db.find(sql);
			ArrayList<SeasonPlayer> seasonPlayerList = beans.Bean.resultSetToList(rs, new SeasonPlayer());
			if (seasonPlayerList != null && seasonPlayerList.size() != 0) {
				return seasonPlayerList;
			}
		}
		return null;
	}

	public ArrayList<SeasonPlayer> getPlayOffPlayer() {
		String sql = "select * from seasonplayer where season = '2014-15' and isPlayOff = 0 and playerName in (select playerName from seasonplayer where season = '2014-15' and isPlayOff = 1) order by  point/numOfGame desc limit 10";
		ResultSet rs = this.nba_db.find(sql);
		ArrayList<SeasonPlayer> seasonPlayerList = beans.Bean.resultSetToList(rs, new SeasonPlayer());
		if (seasonPlayerList != null && seasonPlayerList.size() != 0) {
			return seasonPlayerList;
		}
		return null;
	}
}
