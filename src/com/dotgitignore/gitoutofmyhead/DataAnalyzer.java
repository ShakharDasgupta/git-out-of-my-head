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
 * Objects of the <code>DataAnalyzer</code> class analyze the input given by the
 * MuseServer and send the output as commands to the Controller.
 *
 * @author Christopher Wells <cwellsny@nycap.rr.com>
 */
public class DataAnalyzer {

    private final Controller controller;
    private final MuseServer museServer;
    private final double waitTime;
    private int blinkCount;
    private int jawClenchCount;

    private boolean monitoringBlink;
    private boolean monitoringJawClench;

    /**
     * Initializes a <code>DataAnalyzer</code> with the given buffer size.
     *
     * @param c The Controller of the DataAnalyzer.
     * @param l The double action wait time of the DataAnalyzer.
     * @param p The port of the MuseServer.
     */
    public DataAnalyzer(Controller c, double l, int p) {
        this.controller = c;
        this.waitTime = l;
        this.blinkCount = 0;
        this.jawClenchCount = 0;
        this.monitoringBlink = false;
        this.monitoringJawClench = false;
        this.museServer = new MuseServer(this, p);
    }

    public void addBlink() throws InterruptedException {
        this.blinkCount++;

        if (!this.monitoringBlink) {
            this.monitorBlink();
        }
    }

    public void monitorBlink() throws InterruptedException {
        Thread.sleep((int) this.waitTime);
        if (this.blinkCount > 1) {
            this.controller.doubleBlink();
        } else {
            this.controller.singleBlink();
        }
    }

    public void addJawClench() throws InterruptedException {
        this.jawClenchCount++;

        if (!this.monitoringJawClench) {
            this.monitorJawClench();
        }
    }

    public void monitorJawClench() throws InterruptedException {
        Thread.sleep((int) this.waitTime);
        if (this.jawClenchCount > 1) {
            this.controller.doubleJawClench();
        } else {
            this.controller.singleJawClench();
        }
    }
}
