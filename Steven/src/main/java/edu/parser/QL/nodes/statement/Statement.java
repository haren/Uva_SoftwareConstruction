package edu.parser.QL.nodes.statement;

import edu.parser.QL.QLVisitor;
import edu.parser.QL.nodes.AbstractNode;

/**
 * Created by Steven Kok on 17/02/2015.
 */
public abstract class Statement implements AbstractNode {

    public abstract AbstractNode accept(QLVisitor QLVisitor); //todo: create statement visitor

}
