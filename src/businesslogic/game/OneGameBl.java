package businesslogic.game;

import java.util.ArrayList;

import data.game.OneGameData;
import dataservice.game.OneGameDataService;
import beans.GamePlayer;
import beans.GameTeam;
import businesslogicservice.game.OneGameBlService;

public class OneGameBl implements OneGameBlService {
	private OneGameDataService oneGameData = new OneGameData();

	public ArrayList<GamePlayer> getPlayersPerformOfOneGame(String teamName, String date) {
		return oneGameData.getPlayersPerformOfOneGame(teamName, date);
	}

	public GameTeam getTeamPerformOfOneGame(String teamName, String date) {
		return oneGameData.getTeamPerformOfOneGame(teamName, date);
	}
}
