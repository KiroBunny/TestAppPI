package com.pi.GUI;

import com.pi.Model.ElementModel;
import com.pi.Model.PageElements;
import com.pi.Model.TestSettings;
import com.pi.listener.ClearListListener;
import com.pi.listener.IOButtonListener;
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

    JButton saveToFileButton = new JButton("Zapisz");
    JButton readFromFileButton = new JButton("Wczytaj");
    JButton clearElementButton = new JButton("usuń");
    JButton clearPlanButton = new JButton("wyczyść");
    JButton startButton = new JButton("START");
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
        this.saveToFileButton.addActionListener(new IOButtonListener());
        this.readFromFileButton.addActionListener(new IOButtonListener());
        this.clearElementButton.addActionListener(new ClearListListener("element"));
        this.clearPlanButton.addActionListener(new ClearListListener("all"));
        this.startButton.addActionListener(new StartPlanListener());
    }

    public void setLayoutManager() {
        container.setLayout(null);
        elementOptionsPanel.setLayout(null);
        goToPanel.setLayout(null);
        assertPanel.setLayout(null);
    }

    private void setBounds() {
        saveToFileButton.setBounds(850, 10, 100, 20);
        readFromFileButton.setBounds(730, 10, 100, 20);
        testPlanListPanel.setBounds(550, 40, 400, 450);
        testPlanList.setBounds(0, 0, 440, 450);
        testPlanListPanel.setBackground(new Color(255, 255, 255));
        startButton.setBounds(430, 500, 100, 40);
        clearElementButton.setBounds(730, 500, 100, 20);
        clearPlanButton.setBounds(850, 500, 100, 20);
    }

    private void addComponentsToContainer() {
        container.add(saveToFileButton);
        container.add(readFromFileButton);
        container.add(elementOptionsPanel);
        container.add(goToPanel);
        container.add(testPlanListPanel);
        container.add(assertPanel);
        container.add(startButton);
        container.add(clearElementButton);
        container.add(clearPlanButton);
    }

    public void addToTestPlanListPanel(List<String> list) {
        testPlanList.setListData(list.toArray());

        testPlanListPanel.add(testPlanList);
        if (list.size()>0) System.out.println("Dodano nowy element do planu " + list.get(list.size() - 1));

        testPlanListPanel.repaint();
        testPlanListPanel.setLayout(null);
        testPlanListPanel.setVisible(true);
    }

    public ElementOptionsPanel getElementOptionsPanel() {
        return elementOptionsPanel;
    }

    public JList<Object> getTestPlanList() {
        return testPlanList;
    }
}
