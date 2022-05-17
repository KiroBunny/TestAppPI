package com.pi.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IOButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource());
        JButton button = (JButton) e.getSource();
        if (button.getText().equals("Zapisz")){

        }else if (button.getText().equals("Wczytaj")){

        }
    }
}
