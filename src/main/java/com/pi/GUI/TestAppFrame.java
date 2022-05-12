package com.pi.GUI;

import com.pi.Model.PageElements;
import com.pi.Model.TestPlanList;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TestAppFrame extends JFrame{
    final String pageAddress;
    final String browserName;
    Container container = getContentPane();

    ElementOptionsPanel elementOptionsPanel = new ElementOptionsPanel(PageElements.getActions());

    GoToPanel goToPanel = new GoToPanel();
    AssertPanel assertPanel = new AssertPanel();
    JPanel testPlanListPanel = new JPanel();
    JList<Object> testPlanList = new JList<>();
    public TestAppFrame(String pageAddress, String browserName) {
        this.pageAddress = pageAddress;
        this.browserName = browserName;
        this.goToPanel.setPageAddress(pageAddress);

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
    }

    public void setLayoutManager() {
        container.setLayout(null);
        elementOptionsPanel.setLayout(null);
        goToPanel.setLayout(null);
        assertPanel.setLayout(null);
    }

    private void setLocationAndSize() {
        testPlanListPanel.setBounds(550, 40, 400, 480);
        testPlanList.setBounds(0,0,440,450);
        testPlanListPanel.setBackground(new Color(255,255,255));
    }

    private void addComponentsToContainer() {
        container.add(elementOptionsPanel);
        container.add(goToPanel);
        container.add(testPlanListPanel);
        container.add(assertPanel);
    }

    public void addToTestPlanListPanel(List<String> list) {
        testPlanList.setListData(list.toArray());

        testPlanListPanel.add(testPlanList);
        System.out.println("Dodano nowy element do planu " + list.get(list.size()-1));
        testPlanListPanel.repaint();
        testPlanListPanel.setLayout(null);

        testPlanListPanel.setVisible(true);
    }

    public ElementOptionsPanel getElementOptionsPanel() {
        return elementOptionsPanel;
    }
}
