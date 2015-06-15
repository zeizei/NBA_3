package data.team;

import java.util.ArrayList;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;
import beans.GameTeam;
import beans.PlayOffSeries;
import beans.SeasonPlayer;
import beans.SeasonTeam;

public class OneTeamData_driver {
	private OneTeamData oneTeamData = new OneTeamData();

	public void testGetOneSeasonPlayers() {
		ArrayList<SeasonPlayer> seasonPlayerList = this.oneTeamData.getOneSeasonPlayers("HOU", Season.seasons_with_Career[0], GameKind.regular_game, DataKind.average, Field.point);
		for (int i = 0; i < seasonPlayerList.size(); i++) {
			System.out.println(seasonPlayerList.get(i));
			System.out.println(i);
		}
	}

	public void testGetSeasonTeam() {
		ArrayList<SeasonTeam> seasonTeamList = this.oneTeamData.getRegularSeasonTeam("Atlanta Hawks", DataKind.average, Field.season);
		for (int i = 0; i < seasonTeamList.size(); i++) {
			System.out.println(seasonTeamList.get(i));
			System.out.println(i);
		}
	}

	public void testGetRegularGameTeam() {
		ArrayList<GameTeam> gameTeamList = this.oneTeamData.getRegularGameTeam("Atlanta Hawks", Season.seasons_with_Career[0], Field.date);
		for (int i = 0; i < gameTeamList.size(); i++) {
			System.out.println(gameTeamList.get(i));
			System.out.println(i);
		}
	}

	public void testGetPlayOffSeriesOfTeam() {
		ArrayList<PlayOffSeries> playOffSeriesList = this.oneTeamData.getPlayOffSeriesOfTeam("Atlanta Hawks");
		for (int i = 0; i < playOffSeriesList.size(); i++) {
			System.out.println(playOffSeriesList.get(i));
		}
	}

	public void testGetSeriesGameTeam() {
		ArrayList<PlayOffSeries> playOffSeriesList = this.oneTeamData.getPlayOffSeriesOfTeam("Atlanta Hawks");
		ArrayList<GameTeam> seriesGameTeam = this.oneTeamData.getSeriesGameTeam("Atlanta Hawks", playOffSeriesList.get(0));
		for (int i = 0; i < seriesGameTeam.size(); i++) {
			System.out.println(seriesGameTeam.get(i));
		}
	}

	public static void main(String args[]) {
		OneTeamData_driver driver = new OneTeamData_driver();
		driver.testGetOneSeasonPlayers();
		driver.testGetSeasonTeam();
		driver.testGetRegularGameTeam();
		driver.testGetPlayOffSeriesOfTeam();
		driver.testGetSeriesGameTeam();
	}
}
