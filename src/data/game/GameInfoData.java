package data.game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mysqldatabase.DB;
import beans.Bean;
import beans.GeneralGame;
import dataservice.game.GameInfoDataService;

public class GameInfoData implements GameInfoDataService {
	private DB db = DB.getInstance();

	public String getLatestDate() {
		String sql = "select date from generalgame order by date desc";
		ResultSet rs = this.db.find(sql);
		if (rs != null) {
			try {
				rs.first();
				String latestDate = rs.getString("date");
				return latestDate;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}// 得到最近的日期

	public ArrayList<GeneralGame> getTodayMatches(String date) {
		if (date != null) {
			String sql = "select * from generalgame where date ='" + date + "'";
			ResultSet rs = this.db.find(sql);
			ArrayList<GeneralGame> generalGameList = Bean.resultSetToList(rs, new GeneralGame());
			if (generalGameList != null && generalGameList.size() != 0) {
				return generalGameList;
			}
		}
		return null;
	}// 得到某一天的所有比赛

	public GeneralGame getGeneralMatch(String teamName, String date) {
		return null;
	}

	public static void main(String args[]) {
		GameInfoData gameInfoData = new GameInfoData();
		String latestDate = gameInfoData.getLatestDate();
		System.out.println(latestDate);
	}
}
