package businesslogic.hot;

import java.util.ArrayList;

import businesslogicservice.hot.TeamHotBlService;

import common.datastructure.TeamHotInfo;
import common.statics.Field;
import common.statics.Season;

import data.hot.TeamHotData;
import dataservice.hot.TeamHotDataService;

public class TeamHotBl implements TeamHotBlService {
	private TeamHotDataService teamHotData = new TeamHotData();

	public ArrayList<TeamHotInfo> getTeamHot(Season season, Field sortField) {
		return teamHotData.getTeamHot(season, sortField);
	}
}
