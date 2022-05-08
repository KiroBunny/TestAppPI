package com.pi.GUI;

import com.pi.Model.PageElements;

import javax.swing.*;
import java.awt.*;

public class ElementOptionsPanel extends JPanel {
    JLabel locatorLabel = new JLabel("Znajdz po:");
    JComboBox<String> locatorList = new JComboBox<>(PageElements.getLocators());
    JTextField locatorField = new JTextField();
    JLabel actionLabel = new JLabel("Akcja");
    JComboBox<String> actionList = new JComboBox<>(PageElements.getActions());

    public ElementOptionsPanel() {
        super(null);
        //this.locatorList.setSelectedIndex(0);
        //this.locatorList.addActionListener(new LocatorListener());

        setBounds();
    }

    private void setBounds() {
        locatorLabel.setBounds(0, 10, 200, 20);
        locatorList.setBounds(0, 35, 200, 20);
        locatorField.setBounds(0, 60, 200, 20);

        actionLabel.setBounds(0, 90, 200, 20);
        actionList.setBounds(0, 115, 200, 20);
        /*locatorList.setVisible(true);
        locatorField.setVisible(true);
        locatorLabel.setVisible(true);*/
    }

    public Component[] getAllComponent() {
        return new Component[]{
                locatorList,
                locatorField,
                locatorLabel,
                actionLabel,
                actionList
        };
    }
}
