package org.uva.student.calinwouter.qlqls.qls.model.functions;

import org.uva.student.calinwouter.qlqls.ql.interpreter.TypeCallback;
import org.uva.student.calinwouter.qlqls.ql.interpreter.TypeDescriptor;
import org.uva.student.calinwouter.qlqls.ql.types.TBool;
import org.uva.student.calinwouter.qlqls.ql.types.TInteger;
import org.uva.student.calinwouter.qlqls.ql.types.TString;
import org.uva.student.calinwouter.qlqls.qls.model.WidgetSettingsModel;
import org.uva.student.calinwouter.qlqls.qls.model.abstractions.AbstractFormField;
import org.uva.student.calinwouter.qlqls.qls.model.interfaces.IModel;
import org.uva.student.calinwouter.qlqls.qls.model.interfaces.IQuestionWidgetCallback;

import java.util.HashMap;

// TODO really ugly construction!!!!
public class Question extends AbstractFormField<Question> implements TypeCallback {
    private IQuestionWidgetCallback widgetCallback;

    public void applyWidget(IQuestionWidgetCallback widgetCallback, TypeDescriptor<?> typeDescriptor) {
        this.widgetCallback = widgetCallback;
        typeDescriptor.callTypeMethod(this);
    }

    @Override
    public void apply(IModel iModel) {
        iModel.caseQuestion(this);
    }

    public Question() {
        super();
    }

    @Override
    public void usesBoolean() {
        try {
            getTypeToWidgetSettingsModel().getWidgetSettingsModel(TBool.TYPE_REFERENCE).getWidget()
                    .applyWidget(this, widgetCallback, getWidgetSettingsModel(TBool.TYPE_REFERENCE));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void usesInteger() {
        try {
            getTypeToWidgetSettingsModel().getWidgetSettingsModel(TInteger.TYPE_REFERENCE).getWidget()
                    .applyWidget(this, widgetCallback, getWidgetSettingsModel(TInteger.TYPE_REFERENCE));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void usesString() {
        try {
            getTypeToWidgetSettingsModel().getWidgetSettingsModel(TString.TYPE_REFERENCE).getWidget()
                    .applyWidget(this, widgetCallback, getWidgetSettingsModel(TString.TYPE_REFERENCE));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
