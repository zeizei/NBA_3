package businesslogic.team;

import java.util.ArrayList;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;
import data.team.OneTeamData;
import dataservice.team.OneTeamDataService;
import beans.GameTeam;
import beans.SeasonPlayer;
import beans.SeasonTeam;
import businesslogicservice.team.OneTeamBlService;

public class OneTeamBl implements OneTeamBlService {
	private OneTeamDataService oneTeamData = new OneTeamData();

	@Override
	public ArrayList<SeasonPlayer> getOneSeasonPlayers(String teamName, Season season, GameKind gameKind, DataKind dataKind, Field sortField) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SeasonPlayer> getLatestSeasonPlayers(String teamName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SeasonTeam> getSeasonTeam(String teamName, GameKind gameKind, DataKind dataKind, Field sortField) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GameTeam> getGameTeam(String teamName, Season season, Field sortField) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GameTeam> getLatestGameTeam(String teamName) {
		// TODO Auto-generated method stub
		return null;
	}

}
