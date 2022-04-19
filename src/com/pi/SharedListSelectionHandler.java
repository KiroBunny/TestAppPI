package com.pi;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SharedListSelectionHandler implements ListSelectionListener {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();

        int firstIndex = e.getFirstIndex();
        int lastIndex = e.getLastIndex();
        boolean isAdjusting = e.getValueIsAdjusting();
        System.out.println("Event for indexes "
                + firstIndex + " - " + lastIndex
                + "; isAdjusting is " + isAdjusting
                + "; selected indexes:");

        if (lsm.isSelectionEmpty()) {
            System.out.println(" <none>");
        } else {
            // Find out which indexes are selected.
            int minIndex = lsm.getMinSelectionIndex();

            if (lsm.isSelectedIndex(minIndex)) {
                System.out.println(" " + minIndex);

            }
        }
    }
}