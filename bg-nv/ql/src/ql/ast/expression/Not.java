package ql.ast.expression;

import ql.ast.type.Type;

/**
 * Created by bore on 17/02/15.
 */
public class Not extends UnaryExpr
{
    public Not(Expr operand, int lineNumber)
    {
        super(operand, lineNumber);
    }

    @Override
    public boolean isTypeCompatibleWithExpr(Type t)
    {
        return t.isBool();
    }

    @Override
    public <T> T accept(ExprVisitor<T> visitor)
    {
        return visitor.visit(this);
    }
}
