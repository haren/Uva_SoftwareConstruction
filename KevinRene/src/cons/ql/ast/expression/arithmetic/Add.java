package cons.ql.ast.expression.arithmetic;

import cons.ql.ast.Expression;
import cons.ql.ast.expression.Binary;
import cons.ql.ast.expression.QLType;
import cons.ql.ast.expression.type.QLFloat;
import cons.ql.ast.expression.type.QLNumeric;
import cons.ql.ast.visitor.Visitor;

public class Add extends Binary {
	
	public Add(Expression left, Expression right) {
		super(left, right, "+");
	}
	
	@Override
	public void accept(Visitor visitor) {		
		visitor.visit(this);
	}
	
	@Override
	public QLType getType() {
		return new QLNumeric();
	}

}