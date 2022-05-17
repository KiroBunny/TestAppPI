package com.pi.Model;

import java.io.Serializable;

public class ElementModel implements Serializable {
    final String type;
    final String locatorText;
    final String findBy;
    final String action;
    final Object parameter;

    public ElementModel(String type, String locatorText, String findBy, String action, Object parameter) {
        this.type = type;
        this.locatorText = locatorText;
        this.findBy = findBy;
        this.action = action;
        this.parameter = parameter;
    }

    public ElementModel(int index, String locatorText, int locator, int action, Object parameter) {
        this.type = PageElements.getElements().get(index);
        this.locatorText = locatorText;
        this.findBy = PageElements.getLocators()[locator];
        this.action = PageElements.getActions()[action];
        this.parameter = parameter;
    }

    public ElementModel(int index, String locatorText, int locator, int assertIndex, Object parameter, boolean b) {
        this.type = PageElements.getElements().get(index);
        this.locatorText = locatorText;
        this.findBy = PageElements.getLocators()[locator];
        this.action = PageElements.getAssertionActions()[assertIndex];
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        if (!action.equals("")) {
            return type + '\n' +
                    "\t - " + findBy + " " + locatorText + '\n' +
                    "\t - " + action.substring(0, action.indexOf('(') + 1) + parameter + ')';
        }
        return type + ": " + locatorText;
    }

    public String getType() {
        return type;
    }

    public String getLocatorText() {
        return locatorText;
    }

    public String getFindBy() {
        return findBy;
    }

    public String getAction() {
        return action;
    }

    public Object getParameter() {
        return parameter;
    }
}
