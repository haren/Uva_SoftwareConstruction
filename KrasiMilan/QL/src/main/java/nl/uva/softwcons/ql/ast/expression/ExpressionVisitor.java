package nl.uva.softwcons.ql.ast.expression;

import nl.uva.softwcons.ql.ast.expression.binary.BinaryExpression;
import nl.uva.softwcons.ql.ast.expression.binary.arithmetic.Addition;
import nl.uva.softwcons.ql.ast.expression.binary.arithmetic.Division;
import nl.uva.softwcons.ql.ast.expression.binary.arithmetic.Multiplication;
import nl.uva.softwcons.ql.ast.expression.binary.arithmetic.Subtraction;
import nl.uva.softwcons.ql.ast.expression.binary.comparison.Equal;
import nl.uva.softwcons.ql.ast.expression.binary.comparison.GreaterOrEqual;
import nl.uva.softwcons.ql.ast.expression.binary.comparison.GreaterThan;
import nl.uva.softwcons.ql.ast.expression.binary.comparison.LowerOrEqual;
import nl.uva.softwcons.ql.ast.expression.binary.comparison.LowerThan;
import nl.uva.softwcons.ql.ast.expression.binary.comparison.NotEqual;
import nl.uva.softwcons.ql.ast.expression.binary.logical.And;
import nl.uva.softwcons.ql.ast.expression.binary.logical.Or;
import nl.uva.softwcons.ql.ast.expression.identifier.Identifier;
import nl.uva.softwcons.ql.ast.expression.literal.BooleanLiteral;
import nl.uva.softwcons.ql.ast.expression.literal.NumberLiteral;
import nl.uva.softwcons.ql.ast.expression.literal.StringLiteral;
import nl.uva.softwcons.ql.ast.expression.unary.UnaryExpression;
import nl.uva.softwcons.ql.ast.expression.unary.logical.Not;

public interface ExpressionVisitor<T> {

    /* Binary arithmetic expressions */
    T visit(Addition expr);

    T visit(Division expr);

    T visit(Multiplication expr);

    T visit(Subtraction expr);

    /* Binary comparison expressions */
    T visit(Equal expr);

    T visit(GreaterOrEqual expr);

    T visit(GreaterThan expr);

    T visit(LowerOrEqual expr);

    T visit(LowerThan expr);

    T visit(NotEqual expr);

    /* Binary logical expressions */
    T visit(And expr);

    T visit(Or expr);

    /* Unary logical expressions */
    T visit(Not expr);

    /* Literal and identifier expressions */
    T visit(Identifier expr);

    T visit(BooleanLiteral expr);

    T visit(StringLiteral expr);

    T visit(NumberLiteral expr);

    default T visitLeftOperand(BinaryExpression expr) {
        return expr.getLeftExpression().accept(this);
    }

    default T visitRightOperand(BinaryExpression expr) {
        return expr.getRightExpression().accept(this);
    }

    default T visitUnaryOperand(UnaryExpression expr) {
        return expr.getExpression().accept(this);
    }

}
