package businesslogic.team;

import java.util.ArrayList;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;
import data.team.OneTeamData;
import dataservice.team.OneTeamDataService;
import beans.GameTeam;
import beans.PlayOffSeries;
import beans.SeasonPlayer;
import beans.SeasonTeam;
import businesslogicservice.team.OneTeamBlService;

public class OneTeamBl implements OneTeamBlService {
	private OneTeamDataService oneTeamData = new OneTeamData();

	public ArrayList<SeasonPlayer> getOneSeasonPlayers(String teamName, Season season, GameKind gameKind, DataKind dataKind, Field sortField) {
		return oneTeamData.getOneSeasonPlayers(teamName, season, gameKind, dataKind, sortField);
	}

	public ArrayList<SeasonTeam> getRegularSeasonTeam(String teamName, DataKind dataKind, Field sortField) {
		return oneTeamData.getRegularSeasonTeam(teamName, dataKind, sortField);
	}

	public ArrayList<GameTeam> getRegularGameTeam(String teamName, Season season, Field sortField) {
		return oneTeamData.getRegularGameTeam(teamName, season, sortField);
	}

	public ArrayList<PlayOffSeries> getPlayOffSeriesOfTeam(String teamName) {
		return oneTeamData.getPlayOffSeriesOfTeam(teamName);
	}

	public ArrayList<GameTeam> getSeriesGameTeam(String teamName, PlayOffSeries playOffSeries) {
		return oneTeamData.getSeriesGameTeam(teamName, playOffSeries);
	}
}
