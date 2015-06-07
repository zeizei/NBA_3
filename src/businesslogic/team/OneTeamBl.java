package businesslogic.team;

import java.util.ArrayList;

import common.statics.Field;
import common.statics.Season;

import beans.GameTeam;
import beans.SeasonTeam;
import businesslogicservice.team.OneTeamBlService;

public class OneTeamBl implements OneTeamBlService {
	public OneTeamBl() {

	}

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
