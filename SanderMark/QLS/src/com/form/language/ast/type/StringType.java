package com.form.language.ast.type;

public class StringType extends Type {

	@Override
	public Type getType() {
		return this;
	}

	@Override
	public Boolean isStringType(){
		return true;
	}
	
	@Override
	public String toString(){
		return "String";
	}

/*	@Override
	public GenericValue<?> defaultValue() {
		// TODO Auto-generated method stub
		return new StringValue("");
	}*/

}