package com.pi.listener;

import com.pi.service.SelectedElementService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SharedListSelectionListener implements ListSelectionListener {
    static final SelectedElementService service = new SelectedElementService();

    @Override
    public void valueChanged(ListSelectionEvent event) {
        ListSelectionModel eventSource = (ListSelectionModel) event.getSource();

        if (eventSource.isSelectionEmpty()) {
            System.out.println(" <none>");
        } else {
            // Find out which indexes are selected.
            int minIndex = eventSource.getMinSelectionIndex();

            if (eventSource.isSelectedIndex(minIndex) && eventSource.getValueIsAdjusting()) {
                System.out.println(" " + minIndex + "   " + eventSource.getValueIsAdjusting());
            }
        }
    }
}