package nl.uva.se.ast.statement;

import java.util.List;

import nl.uva.se.ast.expression.Expression;
import nl.uva.se.visitor.Visitor;

public class Condition extends Statement {

	private final Expression expression;
	private final List<Statement> statements;

	public Condition(int lineNumber, int offset, Expression expression,
			List<Statement> statements) {
		super(lineNumber, offset);
		this.expression = expression;
		this.statements = statements;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		expression.accept(visitor);
		for (Statement statement : statements) {
			statement.accept(visitor);
		}
	}

	public List<Statement> getStatements() {
		return statements;
	}
	
}