package cons.ql.ast.expression.type;

import java.util.Arrays;

import cons.ql.ast.expression.QLType;
import cons.ql.ast.visitor.Visitor;

public class QLForm extends QLType {
	public QLForm() {
		super(Arrays.asList());
	}

	@Override
	public QLType getType() {
		return new QLForm();
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
