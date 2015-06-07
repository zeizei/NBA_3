package common.statics;

public class AgeRange {
	private int low;
	private int high;

	public AgeRange(int low, int high) {
		this.low = low;
		this.high = high;
	}

	public String toString() {
		return String.valueOf(low) + "-" + String.valueOf(high);
	}

	public static final AgeRange less_equal_22 = new AgeRange(0, 22);
	public static final AgeRange more_22_less_equal_25 = new AgeRange(23, 25);
	public static final AgeRange more_25_less_equal_30 = new AgeRange(26, 30);
	public static final AgeRange more_30_less_equal_35 = new AgeRange(31, 35);
	public static final AgeRange more_35 = new AgeRange(36, 60);
	public static final AgeRange All = new AgeRange(0, 60);
}
