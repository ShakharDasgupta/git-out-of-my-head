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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Shakhar Dasgupta<sdasgupt@oswego.edu>
 */
public class CommitMenu extends JDialog implements Gestures {

    private JTextArea textArea;
    private Controller controller;

    public CommitMenu(Controller controller) {
        this.controller = controller;
        setTitle("Git Commit Menu");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Commit Message: "));
        textArea = new JTextArea();
        JScrollPane pane = new JScrollPane(textArea);
        pane.setPreferredSize(new Dimension(250, 250));
        panel.add(pane);
        add(panel);
        pack();
        setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2);
        setVisible(true);
    }

    public void singleBlink() {

    }

    public void doubleBlink() {

    }

    public void singleJawClench() {
        try {
            Runtime.getRuntime().exec(new String[]{"git", "-c", "user.name=\"Shakhar Dasgupta\"", "-c", "user.email=\"shakhardasgupta@gmail.com\"", "commit", "-m", "\"" + textArea.getText() + "\""}, new String[]{}, controller.getDirectory());
        } catch (IOException ex) {
            Logger.getLogger(CommitMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        setVisible(false);
        controller.setWindow(null);
    }

    public void doubleJawClench() {
        setVisible(false);
        controller.setWindow(null);
    }
}
