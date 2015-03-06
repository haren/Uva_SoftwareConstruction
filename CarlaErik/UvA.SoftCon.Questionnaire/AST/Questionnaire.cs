﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UvA.SoftCon.Questionnaire.AST.Statements;

namespace UvA.SoftCon.Questionnaire.AST
{
    /// <summary>
    /// Represents a questionnaire.
    /// </summary>
    public sealed class Questionnaire
    {
        public IReadOnlyCollection<IStatement> Statements
        {
            get;
            private set;
        }

        public Questionnaire(IReadOnlyCollection<IStatement> statements)
        {
            Statements = statements;
        }

        public override string ToString()
        {
            var result = new StringBuilder();

            foreach (var statement in Statements)
            {
                result.AppendLine(statement.ToString());
            }
            return result.ToString();
        }
    }
}