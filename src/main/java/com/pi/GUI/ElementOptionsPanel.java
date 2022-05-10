package com.pi.GUI;

import com.pi.Model.PageElements;
import com.pi.SharedListSelectionListener;
import com.pi.service.SelectedElementService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElementOptionsPanel extends JPanel  implements ActionListener {
    JLabel choseLabel = new JLabel("Wybierz element do testowania");
    JList<Object> elementList;
    JLabel locatorLabel = new JLabel("Znajdz po:");
    JComboBox<String> locatorList = new JComboBox<>(PageElements.getLocators());
    JTextField locatorField = new JTextField();
    JLabel actionLabel = new JLabel("Akcja");
    JComboBox<String> actionList;
    JButton chooseButton = new JButton("Dodaj");

    SelectedElementService service = new SelectedElementService();


    public ElementOptionsPanel(String[] actions) {
        super(null);
        //this.locatorList.setSelectedIndex(0);
        this.actionList = new JComboBox<>(actions);
        setBounds();
        chooseButton.addActionListener(this::actionPerformed);
        this.add(choseLabel);
        this.add(elementList);
        this.add(locatorList);
        this.add(locatorField);
        this.add(locatorLabel);
        this.add(actionLabel);
        this.add(actionList);
        this.add(chooseButton);

    }

    private void setBounds() {
        int elementsHeight = initListWithElementsAndGetSize();
        choseLabel.setBounds(0, 10, 200, 30);
        elementList.setBounds(0, 50, 200, 150);
        locatorLabel.setBounds(0, 210, 200, 20);
        locatorList.setBounds(0, 235, 200, 20);
        locatorField.setBounds(0, 260, 200, 20);

        actionLabel.setBounds(0, 285, 200, 20);
        actionList.setBounds(0, 310, 200, 20);

        chooseButton.setBounds(120, 360, 80, 20);
    }

    private int initListWithElementsAndGetSize() {
        elementList = new JList<>(PageElements.getElements().toArray());
        return PageElements.getElements().size() * 20;
    }

    public JList<Object> getElementList() {
        return elementList;
    }

    public JComboBox<String> getLocatorList() {
        return locatorList;
    }

    public JTextField getLocatorField() {
        return locatorField;
    }

    public JComboBox<String> getActionList() {
        return actionList;
    }

    public JButton getChooseButton() {
        return chooseButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int element = elementList.getSelectedIndex();
        int locator = locatorList.getSelectedIndex();
        String value = locatorField.getText();
        int action = actionList.getSelectedIndex();
        System.out.println("cos sie dzieje " + element + " - " + locator + " - " + value + " - " + action);
        service.addElementToPlanList(element, locator, value, action);
    }
}
