package common.statics;

public class Position {
	private String position;

	private Position(String position) {
		this.position = position;
	}

	public String toString() {
		return this.position;
	}

	public static Position All = new Position("All");
	public static Position C = new Position("C");
	public static Position F = new Position("F");
	public static Position G = new Position("G");
	public static Position PG = new Position("PG");// 控球后卫
	public static Position SG = new Position("SG");// 得分后卫
	public static Position PF = new Position("PF");// 大前锋
	public static Position SF = new Position("SF");// 小前锋
}
