package com.pi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestAppFrame extends JFrame implements ActionListener {
    final String pageAddress;
    final String browserName;
    Container container = getContentPane();
    JLabel choseLabel = new JLabel("Wybierz element do testowania");
    JList elementList;
    JButton chooseButton = new JButton("Wybierz");
    private ListSelectionModel listSelectionModel;

    public TestAppFrame(String pageAddress, String browserName) {
        this.pageAddress = pageAddress;
        this.browserName = browserName;

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    private int initListWithElementsAgdGetSize()
    {
        List elList = PageElements.getElements();
        elementList = new JList(elList.getItems());
        listSelectionModel = elementList.getSelectionModel();
        listSelectionModel.addListSelectionListener(
                new SharedListSelectionHandler());
        return elList.getItemCount()*20;
    }

    private void setLocationAndSize() {

        int elementsHeight = initListWithElementsAgdGetSize();

        choseLabel.setBounds(50,10,200,30);
        elementList.setBounds(50, 50, 200, elementsHeight);
        chooseButton.setBounds(60, elementsHeight + 70, 100, 30);
    }

    private void addComponentsToContainer() {
        container.add(choseLabel);
        container.add(elementList);
        container.add(chooseButton);
    }

    private void addActionEvent() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
