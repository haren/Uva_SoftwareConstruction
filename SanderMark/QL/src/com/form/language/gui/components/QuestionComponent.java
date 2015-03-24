package com.form.language.gui.components;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.form.language.ast.expression.Expression;
import com.form.language.ast.statement.Question;
import com.form.language.gui.widget.CheckBox;
import com.form.language.gui.widget.IntegerTextField;
import com.form.language.gui.widget.Label;
import com.form.language.gui.widget.TextField;
import com.form.language.gui.widget.Widget;
import com.form.language.memory.Context;
import com.form.language.memory.IdCollection;

public class QuestionComponent {

    private Question question;
    private Context rm;
    private JPanel panel;

    public QuestionComponent(Question question, Context rm, Expression ifCondition) {
	this.question = question;
	this.rm = rm;
	this.panel = new JPanel();
	
	this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.X_AXIS));
	Label label = new Label(question.getText());
	this.panel.add(label.getLabel());

	if (ifCondition != null) {
	    this.panel.setVisible(false);
	    rm.addDependantQuestion(ifCondition, this);

	    IdCollection idCollection = new IdCollection();
	    ifCondition.collectIds(idCollection);
	    rm.addReference(idCollection, ifCondition);
	}
	createQuestionType();
    }

    // TODO: Type checker implementation to be added
    // TODO: casten to specifc widget needed?
    // TODO: kent beck Case Statements, chapter 3 code smells
    private void createQuestionType() {
	if (question.getType(rm).isBoolType()) {
		Widget checkbox = new CheckBox(question, this, rm);
	    JCheckBox cb = ((CheckBox) checkbox).getCheckBox();
		cb.setName(question.getId());
	    this.panel.add(cb);
	} else if (question.getType(rm).isStringType()) {
		Widget textfield = new TextField(question, this, rm);
	    JTextField tx = ((TextField) textfield).getTextField();
	    tx.setName(question.getId());
	    this.panel.add(tx);
	} else {
		Widget textfield = new IntegerTextField(question, this, rm);
	    JTextField tx = ((IntegerTextField) textfield).getTextField();
	    tx.setName(question.getId());
	    this.panel.add(tx);
	}
    }

    public Question getQuestion() {
    	return question;
    }
    
	public JPanel getPanel()
	{
		return this.panel;
	}

    public void checkVisibility(boolean visible) {
    	this.panel.setVisible(visible);
    }
}