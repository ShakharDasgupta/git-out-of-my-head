/*
 * Copyright (C) 2016 Shakhar Dasgupta<sdasgupt@oswego.edu>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.dotgitignore.gitoutofmyhead;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Shakhar Dasgupta<sdasgupt@oswego.edu>
 */
public class BranchMenu extends JDialog implements Gestures {
    
    private Controller controller;
    private JList<String> list;
    private String remoteListSelected;

    public BranchMenu(Controller controller, String remoteListSelected) {
        this.controller = controller;
        this.remoteListSelected = remoteListSelected;
        setTitle("Git Branch Menu");
        JPanel panel = new JPanel();
        DefaultListModel<String> model = new DefaultListModel<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[]{"git", "branch"}, new String[]{}, controller.getDirectory()).getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                model.addElement(line.substring(2));
            }
        } catch (IOException ex) {
            Logger.getLogger(AddMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        list = new JList<>(model);
        list.setFixedCellHeight(50);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        JScrollPane pane = new JScrollPane(list);
        pane.setPreferredSize(new Dimension(250, 250));
        panel.add(pane);
        add(panel);
        pack();
        setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2);
        setVisible(true);
    }
    
    public void singleBlink() {
        list.setSelectedIndex((list.getSelectedIndex() + 1) % list.getModel().getSize());
    }

    public void doubleBlink() {
        
    }
    public void singleJawClench() {
        try {
            Runtime.getRuntime().exec(new String[]{"git", "push", remoteListSelected, list.getSelectedValue()}, new String[]{}, controller.getDirectory());
        } catch (IOException ex) {
            Logger.getLogger(BranchMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        setVisible(false);
        controller.setWindow(null);
    }

    public void doubleJawClench() {
        setVisible(false);
        controller.setWindow(null);
    }
}
