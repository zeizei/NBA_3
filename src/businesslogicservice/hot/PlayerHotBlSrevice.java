package businesslogicservice.hot;

import java.util.ArrayList;

import common.datastructure.PlayerHotInfo;
import common.datastructure.PlayerKingInfo;
import common.statics.Field;
import common.statics.Season;

public interface PlayerHotBlSrevice {
	public String getLatestDate();

	// 得到进步最快球员数组
	public ArrayList<PlayerHotInfo> getPlayerHot(Season season, Field field);

	// 得到赛季数据王数组
	public ArrayList<PlayerKingInfo> getPlayerKingOfSeason(Season season, Field sortField);

	// 得到当日数据王数组
	public ArrayList<PlayerKingInfo> getPlayerKingOfDaily(String date, Field sortField);
}
