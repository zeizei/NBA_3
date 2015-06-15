package common.statics;

public class AgeRange {
	private String sql;

	public AgeRange(String str) {
		this.sql = str;
	}

	public String toString() {
		return this.sql;
	}

	public static final AgeRange less_equal_22 = new AgeRange("age <= 22");
	public static final AgeRange more_equal_23_less_equal_25 = new AgeRange("age between 23 and 25");
	public static final AgeRange more_equal_26_less_equal_30 = new AgeRange("age between 26 and 30");
	public static final AgeRange more_equal_31_less_equal_35 = new AgeRange("age between 31 and 35");
	public static final AgeRange more_equal_36 = new AgeRange("age >= 36");
	public static final AgeRange All = new AgeRange("all_age");

	//
	public static final AgeRange[] ageRanges = { All, less_equal_22, more_equal_23_less_equal_25, more_equal_26_less_equal_30, more_equal_31_less_equal_35, more_equal_36 };
}
