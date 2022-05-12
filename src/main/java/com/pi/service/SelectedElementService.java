package com.pi.service;

import com.pi.GUI.TestAppFrame;
import com.pi.Model.ElementModel;
import com.pi.Model.TestPlanList;
import com.pi.components.Element;
import com.pi.components.Locator;

import java.util.ArrayList;
import java.util.List;

public class SelectedElementService {
    public static TestAppFrame testAppFrame;
    ElementModel element;
    static TestPlanList testPlanList = new TestPlanList();

    public void setElement(ElementModel element) {
        this.element = element;
    }

    public ElementModel getElement() {
        return element;
    }

    public static TestAppFrame getTestAppFrame() {
        return testAppFrame;
    }

    public void showTestPlanList() {
        List<String> list = new ArrayList<>();
        for (ElementModel e :
                testPlanList.getTesPlanList()) {
            list.add(e.toString());
        }
        testAppFrame.addToTestPlanListPanel(list);
    }

    public void addElementToPlanList(int action, String parameter) {
        int index = testAppFrame.getElementOptionsPanel().getSelectedElement();
        int locatorIndex = testAppFrame.getElementOptionsPanel().getSelectedLocator();
        String value = testAppFrame.getElementOptionsPanel().getLocatorText();

        if (index < 0 || locatorIndex < 0 || action < 0 || value.equals("")) {
            System.err.println("Podaj dobre wartosci");
        } else {
            testPlanList.add(new ElementModel(index, value, locatorIndex, action, parameter));
            showTestPlanList();
        }
    }

    public void addElementToPlanList(String text) {
        if (text.equals("")) {
            System.err.println("---Podaj odpowiedni adres strony---");
        } else {
            testPlanList.add(new ElementModel("GoTo", text, "", "", ""));
            showTestPlanList();
        }
    }

    public void addElementToPlanList(int assertIndex, String parameter, int equalsIndex, String equals) {
        int index = testAppFrame.getElementOptionsPanel().getSelectedElement();
        int locatorIndex = testAppFrame.getElementOptionsPanel().getSelectedLocator();
        String value = testAppFrame.getElementOptionsPanel().getLocatorText();

        if (index < 0 || locatorIndex < 0 || value.equals("") || assertIndex < 0 || equalsIndex < 0 || equals.equals("")) {
            System.err.println("Podaj dobre wartosci");
        } else {
            List<String> paramEquals = new ArrayList<>();
            paramEquals.add(parameter);
            paramEquals.add(equalsIndex == 0? "==": "!=");
            paramEquals.add(equals);
            testPlanList.add(new ElementModel(index, value, locatorIndex, assertIndex, paramEquals, true));
            showTestPlanList();
        }
    }

    private void createNewElement(ElementModel elementModel) {
        Locator locator = convertToLocator(elementModel);
        Element element = convertToElement(elementModel);
    }

    private static Locator convertToLocator(ElementModel elementModel) {
        /*Locator locator = new Locator();

        Method getNameMethod;
        try {
            getNameMethod = locator.getClass().getMethod(PageElements.getLocators()[locatorIndex], String.class);
            return (Locator) getNameMethod.invoke(locator, value);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }*/
        return null;
    }

    private Element convertToElement(ElementModel elementModel) {
      /*  switch (PageElements.getElements().get(index)) {
            case "Button":
                return new Button(locator);
            case "InputText":
                return new InputText(locator);
            case "CheckBox":
                return new CheckBox(locator);
        }*/

        return null;
    }
}
