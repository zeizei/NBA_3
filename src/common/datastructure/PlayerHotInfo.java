package common.datastructure;

public class PlayerHotInfo extends HotVo {
	private String field;
	private String playerId;
	private String playerName;
	private String position;
	private String teamName;
	private double upgradeRate;
	private double value;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public double getUpgradeRate() {
		return upgradeRate;
	}

	public void setUpgradeRate(double upgradeRate) {
		this.upgradeRate = upgradeRate;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
