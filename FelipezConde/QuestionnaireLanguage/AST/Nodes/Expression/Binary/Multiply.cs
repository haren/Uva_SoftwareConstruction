﻿using AST.Nodes.Interfaces;
using AST.Representation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AST.Nodes.Expression.Binary
{
    public class Multiply : ASTNode, IExpression, IBinary
    {
        private readonly IExpression left;
        private readonly IExpression right;
        private string parsedString;

        public Multiply(IExpression left, IExpression right, string parsedString, PositionInText position)
            : base(position)
        {
            this.left = left;
            this.right = right;
            this.parsedString = parsedString;
        }

         public IExpression Left()
        { return left; }

        public IExpression Right()
        { return right; }

        public override string GetParsedString()
        {
            return parsedString;
        }

        public override void Accept(Visitors.IVisitor visitor)
        {
            visitor.Visit(this);
        }

        public override T Accept<T>(Visitors.IVisitor<T> visitor)
        {
           return visitor.Visit(this);
        }

        public string MakeString()
        {
            return "*";
        }

        public IValue GetCompatibleType(Values.Int leftType, Values.Int rightType)
        {
            return new Values.Int(0);
        }

        public IValue GetCompatibleType(IValue leftType, IValue rightType)
        {
            return new Values.Undefined();
        }


    }
}
