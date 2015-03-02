package edu.parser.QL.nodes.expression;

import edu.parser.QL.QLVisitor;
import edu.parser.AbstractNode;

/**
 * Created by Steven Kok on 21/02/2015.
 */
public class Not extends UnaryExpression {

    public Not(Expression operand) {
        super(operand);
    }

    @Override
    public AbstractNode accept(QLVisitor QLVisitor) {
        return QLVisitor.visit(this);
    }

    @Override
    public boolean hasBooleanOperands() {
        return true;
    }
}