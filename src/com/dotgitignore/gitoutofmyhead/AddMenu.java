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
import java.util.ArrayList;
import java.util.Arrays;
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
public class AddMenu extends JDialog implements Gestures {

    private Controller controller;
    private ArrayList<JCheckBox> boxList;
    private JList<String> list;

    public AddMenu(Controller controller) {
        this.controller = controller;
        setTitle("Git Add Menu");
        JPanel panel = new JPanel();
        JPanel checkPanel = new JPanel();
        JPanel listPanel = new JPanel();
        checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.Y_AXIS));
        boxList = new ArrayList<>();
        DefaultListModel<String> model = new DefaultListModel<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[]{"git", "status", "-s"}, new String[]{}, controller.getDirectory()).getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                JCheckBox box = new JCheckBox();
                boxList.add(box);
                checkPanel.add(box);
                model.addElement(line.substring(3));
            }
        } catch (IOException ex) {
            Logger.getLogger(AddMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        list = new JList<>(model);
        list.setFixedCellHeight(20);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        listPanel.add(list);
        panel.add(checkPanel);
        panel.add(listPanel);
        JScrollPane pane = new JScrollPane(panel);
        pane.setPreferredSize(new Dimension(250, 250));
        add(pane);
        pack();
        setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2);
        setVisible(true);
    }

    public void singleBlink() {
        list.setSelectedIndex((list.getSelectedIndex() + 1) % list.getModel().getSize());
    }

    public void doubleBlink() {
        JCheckBox box = boxList.get(list.getSelectedIndex());
        if (box.isSelected()) {
            box.setSelected(false);
        } else {
            box.setSelected(true);
        }
    }

    public void singleJawClench() {

        String[] cmd = new String[list.getModel().getSize() + 2];
        cmd[0] = "git";
        cmd[1] = "add";
        int c = 2;
        for (int i = 0; i < list.getModel().getSize(); i++) {
            if (boxList.get(i).isSelected()) {
                cmd[c++] = list.getModel().getElementAt(i);
            }
        }
        try {
            Runtime.getRuntime().exec(Arrays.copyOf(cmd, c), new String[]{}, controller.getDirectory());
        } catch (IOException ex) {
            Logger.getLogger(AddMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        setVisible(false);
        controller.setWindow(null);
    }

    public void doubleJawClench() {
        setVisible(false);
        controller.setWindow(null);
    }
}
