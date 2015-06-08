package data.team;

import java.util.ArrayList;

import beans.GameTeam;
import beans.SeasonPlayer;
import beans.SeasonTeam;
import common.statics.DataKind;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Season;
import dataservice.team.OneTeamDataService;

public class OneTeamData implements OneTeamDataService {

	@Override
	public ArrayList<SeasonPlayer> getOneSeasonPlayers(String teamName, Season season, GameKind gameKind, DataKind dataKind, Field sortField) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SeasonTeam> getSeasonTeam(String teamName, GameKind gameKind, DataKind dataKind, Field sortField) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GameTeam> getGameTeam(String teamName, Season season, Field sortField) {
		// TODO Auto-generated method stub
		return null;
	}

}
