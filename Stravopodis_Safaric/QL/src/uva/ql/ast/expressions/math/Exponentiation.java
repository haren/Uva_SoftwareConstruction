package uva.ql.ast.expressions.math;

import uva.ql.ast.CodeLines;
import uva.ql.ast.expressions.*;
import uva.ql.ast.value.NumberValue;
import uva.ql.ast.visitor.ExpressionVisitorInterface;

public class Exponentiation extends BinaryExpressions{

	public Exponentiation(Expression left, Expression right, CodeLines _codeLines) {
		super(left, right, Operator.EXP, _codeLines);
	}
	
	@Override
	public <T> T accept(ExpressionVisitorInterface<T> visitor) {
		return visitor.visitExponentiation(this);
	}
	
	@Override
	public NumberValue evaluate() {
		if (!NumberValue.isNumberValue(getLeftExpr()) || !NumberValue.isNumberValue(getRightExpr()))
			throw new IllegalArgumentException("Operands Not Of The Same Type. Exponentiation requires numbers.");
			
		return NumberValue.numberValueFromExpr(getLeftExpr()).exponentiation(NumberValue.numberValueFromExpr(getRightExpr()));
	}

	@Override
	public String evaluateType() {
		return Exponentiation.class.getName();
	}
	
	@Override 
	public String toString(){
		return "Exponentiation(" + this.getLeftExpr() + "," + this.getRightExpr() + ")";
	}
}