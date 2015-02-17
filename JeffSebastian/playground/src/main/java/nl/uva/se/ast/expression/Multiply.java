package nl.uva.se.ast.expression;

import nl.uva.se.visitor.Visitor;

public class Multiply extends Binary{

	public Multiply(int lineNumber, int offset, Expression left,
			Expression right) {
		super(lineNumber, offset, left, right);
	}

	@Override
	public void accept(Visitor visitor) {
	visitor.visit(this);
	}
	
}
