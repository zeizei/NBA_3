package data.hot;

import java.util.ArrayList;

import common.datastructure.TeamHotInfo;
import common.statics.Field;
import common.statics.Season;

public class TeamHotData_driver {
	private TeamHotData teamHotData = new TeamHotData();

	private void testGetTeamHot() {
		ArrayList<TeamHotInfo> teamHotInfoList = teamHotData.getTeamHot(Season.all_seasons[0], Field.point);
		for (int i = 0; i < teamHotInfoList.size(); i++) {
			System.out.println(teamHotInfoList.get(i));
		}
	}

	public static void main(String args[]) {
		TeamHotData_driver driver = new TeamHotData_driver();
		driver.testGetTeamHot();
	}
}
