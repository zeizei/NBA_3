package common.datastructure;

import java.util.HashSet;

import common.statics.Field;
import common.statics.MathOperation;

public class CombineSelectionCell {
	private Field field;
	private MathOperation operation;
	private double number;

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}

	public MathOperation getOperation() {
		return operation;
	}

	public void setOperation(MathOperation operation) {
		this.operation = operation;
	}

	public String getSqlStr() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(field.toString()).append(" ").append(operation.toString()).append(" ").append(number);
		return buffer.toString();
	}

	public String getAvgStr() {
		HashSet<String> changed = new HashSet<String>();
		String[] changedField = { "minute", "totalHit", "totalShot", "threeHit", "threeShot", "twoShot", "twoHit", "freeHit", "freeShot", "offRebound", "defRebound", "totRebound", "assist", "steal",
				"block", "fault", "foul", "point" };
		for (int i = 0; i < changedField.length; i++) {
			changed.add(changedField[i]);
		}
		StringBuffer buffer = new StringBuffer();
		if (changed.contains(field.toString())) {
			buffer.append(field.toString()).append(" /numOfGame ").append(operation.toString()).append(" ").append(number);
			return buffer.toString();
		}
		else {
			buffer.append(field.toString()).append(" ").append(operation.toString()).append(" ").append(number);
			return buffer.toString();
		}
	}
}
