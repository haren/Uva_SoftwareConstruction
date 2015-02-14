package org.uva.student.calinwouter.ql.interpreter.typechecker;

import org.uva.student.calinwouter.ql.generated.node.AForm;
import org.uva.student.calinwouter.ql.interpreter.components.FormInterpreter;
import org.uva.student.calinwouter.ql.interpreter.components.StmtInterpreter;
import org.uva.student.calinwouter.ql.interpreter.exceptions.InterpretationException;

import java.io.PrintStream;

/**
 * This FormTypeChecker detects:
 *
 * - Reference to undefined questions
 * - Duplicate question declarations (NOT with different types)
 * - Conditions that are not of the type boolean
 * - Operands of invalid type to operators
 * - Duplicate labels (warning)
 *
 * Does not detect cyclic dependencies between questions, because it is not possible to create cyclic dependencies.
 */
public class FormTypeChecker extends FormInterpreter {
    private InterpretationException fatalException;

    @Override
    protected StmtInterpreter createStmtInterpreter() {
        return new StmtTypeChecker(this);
    }

    public InterpretationException getFatalException() {
        return fatalException;
    }

    @Override
    public void caseAForm(AForm node) {
        super.caseAForm(node);    //To change body of overridden methods use File | Settings | File Templates.
        try {
            super.reInterpret();
        } catch(InterpretationException e) {
            fatalException = e;
        }
    }

    public void output(PrintStream err, PrintStream out) {
        try {
            for (InterpretationException e : super.getWarnings()) {
                err.println("Warning: " + e.getMessage());
            }
        } catch(InterpretationException e) {
            err.println("An error has occured: " + e.getMessage());
            err.flush();
        }
    }
}