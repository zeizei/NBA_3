package common.statics;

public class Season {
	private String season;

	private Season(String season) {
		this.season = season;
	}

	public String toString() {
		return this.season;
	}

	public static Season[] season_array = new Season[69];
	static {
		int base = 1946;
		for (int i = 0; i < 69; i++) {
			int start = base + i;
			int finish = start + 1;
			season_array[i] = new Season(String.valueOf(start) + "-" + String.valueOf(finish).substring(2));
		}
	}
}
