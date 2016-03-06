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

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Shakhar Dasgupta<sdasgupt@oswego.edu>
 */
public class Main {
    public static void main(String[] args) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fc.showOpenDialog(null);
        File dir = null;
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            dir = fc.getSelectedFile();
            System.out.println(dir);
        }
        else {
            return;
        }
        Controller controller = new Controller(500, 1500, 5000, dir);
    }
}
