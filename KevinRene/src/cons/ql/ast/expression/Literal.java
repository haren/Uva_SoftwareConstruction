package cons.ql.ast.expression;

import java.util.Arrays;

import cons.ql.ast.Expression;

public abstract class Literal<T> extends Expression {
	private final T value;
	
	public Literal(T value) {
		super(Arrays.asList());
		this.value = value;
	}
	
	public T getValue() {
		return this.value;
	}
	
	public String toString() {
		return value.toString();
	}
}
