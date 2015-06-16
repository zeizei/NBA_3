package statistics.blservice;

import java.util.ArrayList;

import statistics.analysisbeans.PlayerOnOrOff;
import statistics.analysisbeans.PlayerShootDistance;
import statistics.analysisbeans.PlayerShootOpponent;
import statistics.analysisbeans.PlayerShootType;
import beans.GamePlayer;
import beans.SeasonPlayer;

public interface StatisticsBlService {
	
	public boolean isBetterThanRegular(String playerID,String field);
	
	public double[] forcast(String playerID,String field);
	
	public double[] getRangeEstimation(double aver,double s,int n,double alpha);//单总体均值区间估计      返回数组第一个值是下限，第二个值是上限
	
	public boolean hypothesisTest_z(double newAverage,double oldAverage,double s,int n,int type,double alpha);//参数假设检验   z检验  条件：方差已知或方差未知但样本容量大
	                                                                    //type为左边检验、右边检验、双边检验。返回值为是否在拒绝域
	public boolean hypothesisTest_x2(double s2,double sigma2,int n,int type,double alpha);//注意这里传的参数是sigma平方
	
	public boolean rank_sum(double x[],double y[],double alpha);
	
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