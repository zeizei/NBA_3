package dataservice.hot;

import java.util.ArrayList;

import beans.SeasonTeam;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;

public interface TeamHotDataService {
	// 数据王球队
	public ArrayList<SeasonTeam> getTeamHot(Season season, GameKind gameKind, DataKind dataKind, Field sortField);
}
