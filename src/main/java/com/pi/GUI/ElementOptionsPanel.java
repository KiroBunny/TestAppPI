package com.pi.GUI;

import com.pi.LocatorListener;
import com.pi.Model.PageElements;

import javax.swing.*;
import java.awt.*;

public class ElementOptionsPanel extends JPanel {
    JLabel locatorLabel = new JLabel("Znajdz po:");
    JComboBox<String> locatorList = new JComboBox<>(PageElements.getLocators());
    JTextField locatorField = new JTextField();
    JLabel actionLabel = new JLabel("Akcja");
    JComboBox<String> actionList;
    JButton chooseButton = new JButton("Dodaj");


    public ElementOptionsPanel(String[] actions) {
        super(null);
        //this.locatorList.setSelectedIndex(0);

        this.actionList = new JComboBox<>(actions);
        this.chooseButton.addActionListener(new LocatorListener());
        setBounds();
    }

    private void setBounds() {
        locatorLabel.setBounds(0, 10, 200, 20);
        locatorList.setBounds(0, 35, 200, 20);
        locatorField.setBounds(0, 60, 200, 20);

        actionLabel.setBounds(0, 90, 200, 20);
        actionList.setBounds(0, 115, 200, 20);

        chooseButton.setBounds(120, 200, 80, 20);
    }

    public Component[] getAllComponent() {
        return new Component[]{
                locatorList,
                locatorField,
                locatorLabel,
                actionLabel,
                actionList,
                chooseButton
        };
    }
}
