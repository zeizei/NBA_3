package statistics.analysisbeans;

public class PlayerShootOpponent extends Bean {

	private static final long serialVersionUID = 1L;
	// 主键
	protected String playerId;// 该球员编号
	protected String season;// 赛季
	protected int isPlayOff;// 是否为季后赛
	protected String opponent;// 对手
	// 球员该赛季 常规赛或 季后赛信息
	private String playerName;
	private double fg;// 命中数
	private double fga;// 出手数
	private double fgr;// 命中率
	private double efg;// 有效命中率
	private double astd;// 受助功数
	private double astdr;// 受助攻率

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public int getIsPlayOff() {
		return isPlayOff;
	}

	public void setIsPlayOff(int isPlayOff) {
		this.isPlayOff = isPlayOff;
	}

	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	public String getPlayerName() {
		if (this.playerName.contains("'")) {
			this.playerName = this.playerName.replace('\'', '-');
		}
		return playerName;
	}

	public void setPlayerName(String playerName) {
		if (playerName.contains("'")) {
			playerName = playerName.replace('\'', '-');
		}
		this.playerName = playerName;
	}

	public double getFg() {
		return fg;
	}

	public void setFg(double fg) {
		this.fg = fg;
	}

	public double getFga() {
		return fga;
	}

	public void setFga(double fga) {
		this.fga = fga;
	}

	public double getFgr() {
		return fgr;
	}

	public void setFgr(double fgr) {
		this.fgr = fgr;
	}

	public double getEfg() {
		return efg;
	}

	public void setEfg(double efg) {
		this.efg = efg;
	}

	public double getAstd() {
		return astd;
	}

	public void setAstd(double astd) {
		this.astd = astd;
	}

	public double getAstdr() {
		return astdr;
	}

	public void setAstdr(double astdr) {
		this.astdr = astdr;
	}
}
