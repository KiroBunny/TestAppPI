package com.pi.GUI;

import com.pi.Model.PageElements;
import com.pi.service.SelectedElementService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElementOptionsPanel extends JPanel implements ActionListener {
    JLabel choseLabel = new JLabel("Wybierz element do testowania");
    JList<Object> elementList;
    JLabel locatorLabel = new JLabel("Znajdź po:");
    JComboBox<String> locatorList = new JComboBox<>(PageElements.getLocators());
    JTextField locatorField = new JTextField();
    JLabel actionLabel = new JLabel("Akcja");
    JComboBox<String> actionList;
    JTextField actionParameterField = new JTextField();
    JButton chooseButton = new JButton("Dodaj");

    SelectedElementService service = new SelectedElementService();


    public ElementOptionsPanel(String[] actions) {
        super(null);

        this.actionList = new JComboBox<>(actions);
        elementList = new JList<>(PageElements.getElements().toArray());
        setBounds();
        chooseButton.addActionListener(this);

        addComponents();
    }

    private void addComponents() {
        this.add(choseLabel);
        this.add(elementList);
        this.add(locatorList);
        this.add(locatorField);
        this.add(locatorLabel);
        this.add(actionLabel);
        this.add(actionList);
        this.add(actionParameterField);
        this.add(chooseButton);
    }

    private void setBounds() {
        this.setBounds(50, 0, 200, 450);
        choseLabel.setBounds(0, 0, 200, 30);
        elementList.setBounds(0, 40, 200, 150);
        locatorLabel.setBounds(0, 200, 200, 20);
        locatorList.setBounds(0, 225, 200, 20);
        locatorField.setBounds(0, 250, 200, 20);

        actionLabel.setBounds(0, 275, 200, 20);
        actionList.setBounds(0, 300, 200, 20);
        actionParameterField.setBounds(0, 325, 200, 20);
        chooseButton.setBounds(120, 350, 80, 20);
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

    public int getSelectedElement(){
        return elementList.getSelectedIndex();
    }

    public int getSelectedLocator(){
        return locatorList.getSelectedIndex();
    }

    public String getLocatorText(){
        return locatorField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int element = elementList.getSelectedIndex();
        int locator = locatorList.getSelectedIndex();
        String value = locatorField.getText();
        int action = actionList.getSelectedIndex();
        String parameters = actionParameterField.getText();
        System.out.println("cos sie dzieje " + element + " - " + locator + " - " + value + " - " + action);
        service.addElementToPlanList(action, parameters);
    }
}
