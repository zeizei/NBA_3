package data.player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mysqldatabase.DB;
import beans.Bean;
import beans.SeasonPlayer;

import common.datastructure.CombineSelectionCell;
import common.statics.AgeRange;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.League;
import common.statics.Position;
import common.statics.Season;

import dataservice.player.PlayerInfoDataService;

public class PlayerInfoData implements PlayerInfoDataService {
	private DB db = DB.getInstance();

	public ArrayList<SeasonPlayer> getSeasonPlayer(Season season, GameKind gameKind, DataKind dataKind, League league, Position position, AgeRange ageRange, Field sortField) {
		if (season != null && gameKind != null && dataKind != null && league != null && position != null && ageRange != null && sortField != null) {
			String isPlayOffSql = " and isPlayOff = 0 ";
			if (GameKind.playOff_game.equals(gameKind)) {
				isPlayOffSql = " and isPlayOff = 1 ";
			}// 是否季后赛
			String positionSql = " and position like '%" + position.toString() + "%'";
			if (position.equals(Position.All) || season.equals(Season.seasons_with_Career[0])) {
				positionSql = "";
			}
			String ageSql = " and " + ageRange.toString();
			if (ageRange.equals(AgeRange.All) || season.equals(Season.seasons_with_Career[0])) {
				ageSql = "";
			}
			String dataKindSql = "select *";
			if (dataKind.equals(DataKind.average)) {
				dataKindSql = "select " + SeasonPlayer.getAvgSqlString();
			}
			String sql = dataKindSql + " from seasonplayer, generalteam where season = '" + season.toString() + "'" + isPlayOffSql + positionSql + ageSql
					+ " and (generalteam.teamName = seasonplayer.teamName) and (seasonplayer.season between generalteam.startSeason and generalteam.finishSeason) and generalteam.league = '"
					+ league.toString() + "' order by " + sortField.toString() + " desc limit 50";
			if (league.equals(League.all_league) || season.equals(Season.seasons_with_Career[0])) {
				sql = dataKindSql + " from seasonplayer where season = '" + season.toString() + "'" + isPlayOffSql + positionSql + ageSql + " order by " + sortField.toString() + " desc limit 50";
			}
			System.out.println(sql);
			ResultSet rs = this.db.find(sql);
			ArrayList<SeasonPlayer> seasonPlayerList = Bean.resultSetToList(rs, new SeasonPlayer());
			if (seasonPlayerList != null && seasonPlayerList.size() != 0) {
				return seasonPlayerList;
			}
		}
		return null;
	}

	public ArrayList<SeasonPlayer> vagueSearch(String str) {
		if (str != null && str.length() != 0) {
			str = str.toLowerCase();
			String sql = "select " + SeasonPlayer.getAvgSqlString() + " from seasonplayer where lower(playerName) like '%" + str
					+ "%' and seasonplayer.season = 'Career' and seasonplayer.isPlayOff = 0 order by seasonplayer.point desc";
			ResultSet rs = this.db.find(sql);
			ArrayList<SeasonPlayer> seasonPlayerList = Bean.resultSetToList(rs, new SeasonPlayer());
			if (seasonPlayerList != null && seasonPlayerList.size() != 0) {
				return seasonPlayerList;
			}
		}
		return null;
	}// 模糊查找

	public ArrayList<SeasonPlayer> getSeasonPlayer(Season season, GameKind gameKind, DataKind dataKind, League league, Position position, AgeRange ageRange, ArrayList<CombineSelectionCell> cellList) {
		if (season != null && gameKind != null && dataKind != null && league != null && position != null && ageRange != null && cellList != null && cellList.size() != 0) {
			String isPlayOffSql = " and isPlayOff = 0 ";
			if (GameKind.playOff_game.equals(gameKind)) {
				isPlayOffSql = " and isPlayOff = 1 ";
			}// 是否季后赛
			String positionSql = " and position like '%" + position.toString() + "%'";
			if (position.equals(Position.All) || season.equals(Season.seasons_with_Career[0])) {
				positionSql = "";
			}// 位置
			String ageSql = " and " + ageRange.toString();
			if (ageRange.equals(AgeRange.All) || season.equals(Season.seasons_with_Career[0])) {
				ageSql = "";
			}// 年龄
			StringBuffer buffer = new StringBuffer();
			String dataKindSql = "select *";
			if (dataKind.equals(DataKind.average)) {
				dataKindSql = "select " + SeasonPlayer.getAvgSqlString();
				for (int i = 0; i < cellList.size(); i++) {
					buffer.append(" and ").append(cellList.get(i).getAvgStr());
				}
			}
			else {
				for (int i = 0; i < cellList.size(); i++) {
					buffer.append(" and ").append(cellList.get(i).getSqlStr());
				}
			}// 数据类型以及筛选内容
			String selectSql = buffer.toString();
			String sql = dataKindSql + " from seasonplayer, generalteam where season = '" + season.toString() + "'" + isPlayOffSql + positionSql + ageSql
					+ " and (generalteam.teamName = seasonplayer.teamName) and (seasonplayer.season between generalteam.startSeason and generalteam.finishSeason) and generalteam.league = '"
					+ league.toString() + "' " + selectSql + " order by point desc";
			if (league.equals(League.all_league) || season.equals(Season.seasons_with_Career[0])) {
				sql = dataKindSql + " from seasonplayer where season = '" + season.toString() + "'" + isPlayOffSql + positionSql + ageSql + selectSql + " order by point desc";
			}
			System.out.println(sql);
			ResultSet rs = this.db.find(sql);
			ArrayList<SeasonPlayer> seasonPlayerList = Bean.resultSetToList(rs, new SeasonPlayer());
			if (seasonPlayerList != null && seasonPlayerList.size() != 0) {
				return seasonPlayerList;
			}
		}
		return null;
	}// 高级筛选

	public ArrayList<String> getTargetPlayerIdsOfSeason(Season season) {
		if (season != null) {
			ArrayList<String> playerIdList = new ArrayList<String>();
			String sql = "select distinct playerId from seasonplayer where season = '" + season.toString() + "' and numOfGame>=58 and minute/numOfGame>=15";
			ResultSet rs = this.db.find(sql);
			try {
				while (rs.next()) {
					playerIdList.add(rs.getString("playerId"));
				}
				if (playerIdList != null && playerIdList.size() != 0) {
					return playerIdList;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}// 得到某个赛季里所有的参赛球员的名称
}
