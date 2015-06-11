package common.statics;

public class DataKind {
	private int isTotal;

	private DataKind(int isTotal) {
		this.isTotal = isTotal;
	}

	public String toString() {
		if (this.isTotal == 1) {
			return "total";
		}
		else {
			return "average";
		}
	}

	public static final DataKind average = new DataKind(0);
	public static final DataKind total = new DataKind(1);
	//
	public static final DataKind[] dataKinds = { average, total };
}
