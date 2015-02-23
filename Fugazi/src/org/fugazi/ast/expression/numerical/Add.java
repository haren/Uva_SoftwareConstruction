package org.fugazi.ast.expression.numerical;

import org.fugazi.ast.expression.Expression;
import org.fugazi.ast.expression.IExpressionVisitor;
import org.fugazi.ast.type.IntType;

import java.util.ArrayList;
import java.util.List;

public class Add extends Numerical {

    private final List<Class> supportedTypes;

    public Add(Expression _left, Expression _right, int _lineNum) {
        super(_left, _right, _lineNum);

        Class intTypeClass = IntType.class;
        this.supportedTypes = new ArrayList<Class>();
        this.supportedTypes.add(intTypeClass);
    }

    @Override
    public String toString() {
        return this.left.toString() + " + " + this.right.toString();
    }

    @Override
    public List<Class> getSupportedTypes() {
        return this.supportedTypes;
    }

    public <T> T accept(IExpressionVisitor<T> visitor) {
        return visitor.visitAdd(this);
    }
}