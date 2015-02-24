package org.fugazi.ast.expression.comparison;

import org.fugazi.ast.expression.Binary;
import org.fugazi.ast.expression.Expression;

public abstract class Comparison extends Binary {

    public Comparison(Expression _leftExpr, Expression _rightExpr) {
        super(_leftExpr, _rightExpr);
    }

    public Comparison(Expression _leftExpr, Expression _rightExpr, int _lineNum) {
        super(_leftExpr, _rightExpr, _lineNum);
    }

    public Expression getLeft() {
        return this.left;
    }

    public Expression getRight() {
        return this.right;
    }
}