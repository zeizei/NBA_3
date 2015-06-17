package common.statics;

public class GameKind {

	private int isPlayOff;

	private GameKind(int isPlayOff) {
		this.isPlayOff = isPlayOff;
	}

	public String toString() {
		if (this.isPlayOff == 1) {
			return "playOff";
		}
		else {
			return "regular";
		}
	}

	public static final GameKind regular_game = new GameKind(0);
	public static final GameKind playOff_game = new GameKind(1);
	//
	public static final GameKind[] gameKinds = { regular_game, playOff_game };
	public static final String[] gameKindStr = { "常规赛", "季后赛" };
}
