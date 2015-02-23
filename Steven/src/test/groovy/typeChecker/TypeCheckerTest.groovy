package typeChecker

import exceptions.TypeCheckException
import parser.Main
import parser.ParseTreeWalker
import parser.nodes.Form
import spock.lang.Specification

/**
 * Created by Steven Kok on 21/02/2015.
 */
class TypeCheckerTest extends Specification {

    Main parseTreeWalker = new Main();

    def "Should throw exception with duplicate and unreferenced questions"() {
        when:
        Form form = parseTreeWalker.walk(input, new ParseTreeWalker(), Form.class)

        TypeChecker typeChecker = new TypeChecker();
        typeChecker.visit(form)

        then:
        def exception = thrown(TypeCheckException)
        exception.message.contains(message)

        where:
        input                                                              | message
        "src/main/antlr/input/question/QL_duplicateQuestions"              | TypeChecker.ALREADY_DECLARED_QUESTION.substring(0, 20)
        "src/main/antlr/input/question/QL_duplicateQuestionsDifferentType" | TypeChecker.ALREADY_DECLARED_QUESTION_DIFFERENT_TYPE.substring(0, 20)
        "src/main/antlr/input/question/QL_referenceToUndefinedQuestion"    | "r, name9, name1, n"
    }

    def "Should throw exception with non-boolean conditions"() {
        when:
        Form form = parseTreeWalker.walk(input, new ParseTreeWalker(), Form.class)

        TypeChecker typeChecker = new TypeChecker();
        typeChecker.visit(form)

        then:
        def exception = thrown(TypeCheckException)
        exception.message.contains(message)

        where:
        input                                                         | message
        "src/main/antlr/input/nonBoolean/QL_nonBooleanConditions_or"  | TypeChecker.EXPRESSION_EXPECTS_BOOLEAN.substring(0, 30)
        "src/main/antlr/input/nonBoolean/QL_nonBooleanConditions_and" | TypeChecker.EXPRESSION_EXPECTS_BOOLEAN.substring(0, 30)
        "src/main/antlr/input/nonBoolean/QL_nonBooleanConditions_not" | TypeChecker.EXPRESSION_EXPECTS_BOOLEAN.substring(0, 30)

    }
}