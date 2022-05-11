package com.pi.Model;

public class ElementModel {
    final String type;
    final String value;
    final String findBy;
    final String action;
    final Object parameter;

    public ElementModel(String type, String value, String findBy, String action, Object parameter) {
        this.type = type;
        this.value = value;
        this.findBy = findBy;
        this.action = action;
        this.parameter = parameter;
    }

    public ElementModel(int index, String value, int locator, int action, Object parameter) {
        this.type = PageElements.getElements().get(index);
        this.value = value;
        this.findBy = PageElements.getLocators()[locator];
        this.action = PageElements.getActions()[action];
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        if (!action.equals("")) {
            return type + '\n' +
                    "\t - " + findBy + " " + value + '\n' +
                    "\t - " + action.substring(0, action.indexOf('(') + 1) + parameter + ')';
        }
        return type + ": " + value;
    }
}
