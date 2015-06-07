package common.datastructure;

import common.statics.Position;

public class PlayerHotInfo {
	private String field;
	private String playerId;
	private Position position;
	private String teamName;
	private double upGradeRate;
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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public double getUpGradeRate() {
		return upGradeRate;
	}

	public void setUpGradeRate(double upGradeRate) {
		this.upGradeRate = upGradeRate;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
