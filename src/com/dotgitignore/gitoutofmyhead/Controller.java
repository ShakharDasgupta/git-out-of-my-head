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
    private Gestures window;

    /**
     * Initializes a <code>Controller</code> with the double action wait time
     * and port number.
     *
     * @param l Wait time for double actions.
     * @param p The port number of the Controller.
     */
    public Controller(double l, int p) {
        this.dataAnalyzer = new DataAnalyzer(this, l, p);
    }

    /**
     * Runs when a single blink is registered and delivers the signal to the
     * window.
     */
    @Override
    public void singleBlink() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Runs when a double blink is registered and delivers the signal to the
     * window.
     */
    @Override
    public void doubleBlink() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Runs when a single jaw clench is registered and delivers the signal to the
     * window.
     */
    @Override
    public void singleJawClench() {
        this.window.singleJawClench();
    }

    /**
     * Runs when a double jaw clench is registered and delivers the signal to the
     * window.
     */
    @Override
    public void doubleJawClench() {
        if (this.window == null) {
            this.window = new CommandMenu();
        } else {
            this.window.doubleJawClench();
        }
    }

}
