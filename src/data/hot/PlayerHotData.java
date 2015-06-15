package data.hot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mysqldatabase.DB;

import common.datastructure.PlayerHotInfo;
import common.datastructure.PlayerKingInfo;
import common.statics.Field;
import common.statics.Number;
import common.statics.Season;

import dataservice.hot.PlayerHotDataService;

public class PlayerHotData implements PlayerHotDataService {
	private DB db = DB.getInstance();

	public ArrayList<PlayerHotInfo> getPlayerHot(Field field) {

		return null;
	}

	public ArrayList<PlayerKingInfo> getPlayerKingOfSeason(Season season, Field sortField) {
		if (season != null && sortField != null) {
			ArrayList<PlayerKingInfo> playerKingList = new ArrayList<PlayerKingInfo>(Number.defaut_hot_num);
			StringBuffer buffer = new StringBuffer("select playerId,playerName,teamName,position");
			String sortStr = null;
			if (sortField.equals(Field.shot) || sortField.equals(Field.three) || sortField.equals(Field.free)) {
				buffer.append(",").append(sortField.toString());
				if (sortField.equals(Field.shot)) {
					sortStr = " and  point/numOfGame >=10 order by " + sortField.toString() + " desc";
				}
				else {
					sortStr = " and " + sortField.toString() + "Shot >=150 order by " + sortField.toString() + " desc";
				}
			}
			else {
				buffer.append(",").append("round(" + sortField.toString()).append("/numOfGame,2) as '").append(sortField.toString()).append("'");
				sortStr = " order by " + sortField.toString() + "/numOfGame desc";
			}
			buffer.append(" from seasonplayer where season = '").append(season.toString()).append("'");
			buffer.append(" and minute/numOfGame >= 15 and numOfGame >= 58 ");
			buffer.append(sortStr).append(" limit " + Number.defaut_hot_num);
			System.out.println(buffer.toString());
			ResultSet rs = this.db.find(buffer.toString());
			try {
				while (rs.next()) {
					String playerId = rs.getString("playerId");
					String playerName = rs.getString("playerName");
					String teamName = rs.getString("teamName");
					double value = rs.getDouble(sortField.toString());
					String position = rs.getString("position");
					PlayerKingInfo temp = new PlayerKingInfo();
					temp.AutoEncapsulate(new String[] { "field", "playerId", "playerName", "teamName", "value", "position" }, new Object[] { sortField.toString(), playerId, playerName, teamName,
							value, position });
					playerKingList.add(temp);
				}
				if (playerKingList != null && playerKingList.size() != 0) {
					return playerKingList;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}// 得到最近这个赛季的赛季数据王

	public ArrayList<PlayerKingInfo> getPlayerKingOfDaily(String date, Field sortField) {
		if (date != null && sortField != null) {
			ArrayList<PlayerKingInfo> playerKingList = new ArrayList<PlayerKingInfo>(Number.defaut_hot_num);
			String sql = "select gameplayer.playerId,gameplayer.playerName,gameplayer.teamName,generalplayer.position,gameplayer." + sortField.toString()
					+ " from gameplayer,generalplayer where gameplayer.date ='" + date + "' and gameplayer.playerId=generalplayer.playerId order by " + sortField.toString() + " desc limit "
					+ Number.defaut_hot_num;
			ResultSet rs = this.db.find(sql);
			try {
				while (rs.next()) {
					String playerId = rs.getString("playerId");
					String playerName = rs.getString("playerName");
					String teamName = rs.getString("teamName");
					double value = rs.getDouble(sortField.toString());
					String position = rs.getString("position");
					PlayerKingInfo temp = new PlayerKingInfo();
					temp.AutoEncapsulate(new String[] { "field", "playerId", "playerName", "teamName", "value", "position" }, new Object[] { sortField.toString(), playerId, playerName, teamName,
							value, position });
					playerKingList.add(temp);
				}
				if (playerKingList != null && playerKingList.size() != 0) {
					return playerKingList;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}// 得到每日数据王
}
