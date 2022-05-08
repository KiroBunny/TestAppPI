package com.pi.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageElements {
    static List<String> elements = new ArrayList<>(Arrays.asList(
            "Assertion",
            "Title",
            "Button",
            "InputText",
            "Ul",
            "A",
            "Div"
    ));
    static final String[] locators = new String[]{
            "ID",
            "Name",
            "Link Text",
            "CSS Selector",
            "XPath"
    };
    static final String[] actions = new String[]{
            "click",
            "send keys",
            "clear",
            "submit",
            "select"
    };

    public static List<String> getElements() {
        return elements;
    }

    public static String[] getLocators() {
        return locators;
    }

    public static String[] getActions() {
        return actions;
    }
}
