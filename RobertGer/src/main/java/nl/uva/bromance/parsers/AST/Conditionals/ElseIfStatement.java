package nl.uva.bromance.parsers.AST.Conditionals;

import nl.uva.bromance.parsers.AST.Node;

/**
 * Created by Gerrit Krijnen on 2/16/2015.
 */
public class ElseIfStatement extends Node {
    private static final String[] parentsAllowed = {"Form","Label","Calculation"};

    public ElseIfStatement(int lineNumber) {
        super(lineNumber,"ElseIfStatement");
        super.setAcceptedParents(parentsAllowed);
    }
}
