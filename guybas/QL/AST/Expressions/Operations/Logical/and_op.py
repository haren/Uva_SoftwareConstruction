import QL.AST.Expressions.Operations.binary_expression as binary_expression
import QL.AST.Expressions.Types.bool_type as bool_type


class And(binary_expression.BinaryExpression):

    def set_string_operator(self):
        return "and"

    # get the return _type of the _expression
    def return_type(self, type_map):
        return bool_type.Bool()

    def concrete_eval(self, x, y):
        return x and y