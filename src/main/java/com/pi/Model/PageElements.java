package com.pi.Model;

import com.pi.components.Button;
import com.pi.components.Element;
import com.pi.components.Locator;
import org.openqa.selenium.WebElement;

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
    static final String[] locators = Locator.locators;
    static final String[] actions = Element.actionElement;
    static final String[] assertionActions = Element.actionAssert;

    public static List<String> getElements() {
        return elements;
    }

    public static String[] getLocators() {
        return locators;
    }

    public static String[] getActions() {
        return actions;
    }

    public static String[] getAssertionActions() {
        return assertionActions;
    }
}
