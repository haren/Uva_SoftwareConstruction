package uva.qls.ast;

import java.util.List;

import uva.qls.ast.literal.Identifier;
import uva.qls.ast.value.GenericValue;
import uva.qls.supporting.Tuple;

public class StyleSheet extends ASTNode {
	
	private Identifier identifier;
	private List<Page> page; 
	
	public StyleSheet (Identifier _identifier, List<Page> _page, CodeLines _codeLines) {
		super(_codeLines);
		this.identifier=_identifier;
		this.page=_page;
	}
	
	public Identifier getIdentifier(){
		return this.identifier;
	}
	public List<Page> getPage(){
		return this.page;
	}

	@Override
	public Tuple<Integer, Integer> getLOCTuple() {
		return this.codeLines.getCodeLocation();
	}

	@Override
	public CodeLines getLOC() {
		return this.codeLines;
	}
	
	@Override
	public GenericValue<?> evaluate() {
		return null;
	}
	@Override
	public String toString(){
		return "StyleSheet(" + this.identifier.toString() + "," + page.toString() + ")";
	}
}
