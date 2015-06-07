package dataservice.team;

import java.util.ArrayList;

import beans.GameTeam;
import beans.SeasonTeam;

import common.statics.Field;
import common.statics.Season;

public interface OneTeamDataService {
	// 根据球队名称和赛季得到该赛季球队所有球员姓名
	public ArrayList<String> getAllPlayerIds(String teamName, Season season);

	// 根据球队名称得到球队所有赛季数据
	public ArrayList<SeasonTeam> getSeasonTeam(String teamName, Field sortField);

	// 得到球队一个赛季里的所有比赛详细数据
	public ArrayList<GameTeam> getGameTeam(String teamName, Season season, Field sortField);
}
