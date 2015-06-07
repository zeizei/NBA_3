package businesslogic.team;

import java.util.ArrayList;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.League;
import common.statics.Season;
import data.team.TeamInfoData;
import dataservice.team.TeamInfoDataService;
import beans.SeasonTeam;
import businesslogicservice.team.TeamInfoBlService;

public class TeamInfoBl implements TeamInfoBlService {
	private TeamInfoDataService teamInfoData = new TeamInfoData();

	public ArrayList<SeasonTeam> getSeasonTeam(Season season, GameKind gameKind, DataKind dataKind, League league, Field sortField) {
		return teamInfoData.getSeasonTeam(season, gameKind, dataKind, league, sortField);
	}
}
