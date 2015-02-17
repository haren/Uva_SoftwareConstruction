package nl.uva.se.ast.expression.MathematicalOperators;

import nl.uva.se.ast.expression.Binary;
import nl.uva.se.ast.expression.Expression;
import nl.uva.se.visitor.Visitor;

public class Power extends Binary{

	public Power(int lineNumber, int offset, Expression left, Expression right) {
		super(lineNumber, offset, left, right);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		
	}

}
