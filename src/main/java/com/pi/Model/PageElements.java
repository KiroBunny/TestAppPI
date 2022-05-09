package com.pi.Model;

import com.pi.components.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageElements {
    static List<String> elements = new ArrayList<>(Arrays.asList(
            "Button",
            "InputText",
            "CheckBox",
            "A",
            "Div"
    ));
    static final String[] locators = new String[]{
            "ID",
            "Name",
            "CSS Selector",
            "XPath",
            "Tag",
            "className",
            "Link Text",
            "PartialLinkText",
            "ID or Name"
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
