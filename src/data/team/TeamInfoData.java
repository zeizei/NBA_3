package data.team;

import java.sql.ResultSet;
import java.util.ArrayList;

import mysqldatabase.DB;
import beans.Bean;
import beans.SeasonTeam;
import beans.generalTeam;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.League;
import common.statics.Season;

import dataservice.team.TeamInfoDataService;

public class TeamInfoData implements TeamInfoDataService {
	private DB db = DB.getInstance();

	public ArrayList<SeasonTeam> getSeasonTeam(Season season, DataKind dataKind, League league, Field sortField) {
		if (season != null && dataKind != null && league != null && sortField != null) {
			String dataKindSql = "select *";
			if (dataKind.equals(DataKind.average)) {
				dataKindSql = "select " + SeasonTeam.getAvgSqlString();
			}
			String sql = null;
			if (league.equals(League.all_league)) {
				sql = dataKindSql + " from seasonteam where season = '" + season.toString() + "' order by " + sortField.toString() + " desc";
			}
			else {
				sql = dataKindSql + " from seasonteam,generalteam where seasonteam.season = '" + season.toString()
						+ "' and seasonteam.teamName=generalteam.teamName and seasonteam.season>=generalteam.startSeason and seasonteam.season<=generalteam.finishSeason and generalteam.league = '"
						+ league.toString() + "' order by " + sortField.toString() + " desc";
			}
			System.out.println(sql);
			ResultSet rs = this.db.find(sql);
			ArrayList<SeasonTeam> seasonTeamList = Bean.resultSetToList(rs, new SeasonTeam());
			if (seasonTeamList != null && seasonTeamList.size() != 0) {
				return seasonTeamList;
			}
		}

		return null;
	}

	public ArrayList<SeasonTeam> vagueSearch(String str) {
		if (str != null && str.length() != 0) {
			str = str.toLowerCase();
			String sql = "select " + SeasonTeam.getAvgSqlString() + " from seasonteam where lower(teamName) like '%" + str + "%' order by season desc";
			ResultSet rs = this.db.find(sql);
			ArrayList<SeasonTeam> seasonTeamList = Bean.resultSetToList(rs, new SeasonTeam());
			if (seasonTeamList != null && seasonTeamList.size() != 0) {
				return seasonTeamList;
			}
		}
		return null;
	}// 模糊查找

	public generalTeam getGeneralTeam(String teamName, Season season) {
		if (teamName != null && season != null) {
			String sql = "select * from generalteam where teamName= '" + teamName + "' and startSeason <= '" + season.toString() + "' and finishSeason >= '" + season + "'";
			ResultSet rs = this.db.find(sql);
			ArrayList<generalTeam> generalTeamList = Bean.resultSetToList(rs, new generalTeam());
			if (generalTeamList != null && generalTeamList.size() == 1) {
				return generalTeamList.get(0);
			}
		}
		return null;
	}// 得到球队基本信息
}
