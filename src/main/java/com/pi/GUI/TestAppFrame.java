package com.pi.GUI;

import com.pi.Model.PageElements;
import com.pi.SharedListSelectionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TestAppFrame extends JFrame implements ActionListener {
    final String pageAddress;
    final String browserName;
    Container container = getContentPane();
    JLabel choseLabel = new JLabel("Wybierz element do testowania");
    JList<Object> elementList;
    JPanel elementOptionsPanel = new JPanel();

    public TestAppFrame(String pageAddress, String browserName) {
        this.pageAddress = pageAddress;
        this.browserName = browserName;

        setLayoutManager();
        setLocationAndSize();
        setVisibleToConfigElementPanel(true);
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
        elementOptionsPanel.setLayout(null);
    }

    private void setLocationAndSize() {
        int elementsHeight = initListWithElementsAndGetSize();

        choseLabel.setBounds(50,10,200,30);
        elementList.setBounds(50, 50, 200, elementsHeight);
        //chooseButton.setBounds(60, elementsHeight + 70, 100, 30);
        elementOptionsPanel.setBounds(50, elementsHeight + 70, 200, 325);
        //elementOptionsPanel.setBackground(new Color(921213122));
    }

    private int initListWithElementsAndGetSize()
    {
        elementList = new JList<>(PageElements.getElements().toArray());
        ListSelectionModel listSelectionModel = elementList.getSelectionModel();
        listSelectionModel.addListSelectionListener(
                new SharedListSelectionListener());
        return PageElements.getElements().size()*20;
    }

    private void addComponentsToContainer() {
        container.add(choseLabel);
        container.add(elementList);
        container.add(elementOptionsPanel);
    }

    public void addToElementOptionPanel(Component component){
        elementOptionsPanel.add(component);
        elementOptionsPanel.repaint();
        elementOptionsPanel.setVisible(true);
    }

    public void setVisibleToConfigElementPanel(boolean flag) {
        elementOptionsPanel.setVisible(flag);
    }

    private void addActionEvent() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
