/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.uva.student.calinwouter.ql.generated.node;

import org.uva.student.calinwouter.ql.generated.analysis.*;

@SuppressWarnings("nls")
public final class AIntType extends PType
{

    public AIntType()
    {
        // Constructor
    }

    @Override
    public Object clone()
    {
        return new AIntType();
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIntType(this);
    }

    @Override
    public String toString()
    {
        return "";
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        throw new RuntimeException("Not a child.");
    }
}