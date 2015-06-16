package statistics.dataservice;

import java.util.ArrayList;

import statistics.analysisbeans.PlayerOnOrOff;
import statistics.analysisbeans.PlayerShootDistance;
import statistics.analysisbeans.PlayerShootOpponent;
import statistics.analysisbeans.PlayerShootType;
import beans.GamePlayer;
import beans.SeasonPlayer;

public interface StatisticsDataService {
	
	public ArrayList<GamePlayer>  getRegularSeasonPlayersGames(String playerID);//得到一个球员14-15常规赛的所有比赛数据
	public ArrayList<GamePlayer>  getPlayOffSeasonPlayersGames(String playerID);//季后赛数据
	
	public SeasonPlayer getRegularSeasonPlayer(String playerID);//14-15赛季常规赛一个球员的平均数据
	public SeasonPlayer getPlayOffSeasonPlayer(String playerID);//14-15赛季季后赛一个球员的平均数据
	
	public ArrayList<PlayerOnOrOff> getRegularSeasonCourtPerform(String playerID);//14-15赛季常规赛球员在场、不在场数据
	public ArrayList<PlayerOnOrOff> getPlayOffSeasonCourtPerform(String playerID);//14-15赛季季后赛球员在场、不在场数据
	
	public ArrayList<PlayerShootDistance> getRegularSeasonDistanceShoot(String playerID);//14-15赛季常规赛球员不同距离命中率
	public ArrayList<PlayerShootDistance> getPlayOffSeasonDistanceShoot(String playerID);
	
	public ArrayList<PlayerShootOpponent> getRegularSeasonOpponentShoot(String playerID);//14-15赛季常规赛球员不同对手命中率
	public ArrayList<PlayerShootOpponent> getPlayOffSeasonOpponentShoot(String playerID);

	public ArrayList<PlayerShootType> getRegularSeasonShootType(String playerID);//14-15赛季常规赛球员不同出手类型命中率 
	public ArrayList<PlayerShootType> getPlayOffSeasonShootType(String playerID);//14-15赛季季后赛球员不同出手类型命中率
}
