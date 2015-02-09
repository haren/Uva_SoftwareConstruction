package org.uva.sea.ql.ast;

public abstract class Binary extends Expr {
	
	protected Expr left, right;
	protected String operator;
	
	public Binary(Expr left, Expr right, String operator) {
		this.left = left;
		this.right = right;
		this.operator = operator;
	}

	public String show() {
		return this.left.show() + " " + this.operator + " " + this.right.show();
	}
}