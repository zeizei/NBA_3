package businesslogic.game;

import java.util.ArrayList;

import data.game.GameInfoData;
import dataservice.game.GameInfoDataService;
import beans.GeneralGame;
import businesslogicservice.game.GameInfoBlService;

public class GameInfoBl implements GameInfoBlService {
	private GameInfoDataService gameInfoData = new GameInfoData();

	public String getLatestDate() {
		return gameInfoData.getLatestDate();
	}

	public ArrayList<GeneralGame> getTodayMatches(String date) {
		return gameInfoData.getTodayMatches(date);
	}

	public GeneralGame getGeneralMatch(String teamName, String date) {
		return gameInfoData.getGeneralMatch(teamName, date);
	}
}
