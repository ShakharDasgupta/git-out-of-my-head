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

import javax.swing.JDialog;

/**
 *
 * @author Christopher Wells <cwellsny@nycap.rr.com>
 */
public class Controller implements Gestures {

    private final DataAnalyzer dataAnalyzer;
    private JDialog window;

    /**
     * Initializes a <code>Controller</code> with the given buffer size and port
     * number.
     *
     * @param b The buffer size of the Controller.
     * @param p The port number of the Controller.
     */
    public Controller(int b, int p) {
        this.dataAnalyzer = new DataAnalyzer(this, b, p);
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
        if (this.window instanceof CommandMenu) {
            ((CommandMenu) this.window).singleJawClench();
        } else if (this.window instanceof AddMenu) {
            ((AddMenu) this.window).singleJawClench();
        }
    }

    @Override
    public void doubleJawClench() {
        if (this.window == null) {
            this.window = new CommandMenu();
        } else {
            ((Gestures) this.window).doubleJawClench();
        }
    }

}
