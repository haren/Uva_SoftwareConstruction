from .Visitor import Visitor
from .common import typeOfIdentifier, nativeQuestionType
from . import Message

def check(ast):
    return QuestionRedefinitionsVisitor(ast).visit(ast.root)

class QuestionRedefinitionsVisitor(Visitor):
    def _visitQuestionStatement(self, node):
        myType = nativeQuestionType(node.type)
        expectedType = typeOfIdentifier(
            node.identifier, self._ast.root
        )

        if myType != expectedType:
            self._result = self._result.withMessage(
                Message.Error(
                    'Duplicate definition of question `'\
                   +node.identifier+'` with different type `'\
                   +str(myType)+'` (expected type `'\
                   +str(expectedType)+'`)',
                    node
                )
            )