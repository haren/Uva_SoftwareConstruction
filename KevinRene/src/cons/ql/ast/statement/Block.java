package cons.ql.ast.statement;

import java.util.ArrayList;

import cons.ql.ast.Statement;
import cons.ql.ast.visitor.Visitor;

public class Block extends Statement {
	private ArrayList<Statement> statements = new ArrayList<Statement>();
	/**
	 * Constructor for the statement case
	 * @param statement
	 */
	public Block(Statement statement) {
		this.statements.add(statement);
	}
	
	public Block(Statement statement, Block statements) {
		this.statements.add(statement);
		this.statements.addAll(statements.statements());
	}
	
	public ArrayList<Statement> statements() {
		return this.statements;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Block(");
		
		for(Statement statement : statements) {
			sb.append(statement.toString() + ", ");
		}
		
		sb.setLength(sb.length() - 2);
		sb.append(")");
		
		return sb.toString();
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);		
	}
}
