package parser.nodes.question;

import parser.Visitor;
import parser.nodes.AbstractNode;
import parser.nodes.expression.Expression;
import parser.nodes.expression.Identifier;
import parser.nodes.statement.Statement;

import java.util.Optional;

/**
 * Created by Steven Kok on 21/02/2015.
 */
public class Question extends Statement {

    private final Identifier identifier;
    private final QuestionType questionType;
    private final Label label;
    private final Optional<Expression> expression;

    public Question(Identifier identifier, QuestionType questionType, Label label, Optional<Expression> expression) {
        this.identifier = identifier;
        this.questionType = questionType;
        this.label = label;
        this.expression = expression;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public Label getLabel() {
        return label;
    }

    public Optional<Expression> getExpression() {
        return expression;
    }

    @Override
    public AbstractNode accept(Visitor visitor) {
        return visitor.visit(this);
    }
}