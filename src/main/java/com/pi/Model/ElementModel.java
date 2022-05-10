package com.pi.Model;

public class ElementModel {
    final String type;
    final String value;
    final String findBy;
    final String action;

    public ElementModel(String type, String value, String findBy, String action) {
        this.type = type;
        this.value = value;
        this.findBy = findBy;
        this.action = action;
    }

    public ElementModel(int index, String value, int locator, int action) {
        this.type = PageElements.getElements().get(index);
        this.value = value;
        this.findBy = PageElements.getLocators()[locator];
        this.action = PageElements.getActions()[action];
    }

    @Override
    public String toString() {
        return "" + type + '\n' +
                "\tfindBy='" + findBy + " " + value + '\n' +
                "\taction='" + action + '\n';
    }
}
