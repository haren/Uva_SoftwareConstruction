package org.uva.student.calinwouter.qlqls.ql.interpreter.impl.headless;

import org.uva.student.calinwouter.qlqls.generated.node.*;
import org.uva.student.calinwouter.qlqls.ql.interpreter.ExpInterpreter;
import org.uva.student.calinwouter.qlqls.ql.interpreter.FormInterpreter;
import org.uva.student.calinwouter.qlqls.ql.interpreter.StmtInterpreter;
import org.uva.student.calinwouter.qlqls.ql.interpreter.TypeInterpreter;
import org.uva.student.calinwouter.qlqls.ql.model.QuestionField;

public class HeadlessStmtInterpreter extends StmtInterpreter {

    @Override
    public void caseAQuestionStmt(final AQuestionStmt node) {
        TypeInterpreter typeInterpreter = new TypeInterpreter();
        node.getType().apply(typeInterpreter);
        if (formInterpreter.getField(node.getIdent().getText()) == null) {
            formInterpreter.setField(node.getIdent().getText(),
                    typeInterpreter.getValue().getDefaultValue());
        }
        ((HeadlessFormInterpreter) formInterpreter).addFormField(new QuestionField(node.getStr().getText(),
                node.getIdent().getText(), typeInterpreter.getValue(), formInterpreter));
    }

    @Override
    public void caseAValueStmt(final AValueStmt node) {
        ExpInterpreter expInterpreter = new ExpInterpreter(formInterpreter);
        node.getExp().apply(expInterpreter);
        formInterpreter.setField(node.getIdent().getText(),
                expInterpreter.getValue());
    }

    protected StmtInterpreter createStmtInterpreter() {
        return new HeadlessStmtInterpreter(formInterpreter);
    }

    @Override
    public void caseAIfelseStmt(AIfelseStmt node) {
        ExpInterpreter expInterpreter = new ExpInterpreter(formInterpreter);
        node.getExp().apply(expInterpreter);
        if (expInterpreter.getValue().getValue() == Boolean.TRUE) {
            executeStmtList(node.getThenStmtList());
        } else {
            executeStmtList(node.getElseStmtList());
        }
    }

    @Override
    public void caseAIfStmt(AIfStmt node) {
        ExpInterpreter expInterpreter = new ExpInterpreter(formInterpreter);
        node.getExp().apply(expInterpreter);
        if (expInterpreter.getValue().getValue() == Boolean.TRUE) {
            executeStmtList(node.getThenStmtList());
        }
        executeStmtList(node.getThenStmtList());
    }

    public HeadlessStmtInterpreter(FormInterpreter formInterpreter) {
        super(formInterpreter);
    }

}