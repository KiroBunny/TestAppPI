package com.pi.Model;

public class ElementModel {
    final String type;
    final String name;
    final String findBy;
    final String action;

    public ElementModel(String type, String name, String findBy, String action) {
        this.type = type;
        this.name = name;
        this.findBy = findBy;
        this.action = action;
    }
}
