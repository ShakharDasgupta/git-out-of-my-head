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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Shakhar Dasgupta<sdasgupt@oswego.edu>
 */
public class AddMenu extends JDialog {

    private boolean visible;
    ArrayList<JCheckBox> boxList = new ArrayList<>();

    public AddMenu() {
        setTitle("Git Add Menu");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JCheckBox all = new JCheckBox("All");
        all.setSelected(true);
        boxList.add(all);
        panel.add(all);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("ls").getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                JCheckBox box = new JCheckBox(line);
                boxList.add(box);
                panel.add(box);
            }
        } catch (IOException ex) {
            Logger.getLogger(AddMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
            JScrollPane pane = new JScrollPane(panel);
            pane.setPreferredSize(new Dimension(250, 250));
            add(pane);
            pack();
            setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2);
            setVisible(true);
    }
}
