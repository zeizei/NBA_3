package beans;

public class generalTeam extends Bean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String teamName;
	private String league;
	private String startSeason;
	private String finishSeason;

	public String getImgName() {
		return this.teamName + " " + this.startSeason + " " + this.finishSeason;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getStartSeason() {
		return startSeason;
	}

	public void setStartSeason(String startSeason) {
		this.startSeason = startSeason;
	}

	public String getFinishSeason() {
		return finishSeason;
	}

	public void setFinishSeason(String finishSeason) {
		this.finishSeason = finishSeason;
	}
}
