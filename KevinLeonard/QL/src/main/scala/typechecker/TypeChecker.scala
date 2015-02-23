package typechecker

import ast._
import scala.util.parsing.input.Position

class TypeChecker {

  def check(form: Form, env: Environment = new Environment()): Environment = check(form.s, env)

  def check(s: Statement, env: Environment): Environment = s match {
    case Sequence(statements: List[Statement]) => statements.foldLeft(env) { (env, statement) => check(statement, env) }
    case s @ IfStatement(e: Expression, s1: Statement, None) => check(e, env) match {
      case BooleanType() =>
        check(s1, env)
        env // Return environment without the questions in s1.
      case _ => sys.error(s"Invalid boolean condition for if statement at line ${s.pos}")
    }
    case s @ IfStatement(e: Expression, s1: Statement, Some(s2: Statement)) => check(e, env) match {
      case BooleanType() =>
        check(s1, env)
        check(s2, env)
        env // Return environment without the questions in s1 and s2.
      case _ => sys.error(s"Invalid boolean condition for if statement at line ${s.pos}")
    }
    case s @ Question(BooleanType(), v: Variable, label: String, None) => env.tryAddVariable(v, BooleanType()).tryAddLabel(s)
    case s @ Question(BooleanType(), v: Variable, label: String, Some(e: Expression)) => check(e, env) match {
      case BooleanType() => env.tryAddVariable(v, BooleanType()).tryAddLabel(s)
      case _ => sys.error(s"Invalid expression for value of computed boolean question at line ${s.pos}")
    }
    case s @ Question(NumberType(), v: Variable, label: String, None) => env.tryAddVariable(v, NumberType()).tryAddLabel(s)
    case s @ Question(NumberType(), v: Variable, label: String, Some(e: Expression)) => check(e, env) match {
      case NumberType() => env.tryAddVariable(v, NumberType()).tryAddLabel(s)
      case _ => sys.error(s"Invalid expression for value of computed number expression at line ${s.pos}")
    }
    case s @ Question(StringType(), v: Variable, label: String, None) => env.tryAddVariable(v, StringType()).tryAddLabel(s)
    case s @ Question(StringType(), v: Variable, label: String, Some(e: Expression)) => check(e, env) match {
      case StringType() => env.tryAddVariable(v, StringType()).tryAddLabel(s)
      case _ => sys.error(s"Invalid expression for value of computed string expression at line ${s.pos}")
    }
  }

