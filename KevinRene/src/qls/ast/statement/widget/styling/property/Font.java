package qls.ast.statement.widget.styling.property;

import java.util.Arrays;

import ql.ast.type.QLString;
import qls.ast.expression.Literal;
import qls.ast.statement.widget.styling.Property;
import qls.ast.visitor.StatementVisitor;

public class Font extends Property {
	public Font(Literal<?> value) {
		super(Arrays.asList(new QLString()), value);
	}
	
	@Override
	public <T> T accept(StatementVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
