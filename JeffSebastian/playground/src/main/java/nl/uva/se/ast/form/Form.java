package nl.uva.se.ast.form;

import java.util.List;

import nl.uva.se.ast.Node;
import nl.uva.se.ast.statement.Statement;
import nl.uva.se.visitor.FormVisitor;
import nl.uva.se.visitor.StatementVisitor;

public class Form extends Node {

	private final String id;
	private final List<Statement> statements;
	
	public Form(String id, int lineNumber, int offset, List<Statement> statements) {
		super(lineNumber, offset);
		this.id = id;
		this.statements = statements;
	}

	public String getId() {
		return id;
	}
	
	public List<Statement> getStatements() {
		return statements;
	}

	public void accept(FormVisitor visitor) {
		visitor.visit(this);
	}
	
	public void visitChildren(StatementVisitor visitor) {
		for (Statement statement : statements) {
			statement.accept(visitor);
		}
	}

}
