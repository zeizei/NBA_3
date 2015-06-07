package businesslogicservice.match;

import java.util.ArrayList;

import beans.GeneralMatch;

public interface MatchInfoBlService {
	// 得到最近的有比赛的日期
	public String getLatestDate();

	// 根据当前日期得到今天所有比赛、、如果没有比赛则null
	public ArrayList<GeneralMatch> getTodayMatches(String date);

	// 根据球队名称和日期查找其当天参加的比赛
	public GeneralMatch getGeneralMatch(String teamName, String date);
}
