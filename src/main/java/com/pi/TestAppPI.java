package com.pi;

import com.pi.GUI.StartFrame;

import javax.swing.*;

public class TestAppPI {

    public static void main(String[] args) {
        StartFrame frame = new StartFrame();
        frame.setTitle("Projekt Inżynierski Edyta Pałka");
        frame.setVisible(true);
        frame.setBounds(500, 100, 370, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
