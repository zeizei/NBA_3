package common.statics;

public class Field {
	private String field;

	private Field(String field) {
		this.field = field;
	}

	public String toString() {
		return this.field;
	}

	public static final Field date = new Field("date");

	// 普通赛季数据
	public static final Field point = new Field("point");
	public static final Field offRebound = new Field("offRebound");
	public static final Field defRebound = new Field("defRebound");
	public static final Field numOfGame = new Field("numOfGame");
	public static final Field numOfStart = new Field("numOfStart");
	public static final Field minute = new Field("minute");
	public static final Field totalHit = new Field("totalHit");
	public static final Field totalShot = new Field("totalShot");
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
	public static final Field totRebound = new Field("totRebound");
	public static final Field assist = new Field("assist");
	public static final Field steal = new Field("steal");
	public static final Field block = new Field("block");
	public static final Field fault = new Field("fault");
	public static final Field foul = new Field("foul");

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

}
