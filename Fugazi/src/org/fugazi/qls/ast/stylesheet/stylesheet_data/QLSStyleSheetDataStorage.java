package org.fugazi.qls.ast.stylesheet.stylesheet_data;


import org.fugazi.ql.ast.statement.Question;
import org.fugazi.qls.ast.stylesheet.StyleSheet;
import org.fugazi.qls.ast.stylesheet.stylesheet_data.visitor.QuestionsVisitor;

import java.util.List;

public class QLSStyleSheetDataStorage {
    private final StyleSheet sheet;
    private final QuestionsVisitor questionsVisitor;

    public QLSStyleSheetDataStorage(StyleSheet _sheet) {
        this.sheet = _sheet;

        this.questionsVisitor = new QuestionsVisitor(_sheet);
    }

    /**
     * =====================
     * Public exposed getters
     * =====================
     */

    public List<org.fugazi.qls.ast.question.Question> getQuestions() {
        return this.questionsVisitor.getQuestions();
    }
}