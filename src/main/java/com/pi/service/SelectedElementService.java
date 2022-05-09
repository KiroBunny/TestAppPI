package com.pi.service;

import com.pi.GUI.ElementOptionsPanel;
import com.pi.GUI.TestAppFrame;
import com.pi.Model.ElementModel;
import com.pi.Model.PageElements;

import java.awt.*;

public class SelectedElementService {
    ElementModel element;
    int elementListIndex;
    public static TestAppFrame testAppFrame;


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
        ElementOptionsPanel elementOptionsPanel = new ElementOptionsPanel(setActionListToComponent(index));
        Component[] components = elementOptionsPanel.getAllComponent();

        for (Component comp : components) {
            testAppFrame.addToElementOptionPanel(comp);
        }
    }

    private String[] setActionListToComponent(int index) {
        String[] actions = new String[5];
        actions[0] = PageElements.getActions()[0];
        switch(index) {
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
}
