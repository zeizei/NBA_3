package common.statics;

public class Field {
	private String field;

	private Field(String field) {
		this.field = field;
	}

	public String toString() {
		return this.field;
	}

	public static final Field playerId = new Field("playerId");
	public static final Field teamName = new Field("teamName");
	public static final Field date = new Field("date");
	public static final Field season = new Field("season");
	public static final Field isPlayOff = new Field("isPlayOff");
	public static final Field playerName = new Field("playerName");
	public static final Field age = new Field("age");
	public static final Field position = new Field("position");
	// 普通赛季数据
	public static final Field numOfGame = new Field("numOfGame");
	public static final Field numOfStart = new Field("numOfStart");
	public static final Field minute = new Field("minute");
	public static final Field totalHit = new Field("totalHit");
	public static final Field totalShot = new Field("totalShot");
	public static final Field totHit = new Field("totHit");
	public static final Field totShot = new Field("totShot");
	public static final Field shot = new Field("shot");
	public static final Field threeHit = new Field("threeHit");
	public static final Field threeShot = new Field("threeShot");
	public static final Field three = new Field("three");
	public static final Field twoShot = new Field("twoShot");
	public static final Field twoHit = new Field("twoHit");
	public static final Field two = new Field("two");
	public static final Field shotEFF = new Field("shotEFF");
	public static final Field freeHit = new Field("freeHit");
	public static final Field freeShot = new Field("freeShot");
	public static final Field free = new Field("free");
	public static final Field offRebound = new Field("offRebound");
	public static final Field defRebound = new Field("defRebound");
	public static final Field totRebound = new Field("totRebound");
	public static final Field assist = new Field("assist");
	public static final Field steal = new Field("steal");
	public static final Field block = new Field("block");
	public static final Field fault = new Field("fault");
	public static final Field foul = new Field("foul");
	public static final Field point = new Field("point");
	// 高级赛季数据
	public static final Field playerEFF = new Field("playerEFF");
	public static final Field realShot = new Field("realShot");
	public static final Field threeEFF = new Field("threeEFF");
	public static final Field freeEFF = new Field("freeEFF");
	public static final Field offReboundEFF = new Field("offReboundEFF");
	public static final Field defReboundEFF = new Field("defReboundEFF");
	public static final Field totReboundEFF = new Field("totReboundEFF");
	public static final Field assistEFF = new Field("assistEFF");
	public static final Field stealEFF = new Field("stealEFF");
	public static final Field blockEFF = new Field("blockEFF");
	public static final Field faultEFF = new Field("faultEFF");
	public static final Field useEFF = new Field("useEFF");
	public static final Field offWinShare = new Field("offWinShare");
	public static final Field defWinShare = new Field("defWinShare");
	public static final Field winShare = new Field("winShare");
	public static final Field winSharePer48 = new Field("winSharePer48");
	public static final Field offBoxPM = new Field("offBoxPM");
	public static final Field defBoxPM = new Field("defBoxPM");
	public static final Field BoxPM = new Field("BoxPM");
	public static final Field replaceValue = new Field("replaceValue");
	// 球队
	public static final Field oppTotalHit = new Field("oppTotalHit");
	public static final Field oppTotalShot = new Field("oppTotalShot");
	public static final Field oppShot = new Field("oppShot");
	public static final Field oppThreeHit = new Field("oppThreeHit");
	public static final Field oppThreeShot = new Field("oppThreeShot");
	public static final Field oppThree = new Field("oppThree");
	public static final Field oppTwoShot = new Field("oppTwoShot");
	public static final Field oppTwoHit = new Field("oppTwoHit");
	public static final Field oppTwo = new Field("oppTwo");
	public static final Field oppFreeHit = new Field("oppFreeHit");
	public static final Field oppFreeShot = new Field("oppFreeShot");
	public static final Field oppFree = new Field("oppFree");
	public static final Field oppOffRebound = new Field("oppOffRebound");
	public static final Field oppDefRebound = new Field("oppDefRebound");
	public static final Field oppTotRebound = new Field("oppTotRebound");
	public static final Field oppAssist = new Field("oppAssist");
	public static final Field oppSteal = new Field("oppSteal");
	public static final Field oppBlock = new Field("oppBlock");
	public static final Field oppFault = new Field("oppFault");
	public static final Field oppFoul = new Field("oppFoul");
	public static final Field oppPoint = new Field("oppPoint");
	//
	public static final Field avgAge = new Field("avgAge");
	public static final Field numOfWin = new Field("numOfWin");
	public static final Field numOfLose = new Field("numOfLose");
	public static final Field pointOfWin = new Field("pointOfWin");
	public static final Field strengthOfSchedule = new Field("strengthOfSchedule");
	public static final Field simpleRatingSystem = new Field("simpleRatingSystem");
	public static final Field offEFF = new Field("offEFF");
	public static final Field defEFF = new Field("defEFF");
	public static final Field pace = new Field("pace");
	public static final Field freePerFieldGoal = new Field("freePerFieldGoal");
	public static final Field oppShotEFF = new Field("oppShotEFF");
	public static final Field oppFaultEFF = new Field("oppFaultEFF");
	public static final Field oppFreePerFieldGoal = new Field("oppFreePerFieldGoal");
	public static final Field arean = new Field("arean");
	public static final Field attendance = new Field("attendance");
	//

	public static final Field[] player_sort_fields = { point, teamName, age, position, numOfGame, numOfStart, minute, shot, totRebound, assist, steal, block, fault, foul, totalHit, totalShot,
			threeHit, threeShot, three, twoShot, twoHit, two, shotEFF, freeHit, freeShot, free, offRebound, defRebound, playerEFF, realShot, threeEFF, freeEFF, offReboundEFF, defReboundEFF,
			totReboundEFF, assistEFF, stealEFF, blockEFF, faultEFF, useEFF, offWinShare, defWinShare, winShare, winSharePer48, offBoxPM, defBoxPM, BoxPM, replaceValue };

	public static final Field[] player_advanced_selection_fields = { numOfGame, numOfStart, minute, point, shot, totRebound, assist, steal, block, fault, foul, totalHit, totalShot, threeHit,
			threeShot, three, twoShot, twoHit, two, shotEFF, freeHit, freeShot, free, offRebound, defRebound, playerEFF, realShot, threeEFF, freeEFF, offReboundEFF, defReboundEFF, totReboundEFF,
			assistEFF, stealEFF, blockEFF, faultEFF, useEFF, offWinShare, defWinShare, winShare, winSharePer48, offBoxPM, defBoxPM, BoxPM, replaceValue };

	public static final Field[] team_sort_field = { numOfWin, numOfLose, avgAge, point, shot, three, two, free, totRebound, assist, steal, block, fault, foul, minute, totalHit, totalShot, threeHit,
			threeShot, twoShot, twoHit, freeHit, freeShot, offRebound, defRebound, offEFF, defEFF, pace, freeEFF, threeEFF, realShot, shotEFF, faultEFF, offReboundEFF, defReboundEFF };
}
