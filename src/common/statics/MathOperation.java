package common.statics;

public class MathOperation {
	private String operation;

	private MathOperation(String operation) {
		this.operation = operation;
	}

	public String toString() {
		return this.operation;
	}

	public static final MathOperation less = new MathOperation("<");
	public static final MathOperation more = new MathOperation(">");
	public static final MathOperation equal = new MathOperation("=");
	public static final MathOperation less_equal = new MathOperation("<=");
	public static final MathOperation more_equal = new MathOperation(">=");
	//
	public static final MathOperation[] operatons = { more_equal, less_equal, less, more, equal };
}
