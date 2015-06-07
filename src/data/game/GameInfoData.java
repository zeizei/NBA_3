package data.game;

import java.util.ArrayList;

import mysqldatabase.DB;
import beans.GeneralGame;
import dataservice.game.GameInfoDataService;

public class GameInfoData implements GameInfoDataService {
	private DB db = DB.getInstance();

	public String getLatestDate() {
		return null;
	}

	public ArrayList<GeneralGame> getTodayMatches(String date) {
		return null;
	}

	public GeneralGame getGeneralMatch(String teamName, String date) {
		return null;
	}
}
