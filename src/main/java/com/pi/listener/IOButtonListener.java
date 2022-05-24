package com.pi.listener;

import com.pi.service.SelectedElementService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IOButtonListener implements ActionListener {
    static final SelectedElementService service = SelectedElementService.getInstance();

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource());
        JButton button = (JButton) e.getSource();
        if (button.getText().equals("Zapisz")) {
            service.saveToFile();
        } else if (button.getText().equals("Wczytaj")) {
            service.readFromFile();
        }
    }
}
