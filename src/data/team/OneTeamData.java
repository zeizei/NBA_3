package data.team;

import java.util.ArrayList;

import beans.GameTeam;
import beans.SeasonTeam;

import common.statics.Field;
import common.statics.Season;

import dataservice.team.OneTeamDataService;

public class OneTeamData implements OneTeamDataService {

	@Override
	public ArrayList<String> getAllPlayerIds(String teamName, Season season) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SeasonTeam> getSeasonTeam(String teamName, Field sortField) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GameTeam> getGameTeam(String teamName, Season season, Field sortField) {
		// TODO Auto-generated method stub
		return null;
	}

}
