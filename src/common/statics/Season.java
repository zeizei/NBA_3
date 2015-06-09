package common.statics;

public class Season {
	private String season;
	private String startYear;
	private String finishYear;

	private Season(String season) {
		this.season = season;
		this.startYear = season.substring(0, 4);
		this.finishYear = String.valueOf(Integer.parseInt(startYear) + 1);

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

	public static Season[] season_array = new Season[69];
	static {
		int base = 1946;
		for (int i = 0; i < 69; i++) {
			int start = base + i;
			int finish = start + 1;
			season_array[68 - i] = new Season(String.valueOf(start) + "-" + String.valueOf(finish).substring(2));
		}
	}
}
