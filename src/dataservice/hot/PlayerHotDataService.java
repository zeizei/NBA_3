package dataservice.hot;

import java.util.ArrayList;

import beans.GamePlayer;

import common.statics.Field;

public interface PlayerHotDataService {
	// 得到当日数据王数组
	public ArrayList<GamePlayer> getPlayerKingOfDaily(String date, Field sortField);
}
