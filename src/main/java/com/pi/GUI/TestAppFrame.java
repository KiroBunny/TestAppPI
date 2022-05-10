package com.pi.GUI;

import com.pi.Model.ElementModel;
import com.pi.Model.PageElements;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TestAppFrame extends JFrame{
    final String pageAddress;
    final String browserName;
    Container container = getContentPane();
    ElementOptionsPanel elementOptionsPanel = new ElementOptionsPanel(PageElements.getActions());
    JPanel testPlanListPanel = new JPanel();
    JList<Object> testPlanList;

    public TestAppFrame(String pageAddress, String browserName) {
        this.pageAddress = pageAddress;
        this.browserName = browserName;

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
    }

    public void setLayoutManager() {
        container.setLayout(null);
        elementOptionsPanel.setLayout(null);
    }

    private void setLocationAndSize() {
        elementOptionsPanel.setBounds(50, 10, 200, 450);
        testPlanListPanel.setBounds(550, 50, 200, 450);
        //testPlanListPanel.setBackground(new Color(123213));
    }

    private void addComponentsToContainer() {
        container.add(elementOptionsPanel);
        container.add(testPlanListPanel);
    }

    public void addToElementOptionPanel(Component component) {
        elementOptionsPanel.add(component);
        elementOptionsPanel.repaint();
        elementOptionsPanel.setVisible(true);
    }

    public void addToTestPlanListPanel(List<ElementModel> elementList) {
        List<String> list = new ArrayList<>();
        for (ElementModel e :
                elementList) {
            list.add(e.toString());
        }

        testPlanList = new JList<>(list.toArray());
        System.out.println(list);
        testPlanList.setBounds(550,50,200,450);

        testPlanListPanel.add(testPlanList);
        System.out.println("Dodano nowy element do planu " + elementList.get(elementList.size()-1));
        testPlanListPanel.repaint();
        testPlanListPanel.setVisible(true);
    }
}
