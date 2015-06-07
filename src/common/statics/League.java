package common.statics;

public class League {
	private String league;

	private League(String league) {
		this.league = league;
	}

	public String toString() {
		return this.league;
	}

	public static final League west = new League("West");
	public static final League east = new League("East");
	public static final League all_league = new League("All");
}
