package com.pi.service;

import com.pi.GUI.ElementOptionsPanel;
import com.pi.GUI.TestAppFrame;
import com.pi.Model.ElementModel;
import com.pi.Model.PageElements;
import com.pi.Model.TestPlanList;
import com.pi.components.*;
import com.pi.components.Button;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SelectedElementService implements ActionListener {
    public static TestAppFrame testAppFrame;

    int elementListIndex;
    ElementModel element;
    ElementOptionsPanel elementOptionsPanel = new ElementOptionsPanel(PageElements.getActions());
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

    public void showNetElements(int index) {
        elementListIndex = index;
        Component[] components = elementOptionsPanel.getAllComponent();

        elementOptionsPanel.getChooseButton().addActionListener(this);

        for (Component comp : components) {
            testAppFrame.addToElementOptionPanel(comp);
        }
    }

    private String[] setActionListToComponent(int index) {
        String[] actions = new String[5];
        actions[0] = PageElements.getActions()[0];
        switch (index) {
            /*case 0:
                return actions;
            case 1:
                actions[1] = PageElements.getActions()[1];
                actions[2] = PageElements.getActions()[2];
                return actions;
            case 2:
            case 3:
            case 4:*/
            default:
                return PageElements.getActions();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int locator = elementOptionsPanel.getLocatorList().getSelectedIndex();
        String value = elementOptionsPanel.getLocatorField().getText();
        int action = elementOptionsPanel.getActionList().getSelectedIndex();

        createNewElement(elementListIndex, locator, value, action);
    }

    private void createNewElement(int index, int locatorIndex, String value, int action) {
        Locator locator = convertToLocator(locatorIndex, value);
        Element element = convertToElement(index, locator);
        testPlanList.add(element, PageElements.getActions()[action]);
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
        switch (PageElements.getElements().get(index)){
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
