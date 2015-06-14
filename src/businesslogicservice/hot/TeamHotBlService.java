package businesslogicservice.hot;

import java.util.ArrayList;

import common.datastructure.TeamHotInfo;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;

public interface TeamHotBlService {
	// 数据王球队
	public ArrayList<TeamHotInfo> getTeamHot(Season season, GameKind gameKind, DataKind dataKind, Field sortField);
}
