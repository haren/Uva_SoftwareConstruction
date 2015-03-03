package com.klq.logic.question;

import com.klq.logic.IKLQItem;
import com.klq.logic.expression.AExpression;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timon on 10.02.2015.
 */
public class Question implements IKLQItem{
    private final Id id;
    private final Type type;
    private final OptionSet options;
    private final Text text;
    private final List<AExpression> dependencies;

    private final boolean computedQuestion;
    private final AExpression computedValue;

    private final SimpleBooleanProperty visibleProperty;
    private final SimpleStringProperty computedProperty;

    public Question (Id id, Type type, OptionSet options, Text text){
        this.id = id;
        this.type = type;
        this.options = options;
        this.text = text;
        this.dependencies = new ArrayList<>();
        visibleProperty = new SimpleBooleanProperty(dependencies.isEmpty());
        computedProperty = new SimpleStringProperty("");

        if (this.options != null) {
            if (this.options.size() == 1) {
                computedQuestion = true;
                computedValue = options.get(0).evaluate();
                return;
            } else if (this.options.size() > 1){
                for (int i=0; i<this.options.size(); i++){
                    options.add(i, options.remove(i).evaluate());
                }
            }
        }
        computedQuestion = false;
        computedValue = null;
    }

    public boolean isComputedQuestion() {
        return computedQuestion;
    }

    public Id getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public OptionSet getOptions() {
        return options;
    }

    public Text getText() {
        return text;
    }

    public List<AExpression> getDependencies() {
        return dependencies;
    }

    public void addDependency(AExpression dependency){
        dependencies.add(dependency);
    }

    public AExpression getComputedValue() {
        return computedValue;
    }

    public BooleanProperty visibleProperty(){
        return visibleProperty;
    }

    public StringProperty computedProperty(){
        return computedProperty;
    }
}