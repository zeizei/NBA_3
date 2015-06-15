package data.team;

import java.sql.ResultSet;
import java.util.ArrayList;

import mysqldatabase.DB;
import beans.Bean;
import beans.GameTeam;
import beans.PlayOffSeries;
import beans.SeasonPlayer;
import beans.SeasonTeam;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;
import dataservice.team.OneTeamDataService;

public class OneTeamData implements OneTeamDataService {
	private DB db = DB.getInstance();

	public ArrayList<SeasonPlayer> getOneSeasonPlayers(String teamName, Season season, GameKind gameKind, DataKind dataKind, Field sortField) {
		if (teamName != null && season != null && gameKind != null && dataKind != null && sortField != null) {
			int isPlayOff = 0;
			if (GameKind.playOff_game.equals(gameKind)) {
				isPlayOff = 1;
			}
			String sql = "select " + SeasonPlayer.getAvgSqlString() + " from seasonplayer where teamName = '" + teamName + "' and isPlayOff=" + isPlayOff + " and season = '" + season.toString()
					+ "' order by " + sortField.toString() + " desc";
			if (DataKind.total.equals(dataKind)) {
				sql = "select * from seasonplayer where teamName = '" + teamName + "' and isPlayOff=" + isPlayOff + " and season = '" + season.toString() + "' order by " + sortField.toString()
						+ " desc";
			}
			ResultSet rs = this.db.find(sql);
			ArrayList<SeasonPlayer> seasonPlayerList = Bean.resultSetToList(rs, new SeasonPlayer());
			if (seasonPlayerList != null && seasonPlayerList.size() != 0) {
				return seasonPlayerList;
			}
		}
		return null;
	}// 得到一支球队中一个赛季里所有球员的比赛数据

	public ArrayList<SeasonTeam> getRegularSeasonTeam(String teamName, DataKind dataKind, Field sortField) {
		if (teamName != null && dataKind != null && sortField != null) {
			String sql = "select " + SeasonTeam.getAvgSqlString() + " from seasonteam where teamName = '" + teamName + "' order by " + sortField.toString() + " desc";
			if (DataKind.total.equals(dataKind)) {
				sql = "select * from seasonteam where teamName = '" + teamName + "' order by " + sortField.toString() + " desc";
			}
			ResultSet rs = this.db.find(sql);
			ArrayList<SeasonTeam> seasonTeamList = Bean.resultSetToList(rs, new SeasonTeam());
			if (seasonTeamList != null && seasonTeamList.size() != 0) {
				return seasonTeamList;
			}
		}
		return null;
	}// 得到一支球队所有赛季的比赛数据

	public ArrayList<GameTeam> getRegularGameTeam(String teamName, Season season, Field sortField) {
		if (teamName != null && season != null && sortField != null) {
			String sql = "select * from gameteam where (date in (select date from generalgame where (date between '" + season.getStartDate() + "' and '" + season.getFinishDate()
					+ "')and (isPlayOff = 0))) and (teamName = '" + teamName + "') order by " + sortField.toString() + " desc";
			ResultSet rs = this.db.find(sql);
			ArrayList<GameTeam> gameTeamList = Bean.resultSetToList(rs, new GameTeam());
			if (gameTeamList != null && gameTeamList.size() != 0) {
				return gameTeamList;
			}
		}
		return null;
	}// 得到一支球队一个赛季里的所有常规赛比赛

	public ArrayList<PlayOffSeries> getPlayOffSeriesOfTeam(String teamName) {
		if (teamName != null) {
			String sql = "select * from playoffseries where winTeam = '" + teamName + "' or loseTeam = '" + teamName + "' order by startDate desc";
			ResultSet rs = this.db.find(sql);
			ArrayList<PlayOffSeries> playOffSeriesList = Bean.resultSetToList(rs, new PlayOffSeries());
			if (playOffSeriesList != null && playOffSeriesList.size() != 0) {
				return playOffSeriesList;
			}
		}
		return null;
	}// 得到一支球队参加的所有季后赛系列赛

	public ArrayList<GameTeam> getSeriesGameTeam(String teamName, PlayOffSeries playOffSeries) {
		if (playOffSeries != null) {
			String sql = "select * from gameteam where teamName = '" + teamName + "' and date between '" + playOffSeries.getStartDate() + "' and '" + playOffSeries.getFinishDate()
					+ "' order by date desc";
			ResultSet rs = this.db.find(sql);
			ArrayList<GameTeam> seriesGameTeam = Bean.resultSetToList(rs, new GameTeam());
			if (seriesGameTeam != null && seriesGameTeam.size() != 0) {
				return seriesGameTeam;
			}
		}
		return null;
	}// 得到一个季后赛系列赛的所有比赛详细信息
}
