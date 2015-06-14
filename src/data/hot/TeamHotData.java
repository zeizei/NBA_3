package data.hot;

import java.util.ArrayList;

import common.datastructure.TeamHotInfo;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;

import dataservice.hot.TeamHotDataService;

public class TeamHotData implements TeamHotDataService {

	@Override
	public ArrayList<TeamHotInfo> getTeamHot(Season season, GameKind gameKind, DataKind dataKind, Field sortField) {
		// TODO Auto-generated method stub
		return null;
	}

}
