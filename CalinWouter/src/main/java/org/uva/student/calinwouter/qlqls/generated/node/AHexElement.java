/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.uva.student.calinwouter.qlqls.generated.node;

import org.uva.student.calinwouter.qlqls.generated.analysis.Analysis;

@SuppressWarnings("nls")
public final class AHexElement extends PElement {
    private THex _hex_;

    public AHexElement() {
        // Constructor
    }

    public AHexElement(
            @SuppressWarnings("hiding") THex _hex_) {
        // Constructor
        setHex(_hex_);

    }

    @Override
    public Object clone() {
        return new AHexElement(
                cloneNode(this._hex_));
    }

    @Override
    public void apply(Switch sw) {
        ((Analysis) sw).caseAHexElement(this);
    }

    public THex getHex() {
        return this._hex_;
    }

    public void setHex(THex node) {
        if (this._hex_ != null) {
            this._hex_.parent(null);
        }

        if (node != null) {
            if (node.parent() != null) {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._hex_ = node;
    }

    @Override
    public String toString() {
        return ""
                + toString(this._hex_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child) {
        // Remove child
        if (this._hex_ == child) {
            this._hex_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild) {
        // Replace child
        if (this._hex_ == oldChild) {
            setHex((THex) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
