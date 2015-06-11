package dataservice.team;

import java.util.ArrayList;

import beans.GameTeam;
import beans.PlayOffSeries;
import beans.SeasonPlayer;
import beans.SeasonTeam;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;

public interface OneTeamDataService {
	// 根据球队名称和赛季得到该赛季球队所有球员数据
	public ArrayList<SeasonPlayer> getOneSeasonPlayers(String teamName, Season season, GameKind gameKind, DataKind dataKind, Field sortField);

	// 根据球队名称得到球队所有赛季常规赛数据
	public ArrayList<SeasonTeam> getRegularSeasonTeam(String teamName, DataKind dataKind, Field sortField);

	// 根据球队名称得到球队所有赛季的季后赛系列赛信息
	public ArrayList<PlayOffSeries> getPlayOffSeriesOfTeam(String teamName);

	// 得到一支球队在一个季后赛系列赛的所有比赛详细数据
	public ArrayList<GameTeam> getSeriesGameTeam(String teamName, PlayOffSeries playOffSeries);

	// 得到球队一个赛季里的所有常规赛比赛详细数据
	public ArrayList<GameTeam> getRegularGameTeam(String teamName, Season season, Field sortField);
}
