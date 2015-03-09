package ql.gui

import ql.ast._
import ql.evaluator.Evaluator
import ql.types.{Dependencies, VariableName}

import scala.collection.immutable.StringOps
import scala.util.Try
import scalafx.beans.value.ObservableValue
import scalafx.collections.ObservableMap
import scalafx.collections.ObservableMap.{Add, Replace}
import scalafx.geometry.Insets
import scalafx.scene.control.{CheckBox, Label, TextField}
import scalafx.scene.layout.VBox

abstract class QuestionBox(q: Question, visibilityExpressions: List[Expression], env: ObservableMap[String, Value])
  extends VBox {

  val evaluator = new Evaluator()
  val dependencyResolver = new DependencyResolver()

  val DefaultPadding = 0
  val PaddingBottom = 10

  val name: VariableName = q.variable.name
  val valueDependencies: Dependencies = q.optionalExpression.fold[Dependencies](List())(e => dependencyResolver.resolve(e))
  val visibilityDependencies: Dependencies = visibilityExpressions.flatMap(e => dependencyResolver.resolve(e))

  def updateVisibility(key: String): Unit = if (visibilityDependencies contains key) {
    visible = shouldBeVisible
    managed = isVisible
  }
  def shouldBeVisible: Boolean = visibilityExpressions.forall(evaluator.eval(_, env) == BooleanValue(true))
  def isVisible: Boolean = visible.value

  visible = shouldBeVisible
  managed = isVisible
  padding = Insets(DefaultPadding, DefaultPadding, PaddingBottom, DefaultPadding)

  val label = new Label(q.label)
  label.margin = Insets(0, 0, 5, 0)
  children.add(label)
}

class BooleanQuestionBox(q: Question, visibilityExpressions: List[Expression], env: ObservableMap[String, Value])
  extends QuestionBox(q: Question, visibilityExpressions: List[Expression], env: ObservableMap[String, Value]) {

  type UpdateFunction = (ObservableValue[Boolean, java.lang.Boolean], java.lang.Boolean, java.lang.Boolean) => Unit

  // Initialize
  val field = addCheckBoxElement(evalValue, (_, _, newValue) => { env += (name -> BooleanValue(newValue))})
  def addCheckBoxElement(value: Boolean, updateEnvironment: UpdateFunction): CheckBox = {
    env += (name -> BooleanValue(value))
    val checkbox = new CheckBox
    checkbox.selected = value
    checkbox.selected.onChange(updateEnvironment)
    checkbox
  }
  children.add(field)

  // On change functions
  env.onChange((map, change) => change match {
    case Add(key, _) => updateProperties(field, key)
    case Replace(key, _, _) => updateProperties(field, key)
  })

  def updateProperties(field: CheckBox, key: String): Unit = {
    updateVisibility(key)
    if (isVisible) {
      updateValue(field, key, evalValue)
    }
  }

  def updateValue(field: CheckBox, key: String, value: Boolean): Unit =  if (valueDependencies contains key) field.selected = value

  // Evaluation function
  def evalValue: Boolean = q.optionalExpression match {
    case Some(e) => evaluator.eval(e, env) match {
      case BooleanValue(v) => v
      case _ => throw new AssertionError(s"Error in type checker. Variable $name not of type Boolean.")
    }
    case None => false
  }
}

class NumberQuestionBox(q: Question, visibilityExpressions: List[Expression], env: ObservableMap[String, Value])
  extends QuestionBox(q: Question, visibilityExpressions: List[Expression], env: ObservableMap[String, Value]) {

  type UpdateFunction = (ObservableValue[String, java.lang.String], java.lang.String, java.lang.String) => Unit

  // Initialize
  val field = addTextFieldElement(evalValue, (obs, oldValue, newValue) => {
    val newIntV = Try(new StringOps(newValue).toInt).toOption.getOrElse(0)
    env += (name -> NumberValue(newIntV))
  })
  def addTextFieldElement(value: Int, updateEnvironment: UpdateFunction): TextField = {
    env += (name -> NumberValue(value))
    val textField = new TextField
    textField.text = value.toString
    textField.text.onChange(updateEnvironment)
    textField
  }
  children.add(field)

  // On change functions
  env.onChange((map, change) => change match {
    case Add(key, _) => updateProperties(field, key)
    case Replace(key, added, removed) => updateProperties(field, key)
  })

  def updateProperties(field: TextField, key: String): Unit = {
    updateVisibility(key)
    if (isVisible) {
      updateValue(field, key, evalValue)
    }
  }

  def updateValue(field: TextField, key: String, value: Int): Unit =  if (valueDependencies contains key) field.text = value.toString

  // Evaluation function
  def evalValue: Int = q.optionalExpression match {
    case Some(e) => evaluator.eval(e, env) match {
      case NumberValue(v) => v
      case _ => throw new AssertionError(s"Error in type checker. Variable $name not of type Number.")
    }
    case None => 0
  }
}

class StringQuestionBox(q: Question, visibilityExpressions: List[Expression], env: ObservableMap[String, Value])
  extends QuestionBox(q: Question, visibilityExpressions: List[Expression], env: ObservableMap[String, Value]) {

  type UpdateFunction = (ObservableValue[String, java.lang.String], java.lang.String, java.lang.String) => Unit

  // Initialize
  val field = addTextFieldElement(evalValue, (obs, oldValue, newValue) => { env += (name -> StringValue(newValue)) })
  def addTextFieldElement(value: String, updateEnvironment: UpdateFunction): TextField = {
    env += (name -> StringValue(value))
    val textField = new TextField
    textField.text = value
    textField.text.onChange(updateEnvironment)
    textField
  }
  children.add(field)

  // On change functions
  env.onChange((map, change) => change match {
    case Add(key, _) => updateProperties(field, key)
    case Replace(key, added, removed) => updateProperties(field, key)
  })

  def updateProperties(field: TextField, key: String): Unit = {
    updateVisibility(key)
    if (isVisible) {
      updateValue(field, key, evalValue)
    }
  }

  def updateValue(field: TextField, key: String, value: String): Unit =  if (valueDependencies contains key) field.text = value

  // Evaluation function
  def evalValue: String = q.optionalExpression match {
    case Some(e) => evaluator.eval(e, env) match {
      case StringValue(v) => v
      case _ => throw new AssertionError(s"Error in type checker. Variable $name not of type String.")
    }
    case None => ""
  }
}
