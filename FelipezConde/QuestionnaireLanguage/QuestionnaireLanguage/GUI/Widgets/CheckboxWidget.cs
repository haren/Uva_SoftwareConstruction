﻿using QuestionnaireLanguage.GUI.CustomUIElements.CustomControls;
using System.Windows;

namespace QuestionnaireLanguage.GUI.FormObject
{
    public class CheckboxWidget : Widget
    {
        public CheckboxWidget(string id)
        {
            Id = id;
        }

        public override UIElement CreateUIControl(dynamic value)
        {
            return new CustomCheckBox() { Name = Id, IsChecked = value, IsEnabled = !IsReadOnly };
        }
    }
}
