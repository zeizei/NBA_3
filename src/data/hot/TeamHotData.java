package data.hot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mysqldatabase.DB;

import common.datastructure.TeamHotInfo;
import common.statics.Field;
import common.statics.Number;
import common.statics.Season;

import dataservice.hot.TeamHotDataService;

public class TeamHotData implements TeamHotDataService {
	private DB db = DB.getInstance();

	public ArrayList<TeamHotInfo> getTeamHot(Season season, Field sortField) {
		if (season != null && sortField != null) {
			ArrayList<TeamHotInfo> teamHotList = new ArrayList<TeamHotInfo>(Number.defaut_hot_num);
			StringBuffer buffer = new StringBuffer("select seasonteam.teamName,generalteam.league");
			String sortStr = null;
			if (sortField.equals(Field.shot) || sortField.equals(Field.three) || sortField.equals(Field.free)) {
				buffer.append(",seasonteam.").append(sortField.toString());
				sortStr = " order by " + sortField.toString() + " desc";
			}
			else {
				buffer.append(",round(seasonteam.").append(sortField.toString()).append("/numOfGame,2) as '").append(sortField.toString()).append("'");
				sortStr = " order by " + sortField.toString() + "/numOfGame desc";
			}
			buffer.append(" from seasonteam,generalteam where seasonteam.season = '").append(season.toString()).append("' ")
					.append("and seasonteam.season <=generalteam.finishSeason and seasonteam.season >=generalteam.startSeason and generalteam.teamName=seasonteam.teamName");
			buffer.append(sortStr).append(" limit " + Number.defaut_hot_num);
			System.out.println(buffer.toString());
			ResultSet rs = this.db.find(buffer.toString());
			try {
				while (rs.next()) {
					String teamName = rs.getString("teamName");
					String league = rs.getString("league");
					double value = rs.getDouble(sortField.toString());
					TeamHotInfo temp = new TeamHotInfo();
					temp.AutoEncapsulate(new String[] { "field", "teamName", "league", "value" }, new Object[] { sortField.toString(), teamName, league, value });
					teamHotList.add(temp);
				}
				if (teamHotList != null && teamHotList.size() != 0) {
					return teamHotList;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
