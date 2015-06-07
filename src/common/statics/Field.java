package common.statics;

public class Field {
	private String field;

	private Field(String field) {
		this.field = field;
	}

	public String toString() {
		return this.field;
	}

	public static final Field point = new Field("point");
	public static final Field offRebound = new Field("offRebound");
	public static final Field defRebound = new Field("defRebound");
}
