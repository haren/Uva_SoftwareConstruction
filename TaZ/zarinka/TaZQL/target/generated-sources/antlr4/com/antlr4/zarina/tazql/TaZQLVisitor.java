// Generated from TaZQL.g4 by ANTLR 4.4
package com.antlr4.zarina.tazql;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TaZQLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TaZQLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TaZQLParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(@NotNull TaZQLParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaZQLParser#questionLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionLabel(@NotNull TaZQLParser.QuestionLabelContext ctx);
}