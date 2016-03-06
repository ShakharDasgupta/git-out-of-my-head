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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shakhar Dasgupta<sdasgupt@oswego.edu>
 */
public class BlinkMonitor implements Runnable {
    
    private Thread t;
    private DataAnalyzer dataAnalyzer;
    private int waitTime;
    
    public BlinkMonitor(DataAnalyzer dataAnalyzer, int waitTime) {
        t = new Thread(this);
        this.dataAnalyzer = dataAnalyzer;
        this.waitTime = waitTime;
    }
    
    public void run() {
        dataAnalyzer.monitoringBlink = true;
        try {
            t.sleep(this.waitTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(BlinkMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (dataAnalyzer.blinkCount > 1) {
            dataAnalyzer.controller.doubleBlink();
        } else {
            dataAnalyzer.controller.singleBlink();
        }
        dataAnalyzer.blinkCount = 0;
        dataAnalyzer.monitoringBlink = false;
    }
    
    public void start() {
        t.start();
    }
}