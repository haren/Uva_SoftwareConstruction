﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using UvA.SoftCon.Questionnaire.QL;
using UvA.SoftCon.Questionnaire.QL.AST.Model;
using UvA.SoftCon.Questionnaire.QL.AST.Model.Statements;
using UvA.SoftCon.Questionnaire.WinForms.Controls;

namespace UvA.SoftCon.Questionnaire.WinForms.UIBuilding
{
    /// <summary>
    /// Creates a default UI control tree based on a list of questions.
    /// </summary>
    internal class DefaultUIBuilder : QLVisitor<object>
    {
        private ICollection<QuestionWidget> questionControls = new List<QuestionWidget>();

        public QuestionFormControl BuildUi(QuestionForm form, OutputWindow outputWindow)
        {
            Visit(form);

            return new QuestionFormControl(form, questionControls, outputWindow);
        }

        public override object Visit(BooleanQuestion question)
        {
           questionControls.Add(new CheckBoxWidget(question));
           return null;
        }

        public override object Visit(DateQuestion question)
        {
            questionControls.Add(new CalendarWidget(question));
            return null;
        }

        public override object Visit(IntegerQuestion question)
        {
            questionControls.Add(new SpinBoxWidget(question));
            return null;
        }

        public override object Visit(StringQuestion question)
        {
            questionControls.Add(new TextBoxWidget(question));
            return null;
        }
    }
}