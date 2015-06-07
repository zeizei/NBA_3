package businesslogicservice.match;

import java.util.ArrayList;

import beans.GamePlayer;
import beans.GameTeam;

public interface OneMatchBlService {
	// 根据球队名称和时间得到该场比赛的所有球员比赛信息
	public ArrayList<GamePlayer> getPlayersPerformOfOneGame(String teamName, String date);

	// 根据队名和时间得到该场比赛球队的比赛信息
	public GameTeam getTeamPerformOfOneGame(String teamName, String date);
}
