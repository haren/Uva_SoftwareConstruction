package edu.gui.components.store;

/**
 * Created by Steven Kok on 10/03/2015.
 */
public class TextStore implements Store {

    private final String text;

    public TextStore(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public String getValue() {
        return text;
    }
}
