package com.form.language.ast.expression.logic;

import com.form.language.ast.expression.BinaryExpression;
import com.form.language.ast.expression.Expression;
import com.form.language.ast.expression.literal.BoolLiteral;
import com.form.language.ast.type.BoolType;
import com.form.language.ast.type.ErrorType;
import com.form.language.ast.type.Type;
import com.form.language.ast.values.BoolValue;
public class And extends BinaryExpression implements Expression {

	public And(Expression left, Expression right) {
		super(left, right);
	}
	
	@Override
	public BoolValue evaluate() {
		return ((BoolLiteral)left).evaluate().And(((BoolLiteral)right).evaluate());
	}

	@Override
	public Type getType() {
		if(left.getType().isBoolType() && right.getType().isBoolType()) return new BoolType();
		return new ErrorType();
	}

}
