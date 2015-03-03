package nl.uva.se.ast.expression.literal;

import nl.uva.se.visitor.ExpressionVisitor;

public class BooleanLiteral extends AbstractLiteral<Boolean> {

	public BooleanLiteral(int lineNumber, int offset, Boolean value) {
		super(lineNumber, offset, value);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
