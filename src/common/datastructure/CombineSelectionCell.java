package common.datastructure;

import common.statics.MathOperation;

public class CombineSelectionCell {
	private String field;
	private MathOperation operation;
	private double number;

	public String getField() {
		return field;
	}

	public void setField(String field) {
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
}
