package businesslogic.team;

import java.util.ArrayList;

import beans.SeasonTeam;
import beans.generalTeam;
import businesslogicservice.team.TeamInfoBlService;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.League;
import common.statics.Season;
import data.team.TeamInfoData;
import dataservice.team.TeamInfoDataService;

public class TeamInfoBl implements TeamInfoBlService {
	private TeamInfoDataService teamInfoData = new TeamInfoData();

	public ArrayList<SeasonTeam> getSeasonTeam(Season season, DataKind dataKind, League league, Field sortField) {
		return teamInfoData.getSeasonTeam(season, dataKind, league, sortField);
	}

	public ArrayList<SeasonTeam> vagueSearch(String str) {
		return teamInfoData.vagueSearch(str);
	}

	public generalTeam getGeneralTeam(String teamName, Season season) {
		return teamInfoData.getGeneralTeam(teamName, season);
	}
}
