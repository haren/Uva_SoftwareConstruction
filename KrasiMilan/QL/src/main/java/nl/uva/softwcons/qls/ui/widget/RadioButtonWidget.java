package nl.uva.softwcons.qls.ui.widget;

import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import nl.uva.softwcons.ql.eval.ValueChangeListener;
import nl.uva.softwcons.ql.eval.value.Value;
import nl.uva.softwcons.ql.ui.converter.ValueConverter;
import nl.uva.softwcons.ql.ui.widget.Widget;

public class RadioButtonWidget extends Widget {
    private final RadioButton yesButton;
    private final RadioButton noButton;

    private final HBox hbox;
    private final ToggleGroup group;

    private ValueConverter<Boolean> converter;

    public RadioButtonWidget(final String yesString, final String noString) {
        this.yesButton = new RadioButton(yesString);
        this.noButton = new RadioButton(noString);

        this.hbox = new HBox();
        this.hbox.getChildren().addAll(yesButton, noButton);

        this.group = new ToggleGroup();
        this.yesButton.setToggleGroup(group);
        this.noButton.setToggleGroup(group);
    }

    public RadioButtonWidget(final String yesString, final String noString, final ValueConverter<Boolean> converter) {
        this(yesString, noString);
        this.converter = converter;
    }

    @Override
    public Node getWidget() {
        return hbox;
    }

    @Override
    public void setValue(final Value value) {
        if (value.inConditionalContext()) {
            group.selectToggle(yesButton);
            return;
        }
        group.selectToggle(noButton);
    }

    @Override
    public void setEditable(final boolean editable) {
        this.hbox.setDisable(!editable);
    }

    @Override
    public void addListener(final ValueChangeListener<Value> listener) {
        yesButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            listener.processValueChange(converter.toValue(newValue));
        });
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setConverter(final ValueConverter converter) {
        this.converter = converter;
    }

}
