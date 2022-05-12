package com.pi.listener;

import com.pi.service.SelectedElementService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPlanListener implements ActionListener {
    static final SelectedElementService service = new SelectedElementService();

    @Override
    public void actionPerformed(ActionEvent e) {
        service.startPlan();
    }
}
