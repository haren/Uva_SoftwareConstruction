﻿
namespace AST.Nodes.Expressions.Binaries
{
    public class Subtract : Binary
    {
        public Subtract(Expression left, Expression right, PositionInText position)
            : base(left, right, position)
        { }

        public override string ToString()
        {
            return "-";
        }

        public override T Accept<T>(VisitorInterfaces.IExpressionVisitor<T> visitor)
        {
            return visitor.Visit(this);
        }
    }
}
