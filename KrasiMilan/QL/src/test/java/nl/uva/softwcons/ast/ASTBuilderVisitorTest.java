package nl.uva.softwcons.ast;

import static org.assertj.core.api.Assertions.assertThat;
import nl.uva.softwcons.Questionnaire;
import nl.uva.softwcons.ast.form.Form;
import nl.uva.softwcons.ast.statement.ComputedQuestion;
import nl.uva.softwcons.ast.statement.Question;
import nl.uva.softwcons.ast.type.Type;

import org.junit.Test;

public class ASTBuilderVisitorTest {

    @Test
    public void testSingleQuestionInForm() {
        String questionText = "question: \"Label\" boolean";
        Form form = Questionnaire.build(buildForm("form1", questionText));
        Question question = (Question) form.getStatements().get(0);

        assertThat(form.getStatements().get(0)).isExactlyInstanceOf(Question.class);
        assertThat(question.getId()).isEqualTo("question");
        assertThat(question.getLabel()).isEqualTo("Label");
    }

    @Test
    public void testMultipleQuestionsInForm() {
        String question1Text = "question1: \"Label 1\" boolean";
        String question2Text = "question2: \"Label 2\" boolean";
        Form form = Questionnaire.build(buildForm("form1", question1Text, question2Text));
        Question question1 = (Question) form.getStatements().get(0);
        Question question2 = (Question) form.getStatements().get(1);

        assertThat(form.getStatements()).hasSize(2);
        assertThat(form.getStatements()).hasOnlyElementsOfType(Question.class);
        assertThat(question1.getId()).isEqualTo("question1");
        assertThat(question1.getLabel()).isEqualTo("Label 1");
        assertThat(question2.getId()).isEqualTo("question2");
        assertThat(question2.getLabel()).isEqualTo("Label 2");
    }

    @Test
    public void testAllQuestionTypes() {
        String booleanQuestion = "question: \"Label me\" boolean";
        String stringQuestion = "question: \"Label me\" string";
        String integerQuestion = "question: \"Label me\" integer";
        String dateQuestion = "question: \"Label me\" date";
        String decimalQuestion = "question: \"Label me\" decimal";
        String moneyQuestion = "question: \"Label me\" money";
        Form form = Questionnaire.build(buildForm("form1", booleanQuestion, stringQuestion, integerQuestion,
                dateQuestion, decimalQuestion, moneyQuestion));

        assertThat(form.getStatements()).extracting("type").containsExactly((Object[]) Type.values());
    }

    @Test
    public void testSingleComputedQuestionInForm() {
        String questionText = "question: \"Label\" boolean (6 * 5)";
        Form form = Questionnaire.build(buildForm("form1", questionText));
        ComputedQuestion question = (ComputedQuestion) form.getStatements().get(0);

        assertThat(form.getStatements().get(0)).isExactlyInstanceOf(ComputedQuestion.class);
        assertThat(question.getId()).isEqualTo("question");
        assertThat(question.getLabel()).isEqualTo("Label");
        // uncomment when parsing expressions works
        // assertThat(question.getValue()).isInstanceOf(Expression.class);
    }

    @Test
    public void testMultipleComputedQuestionsInForm() {
        String question1Text = "question1: \"Label 1\" boolean (true)";
        String question2Text = "question2: \"Label 2\" boolean (6+2>3)";
        Form form = Questionnaire.build(buildForm("form1", question1Text, question2Text));
        ComputedQuestion question1 = (ComputedQuestion) form.getStatements().get(0);
        ComputedQuestion question2 = (ComputedQuestion) form.getStatements().get(1);

        assertThat(form.getStatements()).hasSize(2);
        assertThat(form.getStatements()).hasOnlyElementsOfType(ComputedQuestion.class);
        assertThat(question1.getId()).isEqualTo("question1");
        assertThat(question1.getLabel()).isEqualTo("Label 1");
        // uncomment when parsing expressions works
        // assertThat(question1.getValue()).isInstanceOf(Expression.class); TODO
        assertThat(question2.getId()).isEqualTo("question2");
        assertThat(question2.getLabel()).isEqualTo("Label 2");
        // uncomment when parsing expressions works
        // assertThat(question2.getValue()).isInstanceOf(Expression.class);
    }

    @Test
    public void testAllComputedQuestionTypes() {
        String booleanQuestion = "question: \"Label me\" boolean (1+2)";
        String stringQuestion = "question: \"Label me\" string (true)";
        String integerQuestion = "question: \"Label me\" integer(1+2)";
        String dateQuestion = "question: \"Label me\" date(true)";
        String decimalQuestion = "question: \"Label me\" decimal(1+2)";
        String moneyQuestion = "question: \"Label me\" money(true)";
        Form form = Questionnaire.build(buildForm("form1", booleanQuestion, stringQuestion, integerQuestion,
                dateQuestion, decimalQuestion, moneyQuestion));

        assertThat(form.getStatements()).extracting("type").containsExactly((Object[]) Type.values());
    }

    private String buildForm(final String formName, final String... statements) {
        return String.format("form %s { %s }", formName, String.join(" ", statements));
    }

}
