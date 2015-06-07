package businesslogic.team;

import java.util.ArrayList;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.League;
import common.statics.Season;

import beans.SeasonTeam;
import businesslogicservice.team.TeamInfoBlService;

public class TeamInfoBl implements TeamInfoBlService {

	@Override
	public ArrayList<SeasonTeam> getSeasonTeam(Season season, GameKind gameKind, DataKind dataKind, League league, Field sortField) {
		// TODO Auto-generated method stub
		return null;
	}

}
