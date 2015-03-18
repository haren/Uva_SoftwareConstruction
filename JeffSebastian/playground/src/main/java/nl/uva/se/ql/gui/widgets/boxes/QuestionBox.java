package nl.uva.se.ql.gui.widgets.boxes;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nl.uva.se.ql.ast.statement.Question;
import nl.uva.se.ql.gui.builders.QuestionBuilder;
import nl.uva.se.ql.gui.listeners.IMediator;
import nl.uva.se.ql.gui.widgets.questions.BaseQuestion;

public class QuestionBox extends VBox {
	
	private final Question question;
	private final IMediator mediator;

	public QuestionBox(Question question, IMediator mediator) {
		this.question = question;
		this.mediator = mediator;
		addQuestion(question);
	}

	public void addQuestion(Question question) {		
		//Add label to the QuestionBox
		Label title = new Label(question.getLabel());
		this.getChildren().add(title);
		
		//Add the widget to the QuestionBox
		BaseQuestion baseQuestion = question.getType().accept(new QuestionBuilder(question, mediator));
		this.getChildren().add(baseQuestion.getWidget());
	}
	
	public Question getQuestion(){
		return this.question;
	}	
}