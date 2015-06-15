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
			if (all_seasons[i].getStartDate().compareTo(date) < 0 && all_seasons[i].getFinishDate().compareTo(date) > 0) {
				return all_seasons[i];
			}
		}
		return null;
	}

	public static Season getSeason(String str) {
		for (int i = 0; i < 69; i++) {
			if ((all_seasons[i].toString()).equals(str)) {
				return all_seasons[i];
			}
		}
		return null;
	}

	public static final Season[] seasons_with_Career = new Season[70];
	public static final Season[] all_seasons = new Season[69];
	public static final Season this_season = new Season("2014-15");
	static {
		int base = 1946;
		for (int i = 0; i < 69; i++) {
			int start = base + i;
			int finish = start + 1;
			all_seasons[68 - i] = new Season(String.valueOf(start) + "-" + String.valueOf(finish).substring(2));
			seasons_with_Career[69 - i] = new Season(String.valueOf(start) + "-" + String.valueOf(finish).substring(2));
		}
		seasons_with_Career[0] = new Season("Career");
	}
}
