package businesslogic.team;

import java.util.ArrayList;

import common.statics.Field;
import common.statics.Season;
import data.team.OneTeamData;
import dataservice.team.OneTeamDataService;
import beans.GameTeam;
import beans.SeasonPlayer;
import beans.SeasonTeam;
import businesslogicservice.team.OneTeamBlService;

public class OneTeamBl implements OneTeamBlService {
	private OneTeamDataService oneTeamData = new OneTeamData();

	public ArrayList<SeasonPlayer> getOneSeasonPlayers(String teamName, Season season) {
		return oneTeamData.getOneSeasonPlayers(teamName, season);
	}

	public ArrayList<SeasonTeam> getSeasonTeam(String teamName, Field sortField) {
		return oneTeamData.getSeasonTeam(teamName, sortField);
	}

	public ArrayList<GameTeam> getGameTeam(String teamName, Season season, Field sortField) {
		return oneTeamData.getGameTeam(teamName, season, sortField);
	}
}
