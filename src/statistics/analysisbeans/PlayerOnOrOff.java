package statistics.analysisbeans;

public class PlayerOnOrOff extends Bean {

	private static final long serialVersionUID = 1L;
	// 主键
	protected String playerId;// 该球员编号
	protected String season;// 赛季
	protected int isPlayOff;// 是否为季后赛
	protected String state;// 是否在场
	// 球员该赛季 常规赛或 季后赛信息
	private String playerName;// 球员名称
	private String teamName;// 球队名称
	private double minutes;// 在场时间或不在场时间
	private double efgr;// 有效命中率
	private double orbr;// 进攻篮板效率
	private double drbr;// 防守篮板效率
	private double trbr;// 总篮板效率
	private double astr;// 助攻效率
	private double stlr;// 抢断效率
	private double blkr;// 盖帽效率
	private double tovr;// 失误率
	private double ortg;// 预测每百回合得分数
	private double oppeFG;// 对手有效命中率
	private double oppOrbr;// 进攻篮板效率
	private double oppDrbr;// 防守篮板效率
	private double oppTrbr;// 总篮板效率
	private double oppAstr;// 助攻效率
	private double oppStlr;// 抢断效率
	private double oppBlkr;// 盖帽效率
	private double oppTovr;// 失误率
	private double oppOrtg;// 预测每百回合得分数

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public double getMinutes() {
		return minutes;
	}

	public void setMinutes(double minutes) {
		this.minutes = minutes;
	}

	public double getEfgr() {
		return efgr;
	}

	public void setEfgr(double efgr) {
		this.efgr = efgr;
	}

	public double getOrbr() {
		return orbr;
	}

	public void setOrbr(double orbr) {
		this.orbr = orbr;
	}

	public double getDrbr() {
		return drbr;
	}

	public void setDrbr(double drbr) {
		this.drbr = drbr;
	}

	public double getTrbr() {
		return trbr;
	}

	public void setTrbr(double trbr) {
		this.trbr = trbr;
	}

	public double getAstr() {
		return astr;
	}

	public void setAstr(double astr) {
		this.astr = astr;
	}

	public double getStlr() {
		return stlr;
	}

	public void setStlr(double stlr) {
		this.stlr = stlr;
	}

	public double getBlkr() {
		return blkr;
	}

	public void setBlkr(double blkr) {
		this.blkr = blkr;
	}

	public double getTovr() {
		return tovr;
	}

	public void setTovr(double tovr) {
		this.tovr = tovr;
	}

	public double getOrtg() {
		return ortg;
	}

	public void setOrtg(double ortg) {
		this.ortg = ortg;
	}

	public double getOppeFG() {
		return oppeFG;
	}

	public void setOppeFG(double oppeFG) {
		this.oppeFG = oppeFG;
	}

	public double getOppOrbr() {
		return oppOrbr;
	}

	public void setOppOrbr(double oppOrbr) {
		this.oppOrbr = oppOrbr;
	}

	public double getOppDrbr() {
		return oppDrbr;
	}

	public void setOppDrbr(double oppDrbr) {
		this.oppDrbr = oppDrbr;
	}

	public double getOppTrbr() {
		return oppTrbr;
	}

	public void setOppTrbr(double oppTrbr) {
		this.oppTrbr = oppTrbr;
	}

	public double getOppAstr() {
		return oppAstr;
	}

	public void setOppAstr(double oppAstr) {
		this.oppAstr = oppAstr;
	}

	public double getOppStlr() {
		return oppStlr;
	}

	public void setOppStlr(double oppStlr) {
		this.oppStlr = oppStlr;
	}

	public double getOppBlkr() {
		return oppBlkr;
	}

	public void setOppBlkr(double oppBlkr) {
		this.oppBlkr = oppBlkr;
	}

	public double getOppTovr() {
		return oppTovr;
	}

	public void setOppTovr(double oppTovr) {
		this.oppTovr = oppTovr;
	}

	public double getOppOrtg() {
		return oppOrtg;
	}

	public void setOppOrtg(double oppOrtg) {
		this.oppOrtg = oppOrtg;
	}
}
