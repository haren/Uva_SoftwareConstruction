package org.fugazi.ql.gui.ui_elements;

import org.fugazi.ql.ast.statement.Question;
import org.fugazi.ql.evaluator.expression_value.ExpressionValue;
import org.fugazi.ql.gui.mediator.Colleague;
import org.fugazi.ql.gui.mediator.IMediator;
import org.fugazi.ql.gui.widgets.IWidget;

public abstract class UIQuestion extends Colleague {

    protected IWidget widget;
    protected final Question question;

    UIQuestion(IMediator _med, Question _question, IWidget _widget) {
        super(_med);
        this.question = _question;
        this.widget = _widget;
    }

    public abstract ExpressionValue getState();

    @Override
    public String getId() {
        return question.getIdName();
    }

    public IWidget getWidget() {
        return this.widget;
    }
}
