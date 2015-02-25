from ..Visitor import Visitor as GenericVisitor


class StatementVisitor(GenericVisitor):
    def _visitFormStatement(self, node):
        for n in node.getChildren():
            self.visit(n)

    def _visitQuestionStatement(self, node):
        if node.expr is not None:
            self.visit(node.expr)

    def _visitIfStatement(self, node):
        self.visit(node.expr)
        for n in node.getChildren():
            self.visit(n)


class ExpressionVisitor(GenericVisitor):
    def _visitAtomicExpression(self, node):
        self.visit(node.left)

    def _visitUnaryExpression(self, node):
        self.visit(node.right)

    def _visitBinaryExpression(self, node):
        self.visit(node.left)
        self.visit(node.right)


class AtomVisitor(GenericVisitor):
    def _visitIdentifier(self, node):
        pass

    def _visitStr(self, node):
        pass

    def _visitMoney(self, node):
        pass

    def _visitInt(self, node):
        pass

    def _visitBool(self, node):
        pass


class Visitor(StatementVisitor, ExpressionVisitor, AtomVisitor):
    def _visitRoot(self, node):
        for n in node.getChildren():
            self.visit(n) 