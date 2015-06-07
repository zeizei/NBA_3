package data.hot;

import java.util.ArrayList;

import beans.SeasonTeam;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;

import dataservice.hot.TeamHotDataService;

public class TeamHotData implements TeamHotDataService {

	@Override
	public ArrayList<SeasonTeam> getTeamHot(Season season, GameKind gameKind, DataKind dataKind, Field sortField) {
		// TODO Auto-generated method stub
		return null;
	}

}
