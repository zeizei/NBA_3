package dataservice.team;

import java.util.ArrayList;

import beans.SeasonTeam;

import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.League;
import common.statics.Season;

public interface TeamInfoDataService {
	public ArrayList<SeasonTeam> getSeasonTeam(Season season, GameKind gameKind, DataKind dataKind, League league, Field sortField);
}
