package dataservice.hot;

import java.util.ArrayList;

import common.datastructure.PlayerHotInfo;
import common.datastructure.PlayerKingInfo;
import common.statics.Field;
import common.statics.Season;

public interface PlayerHotDataService {
	public ArrayList<PlayerHotInfo> getPlayerHot(Field field);

	public ArrayList<PlayerKingInfo> getPlayerKingOfSeason(Season season, Field sortField);// 最近这个赛季常规赛数据王

	public ArrayList<PlayerKingInfo> getPlayerKingOfDaily(String date, Field sortField);
}
