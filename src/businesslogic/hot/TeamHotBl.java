package businesslogic.hot;

import java.util.ArrayList;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;
import data.hot.TeamHotData;
import dataservice.hot.TeamHotDataService;
import beans.SeasonTeam;
import businesslogicservice.hot.TeamHotBlService;

public class TeamHotBl implements TeamHotBlService {
	private TeamHotDataService teamHotData = new TeamHotData();

	public ArrayList<SeasonTeam> getTeamHot(Season season, GameKind gameKind, DataKind dataKind, Field sortField) {
		return teamHotData.getTeamHot(season, gameKind, dataKind, sortField);
	}
}
