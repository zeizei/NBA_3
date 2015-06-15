package businesslogicservice.hot;

import java.util.ArrayList;

import common.datastructure.TeamHotInfo;
import common.statics.Field;
import common.statics.Season;

public interface TeamHotBlService {
	// 数据王球队
	public ArrayList<TeamHotInfo> getTeamHot(Season season, Field sortField);
}
