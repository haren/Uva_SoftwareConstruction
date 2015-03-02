﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UvA.SoftCon.Questionnaire.QL.AST.Model.Statements;

namespace UvA.SoftCon.Questionnaire.Runtime.Evaluation.Types
{
    public class StringValue : Value<string>
    {
        public override DataType DataType
        {
            get
            {
                return DataType.String;
            }
        }

        public StringValue(string value)
            : base(value) { }

        public override Value IsEqualTo(Value value)
        {
            return value.IsEqualToString(this);
        }

        internal override Value IsEqualToString(StringValue value)
        {
            return new BooleanValue(value.Val == Val);
        }

        public override Value IsNotEqualTo(Value value)
        {
            return value.IsNotEqualToString(this);
        }

        internal override Value IsNotEqualToString(StringValue value)
        {
            return new BooleanValue(value.Val != Val);
        }

        public override Value Plus(Value value)
        {
            return value.PlusString(this);
        }

        internal override Value PlusString(StringValue value)
        {
            return new StringValue(value.Val + Val);
        }
    }
}