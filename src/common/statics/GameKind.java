package common.statics;

public class GameKind {

	private int isPlayOff;

	private GameKind(int isPlayOff) {
		this.isPlayOff = isPlayOff;
	}

	public String toString() {
		if (this.isPlayOff == 1) {
			return "playOff";
		} else {
			return "regular";
		}
	}

	public static GameKind regular_game = new GameKind(0);
	public static GameKind playOff_game = new GameKind(1);
}
