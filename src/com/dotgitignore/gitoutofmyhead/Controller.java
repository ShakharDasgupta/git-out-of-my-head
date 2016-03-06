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
    public Controller(int blinkInterval, int jawClenchInterval, int port) {
        this.dataAnalyzer = new DataAnalyzer(this, blinkInterval, jawClenchInterval, port);
    }
    
    public void setWindow(Gestures window) {
        this.window = window;
    }

    /**
     * Runs when a single blink is registered and delivers the signal to the
     * window.
     */
    @Override
    public void singleBlink() {
        System.out.println("SINGLE BLINK");
        if (this.window != null) {
            this.window.singleBlink();
        }
    }

    /**
     * Runs when a double blink is registered and delivers the signal to the
     * window.
     */
    @Override
    public void doubleBlink() {
        System.out.println("DOUBLE BLINK");
        if (this.window != null) {
            this.window.doubleBlink();
        }
    }

    /**
     * Runs when a single jaw clench is registered and delivers the signal to
     * the window.
     */
    @Override
    public void singleJawClench() {
        System.out.println("SINGLE CLENCH");
        if (this.window != null) {
            this.window.singleJawClench();
        }
    }

    /**
     * Runs when a double jaw clench is registered and delivers the signal to
     * the window.
     */
    @Override
    public void doubleJawClench() {
        System.out.println("DOUBLE CLENCH");
        if (this.window == null) {
            this.window = new CommandMenu(this);
        } else {
            this.window.doubleJawClench();
        }
    }

}
