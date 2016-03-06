/*
 * Copyright (C) 2016 Christopher Wells <cwellsny@nycap.rr.com>
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
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Christopher Wells <cwellsny@nycap.rr.com>
 */
public class CommitMenu extends JDialog implements Gestures {
    
    private boolean visible;
    private JTextArea textField;
    
    /**
     * Initializes a <code>CommitMenu</code> object.
     */
    public CommitMenu() {
        setTitle("Git Commit Menu");
        JPanel panel = new JPanel();
        textField = new JTextArea();
        JScrollPane pane = new JScrollPane(textField);
        pane.setPreferredSize(new Dimension(250, 250));
        panel.add(pane);
        add(panel);
        pack();
        setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2);
        setVisible(true);
        visible = true;        
    }
    
    /**
     * Returns the text in the TextArea of the CommitMenu.
     * 
     * @return The text in the TextArea.
     */
    public String getText() {
        return this.textField.getText();
    }

    @Override
    public void headDown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void headUp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void singleJawClench() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doubleJawClench() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
