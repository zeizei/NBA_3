package statistics.data;

import java.util.ArrayList;

import beans.GamePlayer;
import beans.SeasonPlayer;
import statistics.analysisbeans.PlayerOnOrOff;
import statistics.analysisbeans.PlayerShootDistance;
import statistics.analysisbeans.PlayerShootOpponent;
import statistics.analysisbeans.PlayerShootType;
import statistics.dataservice.StatisticsDataService;

//import org.python.util.PythonInterpreter;   


public class StatisticsData implements StatisticsDataService{
	public ArrayList<GamePlayer> getRegularSeasonPlayersGames(String playerID) {
		return null;
	}

	public ArrayList<GamePlayer> getPlayOffSeasonPlayersGames(String playerID) {
		return null;
	}

	public SeasonPlayer getRegularSeasonPlayer(String playerID) {
		return null;
	}

	public SeasonPlayer getPlayOffSeasonPlayer(String playerID) {
		return null;
	}

	public ArrayList<PlayerOnOrOff> getRegularSeasonCourtPerform(String playerID) {
		return null;
	}

	public ArrayList<PlayerOnOrOff> getPlayOffSeasonCourtPerform(String playerID) {
		return null;
	}

	public ArrayList<PlayerShootDistance> getRegularSeasonDistanceShoot(
			String playerID) {
		return null;
	}

	public ArrayList<PlayerShootDistance> getPlayOffSeasonDistanceShoot(
			String playerID) {
		return null;
	}

	public ArrayList<PlayerShootOpponent> getRegularSeasonOpponentShoot(
			String playerID) {
		return null;
	}

	public ArrayList<PlayerShootOpponent> getPlayOffSeasonOpponentShoot(
			String playerID) {
		return null;
	}

	public ArrayList<PlayerShootType> getRegularSeasonShootType(String playerID) {
		return null;
	}

	public ArrayList<PlayerShootType> getPlayOffSeasonShootType(String playerID) {
		return null;
	}
	
	
	
}

