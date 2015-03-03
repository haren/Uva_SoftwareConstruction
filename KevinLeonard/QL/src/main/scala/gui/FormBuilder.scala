package gui

import ast._
import evaluator.Evaluator

import scalafx.collections.ObservableMap
import scalafx.scene.layout.VBox

class FormBuilder(var env: ObservableMap[String, Value] = ObservableMap.empty[String, Value]) {

  val evaluator = new Evaluator()

  def build(form: Form): FormGUI = {
    env = evaluator.eval(form)
    new FormGUI(form.label, build(form.s))
  }

  def build(s: Statement, visibilityExpressions: List[Expression] = List()): List[VBox] = s match {
    case Sequence(statements) => statements.flatMap(s => build(s, visibilityExpressions))
    case i: IfStatement => buildIfStatement(i, visibilityExpressions)
    case q: Question => List(buildQuestion(q, visibilityExpressions))
  }

  def buildIfStatement(i: IfStatement, visibilityExpressions: List[Expression]): List[VBox] = {
    val ifBlock = build(i.ifBlock, i.expression :: visibilityExpressions)
    val elseBlock = i.optionalElseBlock match {
      case Some(s) => build(s, Not(i.expression) :: visibilityExpressions)
      case None => List()
    }
    ifBlock ++ elseBlock
  }

  def buildQuestion(q: Question, visibilityExpressions: List[Expression]): VBox = {
    q._type match {
      case BooleanType() => new BooleanQuestionBox(q, visibilityExpressions, env)
      case NumberType() => new NumberQuestionBox(q, visibilityExpressions, env)
      case StringType() => new StringQuestionBox(q, visibilityExpressions, env)
    }
  }
}
