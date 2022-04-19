package com.pi;

import java.awt.*;

public class PageElements {
    static List elements = new List();

    public static List getElements(){
        elements.add("Assertion");
        elements.add("Title");
        elements.add("Button");
        elements.add("InputText");
        elements.add("Ul");
        elements.add("A");
        elements.add("Div");
        return elements;
    }
}
