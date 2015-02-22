package nl.uva.bromance.AST;

import nl.uva.bromance.AST.Conditionals.CanContainConditionals;
import nl.uva.bromance.AST.Conditionals.ElseIfStatement;
import nl.uva.bromance.AST.Conditionals.ElseStatement;
import nl.uva.bromance.AST.Conditionals.IfStatement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Calculation extends Node implements CanContainConditionals {
    private static final List<Class<? extends Node>> parentsAllowed = new ArrayList<Class<? extends Node>>(Arrays.asList(Form.class));
    private String identifier;

    private IfStatement ifStatement;
    private List<ElseIfStatement> elseIfStatements = new ArrayList<>();
    private ElseStatement elseStatement;

    public Calculation(int lineNumber, String id) {
        super(lineNumber, Calculation.class);
        this.setAcceptedParents(parentsAllowed);
        if (id != null) {
            this.identifier = id;
        } else {
            //TODO: Consider putting this in the TypeChecker
            System.err.println("Calculation Error: No identifier specified");
        }
    }

    @Override
    public void printDebug(int i) {
        for (int j = 0; j < i; j++) {
            System.out.print("\t");
        }
        System.out.print("[Calculation] { Name : " + this.identifier + " }\n");
        for (Node n : getChildren()) {
            n.printDebug(i + 1);
        }
    }

    @Override
    public Optional<IfStatement> getIfsStatement() {
        return Optional.of(ifStatement);
    }

    public void setIfStatement(IfStatement ifStatement) {
        this.ifStatement = ifStatement;
    }

    @Override
    public Optional<List<ElseIfStatement>> getElseIfStatement() {
        return Optional.of(elseIfStatements);
    }

    @Override
    public void setElseIfStatement(ElseIfStatement eifs) {
        elseIfStatements.addAll(Arrays.asList(eifs));
    }

    @Override
    public Optional<ElseStatement> getElseStatement() {
        return Optional.of(elseStatement);
    }

    @Override
    public void setElseStatement(ElseStatement es) {
        elseStatement = es;
    }
}