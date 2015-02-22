package uva.ql.ast.expressions.logic;

import uva.ql.ast.CodeLines;
import uva.ql.ast.expressions.BinaryExpressions;
import uva.ql.ast.expressions.Expression;
import uva.ql.ast.expressions.Operator;

public class Less_Eq extends BinaryExpressions{

	public Less_Eq(Expression _left, Expression _right, CodeLines _codeLines){
		super(_left, _right, Operator.LESS_EQ, _codeLines);
	}
	
	@Override
	public String toString(){
		return this.getLeftExpr() + Operator.LESS_EQ.getName() + this.getLeftExpr();
	}
}