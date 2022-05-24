package com.pi.listener;

import com.pi.service.SelectedElementService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearListListener implements ActionListener {
    static final SelectedElementService service = SelectedElementService.getInstance();
    private final String element;


    public ClearListListener(String element) {
        this.element = element;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (element.equals("all")) {
            service.clearList();
        } else {
            service.clearOneElement();
        }
    }
}
