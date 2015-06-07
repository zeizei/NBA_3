package businesslogicservice.hot;

import java.util.ArrayList;

import common.datastructure.PlayerHotInfo;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;
import beans.GamePlayer;
import beans.SeasonPlayer;

public interface PlayerHotBlSrevice {
	// 得到指定赛季的进步最快球员数组
	public ArrayList<PlayerHotInfo> getPlayerHot(String nowDate, String field);

	// 得到赛季数据王数组
	public ArrayList<SeasonPlayer> getPlayerKingOfSeason(Season season, GameKind gameKind, DataKind dataKind, Field sortField);

	// 得到当日数据王数组
	public ArrayList<GamePlayer> getPlayerKingOfDaily(String date, Field sortField);
}
