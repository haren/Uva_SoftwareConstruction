package uva.sc.ql.gui.helpers;

import java.awt.Component;
import java.util.List;

import uva.sc.ql.atom.ID;

public class ListenerHelper {

    public Component getComponentByName(ID element,
	    List<Component> componentList) {
	Component result = null;
	for (Component component : componentList) {
	    if (element.getValue().equals(component.getName())) {
		result = component;
	    }
	}
	return result;
    }
}
