package org.fugazi.ast.expression.comparison;

import org.fugazi.ast.expression.Expression;
import org.fugazi.ast.expression.IExpressionVisitor;
import org.fugazi.ast.type.BoolType;

import java.util.ArrayList;
import java.util.List;

public class Less extends Comparison {

    private final List<Class> supportedTypes;

    public Less(Expression _left, Expression _right, int _lineNum) {
        super(_left, _right, _lineNum);

        Class boolTypeClass = BoolType.class;
        this.supportedTypes = new ArrayList<Class>();
        this.supportedTypes.add(boolTypeClass);
    }

    @Override
    public String toString() {
        return this.left.toString() + " < " + this.right.toString();
    }

    @Override
    public List<Class> getSupportedTypes() {
        return this.supportedTypes;
    }

    public <T> T accept(IExpressionVisitor<T> visitor) {
        return visitor.visitLesser(this);
    }
}