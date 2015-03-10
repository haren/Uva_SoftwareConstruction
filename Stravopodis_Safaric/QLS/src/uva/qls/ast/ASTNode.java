package uva.qls.ast;
import uva.qls.ast.CodeLines;
import uva.qls.ast.value.GenericValue;
public abstract class ASTNode {
	
	protected CodeLines codeLines;
	public abstract GenericValue<?> evaluate();
	
	public ASTNode (CodeLines _codeLines) {
		this.codeLines=_codeLines;
	}
	
	@Override
	public String toString(){
		return this.toString();
	}

}