package edu.parser.QLS.nodes;

import edu.parser.AbstractNode;
import edu.parser.QLS.QLSVisitor;
import edu.parser.QLS.nodes.statement.Statement;

import java.util.List;

/**
 * Created by Steven Kok on 28/02/2015.
 */
public class Section implements AbstractNode<QLSVisitor> {
    private final String title;
    private final List<Statement> statements;

    public Section(String title, List<Statement> statements) {
        this.title = title;
        this.statements = statements;
    }

    public String getTitle() {
        return title;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public AbstractNode accept(QLSVisitor QLSVisitor) {
        return QLSVisitor.visit(this);
    }
}