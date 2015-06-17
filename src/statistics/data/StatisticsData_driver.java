package statistics.data;

import java.util.ArrayList;

import statistics.analysisbeans.PlayerOnOrOff;
import statistics.analysisbeans.PlayerShootDistance;
import statistics.analysisbeans.PlayerShootOpponent;
import statistics.analysisbeans.PlayerShootType;
import beans.GamePlayer;
import beans.SeasonPlayer;

public class StatisticsData_driver {
	private StatisticsData data = new StatisticsData();

	public void testGetPlayersGames() {
		ArrayList<GamePlayer> gamePlayerList = data.getPlayersGames("curryst01", 1);
		for (int i = 0; i < gamePlayerList.size(); i++) {
			System.out.println(gamePlayerList.get(i));
		}
	}

	public void testGetSeasonPlayer() {
		SeasonPlayer seasonPlayer = data.getSeasonPlayer("curryst01", 0);
		System.out.println(seasonPlayer);
	}

	public void testGetCourtPerform() {
		ArrayList<PlayerOnOrOff> playerOnOrOff = data.getCourtPerform("curryst01", 0);
		for (int i = 0; i < playerOnOrOff.size(); i++) {
			System.out.println(playerOnOrOff.get(i));
		}
	}

	public void testGetDistanceShoot() {
		ArrayList<PlayerShootDistance> playerDistanceShootList = data.getDistanceShoot("curryst01", 1);
		for (int i = 0; i < playerDistanceShootList.size(); i++) {
			System.out.println(playerDistanceShootList.get(i));
		}
	}

	public void testGetOpponentShoot() {
		ArrayList<PlayerShootOpponent> playerOpponentShootList = data.getOpponentShoot("curryst01", 0);
		for (int i = 0; i < playerOpponentShootList.size(); i++) {
			System.out.println(playerOpponentShootList.get(i));
		}
	}

	public void testGetTypeShoot() {
		ArrayList<PlayerShootType> playerTypeShootList = data.getShootType("jamesle01", 0);
		for (int i = 0; i < playerTypeShootList.size(); i++) {
			System.out.println(playerTypeShootList.get(i));
		}
	}

	public void testVagueSearch() {
		ArrayList<SeasonPlayer> seasonPlayerList = data.vagueSearchPlayer("brook");
		for (int i = 0; i < seasonPlayerList.size(); i++) {
			System.out.println(seasonPlayerList.get(i));
		}
	}

	public void testGetPlayOffPlayer() {
		ArrayList<SeasonPlayer> seasonPlayerList = data.getPlayOffPlayer();
		for (int i = 0; i < seasonPlayerList.size(); i++) {
			System.out.println(seasonPlayerList.get(i));
		}
	}

	public static void main(String args[]) {
		StatisticsData_driver driver = new StatisticsData_driver();
		// driver.testGetPlayersGames();
		// driver.testGetSeasonPlayer();
		// driver.testGetCourtPerform();
		// driver.testGetDistanceShoot();
		// driver.testGetOpponentShoot();
		// driver.testGetTypeShoot();
		// driver.testVagueSearch();
		driver.testGetPlayOffPlayer();
	}
}
