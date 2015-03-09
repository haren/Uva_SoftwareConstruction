﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UvA.SoftCon.Questionnaire.QL.AST.Model.Expressions;
using UvA.SoftCon.Questionnaire.Utilities.AST;

namespace UvA.SoftCon.Questionnaire.QL.AST.Model.Statements
{
    public class Question : QLNode, IStatement
    {
        public DataType DataType
        {
            get;
            private set;
        }

        public Identifier Id
        {
            get;
            private set;
        }

        public string Label
        {
            get;
            private set;
        }

        public IExpression Expression
        {
            get;
            private set;
        }

        public bool IsComputed
        {
            get
            {
                return Expression != null;
            }
        }

        public Question(DataType dataType, Identifier id, string label, TextPosition position)
            : base(position)
        {
            DataType = dataType;
            Id = id;
            Label = label;
            Expression = null;
        }

        public Question(DataType dataType, Identifier id, string label, IExpression expression, TextPosition position)
            : this(dataType, id, label, position)
        {
            Expression = expression;
        }

        public override void Accept(IQLVisitor visitor)
        {
            visitor.Visit(this);
        }

        public override T Accept<T>(IQLVisitor<T> visitor)
        {
            return visitor.Visit(this);
        }

        public void AppendQuestions(ICollection<Question> questions)
        {
            questions.Add(this);
        }
    }
}
