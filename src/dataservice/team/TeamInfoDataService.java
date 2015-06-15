package dataservice.team;

import java.util.ArrayList;

import beans.SeasonTeam;
import beans.generalTeam;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.League;
import common.statics.Season;

public interface TeamInfoDataService { 
	// 筛选球队
	public ArrayList<SeasonTeam> getSeasonTeam(Season season, DataKind dataKind, League league, Field sortField);

	// 模糊查找
	public ArrayList<SeasonTeam> vagueSearch(String str);

	// 根据队名和当时的赛季得到该支球队的基本信息
	public generalTeam getGeneralTeam(String teamName, Season season);
}
