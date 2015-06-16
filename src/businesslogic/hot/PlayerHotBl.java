package businesslogic.hot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import beans.GamePlayer;
import businesslogicservice.hot.PlayerHotBlSrevice;

import common.datastructure.PlayerHotInfo;
import common.datastructure.PlayerKingInfo;
import common.statics.Field;
import common.statics.GameKind;
import common.statics.Number;
import common.statics.Season;

import data.game.GameInfoData;
import data.hot.PlayerHotData;
import data.player.OnePlayerData;
import data.player.PlayerInfoData;
import dataservice.game.GameInfoDataService;
import dataservice.hot.PlayerHotDataService;
import dataservice.player.OnePlayerDataService;
import dataservice.player.PlayerInfoDataService;

public class PlayerHotBl implements PlayerHotBlSrevice {
	private PlayerHotDataService playerHotData = new PlayerHotData();
	private GameInfoDataService gameInfoBl = new GameInfoData();
	private OnePlayerDataService onePlayerData = new OnePlayerData();
	private PlayerInfoDataService playerInfoData = new PlayerInfoData();

	public ArrayList<PlayerHotInfo> getPlayerHot(Season season, Field field) {
		ArrayList<PlayerHotInfo> playerHotList = new ArrayList<PlayerHotInfo>();
		ArrayList<String> playerIdList = this.playerInfoData.getTargetPlayerIdsOfSeason(season);
		System.out.println(playerIdList.size());
		for (int i = 0; i < playerIdList.size(); i++) {
			String playerId = playerIdList.get(i);
			System.out.println(playerId);
			ArrayList<GamePlayer> onePlayerPerform = this.onePlayerData.getGamePlayer(playerId, season, GameKind.regular_game, Field.date);
			System.out.println(onePlayerPerform.size());
			int numOfGame = onePlayerPerform.size();
			if (numOfGame > 5) {
				PlayerHotInfo tempHotInfo = new PlayerHotInfo();
				tempHotInfo.setField(field.toString());
				tempHotInfo.setPlayerName(onePlayerPerform.get(0).getPlayerName());
				tempHotInfo.setPlayerId(playerId);
				tempHotInfo.setPosition(onePlayerData.getGeneralPlayer(playerId).getPosition());
				tempHotInfo.setTeamName(onePlayerPerform.get(0).getTeamName());
				double before = 0;
				double latestFive = 0;
				double all = 0;
				ArrayList<Double> dataList = new ArrayList<Double>(128);
				for (int m = 0; m < numOfGame; m++) {
					Object[] contents = onePlayerPerform.get(m).getSpeContent(new String[] { field.toString() });
					if (contents != null) {
						double value = (double) contents[0];
						dataList.add(value);
					}
				}
				for (int j = 0; j < dataList.size() - 5; j++) {
					before += dataList.get(j);
				}
				for (int j = dataList.size() - 5; j < dataList.size(); j++) {
					latestFive += dataList.get(j);
				}
				if (before != 0) {
					all = before + latestFive;
					// 以上都为总和，以下为平均
					all /= numOfGame;
					before /= (numOfGame - 5);
					latestFive /= 5;
					double upGradeRate = tempHotInfo.cutTail((latestFive - before) / before, 2);
					tempHotInfo.setValue(tempHotInfo.cutTail(all, 2));
					tempHotInfo.setUpgradeRate(upGradeRate);
					playerHotList.add(tempHotInfo);
				}
			}
		}
		Collections.sort(playerHotList, new Comparator<PlayerHotInfo>() {
			public int compare(PlayerHotInfo o1, PlayerHotInfo o2) {
				if (o1.getUpgradeRate() < o2.getUpgradeRate()) {
					return 1;
				}
				else if (o1.getUpgradeRate() == o2.getUpgradeRate()) {
					return 0;
				}
				else {
					return -1;
				}
			}
		});
		int number = Number.defaut_hot_num;
		if (Number.defaut_hot_num > playerHotList.size()) {
			number = playerHotList.size();
		}
		ArrayList<PlayerHotInfo> resultList = new ArrayList<PlayerHotInfo>(number);
		for (int i = 0; i < number; i++) {
			resultList.add(playerHotList.get(i));
		}
		return resultList;
	}

	public ArrayList<PlayerKingInfo> getPlayerKingOfSeason(Season season, Field sortField) {
		return playerHotData.getPlayerKingOfSeason(season, sortField);
	}

	public ArrayList<PlayerKingInfo> getPlayerKingOfDaily(String date, Field sortField) {
		return playerHotData.getPlayerKingOfDaily(date, sortField);
	}

	public String getLatestDate() {
		return gameInfoBl.getLatestDate();
	}
}
