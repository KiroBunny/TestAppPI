package com.pi.service;

import com.pi.GUI.TestAppFrame;
import com.pi.Model.ElementModel;
import com.pi.Model.PageElements;
import com.pi.Model.TestPlanList;
import com.pi.components.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SelectedElementService {
    public static TestAppFrame testAppFrame;
    ElementModel element;
    TestPlanList testPlanList = new TestPlanList();

    public void setElement(ElementModel element) {
        this.element = element;
    }

    public ElementModel getElement() {
        return element;
    }

    public static TestAppFrame getTestAppFrame() {
        return testAppFrame;
    }

    public void showTestPlanList(){
        testAppFrame.addToTestPlanListPanel(testPlanList.getTesPlanList());
    }

    public void addElementToPlanList(int index, int locatorIndex, String value, int action){
        testPlanList.add(new ElementModel(index, value, locatorIndex, action));
        showTestPlanList();
    }

    private void createNewElement(int index, int locatorIndex, String value, int action) {
        Locator locator = convertToLocator(locatorIndex, value);
        Element element = convertToElement(index, locator);
    }

    private static Locator convertToLocator(int locatorIndex, String value) {
        Locator locator = new Locator();

        Method getNameMethod;
        try {
            getNameMethod = locator.getClass().getMethod(PageElements.getLocators()[locatorIndex], String.class);
            return (Locator) getNameMethod.invoke(locator, value);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Element convertToElement(int index, Locator locator) {
        switch (PageElements.getElements().get(index)) {
            case "Button":
                return new Button(locator);
            case "InputText":
                return new InputText(locator);
            case "CheckBox":
                return new CheckBox(locator);
        }

        return null;
    }
}
