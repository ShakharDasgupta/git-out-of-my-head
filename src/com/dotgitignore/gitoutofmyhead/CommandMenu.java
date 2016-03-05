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

import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Shakhar Dasgupta<sdasgupt@oswego.edu>
 */
public class CommandMenu extends JDialog implements Gestures {

    private final JList<String> list;
    private boolean visible;

    public CommandMenu() {
        setTitle("Git Command Menu");
        list = new JList<>(new String[]{"Add", "Commit", "Checkout"});
        list.setFixedCellHeight(50);
        list.setFixedCellWidth(250);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        add(list);
        pack();
        setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2);
        setVisible(true);
        visible = true;
    }

    public void headDown() {
        list.setSelectedIndex((list.getSelectedIndex() + 1) % list.getModel().getSize());
    }

    public void headUp() {
        list.setSelectedIndex((list.getModel().getSize() + list.getSelectedIndex() - 1) % list.getModel().getSize());
    }

    public void singleJawClench() {

    }

    public void doubleJawClench() {
        if (visible) {
            visible = false;
            setVisible(false);
        } else {
            visible = true;
            setVisible(true);
        }
    }
}