  def check(expression: Expression, env: Environment): Type = expression match {
    case e @ Or(l: Expression, r: Expression) => (check(l, env), check(r, env)) match {
      case (BooleanType(), BooleanType()) => BooleanType()
      case _ => sys.error(s"Invalid or expression at line ${e.pos}")
    }
    case e @ And(l: Expression, r: Expression) => (check(l, env), check(r, env)) match {
      case (BooleanType(), BooleanType()) => BooleanType()
      case _ => sys.error(s"Invalid and expression at line ${e.pos}")
    }
    case e @ Not(expr: Expression) => check(expr, env) match {
      case BooleanType() => BooleanType()
      case _ => sys.error(s"Invalid not expression at line ${e.pos}")
    }
    case e @ Equal(l: Expression, r: Expression) => (check(l, env), check(r, env)) match {
      case (BooleanType(), BooleanType()) => BooleanType()
      case (NumberType(), NumberType()) => BooleanType()
      case (StringType(), StringType()) => BooleanType()
      case _ => sys.error(s"Invalid == expression at line ${e.pos}")
    }
    case e @ NotEqual(l: Expression, r: Expression) => (check(l, env), check(r, env)) match {
      case (BooleanType(), BooleanType()) => BooleanType()
      case (NumberType(), NumberType()) => BooleanType()
      case (StringType(), StringType()) => BooleanType()
      case _ => sys.error(s"Invalid != expression at line ${e.pos}")
    }
    case e @ LessThan(l: Expression, r: Expression) => (check(l, env), check(r, env)) match {
      case (NumberType(), NumberType()) => BooleanType()
      case _ => sys.error(s"Invalid < expression at line ${e.pos}")
    }
    case e @ LessThanEqual(l: Expression, r: Expression) => (check(l, env), check(r, env)) match {
      case (NumberType(), NumberType()) => BooleanType()
      case _ => sys.error(s"Invalid <= expression at line ${e.pos}")
    }
    case e @ GreaterThan(l: Expression, r: Expression) => (check(l, env), check(r, env)) match {
      case (NumberType(), NumberType()) => BooleanType()
      case _ => sys.error(s"Invalid > than expression at line ${e.pos}")
    }
    case e @ GreaterThanEqual(l: Expression, r: Expression) => (check(l, env), check(r, env)) match {
      case (NumberType(), NumberType()) => BooleanType()
      case _ => sys.error(s"Invalid >= expression at line ${e.pos}")
    }
    case e @ Add(l: Expression, r: Expression) => (check(l, env), check(r, env)) match {
      case (NumberType(), NumberType()) => NumberType()
      case _ => sys.error(s"Invalid addition expression at line ${e.pos}")
    }
    case e @ Sub(l: Expression, r: Expression) => (check(l, env), check(r, env)) match {
      case (NumberType(), NumberType()) => NumberType()
      case _ => sys.error(s"Invalid subtraction expression at line ${e.pos}")
    }
    case e @ Mul(l: Expression, r: Expression) => (check(l, env), check(r, env)) match {
      case (NumberType(), NumberType()) => NumberType()
      case _ => sys.error(s"Invalid multiplication expression at line ${e.pos}")
    }
    case e @ Div(l: Expression, r: Expression) => (check(l, env), check(r, env)) match {
      case (NumberType(), NumberType()) => NumberType()
      case _ => sys.error(s"Invalid division expression at line ${e.pos}")
    }
    case v: Variable => env.tryGetVariable(v)
    case Literal(t, _) => t
  }

}

sealed trait Level
case class Warning() extends Level
case class Exception() extends Level

class Error(val level: Level, val message: String, val position: Position) {

  def canEqual(other: Any): Boolean = other.isInstanceOf[Error]

  override def equals(other: Any): Boolean = other match {
    case that: Error =>
      (that canEqual this) &&
        level == that.level &&
        message == that.message &&
        position == that.position
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(level, message, position)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def toString = s"Error($level, $message, $position)"
}

class Environment(val typeOfFields: Map[String, Type] = Map(), val labels: List[String] = List(), val errors: List[Error] = List()) {
  
  // TODO: Return Environment.
  def tryGetVariable(v: Variable): Type = typeOfFields getOrElse(v.name, {this.addError(Exception(), s"Variable ${v.name} is not defined", v.pos); UndefinedType() })

  def tryAddVariable(v: Variable, _type: Type): Environment = {
    if (typeOfFields contains v.name) {
      this.addError(Exception(), s"Variable ${v.name} is already defined", v.pos)
    } else {
      new Environment(typeOfFields + (v.name -> _type), labels, errors)
    }
  }

  def tryAddLabel(q: Question): Environment = {
    if (labels contains q.label) {
      this.addError(Warning(), s"Label ${q.label} is already defined", q.pos)
    } else {
      new Environment(typeOfFields, labels :+ q.label, errors)
    }
  }

  def addError(level: Level, message: String, position: Position): Environment = {
    new Environment(typeOfFields, labels, errors :+ new Error(level, message, position))
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Environment]

  override def equals(other: Any): Boolean = other match {
    case that: Environment =>
      (that canEqual this) &&
        typeOfFields == that.typeOfFields &&
        labels == that.labels &&
        errors == that.errors
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(typeOfFields, labels, errors)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def toString = s"Environment($typeOfFields, $labels, $errors)"
}