package nl.uva.bromance.ast.questiontypes;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import nl.uva.bromance.ast.Question;
import nl.uva.bromance.ast.conditionals.Result;
import nl.uva.bromance.ast.conditionals.StringResult;
import nl.uva.bromance.ast.visitors.QuestionTypeVisitor;
import nl.uva.bromance.visualization.Visualizer;

import java.util.Map;

public class CustomType implements QuestionType {

    private ToggleGroup group;
    private Question q;
    private Label label;

    public CustomType(Question question) {
        this.q = question;
    }

    @Override
    public String getTypeString() {
        return "custom";
    }


    @Override
    public void addQuestionToPane(Pane parent, Map<String, Result> answerMap, Visualizer visualizer) {
        if (q.isVisible()) {
            label = new Label(q.getQuestionString());
            label.getStyleClass().add("prettyLabel");
            parent.getChildren().add(label);

            group = new ToggleGroup();
            String id = q.getIdentifier();
            StringResult answer = (StringResult) answerMap.get(id);

            group.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
                RadioButton rb = (RadioButton) newToggle.getToggleGroup().getSelectedToggle();
                answerMap.put(id, new StringResult(rb.getText()));
                if ((oldToggle != null && !oldToggle.equals(newToggle)) || (answer == null && oldToggle == null)) {
                    visualizer.refresh(q.getUuid());
                }
            });

            for (StringResult option : q.getMultipleChoicesOptions()) {
                RadioButton radioButton = new RadioButton(option.getResult());
                radioButton.setToggleGroup(group);
                if (answer != null && option.getResult().equals(answer.getResult())) {
                    radioButton.setSelected(true);
                    if (visualizer.getFocusUuid() == q.getUuid()) {
                        visualizer.setFocusedNode(radioButton);
                    }
                }
                parent.getChildren().add(radioButton);
            }
        }
    }

    @Override
    public void accept(QuestionTypeVisitor visitor) {
        visitor.visit(this);
    }

}
