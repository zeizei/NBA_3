package data.team;

import java.util.ArrayList;

import common.statics.Season;
import beans.PlayOffSeries;
import beans.SeasonTeam;
import beans.generalTeam;

public class TeamInfoData_driver {
	private TeamInfoData teamInfoData = new TeamInfoData();

	public void testVagueSearch() {
		ArrayList<SeasonTeam> seasonTeamList = teamInfoData.vagueSearch("Utah Stars");
		for (int i = 0; i < seasonTeamList.size(); i++) {
			System.out.println(seasonTeamList.get(i));
		}
	}

	public void testGetGeneralTeam() {
		generalTeam generalTeam = teamInfoData.getGeneralTeam("Charlotte Hornets", Season.all_seasons[0]);
		System.out.println(generalTeam);

	}

	public void testGetPlayOffSeries() {
		ArrayList<PlayOffSeries> playOffSeriesList = teamInfoData.getPlayOffSeries(Season.this_season, "Western Conf First Round");
		for (int i = 0; i < playOffSeriesList.size(); i++) {
			System.out.println(playOffSeriesList.get(i));
		}
	}

	public static void main(String args[]) {
		TeamInfoData_driver driver = new TeamInfoData_driver();
		// driver.testVagueSearch();
		// driver.testGetGeneralTeam();
		driver.testGetPlayOffSeries();
	}

}
