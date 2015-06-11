package common.statics;

public class Position {
	private String position;

	private Position(String position) {
		this.position = position;
	}

	public String toString() {
		return this.position;
	}

	public static final Position All = new Position("all_position");
	public static final Position C = new Position("C");
	public static final Position F = new Position("F");
	public static final Position G = new Position("G");
	public static final Position PG = new Position("PG");// 控球后卫
	public static final Position SG = new Position("SG");// 得分后卫
	public static final Position PF = new Position("PF");// 大前锋
	public static final Position SF = new Position("SF");// 小前锋

	public static final Position[] positions = { All, C, F, G, PG, SG, PF, SF };
}
