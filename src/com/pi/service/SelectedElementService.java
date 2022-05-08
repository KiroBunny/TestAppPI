package com.pi.service;

import com.pi.GUI.ElementOptionsPanel;
import com.pi.GUI.TestAppFrame;
import com.pi.Model.Element;

import java.awt.*;

public class SelectedElementService {
    Element element;
    int elementListIndex;
    public static TestAppFrame testAppFrame;
    ElementOptionsPanel elementOptionsPanel = new ElementOptionsPanel();

    public void setElement(Element element) {
        this.element = element;
    }

    public Element getElement() {
        return element;
    }

    public static TestAppFrame getTestAppFrame() {
        return testAppFrame;
    }

    public void showNetElements(int index) {
        elementListIndex = index;
        Component[] components = elementOptionsPanel.getAllComponent();

        for (Component comp : components) {
            testAppFrame.addToElementOptionPanel(comp);
        }
    }
}
