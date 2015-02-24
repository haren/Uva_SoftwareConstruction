package ast.value;

import ast.Value;

public class Str extends Value{

	private final String value;
	
	public Str(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

}
