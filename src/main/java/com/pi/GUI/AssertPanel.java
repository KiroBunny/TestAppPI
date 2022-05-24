package com.pi.GUI;

import com.pi.Model.PageElements;
import com.pi.service.SelectedElementService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssertPanel  extends JPanel implements ActionListener {
    JLabel assertionLabel = new JLabel("Porównaj");
    JComboBox<String> assertionList;
    JTextField parameterField = new JTextField();
    JComboBox<String> equalsList;
    JTextField equalsField = new JTextField();
    JButton chooseButton = new JButton("Dodaj");

    SelectedElementService service = SelectedElementService.getInstance();

    public AssertPanel() {
        this.assertionList = new JComboBox<>(PageElements.getAssertionActions());
        this.equalsList = new JComboBox<>(new String[]{"==", "!="});

        setBounds();
        chooseButton.addActionListener(this);
        addComponents();
    }

    private void setBounds() {
        this.setBounds(300, 250, 200, 200);
        assertionLabel.setBounds(0,0, 200, 20);
        assertionList.setBounds(0,25, 200, 20);
        parameterField.setBounds(0,50, 200, 20);
        equalsList.setBounds(0,75, 200, 20);
        equalsField.setBounds(0,100, 200, 20);
        chooseButton.setBounds(120,125, 80, 20);
    }

    private void addComponents() {
        this.add(assertionLabel);
        this.add(assertionList);
        this.add(parameterField);
        this.add(equalsList);
        this.add(equalsField);
        this.add(chooseButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int assertIndex = assertionList.getSelectedIndex();
        String parameter = parameterField.getText();
        int equalsIndex = equalsList.getSelectedIndex();
        String equals = equalsField.getText();

        service.addElementToPlanList(assertIndex, parameter, equalsIndex, equals);
    }
}
