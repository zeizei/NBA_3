package businesslogic.hot;

import java.util.ArrayList;

import businesslogicservice.hot.TeamHotBlService;

import common.datastructure.TeamHotInfo;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;

import data.hot.TeamHotData;
import dataservice.hot.TeamHotDataService;

public class TeamHotBl implements TeamHotBlService {
	private TeamHotDataService teamHotData = new TeamHotData();

	public ArrayList<TeamHotInfo> getTeamHot(Season season, GameKind gameKind, DataKind dataKind, Field sortField) {
		return teamHotData.getTeamHot(season, gameKind, dataKind, sortField);
	}
}
