package com.form.language.ast.statement;

import org.antlr.v4.runtime.Token;

import com.form.language.ast.expression.literal.IdLiteral;
import com.form.language.ast.type.Type;
import com.form.language.gui.components.FormComponent;
import com.form.language.gui.components.GUIBuilder;
import com.form.language.memory.Context;
import com.form.language.memory.IdCollector;
import com.form.language.memory.IdTypeTable;

public class Question implements Statement {
	private String id;
	private String questionLabel;
	private Type questionType;
	private Token tokenInfo;

	public Question(String questionLabel, String id, Type questionType, Token tokenInfo) {
		super();
		this.questionLabel = questionLabel;
		this.id = id;
		this.questionType = questionType;
		this.tokenInfo = tokenInfo;
	}
	
	@Override
	public Type getType(Context context) {
		context.addId(new IdLiteral(this.id,this.questionType, null, tokenInfo));
		return this.questionType;
	}

//	@Override
//	public void getErrors(ErrorCollector errs) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void collectIds(IdCollector idCollector) {		
		idCollector.addId(new IdLiteral(this.id,this.questionType,idCollector, tokenInfo));
	}

	@Override
	public void setType(IdTypeTable ids) {}

	public String getText() {
		return this.questionLabel;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public void initMemory(Context context){
		questionType.defaultValue().addToMemory(id, context);
	}

	@Override
	public void createGUIComponent(GUIBuilder guiBuilder,
			FormComponent formGUI, Context context) {
		guiBuilder.createGUIQuestion(this, formGUI,context);
		
	}

	@Override
	public void getReferences(IdCollector idCollector) {
		idCollector.addId(new IdLiteral(this.id,this.questionType,idCollector, tokenInfo));
	}
	
}
