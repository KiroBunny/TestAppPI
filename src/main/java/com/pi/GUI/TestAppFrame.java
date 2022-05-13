package com.pi.GUI;

import com.pi.Model.PageElements;
import com.pi.Model.TestSettings;
import com.pi.listener.ClearListListener;
import com.pi.listener.StartPlanListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TestAppFrame extends JFrame {
    Container container = getContentPane();

    ElementOptionsPanel elementOptionsPanel = new ElementOptionsPanel(PageElements.getActions());

    GoToPanel goToPanel;
    AssertPanel assertPanel;
    JPanel testPlanListPanel;
    JList<Object> testPlanList;
    JButton chooseButton = new JButton("START");
    JButton clearButton = new JButton("wyczyść");

    public TestAppFrame(String pageAddress, String browserName) {
        TestSettings.pageAddress = pageAddress;
        TestSettings.browserName = browserName;
        goToPanel = new GoToPanel();
        assertPanel = new AssertPanel();
        testPlanListPanel = new JPanel();
        testPlanList = new JList<>();

        addListenerToButtons();
        setLayoutManager();
        setBounds();
        addComponentsToContainer();
    }

    private void addListenerToButtons() {
        this.chooseButton.addActionListener(new StartPlanListener());
        this.clearButton.addActionListener(new ClearListListener());
    }

    public void setLayoutManager() {
        container.setLayout(null);
        elementOptionsPanel.setLayout(null);
        goToPanel.setLayout(null);
        assertPanel.setLayout(null);
    }

    private void setBounds() {
        testPlanListPanel.setBounds(550, 40, 400, 450);
        testPlanList.setBounds(0, 0, 440, 450);
        testPlanListPanel.setBackground(new Color(255, 255, 255));
        chooseButton.setBounds(430, 500, 100, 40);
        clearButton.setBounds(870, 500, 80, 20);
    }

    private void addComponentsToContainer() {
        container.add(elementOptionsPanel);
        container.add(goToPanel);
        container.add(testPlanListPanel);
        container.add(assertPanel);
        container.add(chooseButton);
        container.add(clearButton);
    }

    public void addToTestPlanListPanel(List<String> list) {
        testPlanList.setListData(list.toArray());

        testPlanListPanel.add(testPlanList);
        System.out.println("Dodano nowy element do planu " + list.get(list.size() - 1));
        testPlanListPanel.repaint();
        testPlanListPanel.setLayout(null);
        testPlanListPanel.setVisible(true);
    }

    public ElementOptionsPanel getElementOptionsPanel() {
        return elementOptionsPanel;
    }
}
