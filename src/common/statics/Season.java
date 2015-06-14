package common.statics;

public class Season {
	private String season;
	private String startYear;
	private String finishYear;

	private Season(String season) {
		this.season = season;
		if (!season.equals("Career")) {
			this.startYear = season.substring(0, 4);
			this.finishYear = String.valueOf(Integer.parseInt(startYear) + 1);
		}
	}

	public String toString() {
		return this.season;
	}

	public String getStartDate() {
		return startYear + "-10-01";

	}

	public String getFinishDate() {
		return finishYear + "-07-15";

	}

	public boolean equals(Object o) {
		return this.season.equals(o.toString());
	}

	public static Season dateToSeason(String date) {
		for (int i = 0; i < 69; i++) {
			if (season_team[i].getStartDate().compareTo(date) < 0 && season_team[i].getFinishDate().compareTo(date) > 0) {
				return season_team[i];
			}
		}
		return null;
	}

	public static Season getSeason(String str) {
		for (int i = 0; i < 69; i++) {
			if ((season_team[i].toString()).equals(str)) {
				return season_team[i];
			}
		}
		return null;
	}

	public static final Season[] seasons = new Season[70];
	public static final Season[] season_team = new Season[69];
	static {
		int base = 1946;
		for (int i = 0; i < 69; i++) {
			int start = base + i;
			int finish = start + 1;
			season_team[68 - i] = new Season(String.valueOf(start) + "-" + String.valueOf(finish).substring(2));
			seasons[69 - i] = new Season(String.valueOf(start) + "-" + String.valueOf(finish).substring(2));
		}
		seasons[0] = new Season("Career");
	}
}
