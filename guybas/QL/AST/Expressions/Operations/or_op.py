import QL.AST.Expressions.Operations.binary_expression as b


class Or(b.BinaryExpression):

    def set_string_operator(self):
        return "or"

    def return_type(self, type_map):
        return int

    def eval(self, x, y):
        return x or y