package data.team;

import java.util.ArrayList;

import beans.SeasonTeam;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.League;
import common.statics.Season;

import dataservice.team.TeamInfoDataService;

public class TeamInfoData implements TeamInfoDataService {

	@Override
	public ArrayList<SeasonTeam> getSeasonTeam(Season season, GameKind gameKind, DataKind dataKind, League league, Field sortField) {
		// TODO Auto-generated method stub
		return null;
	}

}
