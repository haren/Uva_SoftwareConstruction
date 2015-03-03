package org.fugazi.qls.ast.style;

import org.fugazi.ql.ast.type.Type;
import org.fugazi.qls.ast.AbstractASTQLSNode;
import org.fugazi.qls.ast.widget.Widget;

public class DefaultStyleDeclaration extends AbstractASTQLSNode {

    private final Style style;
    private final Widget widget;
    private final Type questionType;

    public DefaultStyleDeclaration(Style _style, Widget _widget, Type _questionType) {
        this.style = _style;
        this.widget = _widget;
        this.questionType = _questionType;
    }
}