package com.pi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel pageAddressLabel = new JLabel("Page Address");
    JLabel browserNameLabel = new JLabel("browserName");
    JTextField pageTextField = new JTextField();
    JTextField browserNameField = new JTextField();
    JButton saveButton = new JButton("SAVE");
    JButton resetButton = new JButton("RESET");

    public StartFrame() throws HeadlessException {
        pageTextField.setText("192.168.0.88");
        browserNameField.setText("Chrome");
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        //Setting location and Size of each components using setBounds() method.
        pageAddressLabel.setBounds(50, 150, 100, 30);
        browserNameLabel.setBounds(50, 220, 100, 30);
        pageTextField.setBounds(150, 150, 150, 30);
        browserNameField.setBounds(150, 220, 150, 30);
        saveButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
    }

    public void addComponentsToContainer() {
        //Adding each components to the Container
        container.add(pageAddressLabel);
        container.add(browserNameLabel);
        container.add(pageTextField);
        container.add(browserNameField);
        container.add(saveButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        saveButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            String pageAddressText = pageTextField.getText();
            String browserNameText = browserNameField.getText();
            loadTestAppFrame(pageAddressText, browserNameText);
        } else if (e.getSource() == resetButton) {
            pageTextField.setText("192.168.0.88");
            browserNameField.setText("Chrome");
        }
    }

    private void loadTestAppFrame(String pageAddressText, String browserNameText) {
        TestAppFrame frame = new TestAppFrame(pageAddressText, browserNameText);
        frame.setTitle("Stwórz swój własny plan testowy");
        frame.setVisible(true);
        frame.setBounds(500,100,800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
