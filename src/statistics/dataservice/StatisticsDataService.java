package statistics.dataservice;

import java.util.ArrayList;

import statistics.analysisbeans.PlayerOnOrOff;
import statistics.analysisbeans.PlayerShootDistance;
import statistics.analysisbeans.PlayerShootOpponent;
import statistics.analysisbeans.PlayerShootType;
import beans.GamePlayer;
import beans.SeasonPlayer;

public interface StatisticsDataService {

	public ArrayList<GamePlayer> getPlayersGames(String playerID, int isPlayOff);// 得到一个球员14-15常规赛的所有比赛数据

	public SeasonPlayer getSeasonPlayer(String playerID, int isPlayOff);// 14-15赛季常规赛一个球员的平均数据

	public ArrayList<PlayerOnOrOff> getCourtPerform(String playerID, int isPlayOff);// 14-15赛季常规赛球员在场、不在场数据

	public ArrayList<PlayerShootDistance> getDistanceShoot(String playerID, int isPlayOff);// 14-15赛季常规赛球员不同距离命中率

	public ArrayList<PlayerShootOpponent> getOpponentShoot(String playerID, int isPlayOff);// 14-15赛季常规赛球员不同对手命中率

	public ArrayList<PlayerShootType> getShootType(String playerID, int isPlayOff);// 14-15赛季常规赛球员不同出手类型命中率

	public ArrayList<SeasonPlayer> vagueSearchPlayer(String str);// 模糊查询球员
	
	public ArrayList<SeasonPlayer> getPlayOffPlayer();

}
