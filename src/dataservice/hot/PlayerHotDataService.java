package dataservice.hot;

import java.util.ArrayList;

import beans.GamePlayer;
import beans.SeasonPlayer;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;

public interface PlayerHotDataService {
	// 得到指定赛季的进步最快球员数组
	public ArrayList<GamePlayer> getPlayerHot(String nowDate, String field);

	// 得到赛季数据王数组
	public ArrayList<SeasonPlayer> getPlayerKingOfSeason(Season season, GameKind gameKind, DataKind dataKind, Field sortField);

	// 得到当日数据王数组
	public ArrayList<GamePlayer> getPlayerKingOfDaily(String date, Field sortField);
}
