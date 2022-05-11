package com.pi.GUI;

import com.pi.service.SelectedElementService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToPanel extends JPanel implements ActionListener {
    JLabel goToLabel = new JLabel("Przejdż do:");
    JTextField goToField = new JTextField();
    JButton chooseButton = new JButton("Dodaj");

    SelectedElementService service = new SelectedElementService();

    public GoToPanel() {
        super(null);
        chooseButton.addActionListener(this);
        setBounds();
        this.add(goToLabel);
        this.add(goToField);
        this.add(chooseButton);
    }

    private void setBounds() {
        this.setBounds(300, 50, 200, 100);
        this.goToLabel.setBounds(0,0,200,20);
        this.goToField.setBounds(0, 25, 200, 20);
        this.chooseButton.setBounds(120, 60, 80, 20);
    }

    public void setPageAddress(String pageAddress) {
        goToField.setText(pageAddress + "/");
        goToField.setSelectedTextColor(new Color(100,100,100));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        service.addElementToPlanList(goToField.getText());
    }
}