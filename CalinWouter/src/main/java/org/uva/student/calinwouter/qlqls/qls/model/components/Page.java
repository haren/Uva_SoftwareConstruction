package org.uva.student.calinwouter.qlqls.qls.model.components;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
@AllArgsConstructor
public class Page {
    private final String ident;
    private final Sections sections;
    private final Defaults defaults;

    public Page(String ident, Section... sections) {
        this(ident, new Sections(sections), new Defaults(new HashMap()));
    }
}