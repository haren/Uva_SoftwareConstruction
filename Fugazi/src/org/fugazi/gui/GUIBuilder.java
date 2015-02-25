package org.fugazi.gui;

import org.fugazi.ast.form.Form;
import org.fugazi.ast.statement.*;
import org.fugazi.evaluator.Evaluator;
import org.fugazi.gui.ui_elements.UIComputedQuestion;
import org.fugazi.gui.ui_elements.UIForm;
import org.fugazi.gui.ui_elements.UIQuestion;
import org.fugazi.gui.visitor.UITypeVisitor;

public class GUIBuilder implements IStatementVisitor<UIQuestion> {

    private final Form astForm;
    private final UIForm uiForm;
    private final Evaluator evaluator;
    private final UIMediator mediator;

    public GUIBuilder(Form _astForm, Evaluator _evaluator) {
        this.astForm = _astForm;
        this.evaluator = _evaluator;
        this.uiForm = new UIForm(this.astForm.getName());
        this.mediator = new UIMediator();
    }
    
    public void renderGUI() {
        setUpUIElements();
        this.uiForm.showForm();
    }

    private void setUpUIElements() {
        for (Statement statement : astForm.getBody()) {
            statement.accept(this);
        }
    }

    private void addQuestionToTheForm(UIQuestion _quest) {
        this.mediator.addColleague(_quest);
        this.uiForm.addQuestion(_quest);
    }

    /**
     * Statement Visitor
     */
    public UIQuestion visitQuestion(Question _question) {

        UITypeVisitor typeVisitor = new UITypeVisitor(mediator, _question);
        UIQuestion uiQuestion = _question.getType().accept(typeVisitor);

        addQuestionToTheForm(uiQuestion);

        return uiQuestion;
    }

    public UIQuestion visitIfStatement(IfStatement _ifStatement) {
        return null;
    }

    public UIQuestion visitComputedQuestion(ComputedQuestion _assignQuest) {
        UIComputedQuestion uiComputedQuestion = new UIComputedQuestion(mediator, _assignQuest);
        addQuestionToTheForm(uiComputedQuestion);
        return uiComputedQuestion;
    }
}